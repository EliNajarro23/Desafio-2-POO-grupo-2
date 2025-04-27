package model;

public class Revista extends Material {
    private final String editorialRevista;
    private final String periodicidad;
    private final String fechaPublicacion;
    private final int unidadesDisponibles;

    public Revista(String codigo, String titulo, String editorialRevista, 
                  String periodicidad, String fechaPublicacion, int unidadesDisponibles) {
        super(codigo, titulo, "Revista");
        this.editorialRevista = editorialRevista;
        this.periodicidad = periodicidad;
        this.fechaPublicacion = fechaPublicacion;
        this.unidadesDisponibles = unidadesDisponibles;
    }

    // Getters
    public String getEditorialRevista() { return editorialRevista; }
    public String getPeriodicidad() { return periodicidad; }
    public String getFechaPublicacion() { return fechaPublicacion; }
    public int getUnidadesDisponibles() { return unidadesDisponibles; }
}