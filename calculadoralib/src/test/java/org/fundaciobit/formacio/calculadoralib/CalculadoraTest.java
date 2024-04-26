package org.fundaciobit.formacio.calculadoralib;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CalculadoraTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testCalculadora() {
        // Instanciam la clase Calculadora per poder utiltizar els seus mètodes
        Calculadora calculadora = new Calculadora();

        int resultatSuma = calculadora.suma(5, 2);
        int resultatResta = calculadora.resta(5, 2);

        System.out.println("El resultat de la suma és: " + resultatSuma);
        System.out.println("El resultat de la resta és: " + resultatResta);

        assertEquals(7, resultatSuma);
        assertEquals(3, resultatResta);

    }
}
