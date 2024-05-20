<!-- Import delaclasse calculadora2 -->
<%@ page import="org.fundaciobit.formacio.calculadoralib.Calculadora"%>
<%! Calculadora calculadora = new Calculadora(); %>
<% 
// Declaracio de variables: 
String result=null; 
//Var del resultat 
String n1 = request.getParameter("n1");
// Vars del formulari. 
String n2 = request.getParameter("n2"); 
int v1; 
//Variables auxiliars per transformar els string a int. 
int v2; 
//Si el formulari no te cap camp buid: 
if (n1 != null && n1.trim().length()!=0 && n2 != null && n2.trim().length()!=0){ 
//Casting del formulari a int.  
v1 = Integer.parseInt(n1); 
v2 = Integer.parseInt(n2); 
//Segons el boto pitjat, es fa l'operació corresponent. 
if (request.getParameter("suma") == null) {
     result = v1 + " - " + v2 + " = " + calculadora.resta(v1, v2);
 } else {
     result = v1 + " + " + v2 + " = " + calculadora.suma(v1, v2);
 }
} %>
<html>
<body>
	<h2 align="center">Calculadora</h2>
	<p>Introdueix dos valors:</p>
	<!-- Formulari: -->
	<form action="#">
		<input type="text" name="n1" min="1" max="99"> <input
			type="text" name="n2" min="1" max="99"> <input type="submit"
			name="suma" value="suma"> </input> <input type="submit" name="resta"
			value="resta"></input>
	</form>
	<!-- Si el resultates null, no el mostrarem -->
	<% if (result != null) { %>
	Resultat:
	<b><%=result%></b>
	<% } %>
</body>
</html>
