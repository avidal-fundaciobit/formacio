package org.fundaciobit.formacio;

import java.util.Scanner;
import org.fundaciobit.formacio.calculadoralib.Calculadora;

public class CalculadoraConsole {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        Scanner scanner = new Scanner(System.in);
        int opcio;

        //Missatge de benvinguda. Podem fer o suma o resta.
        System.out.println("Benvingut a calculadora. Si us plau, selecciona una opció:");
        System.out.println("1. Suma");
        System.out.println("2. Resta");

        opcio = scanner.nextInt();

        //L'usuari ha de seleccionar obligatoriament una opció vàlida.
        while (opcio < 1 || opcio > 2) {
            System.out.println("Opció no vàlida. Si us plau, selecciona una opció vàlida:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            opcio = scanner.nextInt();
        }

        if (opcio == 1) {
            System.out.println("Ingressa el primer número:");
            int num1 = scanner.nextInt();
            System.out.println("Ingressa el segon número:");
            int num2 = scanner.nextInt();
            int resultat = calculadora.suma(num1, num2);
            System.out.println("El resultat de la suma és: " + resultat);
        } else {
            System.out.println("Ingressa el primer número:");
            int num1 = scanner.nextInt();
            System.out.println("Ingressa el segon número:");
            int num2 = scanner.nextInt();
            int resultat = calculadora.resta(num1, num2);
            System.out.println("El resultat de la resta és: " + resultat);
        }
        
        scanner.close();

        System.out.println("Gràcies per utilitzar la calculadora");
    }
}
