<<<<<<< HEAD
package poo;
import java.util.Date;

public class Examen {
    private int numeroExamen;
    private Date fechaExamen;
    private Curso curso;

    public Examen(int numeroExamen, Date fechaExamen, Curso curso) {
        this.numeroExamen = numeroExamen;
        this.fechaExamen = fechaExamen;
        this.curso = curso;
        curso.agregarExamen(this); // Asocia el examen al curso
    }

    public int getNumeroExamen() { return numeroExamen; }
    public Date getFechaExamen() { return fechaExamen; }
    public Curso getCurso() { return curso; }
    
    public void setNumeroExamen(int numeroExamen) { this.numeroExamen = numeroExamen; }
    public void setFechaExamen(Date fechaExamen) { this.fechaExamen = fechaExamen; }

}
=======
package poo;
import java.util.Date;

public class Examen {
    private int numeroExamen;
    private Date fechaExamen;
    private Curso curso;

    public Examen(int numeroExamen, Date fechaExamen, Curso curso) {
        this.numeroExamen = numeroExamen;
        this.fechaExamen = fechaExamen;
        this.curso = curso;
        curso.agregarExamen(this); // Asocia el examen al curso
    }

    public int getNumeroExamen() { return numeroExamen; }
    public Date getFechaExamen() { return fechaExamen; }
    public Curso getCurso() { return curso; }
    
    public void setNumeroExamen(int numeroExamen) { this.numeroExamen = numeroExamen; }
    public void setFechaExamen(Date fechaExamen) { this.fechaExamen = fechaExamen; }

}
>>>>>>> 73f63f3511ad32eb44e4b718ce3a57762f4b5e1e
