<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="h" uri="/WEB-INF/HtmlFormat"%>

<jsp:useBean id="ac" type="blogapp.BlogAppContext" scope="request" />
<jsp:useBean id="pl" type="blogapp.action.LoginAction.LoginPayload" scope="request" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>It's a login!</title>
  </head>
  <body>
    <h1>Abandon all hope</h1>
<%  if (pl.errorMessage != null) { %>
    <div style="color: red;"><h:HtmlEncode><%=pl.errorMessage%></h:HtmlEncode></div>
<%  } %>
    <form action="?action=login" method="post">
      <div>Username:</div>
      <div>
        <input type="text" name="username" value="<h:QuotedValueEncode><%=pl.username != null ? pl.username : ""%></h:QuotedValueEncode>" />
      </div>
      <div>Password:</div>
      <div>
        <input type="password" name="password" />
      </div>
      <div>
        <input type="submit" value="Login" />
      </div>
    </form>
    <div>&nbsp;</div>
    <div>
      <a href="?action=register">Register</a> if you don't have a login.
    </div>
  </body>
</html>
