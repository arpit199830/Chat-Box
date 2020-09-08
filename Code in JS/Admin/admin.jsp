<%@page import="ECUtils.ECValidattion.PassValidator"%>
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
LinkedList<AppUser> userList = null;
AppUser u1 = (AppUser)s1.getAttribute("u1");
if(u1==null || !u1.isAdmin()){
	response.sendRedirect("index.jsp");
}
else {
	//send msg
	String msg = request.getParameter("msg");
	String forUser = request.getParameter("forUser");
	System.out.println(forUser);
	if(msg != null){
		AppUser newU1 = AppUserDAO.searchByName(u1.getUserName());	
		Chat c1 = new Chat();
		c1.setCreatedTime(MyUtils.getTodayWithTime());
		c1.setMessage(msg);
		c1.setSeenBy("");
		c1.setUserName(u1.getUserName());
		c1.setForUser(forUser);
		ChatDAO.insert(c1);		
	}
	
	//update
	String uname = request.getParameter("uname");
	String action = request.getParameter("action");
	if(uname != null){
		AppUser old = AppUserDAO.searchByName(uname);
		if(old==null) {
			out.println("<h3 class='text-danger'>Does not exist!!</h3>");			
		} else {
			if("mute".equalsIgnoreCase(action)){
				old.setStatus("mute");
				AppUserDAO.update(old);
				out.println("<h3 class='text-success'>Done!!</h3>");							
			} else if("unmute".equalsIgnoreCase(action)){
				old.setStatus("unmute");
				AppUserDAO.update(old);
				out.println("<h3 class='text-success'>Done!!</h3>");							
			} else if("delete".equalsIgnoreCase(action)){	
				AppUserDAO.delete(old);
				out.println("<h3 class='text-success'>Done!!</h3>");											
			}			
		}
	}

	//add user
	String uname2 = request.getParameter("uname2");
	String password2 = request.getParameter("password2");
	if(uname2 != null){
		AppUser old = AppUserDAO.searchByName(uname2);
		if(old!=null) {
			out.println("<h3 class='text-danger'>Already exist!!</h3>");			
		} else {
			AppUser u2 = new AppUser();
			u2.setUserName(uname2);
			u2.setPassword(password2);
			u2.setStatus("unmute");
			u2.setRole("user");
			PassValidator v2 = new PassValidator();
			String err = v2.validate(u2.getPassword());
			if(err!=null) {
				out.println("<h3 class='text-danger'>"+err+"</h3>");			
			} else {
				AppUserDAO.insert(u2);					
				out.println("<h3 class='text-success'>Done!!</h3>");											
			}			
		}
	}
	
	
	//search users
	String si = request.getParameter("si");
	if(s1!=null){
		userList = AppUserDAO.search(si);
	}
	
	//show msg	
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
<input required placeholder="forUser" value="all" class="form-control" type="text" name="forUser" /> 
<button class="btn btn-primary mt-3">Send</button>
</form>
<br>
<form method="post">
<input required placeholder="User Name" class="form-control" type="text" name="uname" /> 
<label><input type="radio" value="mute" name="action"> Mute</label>
<label><input type="radio" value="unmute" name="action" checked="checked"> Unmute</label>
<label><input type="radio" value="delete" name="action" > Delete</label><br>
<button class="btn btn-danger mt-3">Update</button>
</form>

<form method="post">
<input required placeholder="User Name" class="form-control" type="text" name="uname2" /> 
<input required placeholder="Password" class="form-control" type="password" name="password2" /> 
<button class="btn btn-success mt-3">Add</button>
</form>

<form method="post">
<input placeholder="Search User" class="form-control" type="text" name="si" /> 
<button class="btn btn-warning mt-3">Search</button>
</form>

<% 
//show users
	if(userList!=null){
		if(userList.size()==0) {
			out.println("<h3 class='text-danger'>No Record Found!</h3>");
		} else {
			out.println("<table class='table table-striped'>");
			for(AppUser p1 : userList) {
				out.println("<tr><td>"+ p1.getUserName() + "</td><td>" + p1.getStatus()  + "</td></tr>");
			}
			out.println("</table>");
			out.print("</div>");			
		}
	}
%>
</div>
</div>











