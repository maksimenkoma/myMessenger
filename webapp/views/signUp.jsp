
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
     <head>
            <title>Registering a new user</title>
     </head>
        <body>
<c:choose>
    <c:when test="${requestScope.error}">
        <p style="color:red;">${requestScope.message}</p>
    </c:when>

</c:choose>
             <h1>Create new user</h1>


                 <form "${pageContext.request.contextPath}/signUp" method="post">
                    <label>Login:
                      <input type="text" name="login"><br />
                    </label>

                    <label>Password:
                         <input type="password" name="pass"><br />
                    </label>

                    <label>FirstName:
                          <input type="text" name="firstName"><br />
                    </label>

                    <label>LastName:
                          <input type="text" name="lastName"><br />
                    </label>

                    <label>MiddleName:
                          <input type="text" name="middleName"><br />
                    </label>


                    <label>Your date of Birth:

                          <input type="date" name="date" <br />

                    </label>

                             <button type="submit">Send</button>
                 </form>

        </body>

</html>