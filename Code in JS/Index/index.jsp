<%@page import="chat.dao.ChatDAO"%>
<%@page import="chat.dao.AppUserDAO"%>
<%@page import="chat.bean.AppUser"%>
<%@include file="header.jsp" %>
<%
String uname = request.getParameter("uname");
String pass = request.getParameter("password");
AppUser u1 = null;
if(uname != null){
	u1 = AppUserDAO.validateUser(uname, pass);
	if(u1==null) {
		out.println("<h3 style='color:red'>Invalid Credentials!</h3>");
	} else {
		HttpSession s1 = request.getSession();
		s1.setAttribute("u1", u1);
		if(u1.isAdmin()){
			response.sendRedirect("admin.jsp");
		} else {
			response.sendRedirect("/ChatWeb/user.jsp");			
		}
	}
}
%>
<div class="row">
<div class="col-sm-6 offset-sm-3">
<h1 class="text-center text-primary">Login</h1>
<hr>
<form method="post">
<input class="form-control" placeholder="User Name" type="text" name="uname"  /><br>
<input class="form-control" placeholder="Password" type="password" name="password"  /><br>
<input class="btn btn-primary btn-block" type="submit" /> 
</form>
</div>
</div>
