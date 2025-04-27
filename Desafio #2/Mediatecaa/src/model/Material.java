package model;

public abstract class Material {
    protected String codigo;
    protected String titulo;
    protected String tipo;

    public Material(String codigo, String titulo, String tipo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getTipo() { return tipo; }
}