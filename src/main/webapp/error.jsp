<%--
  Created by IntelliJ IDEA.
  User: umalog
  Date: 22.10.17
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" import="java.io.*"%>
<%@ page import="static jdk.nashorn.internal.objects.Global.println" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
</head>
<body>

<h2>Some error in page</h2>

Message:
<%=exception.getMessage()%>


<h3>StackTrace:</h3>
<%
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    exception.printStackTrace(printWriter);
    println("<pre>");
    println(stringWriter);
    println("</pre>");
    printWriter.close();
    stringWriter.close();
%>

</body>
</html>
