<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="h" uri="/WEB-INF/HtmlFormat"%>

<jsp:useBean id="ac" type="blogapp.BlogAppContext" scope="request" />
<jsp:useBean id="pl" type="blogapp.action.RegisterAction.RegisterPayload" scope="request" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <title>Registration</title>

  <script type="text/javascript" src="js/jquery-1.11.2.js"></script>
</head>
<body>
  <script type="text/javascript">
    function submitForm()
    {
      var formData = $("#pageForm").serializeArray();

      $.ajax({
        url: '?action=registerJson',
        data: formData,
        /*{
          username: $("#pageForm [name='username']").val(),
          password: $("#pageForm [name='password']").val(),
          firstName: $("#pageForm [name='firstName']").val(),
          lastName: $("#pageForm [name='lastName']").val(),
          email: $("#pageForm [name='email']").val()
        }*/
        dataType: 'json'
      }).done(function(data) {
        if (data.success) {
          alert('You registered! Lucky...');
          window.document.location = "?";
        } else {
          alert('Failed to register: ' + data.errorMessage);
        }
      }).fail(function(xhr, textStatus, errorThrown) {
        alert('Error talking to the server: ' + errorThrown);
      });
    }
  </script>

  <h1>Who are you?</h1>
<%  if (pl.errorMessage != null) { %>
  <div style="color: red;"><h:HtmlEncode><%=pl.errorMessage%></h:HtmlEncode></div>
<%  } %>
  <form id="pageForm" action="nonexistent" onsubmit="return false"> <%-- action="?action=doRegister" method="post" --%>
    <div>Username:</div>
    <div>
      <input type="text" name="username" value="<h:QuotedValueEncode><%=pl.username != null ? pl.username : ""%></h:QuotedValueEncode>" />
    </div>
    <div>Password:</div>
    <div>
      <input type="password" name="password" />
    </div>
    <div>First Name:</div>
    <div>
      <input type="text" name="firstName" value="<h:QuotedValueEncode><%=pl.firstName != null ? pl.firstName : ""%></h:QuotedValueEncode>" />
    </div>
    <div>Last Name:</div>
    <div>
      <input type="text" name="lastName" value="<h:QuotedValueEncode><%=pl.lastName != null ? pl.lastName : ""%></h:QuotedValueEncode>" />
    </div>
    <div>E-mail:</div>
    <div>
      <input type="text" name="email" value="<h:QuotedValueEncode><%=pl.email != null ? pl.email : ""%></h:QuotedValueEncode>" />
    </div>
    <div>&nbsp;</div>
    <div>
      <input type="submit" value="Register" onclick='submitForm(); return false;' />
<%--
      <script type="text/javascript">
        $("#pageForm :submit").click(submitForm);
      </script>
--%>
    </div>
  </form>
</body>
</html>
