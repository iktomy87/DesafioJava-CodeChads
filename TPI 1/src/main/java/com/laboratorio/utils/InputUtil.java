package com.laboratorio.utils;

import com.laboratorio.dominio.ResultadoExperimento;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int leerInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            scanner.next(); // descarta la entrada incorrecta
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    public static String leerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static ResultadoExperimento leerResultado(String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("exito")) {
                return ResultadoExperimento.EXITO;
            } else if (input.equals("fallo")) {
                return ResultadoExperimento.FALLO;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese 'exito' o 'fallo'.");
                System.out.print(prompt);
            }
        }
    }
}
