package org.fundaciobit.formacio.formacioservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fundaciobit.formacio.calculadoralib.Calculadora;

/**
 * Servlet implementation class FormacioServlet
 */
public class FormacioServlet extends HttpServlet {

    /**
     * Default constructor. 
     */
    public FormacioServlet() {
    }

    protected Calculadora calculadora = new Calculadora();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Var del resultat 
        String result = null;
        // Vars del formulari.        
        String n1 = request.getParameter("n1");
        String n2 = request.getParameter("n2");
        //Si el formulari no te cap camp buid: 
        if (n1 != null && n1.trim().length() != 0 && n2 != null && n2.trim().length() != 0) {
            //Casting del formulari a int.  
            int v1 = Integer.parseInt(n1);
            int v2 = Integer.parseInt(n2);
            //Segons el boto pitjat, es fa l'operació corresponent. 
            if (request.getParameter("suma") == null) {
                result = v1 + " - " + v2 + " = " + calculadora.resta(v1, v2);
            } else {
                result = v1 + " + " + v2 + " = " + calculadora.suma(v1, v2);
            }
        }

        PrintWriter out = response.getWriter();

        out.append("<!DOCTYPE html>");
        out.append("<html>\r\n");
        out.append("   <body>\r\n");
        out.append("   <h2 align=\"center\">Calculadora Servlet</h2>\r\n");
        out.append("   Introdueix dos valors:\r\n");
        out.append("   <!-- Formulari: --> \r\n");
        out.append("   <form action=\"#\" method=\"post\"> \r\n");
        out.append("      <input type=\"text\" name=\"n1\" min=\"1\" max=\"99\"/> \r\n");
        out.append("      <input type=\"text\" name=\"n2\" min=\"1\" max=\"99\"/> \r\n");
        out.append("      <input type=\"submit\" name=\"suma\" value=\"suma\"/>\r\n");
        out.append("      <input type=\"submit\" name=\"resta\" value=\"resta\"/>\r\n");
        out.append("   </form> ");

        // Si el resultat és null, no el mostrarem 
        if (result != null) {
            out.append("Resultat: <b>" + result + "</b>");
        }
        out.append(" </body>\r\n");
        out.append("</html> ");

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(" Passsant pel doPost() ...");
        doGet(request, response);
    }

}
