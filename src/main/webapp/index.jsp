<html>
<head>
    <title>Hello World - JSP</title>
</head>
<body>
    <%-- JASP Commnet --%>
    <h1>This is a index.jsp</h1>
    <p>
        <%
            out.println("My friend");
        %>
        <span style="color=red">
            <%=request.getRemoteAddr()%>
        </span>
    </p>

</body>
</html>
