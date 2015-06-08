<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="ac" type="blogapp.BlogAppContext" scope="request" />
<jsp:useBean id="pl" type="blogapp.action.DoRegisterAction.RegisteredPayload" scope="request" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>Registration Successful!</title>
  </head>
  <body>
    <h1>You are <%=pl.user.username%>!</h1>
    <div>Registration successful. Now <a href="?action=login">Log In</a>.</div>
  </body>
</html>
