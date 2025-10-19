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
}