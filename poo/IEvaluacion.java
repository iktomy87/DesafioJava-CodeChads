<<<<<<< HEAD
package poo;


public interface IEvaluacion {

    void calcularNotasFaltantes(Estudiante estudiante);
    String verificarAprobacion(Estudiante estudiante);
    String encontrarMayorVariacion(Estudiante estudiante);
    boolean verificarProgreso(Estudiante estudiante);
    int[] ordenarNotas(Estudiante estudiante);
    String obtenerNivelFinal(Estudiante estudiante);

    Estudiante obtenerEstudianteMejorPromedio(Curso curso);
    Estudiante obtenerEstudianteMasRegular(Curso curso);
    Estudiante obtenerPeorRendimientoExamen(Curso curso, int numeroExamen);
=======
package poo;


public interface IEvaluacion {

    void calcularNotasFaltantes(Estudiante estudiante);
    String verificarAprobacion(Estudiante estudiante);
    String encontrarMayorVariacion(Estudiante estudiante);
    boolean verificarProgreso(Estudiante estudiante);
    int[] ordenarNotas(Estudiante estudiante);
    String obtenerNivelFinal(Estudiante estudiante);

    Estudiante obtenerEstudianteMejorPromedio(Curso curso);
    Estudiante obtenerEstudianteMasRegular(Curso curso);
    Estudiante obtenerPeorRendimientoExamen(Curso curso, int numeroExamen);
>>>>>>> 73f63f3511ad32eb44e4b718ce3a57762f4b5e1e
}