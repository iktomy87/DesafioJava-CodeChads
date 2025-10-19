public class CodeChads2 {
    public static void main(String[] args) {
        int[] examenes = new int[5];

        /* 
         * 1era PARTE 
         */
        examenes[0] = 40;
        examenes[1] = 60;
        examenes[2] = 70;

        // Cálculo de nota 4
        examenes[3] = (examenes[1] < 60) ? 100 : examenes[1];
        
        // Cálculo de nota 5
        examenes[4] = (examenes[0] + examenes[2] > 150) ? 95 : 70;

        System.out.println("=== CODE CHADS ACADEMY ===");
        System.out.println("Notas del estudiante:");
        for (int i = 0; i < examenes.length; i++) {
            System.out.println("Examen " + (i+1) + ": " + examenes[i]);
        }

        /* 
         * 2da PARTE 
         */
        
        // 1. Verificar si aprobó todas
        System.out.println("\n1. VERIFICACIÓN DE APROBACIÓN:");
        int examenesAprobados = 0;
        
        for (int i = 0; i < examenes.length; i++) {
            if (examenes[i] >= 60) {
                examenesAprobados++;
            }
        }

        if (examenesAprobados == 5) {
            System.out.println("Resultado: Aprobaste todas. ¡Backend Sensei!");
        } else if (examenesAprobados == 0) {
            System.out.println("Resultado: No aprobaste ninguna. ¡Sos un clon de frontend!");
        } else {
            System.out.println("Resultado: Algunas aprobadas. Sos un refactor en progreso.");
        }

        // 2. Prueba más inconsistente 
        System.out.println("\n2. ANÁLISIS DE CONSISTENCIA:");
        int mayorVariacion = 0;
        int variacionMayorPrimerExamen = 0;
        int variacionMayorSegundoExamen = 0;
        int contMejora = 0;

        for (int i = 0; i < examenes.length - 1; i++) {
            if (examenes[i] < examenes[i+1]) {
                int variacion = examenes[i+1] - examenes[i];
                contMejora++;
                if (variacion > mayorVariacion) {
                    mayorVariacion = variacion;
                    variacionMayorPrimerExamen = i + 1;
                    variacionMayorSegundoExamen = i + 2;
                }
            }
        }

        if (mayorVariacion > 0) {
            System.out.println("Mayor salto fue de " + mayorVariacion + " puntos entre la prueba " + variacionMayorPrimerExamen + " y la prueba " + variacionMayorSegundoExamen);
        } else {
            System.out.println("No hubo saltos positivos entre exámenes consecutivos");
        }

        // 3. Bonus por progreso 
        System.out.println("\n3. BONUS POR PROGRESO:");
        if (contMejora == examenes.length - 1) {
            System.out.println("¡Nivel PROGRESIVO! Sos un Stone Chad en crecimiento 📈");
        } 

        // 4. Mostrar notas ordenadas 
        System.out.println("\n4. NOTAS ORDENADAS:");
        int[][] notasOrdenadas = new int[5][2];
        
        for (int i = 0; i < examenes.length; i++) {
            notasOrdenadas[i][0] = examenes[i];
            notasOrdenadas[i][1] = i + 1;
        }

        boolean swapped;
        for (int i = 0; i < notasOrdenadas.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < notasOrdenadas.length - i - 1; j++) {
                if (notasOrdenadas[j][0] < notasOrdenadas[j+1][0]) {

                    int tempNota = notasOrdenadas[j][0];
                    int tempExamen = notasOrdenadas[j][1];
                    
                    notasOrdenadas[j][0] = notasOrdenadas[j+1][0];
                    notasOrdenadas[j][1] = notasOrdenadas[j+1][1];
                    
                    notasOrdenadas[j+1][0] = tempNota;
                    notasOrdenadas[j+1][1] = tempExamen;
                    
                    swapped = true;
                }
            }
            if(!swapped) break;
        }

        for (int i = 0; i < notasOrdenadas.length; i++) {
            System.out.println((i+1) + "° lugar: Examen " + notasOrdenadas[i][1] + " - " + notasOrdenadas[i][0] + " puntos");
        }

        // 5. Evaluación final por nivel
        System.out.println("\n5. EVALUACIÓN FINAL:");
        int total = 0;
        for (int i = 0; i < examenes.length; i++) {
            total += examenes[i];
        }

        String nivel;
        if (total < 250) {
            nivel = "Normie total 😢";
        } else if (total < 350) {
            nivel = "Soft Chad";
        } else if (total < 450) {
            nivel = "Chad";
        } else {
            nivel = "Stone Chad definitivo 💪";
        }
        
        System.out.println("Total acumulado: " + total + " -> " + nivel);

        // 6. (Desafío final) Ranking entre varios alumnos
        System.out.println("\n6. RANKING DE LA CLASE:");
        
        int[][] estudiantes = {
            {85, 55, 90, 100, 95},  
            {70, 65, 80, 70, 70},   
            {90, 85, 95, 70, 95},  
            {60, 55, 65, 100, 70}   
        };

        int mayorPromedio = 0;
        int estudianteMayorPromedio = 0;
        int peorNotaExamenTres = estudiantes[0][2];
        int estudiantePeorNotaTres = 0;
        int menorDesviacion = 0;
        int estudianteMenorDesviacion = 0;

        for (int i = 0; i < estudiantes.length; i++) {
            int totalEstudiante = 0;
            int desviacion = 0;
            
            for (int j = 0; j < 5; j++) {
                totalEstudiante += estudiantes[i][j];
                
                if (j < 4) {
                    if (estudiantes[i][j] < estudiantes[i][j+1]) {
                        desviacion += (estudiantes[i][j+1] - estudiantes[i][j]);
                    }
                }
                
                if (j == 2 && estudiantes[i][j] < peorNotaExamenTres) {
                    peorNotaExamenTres = estudiantes[i][j];
                    estudiantePeorNotaTres = i + 1;
                }
            }
            
            int promedio = totalEstudiante / 5;
            
            if (i == 0) {
                menorDesviacion = desviacion;
                estudianteMenorDesviacion = i + 1;
                mayorPromedio = promedio;
                estudianteMayorPromedio = i + 1;
            } else {
                if (desviacion < menorDesviacion) {
                    menorDesviacion = desviacion;
                    estudianteMenorDesviacion = i + 1;
                }
                if (promedio > mayorPromedio) {
                    mayorPromedio = promedio;
                    estudianteMayorPromedio = i + 1;
                }
            }
        }

        System.out.println("Mejor promedio: Estudiante " + estudianteMayorPromedio + " con " + mayorPromedio + " puntos");
        System.out.println("Más regular: Estudiante " + estudianteMenorDesviacion + " con variación: " + menorDesviacion + " puntos");
        System.out.println("Peor rendimiento en examen 3: Estudiante " + estudiantePeorNotaTres + " con " + peorNotaExamenTres + " puntos");
    }
}