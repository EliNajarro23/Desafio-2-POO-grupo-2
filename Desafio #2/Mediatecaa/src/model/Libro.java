package model;

public class Libro extends Material {
    private final String autor;
    private final int numPaginas;
    private final String editorial;
    private final String isbn;
    private final int anioPublicacion;
    private final int unidadesDisponibles;

    public Libro(String codigo, String titulo, String autor, int numPaginas, 
                String editorial, String isbn, int anioPublicacion, int unidadesDisponibles) {
        super(codigo, titulo, "Libro");
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.editorial = editorial;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.unidadesDisponibles = unidadesDisponibles;
    }


    // Getters
    public String getAutor() { return autor; }
    public int getNumPaginas() { return numPaginas; }
    public String getEditorial() { return editorial; }
    public String getIsbn() { return isbn; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public int getUnidadesDisponibles() { return unidadesDisponibles; }
}