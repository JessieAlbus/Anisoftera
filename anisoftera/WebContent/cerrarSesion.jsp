<%@ page session = "true"%>
<%
	HttpSession sesion = request.getSession();
	sesion.invalidate();
%>
<jsp:forward page="login.html"/>