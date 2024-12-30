<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>

    
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="/sb/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        
        
<jsp:include page="${pageContext.request.contextPath}/sb/common_top.jsp" />
        
        
        
        <div id="layoutSidenav">
            
            
            
            
<jsp:include page="${pageContext.request.contextPath}/sb/common_left.jsp" />            
            
            
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard  > <font color=blue>게시판 상세보기</font></li>
                        </ol>
                        
                        
                        
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                <b>게시판 상세보기</b>
                            </div>
                            <div class="card-body">
                            
                            
                            
                            
<!-- --------------------------------------------------------- -->   

<form id="myForm">     
<input type="hidden" name="bseq"      value="${MY_KEY_BVO.bseq}"> 
<input type="hidden" name="pageGubun"  id="pageGubun">                   
<table class="datatable-table">
    <tbody>
        <tr>
            <th>글번호</th>
            <td>${MY_KEY_BVO.bseq}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td><input type="text" name="title" size="80" value="${MY_KEY_BVO.title}"></td>
        </tr>
        <tr>
            <th>글쓴이</th>
            <td>${MY_KEY_BVO.regid}</td>
        </tr>
        <tr>
            <th>날짜</th>
            <td>${MY_KEY_BVO.regdate}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td><textarea name="contents" rows="5" cols="100">${MY_KEY_BVO.contents}</textarea></td>
        </tr>
    </tbody>
    <tfoot>
    	<tr>
    		<td colspan="2" align="center">
    			<input type="button" value="  수정  " id="updateBtn">
    			<input type="button" value="  삭제  " id="deleteBtn">
    			<input type="button" value="  목록  " id="listBtn">
    		</td>
    	</tr>
    </tfoot>
</table>  
</form><br><br>
<!-- --------------------------------------------------------- -->


<div id="resultDiv">
		<table style="{width:100%}">
		<c:choose>
			<c:when test="${ fn:length(MY_KEY_RLIST) <= 0 }">
				해당 게시물의 댓글이 존재하지 않습니다
			</c:when>
			<c:otherwise> 
						<c:forEach var="rvo" items="${MY_KEY_RLIST}">		
						
							<tr>
								<td>
									<c:if test="${rvo.regid =='guest'}">
									
<!-- 버튼 클래스 추가 ************************************************************************ -->
										<input type='button' value='삭제'  data-gubun='RD001' data-bseq='${rvo.bseq}' data-rseq='${rvo.rseq}' class='restDeleteBtn' > 
									</c:if>
								</td>
								<td>${rvo.reply}</td><td>(${rvo.regid}, ${rvo.regdate})</td>
							</tr>
						</c:forEach>
			</c:otherwise> 
		</c:choose>
		</table>
</div>
<br>
<!-- --------------------------------------------------------- -->
<form name="replyForm" id="replyForm"> 
<input type='hidden' name=pageGubun value="RI001">
<input type="hidden" name="bseq" value="${MY_KEY_BVO.bseq}">
<table style="{width:100%}">
	<tr>
		<td><input type="text" size="60" name="reply" id="reply">
			<input type='button' id="insertBtn" value='댓글저장'><td>
	</tr>
</table>
</form>

<!-- --------------------------------------------------------- -->                                
                            
                                
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/sb/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/sb/js/datatables-simple-demo.js"></script>
    
    
  
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>

	function My_make_reply_table(obj) {
			if(obj.status == "200") { 
				$("#resultDiv").empty();
				htmlStr = "<table style=\"{width:100%}\">";
				$(obj.message).map(function(i, rvo) {
					htmlStr += "<tr>";
					htmlStr += "<td><input type='button' value='삭제'  data-gubun='RD001' data-bseq='"+rvo.bseq+"' data-rseq='"+rvo.rseq+"' class='restDeleteBtn'></td>";
					htmlStr += "<td>"+rvo.rseq+"</td>";
					htmlStr += "<td>"+rvo.reply+"</td>";
					htmlStr += "<td>"+rvo.regid+"</td>";
					htmlStr += "</tr>";
				});
				htmlStr += "</table>";
				$("#resultDiv").html(htmlStr);	
			} else {
				$("#resultDiv").html("입력 중 에러발생 " + obj.message);	
			}
	}

	$( document ).ready(function() {
		
		//jQuery 동적이벤트 바인딩
		$(document).on('click',".restDeleteBtn",function(){
			
			// ****************************************************************************
			// data-* 속성 값 추출
			// ****************************************************************************
			console.log($(this).data());
			
		    var pageGubun = $(this).data('gubun'); // data-gubun
		    var bseq = $(this).data('bseq');           // data-bseq
		    var rseq = $(this).data('rseq');           // data-rseq
			//console.log(pageGubun + "," + bseq + "," + rseq);

		    // 방법1) 템플릿 리터럴로 Query String 생성
		    var formDataStr1 = `pageGubun=${pageGubun}&bseq=${bseq}&rseq=${rseq}`;
		    
		 	// 방법2) URLSearchParams(). 사용해 Query String 생성
		 	var params = new URLSearchParams({ pageGubun, bseq, rseq });
		 	var formDataStr2 = params.toString();
			
		 	// 방법3) $.param(). 사용해 Query String 생성
		    var tempObj = {
		            pageGubun: pageGubun,
		            bseq: bseq,
		            rseq: rseq
		        };
		    var formDataStr3 = params.toString();  //$.param(tempObj);
		    
		    console.log(formDataStr1);
		    console.log(formDataStr2);
		    console.log(formDataStr3);
		    
		 	// ****************************************************************************
		   
		    $.ajax({
		    	url  		: "/myreply" ,
		    	method 		: 'POST' , 
		    	data 		: formDataStr3 , 	  //pageGubun=RD001&bseq=12&rseq=3		
		    	//contentType : "application/x-www-form-urlencoded; charset=UTF-8", 
		    	//dataType 	: "json", 	
		    	success 	: function(obj) { 
		    						My_make_reply_table(obj);
					    	  } ,
		    	error 		: function(err) { console.log("에러:" + err) }  
		    });
		});
		
		
		$("#insertBtn").click( function() {  
				var formData = $("#replyForm").serialize();  //pageGubun=RI001&bseq=12&reply=댓글댓글
				console.log(formData);
				$.ajax({
			    	url  		: "/myreply" ,
			    	method 		: 'POST' , 
			    	data 		: formData , 			
			    	//contentType : "application/x-www-form-urlencoded; charset=UTF-8", 
			    	//dataType 	: "json", 	
			    	success 	: function(obj) { 
			    						My_make_reply_table(obj);
			    						$("#reply").val("");
			    						$("#reply").focus();
						    	  } ,
			    	error 		: function(err) { console.log("에러:" + err) }  
			    });
		});
	
		
		
		$("#updateBtn").click( function() {  
	    	$("#myForm").attr("method", "post");
	    	//$("#myForm").attr("action", "/myboard?pageGubun=U001");
	    	$("#myForm").attr("action", "/myboard");
	    	$("#pageGubun").val("U001");
	    	$("#myForm").submit();
	    } );  
		
		/*
		$("#deleteBtn").click( function() {  
	    	$("#myForm").attr("method", "post");
	    	//$("#myForm").attr("action", "/myboard?pageGubun=D001");
	    	$("#myForm").attr("action", "/myboard");
	    	$("#pageGubun").val("D001");
	    	$("#myForm").submit();
	    } );
		*/
		
		
		$("#listBtn").click( function() {  
	    	$("#myForm").attr("method", "get");
	    	$("#myForm").attr("action", "/myboard");
	    	$("#myForm").submit();
	    } );
		
	    
	});
</script>


    
    
    </body>
</html>
