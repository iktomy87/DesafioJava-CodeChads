package poo;

public class NotaExamen {
    private Estudiante estudiante;
    private Examen examen;
    private int nota;

    public NotaExamen(Estudiante estudiante, Examen examen, int nota) {
        this.estudiante = estudiante;
        this.examen = examen;
        this.nota = nota;
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Examen getExamen() { return examen; }
    public int getNota() { return nota; }
    
    public void setNota(int nota) { this.nota = nota; }
}