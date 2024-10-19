<!DOCTYPE html>
<html>
<head>
    <title>User Form</title>
</head>
<body>
    <h2>User Form</h2>
    <form action="user" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="${user.name}" required></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" value="${user.email}" required></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="country" value="${user.country}"required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
