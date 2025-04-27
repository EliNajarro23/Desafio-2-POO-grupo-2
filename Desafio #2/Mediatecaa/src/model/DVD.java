package model;

public class DVD extends Material {
    private final String director;
    private final String duracion;  // Formato: "HH:MM:SS" (ej: "02:30:00")
    private final String genero;

    // Constructor completo
    public DVD(String codigo, String titulo, String director, String duracion, String genero) {
        super(codigo, titulo, "DVD"); // Tipo fijo como "DVD"
        this.director = director;
        this.duracion = duracion;
        this.genero = genero;
    }


    // Getters (métodos de acceso)
    public String getDirector() {
        return director;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    // Método toString() para representación en texto
    @Override
    public String toString() {
        return "DVD [Código: " + getCodigo() + 
               ", Título: " + getTitulo() + 
               ", Director: " + director + 
               ", Duración: " + duracion + 
               ", Género: " + genero + "]";
    }
}