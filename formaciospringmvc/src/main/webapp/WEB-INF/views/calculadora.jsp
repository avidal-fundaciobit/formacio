<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculadora</title>
</head>
<body>

    <h2 align="center">Calculadora Spring MVC</h2>
    Introdueix dos valors:
    <!-- Formulari: -->
    <form action="#" method="post">
        <input type="text" name="n1" min="1" max="99" />
        <input type="text" name="n2" min="1" max="99" />
        <input type="submit" name="operacio" value="suma" />
        <input type="submit" name="operacio" value="resta" />
    </form>
    <%-- Si el resultat és null, no el mostrarem  --%>
    <c:if test="${not empty result}">
            Resultat: <b>${result}</b>
    </c:if>
</body>
</html>
