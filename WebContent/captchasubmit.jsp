<%
   
	request.setCharacterEncoding("UTF-8");
	String answer = request.getParameter("answer");
	String sessAns =(String)session.getAttribute("ans");
	if ( answer.compareTo(sessAns) == 0) {
%>

<b>Correct Captcha Code !</b> <%
 	} else {
 %> <b>ERROR Captcha Code !</b> <%
 	}
 %>


