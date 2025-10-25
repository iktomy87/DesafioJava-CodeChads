package poo;

public class Curso {
    private Estudiante[] estudiantes = new Estudiante[4];
    private int totalEstudiantes = 0;
    private String nombreCurso;

    public Curso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (totalEstudiantes < estudiantes.length) {
            estudiantes[totalEstudiantes] = estudiante;
            totalEstudiantes++;
        } else {
            System.out.println("El curso está lleno, no se puede agregar a " + estudiante.getNombres());
        }
    }

    public Estudiante[] getEstudiantes() {
        return estudiantes;
    }

    public int getTotalEstudiantes() {
        return totalEstudiantes;
    }
    
    public String getNombreCurso(){
        return this.nombreCurso;
    }

    private Examen[] examenes = new Examen[5];
    private int totalExamenes = 0;

    public void agregarExamen(Examen examen) {
        if (totalExamenes < examenes.length) {
            examenes[totalExamenes] = examen;
            totalExamenes++;
        } else {
            System.out.println("El curso ya tiene 5 exámenes, no se pueden agregar más.");
        }
    }

    public Examen[] getExamenes() {
        return examenes;
    }
}