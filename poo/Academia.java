package poo;

import java.util.Date;

public class Academia {
    
    private IEvaluacion servicio;
    private Curso cursoBackend;

    public Academia(String nombreCurso) {
        this.servicio = new ServicioEvaluacion();       
        this.cursoBackend = new Curso(nombreCurso);

        new Examen(1, new Date(), this.cursoBackend);
        new Examen(2, new Date(), this.cursoBackend);
        new Examen(3, new Date(), this.cursoBackend);
        new Examen(4, new Date(), this.cursoBackend);
        new Examen(5, new Date(), this.cursoBackend);
    }


    public void ejecutarSimulacion() {
        
        // Creación de Estudiantes
        Estudiante est1 = new Estudiante("Juan", "Perez", 101);
        Estudiante est2 = new Estudiante("Ana", "Gomez", 102);
        Estudiante est3 = new Estudiante("Luis", "Vega", 103);
        Estudiante est4 = new Estudiante("Maria", "Luz", 104);

        // Inscripción 
        est1.inscribirseACurso(this.cursoBackend);
        est2.inscribirseACurso(this.cursoBackend);
        est3.inscribirseACurso(this.cursoBackend);
        est4.inscribirseACurso(this.cursoBackend);

        // Asignación de notas 
        Examen[] examenesDelCurso = this.cursoBackend.getExamenes();
        
        est1.asignarNotaExamen(1, new NotaExamen(est1, examenesDelCurso[0], 80));
        est1.asignarNotaExamen(2, new NotaExamen(est1, examenesDelCurso[1], 75));
        est1.asignarNotaExamen(3, new NotaExamen(est1, examenesDelCurso[2], 85));
        
        est2.asignarNotaExamen(1, new NotaExamen(est2, examenesDelCurso[0], 50));
        est2.asignarNotaExamen(2, new NotaExamen(est2, examenesDelCurso[1], 40)); 
        est2.asignarNotaExamen(3, new NotaExamen(est2, examenesDelCurso[2], 55));
        
        est3.asignarNotaExamen(1, new NotaExamen(est3, examenesDelCurso[0], 60));
        est3.asignarNotaExamen(2, new NotaExamen(est3, examenesDelCurso[1], 70));
        est3.asignarNotaExamen(3, new NotaExamen(est3, examenesDelCurso[2], 80));
        
        est4.asignarNotaExamen(1, new NotaExamen(est4, examenesDelCurso[0], 90));
        est4.asignarNotaExamen(2, new NotaExamen(est4, examenesDelCurso[1], 20)); 
        est4.asignarNotaExamen(3, new NotaExamen(est4, examenesDelCurso[2], 95)); 
        
        System.out.println("=== BIENVENIDO A CODECHADS ACADEMY ==="); 
        
        // Análisis Individual 
        for (Estudiante est : this.cursoBackend.getEstudiantes()) {
            if (est == null) continue;
            
            System.out.println("\n--- Análisis de: " + est.getNombres() + " " + est.getApellidos() + " ---");
            
            this.servicio.calcularNotasFaltantes(est);
            
            System.out.println(this.servicio.verificarAprobacion(est));
            System.out.println(this.servicio.encontrarMayorVariacion(est));
            
            if (this.servicio.verificarProgreso(est)) {
                System.out.println("¡Nivel PROGRESIVO! Sos un Stone Chad en crecimiento 📈");
            }
            
            int[] notasOrdenadas = this.servicio.ordenarNotas(est);
            System.out.print("Notas (Mayor a Menor): ");
            for (int nota : notasOrdenadas) {
                System.out.print(nota + " | ");
            }
            System.out.println();
            
            System.out.println("Nivel final: " + this.servicio.obtenerNivelFinal(est));
        }
        
        System.out.println("\n\n=== RANKING FINAL DEL CURSO '" + this.cursoBackend.getNombreCurso() + "' ===");
        
        Estudiante mejor = this.servicio.obtenerEstudianteMejorPromedio(this.cursoBackend);
        System.out.println("Mejor Promedio: " + mejor.getNombres() + " " + mejor.getApellidos());
        
        Estudiante regular = this.servicio.obtenerEstudianteMasRegular(this.cursoBackend);
        System.out.println("Estudiante Más Regular: " + regular.getNombres() + " " + regular.getApellidos());

        Estudiante peor = this.servicio.obtenerPeorRendimientoExamen(this.cursoBackend, 3);
        System.out.println("Peor Rendimiento (Prueba 3): " + peor.getNombres() + " " + peor.getApellidos());
    }
}