
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
     <head>
            <title>Enter</title>
     </head>
        <body>

         <c:choose>
                <c:when test="${requestScope.error}">
                <p style="color:red;">${requestScope.message}</p>
                </c:when>

            </c:choose>


                    <h1>Go chat</h1>

                 <form action="${pageContext.request.contextPath}/signIn" method="post">
                    <label>Login:
                      <input type="text" name="login"><br />
                    </label>

                    <label>Password:
                         <input type="password" name="pass"><br />
                    </label>

                     <button type="submit">Login</button>
                 </form>

                <button onclick="location.href='${pageContext.request.contextPath}/signUp'">Registration</button>
        </body>

</html>