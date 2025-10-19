package poo;

public class ServicioEvaluacion implements IEvaluacion {

    @Override
    public void calcularNotasFaltantes(Estudiante estudiante) {
        NotaExamen nota1 = estudiante.getNotaExamen(1);
        NotaExamen nota2 = estudiante.getNotaExamen(2);
        NotaExamen nota3 = estudiante.getNotaExamen(3);

        if (estudiante.getCurso() == null) {
            System.out.println("El estudiante no está inscrito en un curso, no se pueden calcular las notas faltantes.");
            return;
        }

        Examen[] examenes = estudiante.getCurso().getExamenes();
        Examen examen4 = null;
        Examen examen5 = null;

        for (Examen examen : examenes) {
            if (examen != null && examen.getNumeroExamen() == 4) {
                examen4 = examen;
            } else if (examen != null && examen.getNumeroExamen() == 5) {
                examen5 = examen;
            }
        }

        if (examen4 == null || examen5 == null) {
            System.out.println("No se encontraron los exámenes 4 o 5 en el curso.");
            return;
        }

        int valorNota4 = (nota2.getNota() < 60) ? 100 : nota2.getNota();
        estudiante.asignarNotaExamen(4, new NotaExamen(estudiante, examen4, valorNota4));

        int valorNota5 = (nota1.getNota() + nota3.getNota() > 150) ? 95 : 70;
        estudiante.asignarNotaExamen(5, new NotaExamen(estudiante, examen5, valorNota5));
    }

    @Override
    public String verificarAprobacion(Estudiante estudiante) {
        int aprobados = 0;
        for (NotaExamen notaExamen : estudiante.getTodosLosExamenes()) {
            if (notaExamen.getNota() >= 60) {
                aprobados++;
            }
        }
        if (aprobados == 5) return "Resultado: Aprobaste todas. ¡Backend Sensei!";
        if (aprobados == 0) return "Resultado: No aprobaste ninguna. ¡Sos un clon de frontend!"; 
        return "Resultado: Algunas aprobadas. Sos un refactor en progreso."; 
    }

    @Override
    public String encontrarMayorVariacion(Estudiante estudiante) {
        int mayorVariacion = 0;
        int variacionMayorPrimerExamen = 0;
        int variacionMayorSegundoExamen = 0;
        int contMejora = 0;
        NotaExamen[] examenes = estudiante.getTodosLosExamenes();

        for (int i = 0; i < examenes.length - 1; i++) {
            if (examenes[i].getNota() < examenes[i+1].getNota()) {
                int variacion = examenes[i+1].getNota() - examenes[i].getNota();
                contMejora++;
                if (variacion > mayorVariacion) {
                    mayorVariacion = variacion;
                    variacionMayorPrimerExamen = i + 1;
                    variacionMayorSegundoExamen = i + 2;
                }
            }
        }

        if (mayorVariacion > 0) {
            return "Mayor salto fue de " + mayorVariacion + " puntos entre la prueba " + variacionMayorPrimerExamen + " y la prueba " + variacionMayorSegundoExamen;
        } else {
            return "No hubo saltos positivos entre exámenes consecutivos";
        }
    }

    @Override
    public boolean verificarProgreso(Estudiante estudiante) {
        NotaExamen[] examenes = estudiante.getTodosLosExamenes();
        for (int i = 0; i < examenes.length - 1; i++) {
            if (examenes[i+1].getNota() <= examenes[i].getNota()) {
                return false; 
            }
        }
        return true;
    }

    @Override
    public int[] ordenarNotas(Estudiante estudiante) {
        int[] notasOrdenadas = new int[5];
        NotaExamen[] examenes = estudiante.getTodosLosExamenes();
        
        for (int i = 0; i < examenes.length; i++) {
            notasOrdenadas[i] = examenes[i].getNota();
        }

        boolean swapped;
        for (int i = 0; i < notasOrdenadas.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < notasOrdenadas.length - i - 1; j++) {
                if (notasOrdenadas[j] < notasOrdenadas[j+1]) {
                    int tempNota = notasOrdenadas[j];
                    notasOrdenadas[j] = notasOrdenadas[j+1];
                    notasOrdenadas[j+1] = tempNota;
                    swapped = true;
                }
            }
            if(!swapped) break;
        }
        return notasOrdenadas;
    }

    @Override
    public String obtenerNivelFinal(Estudiante estudiante) {
        int total = this.calcularTotalNotas(estudiante);

        if (total >= 450) return "Stone Chad definitivo"; 
        if (total >= 350) return "Chad"; 
        if (total >= 250) return "Soft Chad"; 
        return "Normie total"; 
    }


    private int calcularTotalNotas(Estudiante estudiante) {
        int total = 0;
        NotaExamen[] examenes = estudiante.getTodosLosExamenes();
        for (int i = 0; i < 5; i++) {
            total = total + examenes[i].getNota();
        }
        return total;
    }

    private double calcularPromedio(Estudiante estudiante) {
        return calcularTotalNotas(estudiante) / 5.0;
    }


    @Override
    public Estudiante obtenerEstudianteMejorPromedio(Curso curso) {
        Estudiante estudianteMayorPromedio  = null;
        double mayorPromedio = 0;

        for (Estudiante est : curso.getEstudiantes()) {
            if (est == null) continue;
            double promedioEstudiante = this.calcularPromedio(est);
            if (promedioEstudiante > mayorPromedio) {
                mayorPromedio = promedioEstudiante;
                estudianteMayorPromedio = est;
            }
        }
        return estudianteMayorPromedio;
    }

    @Override
    public Estudiante obtenerPeorRendimientoExamen(Curso curso, int numeroExamen) {

        Estudiante peorEstudiante = null;
        int notaMasBaja = 101; 
        
        for (Estudiante est : curso.getEstudiantes()) {
            if (est == null) continue;
            
            int nota = est.getNotaExamen(numeroExamen).getNota();
            if (nota < notaMasBaja) {
                notaMasBaja = nota;
                peorEstudiante = est;
            }
        }
        return peorEstudiante;
    }

    @Override
    public Estudiante obtenerEstudianteMasRegular(Curso curso) {
  
        Estudiante estudianteMasRegular = null;
        int menorSumaDeMejoras = Integer.MAX_VALUE; 

        for (Estudiante est : curso.getEstudiantes()) {
            if (est == null) continue;
             
             int sumaDeMejorasActual = 0;
             NotaExamen[] examenes = est.getTodosLosExamenes();
             
             for (int j = 0; j < examenes.length - 1; j++) {
                int notaActual = examenes[j].getNota();
                int notaSiguiente = examenes[j+1].getNota();
                
                if (notaActual < notaSiguiente) {
                    sumaDeMejorasActual += (notaSiguiente - notaActual);
                }
             }
             if (sumaDeMejorasActual < menorSumaDeMejoras) {
                menorSumaDeMejoras = sumaDeMejorasActual;
                estudianteMasRegular = est;
             }
        }
        return estudianteMasRegular;
    }
}