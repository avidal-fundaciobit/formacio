package org.fundaciobit.formacio.formaciobbdddao;

import java.util.List;

/**
 * @author avidal
 */

public interface IFormacioDAO {
    /**
     * Crea un objete formació a la BBDD
     * 
     * @param f objecte Formacio amb les dades a guardar
     * @return objecte Formacio amb id inicialitzat
     * @throws Exception si hi ha error a la creació de l'objecte a la BBDD
     */
    Formacio create(Formacio f) throws Exception;

    Formacio findByPK(Long id) throws Exception;

    Formacio update(Formacio f) throws Exception;

    Formacio delete(Long id) throws Exception;

    List<Formacio> list() throws Exception;
}
