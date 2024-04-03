package org.fundaciobit.formacio.formaciobbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )
    {
        Connection BaseDatos = null;
        Statement st = null;
      
        //Donde se localiza la base de datos
        String url="jdbc:postgresql://localhost:5432/formaciobbdd";
      
        //Credenciales de la base de datos
        String usuario="formaciobbdd";
        String contrasena="formaciobbdd";
      
        try {
            //Conexion con la base de datos
            BaseDatos = DriverManager.getConnection(url, usuario, contrasena);
      
            // Se hara una consulta  de la tabla CDS y Cantante, y se mandara a imprimir.
            st = BaseDatos.createStatement();
            ResultSet rs = st.executeQuery( "SELECT formaciobbddid, nom, edat, genere, data"
                    + "  FROM formaciobbdd"
                    + "  ORDER BY edat;" );
      
            while    ( rs.next() ) {
      
                long formaciobbddid = rs.getLong("formaciobbddid");
                String nom= rs.getString("nom");
                int edat= rs.getInt("edat");
                Boolean genere= rs.getObject("genere", Boolean.class);
                Timestamp data= rs.getTimestamp("data");
                System.out.println( "[ FILA #" + formaciobbddid+" ]" );
                System.out.println( "Nom: " + nom );
                System.out.println( "Edat: " + edat );
                System.out.println( "Genere: " + genere );
                System.out.println( "Data: " + data );
                System.out.println();
            }
      
            rs.close();
            st.close();
            BaseDatos.close();
        } catch (Exception e) {
            System.err.println( e.getMessage() );
            
        }
    }
}
