<%
HttpSession s1 = request.getSession();
s1.removeAttribute("u1");
s1.invalidate();
response.sendRedirect("index.jsp");
%>