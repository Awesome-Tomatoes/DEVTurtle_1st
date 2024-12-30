package com.sb.board;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sb.common.OracleDBManager;


@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Properties props = new Properties();
		props.load(OracleDBManager.class.getClassLoader().getResourceAsStream("fileupload.properties"));
		String uploadPath2 = props.getProperty("fileupload.path");
		int maxFileSize2   = Integer.parseInt(props.getProperty("fileupload.mxsize"));
		System.out.println("properties file path:" + uploadPath2);    
		System.out.println("properties file mx size:" + maxFileSize2);
		
		
		//C:\\IT\\file_upload 
		File uploadDir = new File(uploadPath2);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// 업로드에 해당하는 부분
		MultipartRequest mRequest = new MultipartRequest(request, 
									uploadPath2, 
									maxFileSize2, 
									"UTF-8", 
									new DefaultFileRenamePolicy());
		
		// 파일 관련 정보 추출
		File ufile = mRequest.getFile("ufile"); // upload1.html의 폼태그 값
		System.out.println(ufile); 				// 첨부된 파일의 전체 경로 출력
		System.out.println(ufile.getName());
		
		// 파라미터값 읽어오기
		String title = mRequest.getParameter("title");
		System.out.println(title);
	}

}
