<%@page import="chat.utils.MyUtils"%>
<%@page import="chat.dao.AppUserDAO"%>
<%@page import="chat.bean.Chat"%>
<%@page import="java.util.LinkedList"%>
<%@page import="chat.dao.ChatDAO"%>
<%@page import="chat.bean.AppUser"%>
<%@include file="header.jsp" %>
<a href="logout.jsp">Logout</a><br><br>
<%
HttpSession s1 = request.getSession();
AppUser u1 = (AppUser)s1.getAttribute("u1");
if(u1==null){
	response.sendRedirect("index.jsp");
}
else {
	
	String msg = request.getParameter("msg");
	String forUser = request.getParameter("forUser");
	System.out.println(forUser);
	if(msg != null){
		AppUser newU1 = AppUserDAO.searchByName(u1.getUserName());	
		System.out.println(newU1);
		System.out.println(newU1.getStatus());
		if(!"admin".equalsIgnoreCase(forUser) && "mute".equalsIgnoreCase(newU1.getStatus())) {
			out.println("<h3 class='text-danger'>Sorry u r muted by admin! U can request admin to unmute!</h3>");				
		} else {
			Chat c1 = new Chat();
			c1.setCreatedTime(MyUtils.getTodayWithTime());
			c1.setMessage(msg);
			c1.setSeenBy("");
			c1.setUserName(u1.getUserName());
			c1.setForUser(forUser);
			ChatDAO.insert(c1);		
		}
	}
	
	
	LinkedList<Chat> res = ChatDAO.search(u1.getUserName(), "0");
	out.print("<div class='row p-5'>");
	out.print("<div class='col-sm-8'>");
	out.println("<table class='table table-striped'>");
	for(Chat p1 : res) {
		out.println("<tr><td>"+ p1.getUserName() + "</td><td>" + p1.getMessage()  + "</td></tr>");
	}
	out.println("</table>");
	out.print("</div>");
}

%>
<div class='col-sm-4'>
<h1>${u1.userName}</h1>
<form method="post">
<input required placeholder="MSG" class="form-control" type="text" name="msg" /> 
<label><input type="radio" value="admin" name="forUser"> Admin</label>
<label><input type="radio" value="all" name="forUser" checked="checked"> All</label><br>
<button class="btn btn-primary mt-3">Send</button>
</form>
</div>
</div>
