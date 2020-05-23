<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List customers</title>
    <!-- załadowanie styli -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
            <div id="container">
                <div id="content">
                    <input type="button" value="Add customer"
                        onclick="window.location.href='showFormForAdd'; return false;"
                        class="add-button"
                    />
                    <table>
                        <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Action</th>
                        </tr>

                        <c:forEach var="tmpCustomer" items="${customers}">

                        <!-- konstruujemy zmienną typu *url* zawierającą loink do operacji update na zadanym customerze -->
                        <c:url var="updateLink" value="/customr/showForForUpdate">
                            <c:param name="customerId" value="${tmpCustomer.id}" />
                        </c:url>

                            <tr>
                                <td>${tmpCustomer.firstName}</td>
                                <td>${tmpCustomer.lastName}</td>
                                <td>${tmpCustomer.email}</td>
                                <td><a href="${updateLink}">Update</a></td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </div>
    </div>
</body>
</html>