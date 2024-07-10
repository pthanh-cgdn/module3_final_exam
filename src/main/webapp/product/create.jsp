<%--
  Created by IntelliJ IDEA.
  User: tphan
  Date: 9/7/24
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new customer</title>
    <style>
        .message{
            color:green;
        }
    </style>
</head>
<body>
<h1>Create new product</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/product">Back to product list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Product information</legend>
        <table>

            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" required></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="number" name="price" id="price" required min="100"></td>
            </tr>
            <tr>
                <td>Discount: </td>
                <td>
                    <select name="discount" id="discount" required>
                        <option value="5">5(%)</option>
                        <option value="10">10(%)</option>
                        <option value="15">15(%)</option>
                        <option value="20">20(%)</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Stock: </td>
                <td><input type="number" name="stock" id="stock" required min="11"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create new product"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
