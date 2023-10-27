<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>List of all Quotes</title>
</head>
<body>
   <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
                <th>Quote ID</th>
                <th>Initial Price</th>
                <th>Time Window</th>
                <th>Request ID</th>
                

            </tr>
            <c:forEach var="quote" items="${listQuotes}">
                <tr style="text-align:center">
                    <td><c:out value="${Quote.quoteID}" /></td>
                    <td><c:out value="${Quote.initialPrice}" /></td>
                    <td><c:out value="${Quote.timeWindow}" /></td>
                    <td><c:out value="${Quote.requestID}" /></td>

                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>