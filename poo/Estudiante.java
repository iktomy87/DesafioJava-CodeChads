<<<<<<< HEAD
package poo;

public class Estudiante {
    private String nombres;
    private String apellidos;
    private int legajo;

    private Curso curso; 
    private NotaExamen[] examenes = new NotaExamen[5];

    public Estudiante(String nombres, String apellidos, int legajo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.legajo = legajo;
    }


    public void asignarNotaExamen(int numeroExamen, NotaExamen notaExamen) {
        if (this.curso == null) {
            System.out.println("El estudiante " + this.getNombres() + " no está inscrito en ningún curso.");
            return;
        }
        if (notaExamen.getExamen() != null && notaExamen.getExamen().getCurso() != this.curso) {
            System.out.println("El estudiante " + this.getNombres() + " no puede rendir un examen de un curso en el que no está inscrito.");
            return;
        }
        this.examenes[numeroExamen - 1] = notaExamen; 
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getLegajo() {
        return legajo;
    }

    public void inscribirseACurso(Curso curso) {
        this.curso = curso;
        curso.agregarEstudiante(this);
    }

    public NotaExamen getNotaExamen(int numeroExamen) {
        return examenes[numeroExamen - 1];
    }

    public NotaExamen[] getTodosLosExamenes() {
        return examenes;
    }

    public Curso getCurso() {
        return curso;
    }
=======
package poo;

public class Estudiante {
    private String nombres;
    private String apellidos;
    private int legajo;

    private Curso curso; 
    private NotaExamen[] examenes = new NotaExamen[5];

    public Estudiante(String nombres, String apellidos, int legajo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.legajo = legajo;
    }


    public void asignarNotaExamen(int numeroExamen, NotaExamen notaExamen) {
        if (this.curso == null) {
            System.out.println("El estudiante " + this.getNombres() + " no está inscrito en ningún curso.");
            return;
        }
        if (notaExamen.getExamen() != null && notaExamen.getExamen().getCurso() != this.curso) {
            System.out.println("El estudiante " + this.getNombres() + " no puede rendir un examen de un curso en el que no está inscrito.");
            return;
        }
        this.examenes[numeroExamen - 1] = notaExamen; 
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getLegajo() {
        return legajo;
    }

    public void inscribirseACurso(Curso curso) {
        this.curso = curso;
        curso.agregarEstudiante(this);
    }

    public NotaExamen getNotaExamen(int numeroExamen) {
        return examenes[numeroExamen - 1];
    }

    public NotaExamen[] getTodosLosExamenes() {
        return examenes;
    }

    public Curso getCurso() {
        return curso;
    }
>>>>>>> 73f63f3511ad32eb44e4b718ce3a57762f4b5e1e
}