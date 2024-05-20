package org.fundaciobit.formacio.formaciobbdddao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FormacioBBDDDAOTest {

    private static final String URL = "jdbc:postgresql://localhost:5432/formaciobbdd";
    private static final String USER = "formaciobbdd";
    private static final String PASSWORD = "formaciobbdd";

    private FormacioBBDDDAO dao;
    private List<Formacio> registresOriginals;

    @Before
    public void setUp() throws Exception {
        // Crear una conexió amb la base de dades y guardar els registres originals
        dao = new FormacioBBDDDAO(URL, USER, PASSWORD);
        registresOriginals = backupOriginalRecords();

        // Eliminar tots els registres de la taula
        deleteAllRecords();

        // Inserir registres de prova
        insertTestRecords();
    }

    @After
    public void tearDown() throws Exception {
        // Eliminar tots els registres de prova de la taula
        deleteAllRecords();

        // Restaurar els registres originals
        restoreOriginalRecords();
    }

    private List<Formacio> backupOriginalRecords() throws Exception {
        List<Formacio> registres = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        var resultSet = stmt.executeQuery("SELECT * FROM formaciobbdd");
        while (resultSet.next()) {
            long formaciobbddid = resultSet.getLong("formaciobbddid");
            String nom = resultSet.getString("nom");
            int edat = resultSet.getInt("edat");
            boolean genere = resultSet.getBoolean("genere");
            Timestamp data = resultSet.getTimestamp("data");

            registres.add(new Formacio(formaciobbddid, nom, edat, genere, data));
        }

        return registres;
    }

    private void deleteAllRecords() throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM formaciobbdd");
    }

    private void insertTestRecords() throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO formaciobbdd (formaciobbddid, nom, edat, genere, data) VALUES "
                + "(1, 'Test1', 30, true, CURRENT_TIMESTAMP), " + "(2, 'Test2', 44, false, CURRENT_TIMESTAMP),"
                + "(3, 'Test3', 55, true, CURRENT_TIMESTAMP)," + "(4, 'Test4', 70, false, CURRENT_TIMESTAMP),"
                + "(5, 'Test5', 25, null, CURRENT_TIMESTAMP)");
    }

    private void restoreOriginalRecords() throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        for (Formacio f : registresOriginals) {
            stmt.executeUpdate("INSERT INTO formaciobbdd (formaciobbddid, nom, edat, genere, data) VALUES ("
                    + f.getFormacioBbddId() + ", '" + f.getNom() + "', " + f.getEdat() + ", " + f.getGenere() + ", '"
                    + f.getData() + "')");
        }
    }

    @Test
    public void testCreate() throws Exception {
        Formacio f = new Formacio(null, "Test3", 40, true, new Timestamp(System.currentTimeMillis()));
        Formacio created = dao.create(f);
        assertNotNull(created);

        // Obtenir l'ID asignat automaticament
        Long createdId = created.getFormacioBbddId();
        assertNotNull(createdId);

        // Recuperar l'objecte creat per ID
        Formacio retrieved = dao.findByPK(createdId);
        assertEquals(f, retrieved);
    }

    @Test
    public void testList() throws Exception {
        List<Formacio> list = dao.list();
        assertEquals(5, list.size());
    }
    
    @Test
    public void testFindByPK() throws Exception {
        // Seleccionar una clau primaria existent per cercar
        Long primaryKey = 1L;
        
        // Cercar el registre per clau primaria
        Formacio found = dao.findByPK(primaryKey);
        
        // Verificar si el registre s'ha trobat
        assertNotNull(found);
        
        // Verificar si la clave primaria del registre trobat coincideix amb la clau primaria esperada
        assertEquals(primaryKey, found.getFormacioBbddId());
        
        // Buscar el registro por una clave primaria que no existe
        Formacio notFound = dao.findByPK(100L);
        
        // Verificar que el registro no fue encontrado (debe ser null)
        assertNull(notFound);
    }


    @Test
    public void testUpdate() throws Exception {
        Formacio f = new Formacio(1L, "UpdatedTest1", 35, false, new Timestamp(System.currentTimeMillis()));
        dao.update(f);

        Formacio updated = dao.findByPK(1L);
        assertEquals("UpdatedTest1", updated.getNom());
        assertEquals(35, updated.getEdat());
        assertEquals(false, updated.getGenere());
    }

    @Test
    public void testDelete() throws Exception {
        Formacio deleted = dao.delete(1L);
        assertNotNull(deleted);

        Formacio retrieved = dao.findByPK(1L);
        assertNull(retrieved);
    }
    
    @Test
    public void checkAllTests() throws Exception {
        // Etapa 1: Comprova el mètode list inicial
        List<Formacio> listBeforeCreate = dao.list();
        assertNotNull(listBeforeCreate);
        int sizeBeforeCreate = listBeforeCreate.size();

        // Etapa 2: Crea una nova formació i verifica si s'ha creat correctament
        Formacio f = new Formacio(null, "Test", 25, true, new Timestamp(System.currentTimeMillis()));
        Formacio created = dao.create(f);
        assertNotNull(created);

        // Etapa 3: Comprova que la nova formació apareix a la llista
        List<Formacio> listAfterCreate = dao.list();
        assertNotNull(listAfterCreate);
        assertEquals(sizeBeforeCreate + 1, listAfterCreate.size());

        // Etapa 4: Comprova que es pot trobar la nova formació per la clau primària
        Formacio retrieved = dao.findByPK(created.getFormacioBbddId());
        assertNotNull(retrieved);
        assertEquals(f, retrieved);

        // Etapa 5: Actualitza les dades de la formació i comprova si s'ha actualitzat correctament
        Formacio updated = new Formacio(created.getFormacioBbddId(), "UpdatedTest", 30, false, new Timestamp(System.currentTimeMillis()));
        dao.update(updated);
        Formacio retrievedAfterUpdate = dao.findByPK(created.getFormacioBbddId());
        assertNotNull(retrievedAfterUpdate);
        assertEquals("UpdatedTest", retrievedAfterUpdate.getNom());

        // Etapa 6: Elimina la formació i comprova si s'ha eliminat correctament
        Formacio deleted = dao.delete(created.getFormacioBbddId());
        assertNotNull(deleted);
        Formacio retrievedAfterDelete = dao.findByPK(created.getFormacioBbddId());
        assertNull(retrievedAfterDelete);

        // Etapa 7: Comprova la llista final després de l'eliminació
        List<Formacio> listAfterDelete = dao.list();
        assertNotNull(listAfterDelete);
        assertEquals(sizeBeforeCreate, listAfterDelete.size());
    }


}
