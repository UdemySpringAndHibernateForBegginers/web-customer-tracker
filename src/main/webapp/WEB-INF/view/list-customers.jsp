<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List customers</title>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
            <div id="container">
                <div id="content">
                    <table>
                        <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        </tr>

                        <c:forEach var="tmpCustomer" items="${customers}">
                            <tr>
                                <th>${tmpCustomer.firstName}</th>
                                <th>${tmpCustomer.lastName}</th>
                                <th>${tmpCustomer.email}</th>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </div>
    </div>
</body>
</html>