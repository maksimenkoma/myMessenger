<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Chats</title>
    </head>
        <body>
                <p>User <c:out value="${user.login}"/> received messages from</p>

                   <c:forEach var="message" items ="${history}">
                        <li>${message}</li>
                   </c:forEach>

                    <button onclick="location.href='${pageContext.request.contextPath}/message'">Send message</button>

        </body>

</html>
