package com.sb.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

 
@WebServlet("/myreply")
public class MyreplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//todo
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
   		//response.setContentType("text/html; charset=UTF-8");
   		
   		String pageGubun = request.getParameter("pageGubun");
   		MyreplyDAO rdao = new MyreplyDAO();
   		System.out.println("-" + pageGubun + "-");
   		
   		if (pageGubun == null) {
   			response.sendRedirect("/sb/500.html");
   			

//		 ----------------------------------------------------------- 댓글입력  	
   		//pageGubun=RI001&bseq=12&reply=댓글댓글
   		} else if (pageGubun.equals("RI001")) {
		
			int bseq = 0;
			String bseqStr = request.getParameter("bseq");
			String reply = request.getParameter("reply");
			if(bseqStr != null && !bseqStr.equals(""))  {
				bseq = Integer.parseInt(bseqStr);
				
				MyreplyVO rvo = new MyreplyVO();
				rvo.setReply(reply);
				rvo.setRegid("guest");
				rvo.setBseq(bseq);
				
				int res = rdao.insertReply(rvo);
				
				
//				정상적으로 댓글이 입력된 경우 --> 그자리에 머물러있기
				response.setContentType("application/json; charset=UTF-8");  //★★★★★★★★★★★★★★★★★★★★★★★★
				PrintWriter out = response.getWriter();
				if(res > 0) {
					List<MyreplyVO> rlist = rdao.myreplySelect(bseq);
	   				
					HashMap<String, Object> responseMap = new HashMap<String, Object>();
	   				responseMap.put("status", "200");
	   				responseMap.put("message", rlist);
	   				ObjectMapper mapper = new ObjectMapper();
	   				String jsonString = mapper.writeValueAsString(responseMap);
	   				out.write(jsonString);	
	   			} else {
	   				
	   				HashMap<String, Object> responseMap = new HashMap<String, Object>();
	   				responseMap.put("status", "500");
	   				responseMap.put("message", "error");
	   				ObjectMapper mapper = new ObjectMapper();
	   				String jsonString = mapper.writeValueAsString(responseMap);
	   				out.write(jsonString);	
	   			}
				
				
//				정상적으로 댓글이 입력된 경우 --> 그자리에 머물러있기
//				if(res > 0) {
//	   				response.sendRedirect("/myboard?bseq="+bseq+"&pageGubun=T001");
//	   			} else {
//	   				response.sendRedirect("/sb/500.html");
//	   			}
//			} else {
//				response.sendRedirect("/sb/500.html");
			}
		
//		 ----------------------------------------------------------- 댓글삭제
   		} else if (pageGubun.equals("RD001")) {
   			
   			int bseq = 0;
   			int rseq = 0;
   			String bseqStr = request.getParameter("bseq");
			String rseqStr = request.getParameter("rseq");
			if(  (bseqStr != null && !bseqStr.equals("")) && (rseqStr != null && !rseqStr.equals("")) )  {
				bseq = Integer.parseInt(bseqStr);
				rseq = Integer.parseInt(rseqStr);
				
				int res = rdao.deleteReply(rseq);
				
	   			//정상적으로 댓글이 삭제된 경우 --> 그자리에 머물러있기
				response.setContentType("application/json; charset=UTF-8");  //★★★★★★★★★★★★★★★★★★★★★★★★
				PrintWriter out = response.getWriter();
				if(res > 0) {
					List<MyreplyVO> rlist = rdao.myreplySelect(bseq);
					
	   				HashMap<String, Object> responseMap = new HashMap<String, Object>();
	   				responseMap.put("status", "200");
	   				responseMap.put("message", rlist);
	   				ObjectMapper mapper = new ObjectMapper();
	   				String jsonString = mapper.writeValueAsString(responseMap);
	   				out.write(jsonString);	
	   			} else {
	   				
	   				HashMap<String, Object> responseMap = new HashMap<String, Object>();
	   				responseMap.put("status", "500");
	   				responseMap.put("message", "error");
	   				ObjectMapper mapper = new ObjectMapper();
	   				String jsonString = mapper.writeValueAsString(responseMap);
	   				out.write(jsonString);	
	   			}
				
//				if(res > 0) {
//	   				response.sendRedirect("/myboard?bseq="+bseq+"&pageGubun=T001");
//	   			} else {
//	   				response.sendRedirect("/sb/500.html");
//	   			}
//			}else {
//				response.sendRedirect("/sb/500.html");
			}
   		}
		
	}

}
