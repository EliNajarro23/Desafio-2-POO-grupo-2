package dao;

import model.*;
import Main.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    private Conexion conexion;

    public MaterialDAO() {
        this.conexion = new Conexion(); 
    }

    public List<Material> obtenerMateriales() {
        List<Material> lista = new ArrayList<>();

        String sql = "SELECT * FROM material";

        try (Connection con = conexion.establecerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo");

                Material material = cargarDetallesMaterial(con, codigo, titulo, tipo);
                if (material != null) {
                    lista.add(material);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Material cargarDetallesMaterial(Connection con, String codigo, String titulo, String tipo) {
        try {
            switch (tipo) {
                case "LIBRO":
                    String sqlLibro = "SELECT * FROM libro WHERE codigo = ?";
                    try (PreparedStatement ps = con.prepareStatement(sqlLibro)) {
                        ps.setString(1, codigo);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                return new Libro(
                                    codigo, titulo,
                                    rs.getString("autor"),
                                    rs.getInt("num_paginas"),
                                    rs.getString("editorial"),
                                    rs.getString("isbn"),
                                    rs.getInt("anio_publicacion"),
                                    rs.getInt("unidades_disponibles")
                                );
                            }
                        }
                    }
                    break;
                case "DVD":
                    String sqlDVD = "SELECT * FROM dvd WHERE codigo = ?";
                    try (PreparedStatement ps = con.prepareStatement(sqlDVD)) {
                        ps.setString(1, codigo);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                return new DVD(
                                    codigo, titulo,
                                    rs.getString("director"),
                                    rs.getString("duracion"),
                                    rs.getString("genero")
                                );
                            }
                        }
                    }
                    break;
                case "CD_AUDIO":
                    String sqlCD = "SELECT * FROM cd_audio WHERE codigo = ?";
                    try (PreparedStatement ps = con.prepareStatement(sqlCD)) {
                        ps.setString(1, codigo);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                return new CDAudio(
                                    codigo, titulo,
                                    rs.getString("artista"),
                                    rs.getString("genero"),
                                    rs.getString("duracion"),
                                    rs.getInt("num_canciones"),
                                    rs.getInt("unidades_disponibles")
                                );
                            }
                        }
                    }
                    break;
                case "REVISTA":
                    String sqlRevista = "SELECT * FROM revista WHERE codigo = ?";
                    try (PreparedStatement ps = con.prepareStatement(sqlRevista)) {
                        ps.setString(1, codigo);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                return new Revista(
                                    codigo, titulo,
                                    rs.getString("editorial_revista"),
                                    rs.getString("periodicidad"),
                                    rs.getString("fecha_publicacion"),
                                    rs.getInt("unidades_disponibles")
                                );
                            }
                        }
                    }
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public void agregarMaterial(Material material) throws SQLException {
    String sql;
    PreparedStatement nw;

    // Insertar en tabla "material"
    sql = "INSERT INTO material (codigo, titulo, tipo) VALUES (?, ?, ?)";
    nw = conexion.establecerConexion().prepareStatement(sql);
    nw.setString(1, material.getCodigo());
    nw.setString(2, material.getTitulo());
    nw.setString(3, material.getTipo().toUpperCase());
    nw.executeUpdate();

    // Insertar los campos correspondientes a cada tabla
    switch (material.getTipo().toUpperCase()) {
        case "DVD":
            sql = "INSERT INTO dvd (codigo, director, duracion, genero) VALUES (?, ?, ?, ?)";
            nw = conexion.establecerConexion().prepareStatement(sql);
            nw.setString(1, material.getCodigo());
            nw.setString(2, ((DVD) material).getDirector());
            nw.setString(3, ((DVD) material).getDuracion());
            nw.setString(4, ((DVD) material).getGenero());
            break;

        case "LIBRO":
            sql = "INSERT INTO libro (codigo, autor, num_paginas, editorial, isbn, anio_publicacion, unidades_disponibles) VALUES (?, ?, ?, ?, ?, ?, ?)";
            nw = conexion.establecerConexion().prepareStatement(sql);
            nw.setString(1, material.getCodigo());
            nw.setString(2, ((Libro) material).getAutor());
            nw.setInt(3, ((Libro) material).getNumPaginas());
            nw.setString(4, ((Libro) material).getEditorial());
            nw.setString(5, ((Libro) material).getIsbn());
            nw.setInt(6, ((Libro) material).getAnioPublicacion());
            nw.setInt(7, ((Libro) material).getUnidadesDisponibles());
            break;

        case "CD_AUDIO":
            sql = "INSERT INTO cd_audio (codigo, artista, genero, duracion, num_canciones, unidades_disponibles) VALUES (?, ?, ?, ?, ?, ?)";
            nw = conexion.establecerConexion().prepareStatement(sql);
            nw.setString(1, material.getCodigo());
            nw.setString(2, ((CDAudio) material).getArtista());
            nw.setString(3, ((CDAudio) material).getGenero());
            nw.setString(4, ((CDAudio) material).getDuracion());
            nw.setInt(5, ((CDAudio) material).getNumCanciones());
            nw.setInt(6, ((CDAudio) material).getUnidadesDisponibles());
            break;

        case "REVISTA":
            sql = "INSERT INTO revista (codigo, editorial_revista, periodicidad, fecha_publicacion, unidades_disponibles) VALUES (?, ?, ?, ?, ?)";
            nw = conexion.establecerConexion().prepareStatement(sql);
            nw.setString(1, material.getCodigo());
            nw.setString(2, ((Revista) material).getEditorialRevista());
            nw.setString(3, ((Revista) material).getPeriodicidad());
            nw.setString(4, ((Revista) material).getFechaPublicacion());
            nw.setInt(5, ((Revista) material).getUnidadesDisponibles());
            break;

        default:
            throw new SQLException("Tipo de material desconocido");
    }
    nw.executeUpdate();
}
        
    public Object[][] getDatosTabla() throws SQLException {
        List<Material> materiales = obtenerMateriales();
        Object[][] datos = new Object[materiales.size()][4];

        for (int i = 0; i < materiales.size(); i++) {
            Material m = materiales.get(i);
            datos[i][0] = m.getCodigo();
            datos[i][1] = m.getTitulo();
            datos[i][2] = m.getTipo();
            datos[i][3] = getDetalles(m);
        }
        return datos;
    }

    private String getDetalles(Material m) {
        if (m instanceof DVD) {
            return "Director: " + ((DVD) m).getDirector();
        } else if (m instanceof Libro) {
            return "Autor: " + ((Libro) m).getAutor();
        } else if (m instanceof CDAudio) {
            return "Artista: " + ((CDAudio) m).getArtista();
        } else if (m instanceof Revista) {
            return "Editorial: " + ((Revista) m).getEditorialRevista();
        }
        return "";
    }
}

