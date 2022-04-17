
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
     <head>
            <title>Enter</title>
     </head>
        <body>

        <c:choose>
            <c:when test="${requestScope.success}">
                    <p style="color:green;">Сообщение успешно отправленно. Отправляй ещё раз</p>
            </c:when>
            <c:when test="${requestScope.error}">
                    <p style="color:red;">${requestScope.message}</p>
            </c:when>

        </c:choose>
            <p>Welcome <c:out value="${user.login}"/></p>

                 <form "${pageContext.request.contextPath}/message" method="post">

                    <label>Send message to login:
                      <input type="text" name="sender"><br />
                    </label>

                    <label>Message:</br>

                     <textarea name="message" rows="4" cols="55" wrap="virtual"></textarea></br>
                     </label>

                      <button type="submit">Send message</button>

                 </form>

               <button onclick="location.href='${pageContext.request.contextPath}/chats'">My messages</button>

        </body>

</html>