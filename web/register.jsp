<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="ac" type="blogapp.BlogAppContext" scope="request" />
<jsp:useBean id="pl" type="blogapp.action.RegisterAction.RegisterPayload" scope="request" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>Registration</title>
  </head>
  <body>
    <h1>Who are you?</h1>
<%  if (pl.errorMessage != null) { %>
    <div style="color: red;"><%=pl.errorMessage%></div>
<%  } %>
    <form action="?action=doRegister" method="post">
      <div>Username:</div>
      <div>
        <input type="text" name="username" value="<%=pl.username != null ? pl.username : ""%>" />
      </div>
      <div>Password:</div>
      <div>
        <input type="password" name="password" />
      </div>
      <div>First Name:</div>
      <div>
        <input type="text" name="firstName" value="<%=pl.firstName != null ? pl.firstName : ""%>" />
      </div>
      <div>Last Name:</div>
      <div>
        <input type="text" name="lastName" value="<%=pl.lastName != null ? pl.lastName : ""%>" />
      </div>
      <div>E-mail:</div>
      <div>
        <input type="text" name="email" value="<%=pl.email != null ? pl.email : ""%>" />
      </div>
      <div>&nbsp;</div>
      <div>
        <input type="submit" value="Register" />
      </div>
    </form>
  </body>
</html>
