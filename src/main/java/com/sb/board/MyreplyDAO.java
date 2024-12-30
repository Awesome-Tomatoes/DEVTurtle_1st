package com.sb.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sb.common.DBManager;
import com.sb.common.OracleDBManager;

public class MyreplyDAO {

    public List<MyreplyVO> myreplySelect(int bseq) {
        List<MyreplyVO> replies = new ArrayList<>();
        DBManager dbm = OracleDBManager.getInstance();
        Connection conn = dbm.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "select * from myreply where bseq = ? order by rseq asc";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bseq); // 파라미터를 1번째 ?에 바인딩
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MyreplyVO reply = new MyreplyVO();
                reply.setRseq(rs.getInt("rseq"));
                reply.setReply(rs.getString("reply"));
                reply.setRegid(rs.getString("regid"));
                reply.setRegdate(rs.getString("regdate"));
                reply.setBseq(rs.getInt("bseq"));
                replies.add(reply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbm.close(conn, pstmt, rs);
        }

        return replies;
    }

    public int insertReply(MyreplyVO reply) {
        DBManager dbm = OracleDBManager.getInstance();
        Connection conn = dbm.connect();
        PreparedStatement pstmt = null;
        int rows = 0;

        try {
            String sql = "insert into myreply(rseq, reply, regid, bseq) values(seq_myreply.nextval, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reply.getReply());
            pstmt.setString(2, reply.getRegid());
            pstmt.setInt(3, reply.getBseq());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbm.close(conn, pstmt);
        }

        return rows;
    }

    public int deleteReply(int rseq) {
        DBManager dbm = OracleDBManager.getInstance();
        Connection conn = dbm.connect();
        PreparedStatement pstmt = null;
        int rows = 0;

        try {
            String sql = "delete from myreply where rseq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rseq); // 파라미터를 1번째 ?에 바인딩
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbm.close(conn, pstmt);
        }

        return rows;
    }
//
//    public static void main(String[] args) {
//        MyreplyDAO dao = new MyreplyDAO();
//
//        // 1. 특정 bseq에 대한 댓글 목록 가져오기
//        List<MyreplyVO> replies = dao.myreplySelect(1);
//        for (MyreplyVO reply : replies) {
//            System.out.println(reply.toString());
//        }
//
//        System.out.println("----------");
//
////        // 2. 새로운 댓글 삽입
////        MyreplyVO newReply = new MyreplyVO();
////        newReply.setReply("rr1");
////        newReply.setRegid("hong");
////        newReply.setBseq(2);
////        int insertResult = dao.insertReply(newReply);
////        System.out.println("삽입된 행 수: " + insertResult);
//
//        // 3. 댓글 삭제
////        int deleteResult = dao.deleteReply(4);
////        System.out.println("삭제된 행 수: " + deleteResult);
//    }
    
    
}
