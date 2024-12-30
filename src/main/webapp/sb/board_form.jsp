<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                            <li class="breadcrumb-item active">Dashboard > <font color=blue>게시판 글쓰기</font></li>
                        </ol>
                        
                        
                        
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                <b>게시판 글쓰기</b>
                            </div>
                            <div class="card-body">
                            
                            
                            
                            
<!-- --------------------------------------------------------- -->   
<form id="myForm">
<input type="hidden" name="pageGubun"  id="pageGubun">                            
<table class="datatable-table">
    <tbody>
        <tr>
            <th>제목</th>
            <td><input type="text" name="title" id="title" size="60"></td>
        </tr>
        <tr>
            <th>내용</th>
            <td><textarea name="contents" rows="10" id="contents" cols="100"></textarea></td>
        </tr>
    </tbody>
    <tfoot>
    	<tr>
    		<td colspan="2" align="center">
    			<input type="button" value="  글쓰기  " id="insertBtn">
    			<input type="button" value="  목록  "  id="listBtn">
    		</td>
    	</tr>
    </tfoot>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/sb/assets/demo/chart-area-demo.js"></script>
        <script src="${pageContext.request.contextPath}/sb/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/sb/js/datatables-simple-demo.js"></script>
        
        
        
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	$( document ).ready(function() {
		$("#insertBtn").click( function() {  
	    	
	    	if(  $("#title").val() ==""  ) {
	    		alert("제목을 입력하세요");
	    		$("#title").focus();
	    	} else if(  $("#contents").val() ==""  ) {
	    		alert("내용을 입력하세요");
	    		$("#contents").focus();
	    	} 
	    	
	    	$("#myForm").attr("method", "post");
	    	$("#myForm").attr("action", "/myboard");
	    	$("#pageGubun").val("I001");
		    $("#myForm").submit();    		
	    	
	    } );  
		
		
		$("#listBtn").click( function() {  
	    	$("#myForm").attr("method", "get");
	    	$("#myForm").attr("action", "/myboard");
	    	$("#myForm").submit();
	    } );
		
	    
	});
</script>
        
        
        
    </body>
</html>
