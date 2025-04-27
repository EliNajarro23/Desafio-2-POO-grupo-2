package model;

public class CDAudio extends Material {
    private final String artista;
    private final String genero;
    private final String duracion;
    private final int numCanciones;
    private final int unidadesDisponibles;

    public CDAudio(String codigo, String titulo, String artista, String genero, 
                  String duracion, int numCanciones, int unidadesDisponibles) {
        super(codigo, titulo, "CD Audio");
        this.artista = artista;
        this.genero = genero;
        this.duracion = duracion;
        this.numCanciones = numCanciones;
        this.unidadesDisponibles = unidadesDisponibles;
    }

    // Getters
    public String getArtista() { return artista; }
    public String getGenero() { return genero; }
    public String getDuracion() { return duracion; }
    public int getNumCanciones() { return numCanciones; }
    public int getUnidadesDisponibles() { return unidadesDisponibles; }
}