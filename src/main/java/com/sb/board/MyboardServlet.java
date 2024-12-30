package com.sb.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sb.common.OracleDBManager;
import com.sb.common.PagingUtil;

@WebServlet("/myboard")
public class MyboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//   	response.setContentType("text/html; charset=UTF-8");
   		
   		String pageGubun = request.getParameter("pageGubun");
   		MyboardDAO bdao = new MyboardDAO();
   		
   		System.out.println("-" + pageGubun + "-");
   		
   		
//	 ----------------------------------------------------------- 목록보기
   		if (pageGubun == null || pageGubun.equals("")) {
   			//ArrayList<MyboardVO> blist = bdao.myboardSelect();
   			
   			//----------------------------------------------------------------------------
   			//PagingUtil(String url, int currentPage, int totRecord, int blockCount, int blockPage)
   			int currentPage = 1;
   			String currentPageStr = request.getParameter("currentPage");
   			if(currentPageStr != null && !currentPageStr.equals(""))  {
   				currentPage = Integer.parseInt(currentPageStr);
   			}
   			
   			int totRecord = bdao.myboardSelect().size();
   			int blockCount = 3; 
   			int blockPage = 2;
   			
   			PagingUtil pg = new PagingUtil("/myboard", currentPage, totRecord, blockCount, blockPage);
   			request.setAttribute("MY_KEY_PAGING_HTML", pg.getPagingHtml().toString());
   			//----------------------------------------------------------------------------
   			
   			ArrayList<MyboardVO> blist = bdao.myboardSelect( pg.getStartSeq(), pg.getEndSeq());
   			request.setAttribute("MY_KEY_BLIST", blist);
   			request.getRequestDispatcher("/sb/board_list.jsp").forward(request, response);
   				
//   ----------------------------------------------------------- 상세보기   				
   		} else if (pageGubun.equals("T001")) {
   			int bseq = 1;
   			String bseqStr = request.getParameter("bseq");
   			if(bseqStr != null && !bseqStr.equals(""))  {
   				bseq = Integer.parseInt(bseqStr);
   				MyboardVO bvo = bdao.myboardSelect(bseq);
   				request.setAttribute("MY_KEY_BVO", bvo);		//해당게시물의 상세내용
   				
   				MyreplyDAO rdao = new MyreplyDAO();
   				List<MyreplyVO> rlist = rdao.myreplySelect(bseq);
   				request.setAttribute("MY_KEY_RLIST", rlist);	//해당게시물의 댓글목록
   				
   	   			request.getRequestDispatcher("/sb/board_detail.jsp").forward(request, response);
   			} else {
   				response.sendRedirect("/sb/500.html");
   			}
   	//   ----------------------------------------------------------- 상세보기   				
   	   	} else if (pageGubun.equals("TR001")) {
   	   			int bseq = 1;
   	   			String bseqStr = request.getParameter("bseq");
   	   			if(bseqStr != null && !bseqStr.equals(""))  {
   	   				bseq = Integer.parseInt(bseqStr);
   	   				
   	   				MyboardVO2 bvo = bdao.myboardReplySelect(bseq);
   	   				request.setAttribute("MY_KEY_BVO_RLIST", bvo);		//해당게시물 상세 + 댓글
   	   				
   	   	   			request.getRequestDispatcher("/sb/board_detail2.jsp").forward(request, response);
   	   			} else {
   	   				response.sendRedirect("/sb/500.html");
   	   			}
   		} 
   		
//   ----------------------------------------------------------- 		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//   	response.setContentType("text/html; charset=UTF-8");
   		
   		ServletContext context = getServletContext();
		String uploadPath = context.getInitParameter("MY_CTX_UPLOAD_PATH");
		int maxFileSize   = Integer.parseInt(context.getInitParameter("MY_CTX_MAX_FILE_SIZE"));
		System.out.println("context file path:" + uploadPath);
		System.out.println("context file mx size:" + maxFileSize);
		
		Properties props = new Properties();
		props.load(OracleDBManager.class.getClassLoader().getResourceAsStream("fileupload.properties"));
		String uploadPath2 = props.getProperty("fileupload.path");
		int maxFileSize2   = Integer.parseInt(props.getProperty("fileupload.mxsize"));
		System.out.println("properties file path:" + uploadPath2);
		System.out.println("properties file mx size:" + maxFileSize2);
		
		
   		String pageGubun = request.getParameter("pageGubun");
   		MyboardDAO bdao = new MyboardDAO();
   		
   		System.out.println("-" + pageGubun + "-");
   		
   		
   		if (pageGubun == null) {
   			response.sendRedirect("/sb/500.html");
   			
//   		 ----------------------------------------------------------- 입력폼
   		} else if (pageGubun.equals("I001")) {
   			String title = request.getParameter("title");
   			String contents = request.getParameter("contents");
   			
   			MyboardVO bvo  = new MyboardVO();
   			bvo.setTitle(title);
   			bvo.setContents(contents);
   			bvo.setRegid("kim");
   			int res = bdao.myboardInsert(bvo);
   			//정상적으로 입력된 경우 --> 목록으로 가기
			if(res > 0) {
   				response.sendRedirect("/myboard");
   			} else {
   				response.sendRedirect("/sb/500.html");
   			}
   			
   			
//   		 ----------------------------------------------------------- 수정		
   		} else if (pageGubun.equals("U001")) {
   			
   			int bseq = 0;
   			String bseqStr = request.getParameter("bseq");
   			String title = request.getParameter("title");
   			String contents = request.getParameter("contents");
   			
   			if(bseqStr != null && !bseqStr.equals(""))  {
   				bseq = Integer.parseInt(bseqStr);
   				int res = bdao.myboardUpdate(title,contents,bseq); //String title, String contents, int bseq
   				
   				//정상적으로 수정 된 경우 --> 그자리에 머물러있기
   				if(res > 0) {
   	   				response.sendRedirect("/myboard?bseq="+bseq+"&pageGubun=T001");
   	   			} else {
   	   				response.sendRedirect("/sb/500.html");
   	   			}
   			} else {
   				response.sendRedirect("/sb/500.html");
   			}
   			
   			
//   		 ----------------------------------------------------------- 삭제   			
   		} else if (pageGubun.equals("D001")) {
   			
   			int bseq = 0;
   			String bseqStr = request.getParameter("bseq");
   			if(bseqStr != null && !bseqStr.equals(""))  {
   				bseq = Integer.parseInt(bseqStr);
   				int res = bdao.myboardDelete(bseq);	//int bseq
	   			if(res > 0) {
	   				//정상적으로 삭제가 된 경우 --> 목록보기로 이동
	   				response.sendRedirect("/myboard");
	   				
	   				//ArrayList<MyboardVO> blist = bdao.myboardSelect();
	   	   			//request.setAttribute("MY_KEY_BLIST", blist);
	   	   			//request.getRequestDispatcher("/sb/board_list.jsp").forward(request, response);
	   				
	   			} else {
	   				response.sendRedirect("/sb/500.html");
	   			}
   			} else {
   				response.sendRedirect("/sb/500.html");
   			}
//   		 ----------------------------------------------------------- 그외
   		} else {
   			response.sendRedirect("/sb/500.html");
   		}
	}
}
