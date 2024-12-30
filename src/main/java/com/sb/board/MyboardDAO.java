package com.sb.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.sb.common.DBManager;
import com.sb.common.OracleDBManager;

public class MyboardDAO {
	
		//-------------------------------------------------------------------------------- 게시물검색
		public ArrayList<MyboardVO> myboardSelect(String searchGubun , String searchStr) {

			ArrayList<MyboardVO> alist = new ArrayList<MyboardVO>();
			
			DBManager dbm = OracleDBManager.getInstance();  	//new OracleDBManager();
			Connection conn = dbm.connect();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			try {
				//select * from myboard where title like '%제목%'
				//select * from myboard where regid like '%홍길동%'
				String sql = "select * from myboard where "+searchGubun+" like ? order by regdate desc";
				pstmt =  conn.prepareStatement(sql);
				pstmt.setString(1, '%'+searchStr+'%');
				rs = pstmt.executeQuery();  
				while(rs.next()) {
					MyboardVO bvo = new MyboardVO();
					bvo.setBseq(  rs.getInt("bseq")     );
					bvo.setTitle(  rs.getString("title")  );
					bvo.setContents(  rs.getString("contents")  );
					bvo.setRegid(  rs.getString("regid")  );
					bvo.setRegdate(  rs.getString("regdate")  );
					alist.add(bvo);
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}	finally {
					dbm.close(conn, pstmt, rs);
			}
			return alist;
		}
		
	
	//-------------------------------------------------------------------------------- 전체게시물(s~e페이징)
	public ArrayList<MyboardVO> myboardSelect(int startSeq , int endSeq) {
		
		ArrayList<MyboardVO> alist = new ArrayList<MyboardVO>();
		
		DBManager dbm = OracleDBManager.getInstance();  	//new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
//			select  ss.*
//			from (
//			    select  rownum as rnum, s.*
//			    from (select * from myboard order by regdate desc ) s
//			) ss
//			where rnum between 3 and 6;
			String sql = "select s.*  \r\n"
						+ "from   \r\n"
						+ "  (select myboard.*, (ROW_NUMBER() OVER(order by regdate desc)) as rnum \r\n"
						+ "  from myboard) s  \r\n"
						+ "where  rnum between ? and ?";
			pstmt =  conn.prepareStatement(sql);
			pstmt.setInt(1, startSeq);
			pstmt.setInt(2, endSeq);
			
			rs = pstmt.executeQuery();  
			while(rs.next()) {
				MyboardVO bvo = new MyboardVO();
				bvo.setBseq(  rs.getInt("bseq")     );
				bvo.setTitle(  rs.getString("title")  );
				bvo.setContents(  rs.getString("contents")  );
				bvo.setRegid(  rs.getString("regid")  );
				bvo.setRegdate(  rs.getString("regdate")  );
				alist.add(bvo);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return alist;
	}
	
	//-------------------------------------------------------------------------------- 전체게시물
	public ArrayList<MyboardVO> myboardSelect() {
	
		ArrayList<MyboardVO> alist = new ArrayList<MyboardVO>();
		
		DBManager dbm = OracleDBManager.getInstance();  	//new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "select * from myboard order by regdate desc";
			pstmt =  conn.prepareStatement(sql);
			rs = pstmt.executeQuery();  
			while(rs.next()) {
				MyboardVO bvo = new MyboardVO();
				bvo.setBseq(  rs.getInt("bseq")     );
				bvo.setTitle(  rs.getString("title")  );
				bvo.setContents(  rs.getString("contents")  );
				bvo.setRegid(  rs.getString("regid")  );
				bvo.setRegdate(  rs.getString("regdate")  );
				alist.add(bvo);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return alist;
	}
	//--------------------------------------------------------------------------------
	
	
	public MyboardVO myboardSelect(int bseq) {
		MyboardVO bvo = new MyboardVO();
		
		//DBManager dbm = OracleDBManager.getInstance();    //new OracleDBManager();
		DBManager dbm = OracleDBManager.getInstance();  		//new MariaDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from myboard where bseq=?";
			pstmt =  conn.prepareStatement(sql);
			pstmt.setInt(1, bseq);	//------파라미터를 1번째?에 바인딩
			
			rs = pstmt.executeQuery();  
			rs.next();
			
			bvo.setBseq(  rs.getInt("bseq")     );
			bvo.setTitle(  rs.getString("title")  );
			bvo.setContents(  rs.getString("contents")  );
			bvo.setRegid(  rs.getString("regid")  );
			bvo.setRegdate(  rs.getString("regdate")  );
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			dbm.close(conn, pstmt, rs);
		}
		return bvo;
	}
	//--------------------------------------------------------------------------------
	
	
		public MyboardVO2 myboardReplySelect(int bseq) {
			
			//DBManager dbm = OracleDBManager.getInstance();    //new OracleDBManager();
			DBManager dbm = OracleDBManager.getInstance();  		//new MariaDBManager();
			Connection conn = dbm.connect();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MyboardVO2 bvo = null;  
			try {
				String sql = "select b.*, r.*\r\n"
							+ "from (select * from myboard where bseq=?) b, myreply r\r\n"
							+ "where b.bseq = r.bseq";
				pstmt =  conn.prepareStatement(sql);
				pstmt.setInt(1, bseq);	//------파라미터를 1번째?에 바인딩
				rs = pstmt.executeQuery();  
				
				
				ArrayList<MyreplyVO> rlist = new  ArrayList<MyreplyVO>(); 
				while(rs.next()) {
					//-------------------------------------------------
					// ORM 1:N 효과 
					//-------------------------------------------------
					MyreplyVO rvo =  new MyreplyVO(); 
					//상세 (1)
					if(bvo == null) { 
						bvo = new MyboardVO2();
						bvo.setBseq(  rs.getInt("bseq")     );
						bvo.setTitle(  rs.getString("title")  );
						bvo.setContents(  rs.getString("contents")  );
						bvo.setRegid(  rs.getString("regid")  );
						bvo.setRegdate(  rs.getString("regdate")  );
					}
					//댓글 (N)
					rvo.setRseq(rs.getInt("rseq"));
					rvo.setReply(rs.getString("reply"));
					rvo.setRegid(rs.getString("regid"));
					rvo.setRegdate(rs.getString("regdate"));
					rvo.setBseq(rs.getInt("bseq"));
					rlist.add(rvo);
				}
				bvo.setRlist(rlist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	finally {
				dbm.close(conn, pstmt, rs);
			}
			return bvo;
		}
	
	//public int myboardInsert(String title, String contents, String regid) {
	public int myboardInsert(MyboardVO bvo) {
		DBManager dbm  = OracleDBManager.getInstance();  //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		int rows = 0;
		try {   
			conn.setAutoCommit(false);
			
			String sql = "insert into myboard(bseq, title, contents, regid)\r\n"
					+ "values(seq_myboard.nextval, ?,?,?)";
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getTitle());	//------파라미터를 1번째?에 바인딩
			pstmt.setString(2, bvo.getContents());
			pstmt.setString(3, bvo.getRegid());
			rows = pstmt.executeUpdate();
			if(rows == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	finally {
			dbm.close(conn, pstmt);
		}
		return rows;
	}
	
	
	public int myboardUpdate(String title, String contents, int bseq) {
		DBManager dbm  = OracleDBManager.getInstance();  //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			String sql = "update myboard set title=? , contents = ? where bseq=?";
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, title);	//------파라미터를 1번째?에 바인딩
			pstmt.setString(2, contents);
			pstmt.setInt(3, bseq);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			dbm.close(conn, pstmt);
		}
		return rows;
	}
	
	public int myboardDelete(int bseq) {
		DBManager dbm  = OracleDBManager.getInstance();  //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			String sql = "delete from myboard where bseq=?";
			pstmt =  conn.prepareStatement(sql);
			pstmt.setInt(1, bseq);	//------파라미터를 1번째?에 바인딩
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			dbm.close(conn, pstmt);
		}
		return rows;
	}	


	
	
	
	
//	// --------- 호출 테스트 ---------
//	public static void main(String[] args) throws InterruptedException  {
//		
//		MyboardDAO o = new MyboardDAO();
//		
//		ArrayList<MyboardVO> res2 = o.myboardSelect();
//		for(MyboardVO evo : res2)
//			System.out.println(evo.toString());
//		
//		System.out.println("----------");
//		
//		MyboardVO vo = o.myboardSelect(1);
//		System.out.println("호출결과1:" + vo.toString() + "\n");
//	
////		MyboardVO mm = new MyboardVO();
////		mm.setTitle("titlte");
////		mm.setContents("ccccccc");
////		mm.setRegid("hong");
////		int res3 = o.myboardInsert(mm);
////		System.out.println("호출결과3:" + res3 + "\n");
////	
////		int res4 = o.myboardUpdate("titlte2222", "ccccccc2222", 3);
////		System.out.println("호출결과4:" + res4 + "\n");
////		
////		int res5 = o.myboardDelete(3);
////		System.out.println("호출결과5:" + res5 + "\n");
////		
//	}
	
	
		
	
}





