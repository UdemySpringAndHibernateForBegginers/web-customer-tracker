<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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

                    <!--  add a search box -->
                    <form:form action="search" method="GET">
                        <label>Search customer:</label><input type="text" name="searchName" />
                        <input type="submit" value="Search" class="add-button" />
                    </form:form>

                    <table>
                        <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Action</th>
                        </tr>

                        <c:forEach var="tmpCustomer" items="${customers}">

                        <!-- konstruujemy zmienną typu *url* zawierającą loink do operacji update na zadanym customerze -->
                        <c:url var="updateLink" value="/customer/showFormForUpdate">
                            <c:param name="customerId" value="${tmpCustomer.id}" />
                        </c:url>
                        <!-- konstruujemy zmienną typu *url* zawierającą loink do operacji delete na zadanym customerze -->
                        <c:url var="deleteLink" value="/customer/delete">
                            <c:param name="customerId" value="${tmpCustomer.id}" />
                        </c:url>

                            <tr>
                                <td>${tmpCustomer.firstName}</td>
                                <td>${tmpCustomer.lastName}</td>
                                <td>${tmpCustomer.email}</td>
                                <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if (!(confirm('Are you sure?'))) return false">Delete</a></td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </div>
    </div>
</body>
</html>