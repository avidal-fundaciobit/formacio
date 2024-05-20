<%@page import="java.util.Locale"
%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"
%><%@include file="/WEB-INF/tiles/includes.jsp"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Exemple de TILES</title>
</head>
<body id="main-body">
    <tiles:insertAttribute name="cap" />
    <tiles:insertAttribute name="contingut" />
    <tiles:insertAttribute name="peu" />
</body>
</html>