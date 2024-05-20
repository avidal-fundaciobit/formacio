package org.fundaciobit.formacio.formaciobbdddao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author avidal
 */
public class FormacioBBDDDAO  implements IFormacioDAO  {

    private String url;
    private String usuari;
    private String contrasenya;

    public FormacioBBDDDAO(String url, String usuari, String contrasenya) {
        super();
        this.url = url;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
    }

    private Connection getConnection() throws Exception {     
        Connection connection = DriverManager.getConnection(this.url, this.usuari, this.contrasenya);
        return connection;
    }


    public List<Formacio> list() throws Exception {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT formaciobbddid, nom, edat, genere, data FROM formaciobbdd ORDER BY formaciobbddid ASC");

            List<Formacio> list = new ArrayList<>();
            while (rs.next()) {
                long formaciobbddid = rs.getLong("formaciobbddid");
                String nom = rs.getString("nom");
                int edat = rs.getInt("edat");
                Boolean genere = rs.getObject("genere", Boolean.class);
                Timestamp data = rs.getTimestamp("data");

                Formacio f = new Formacio(formaciobbddid, nom, edat, genere, data);
                list.add(f);
            }

            return list;
        } finally {
            closeResources(connection, st, rs);
        }
    }

    public Formacio create(Formacio f) throws Exception {
        // Obtenir el pròxim ID disponible
        Long nextId = getMaxId();

        String sql = "INSERT INTO formaciobbdd (formaciobbddid, nom, edat, genere, data) VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setLong(1, nextId); // Utilitzar el pròxim ID
            statement.setString(2, f.getNom());
            statement.setInt(3, f.getEdat());
            statement.setBoolean(4, f.getGenere());
            statement.setTimestamp(5, f.getData());

            int filesAfectades = statement.executeUpdate();

            if (filesAfectades == 1) {
                // Si s'insereix correctament, retorna l'objecte Formacio
                f.setFormacioBbddId(nextId); // Estableix l'ID generat
                return f;
            } else {
                throw new Exception("Error al crear el registro en la base de datos");
            }
        } finally {
            closeResources(connection, statement, null);
        }
    }


    public Formacio findByPK(Long id) throws Exception {
        String sql = "SELECT * FROM formaciobbdd WHERE formaciobbddid = ?";
        Formacio formacio = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                formacio = new Formacio();
                formacio.setFormacioBbddId(resultSet.getLong("formaciobbddid"));
                formacio.setNom(resultSet.getString("nom"));
                formacio.setEdat(resultSet.getInt("edat"));
                formacio.setGenere(resultSet.getBoolean("genere"));
                formacio.setData(resultSet.getTimestamp("data"));
            }

            return formacio;
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    public Formacio update(Formacio formacio) throws Exception {
        String sql = "UPDATE formaciobbdd SET nom = ?, edat = ?, genere = ?, data = ? WHERE formaciobbddid = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, formacio.getNom());
            statement.setInt(2, formacio.getEdat());
            statement.setBoolean(3, formacio.getGenere());
            statement.setTimestamp(4, formacio.getData());
            statement.setLong(5, formacio.getFormacioBbddId());

            int filesAfectades = statement.executeUpdate();

            if (filesAfectades > 0) {
                // S'ha actualitzat al manco un registre a la base de dades
                return formacio;
            } else {
                // No s'ha actualitzat cap registre, pero no volem canviar l'estat de la base de dades
                return null;
            }

        } finally {
            closeResources(connection, statement, null);
        }
    }



    public Formacio delete(Long formacioBbddId) throws Exception {
        String sql = "DELETE FROM formaciobbdd WHERE formaciobbddid = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, formacioBbddId);

            int filesAfectades = statement.executeUpdate();

            if (filesAfectades == 1) {
                // S'ha eliminat exitosament, retorna un objecte Formacio amb l'ID del registre eliminat
                return new Formacio(formacioBbddId, null, 0, null, null);
            } else {
                // No s'ha trobat cap registre per eliminar
                return null;
            }

        } finally {
            closeResources(connection, statement, null);
        }
    }


    
    private void closeResources(Connection connection, Statement statement, ResultSet resultSet) throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public Long getMaxId() throws Exception {
        String sql = "SELECT MAX(formaciobbddid) FROM formaciobbdd";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obté el màxim ID y li suma un
                return resultSet.getLong(1) + 1;
            } else {
                // Si la taula és buida, retorna 1 com el primer ID
                return 1L;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }



}
