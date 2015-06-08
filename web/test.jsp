<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logic.model.User"%>

<jsp:useBean id="ac" type="blogapp.BlogAppContext" scope="request" />
<jsp:useBean id="pl" type="blogapp.action.TestAction.TestPayload" scope="request" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>It's a test!</title>
  </head>
  <body>
    <h1>Hello Cruel World!</h1>
<%  if (ac.user != null) { %>
    <div>You are <b><%=ac.user.firstName%> <%=ac.user.lastName%></b>, right?</div>
    <div>Or <a href="?action=logout">you are not</a>?</div>
<%  } else { %>
    <div>
      <a href="?action=login">Log in</a>
    </div>
<%  } %>
    <div>I have <%=pl.postCount%> post(s) for you.</div>

    <div>
      My users:
<%  for (User user: pl.users) { %>
      <div>#<%=user.id%>, created <%=user.createTimestamp.toString()%></div>
<%  } %>
    </div>

    <form action="?action=post" method="post">
      <div>Enter something:</div>
      <div>
        <input type="text" name="enteredText" />
      </div>
      <div>
        <input type="submit" value="I did it!" />
      </div>
    </form>
  </body>
</html>
