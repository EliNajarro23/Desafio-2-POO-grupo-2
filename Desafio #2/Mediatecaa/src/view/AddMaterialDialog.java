package view;

import dao.MaterialDAO;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddMaterialDialog extends JDialog {
    private final MaterialDAO materialDAO;
    private final JComboBox<String> tipoCombo;
    private final JPanel formPanel;
    private final JTextField codigoField;
    private final JTextField tituloField;
    private JTextField directorField;
    private JTextField duracionField, generoField;
    private JTextField autorField, paginasField, editorialField, isbnField, anioField, unidadesLibroField;
    private JTextField artistaField, generoCDField, duracionCDField, numCancionesField, unidadesCDField;
    private JTextField editorialRevistaField, periodicidadField, fechaPublicacionField, unidadesRevistaField;

    public AddMaterialDialog(JFrame parent, MaterialDAO materialDAO) {
        super(parent, "Agregar Material", true);
        this.materialDAO = materialDAO;
        setSize(500, 400);
        setLocationRelativeTo(parent);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Se selcciona el tipo
        tipoCombo = new JComboBox<>(new String[]{"DVD", "Libro", "CD_Audio", "Revista"});
        tipoCombo.addActionListener(this::cambiarFormulario);
        mainPanel.add(tipoCombo, BorderLayout.NORTH);

        
        codigoField = new JTextField(15);
        tituloField = new JTextField(15);

        formPanel = new JPanel();
        cambiarFormulario(null);
        mainPanel.add(new JScrollPane(formPanel), BorderLayout.CENTER);

        // Botón de guardar
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(this::guardarMaterial);
        mainPanel.add(saveButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void cambiarFormulario(ActionEvent e) {
    formPanel.removeAll();
    formPanel.setLayout(new GridLayout(0, 2, 5, 5));

    
    formPanel.add(new JLabel("Código:"));
    formPanel.add(codigoField);
    formPanel.add(new JLabel("Título:"));
    formPanel.add(tituloField);

    String tipo = (String) tipoCombo.getSelectedItem();

    switch (tipo) {
        case "DVD":
            directorField = new JTextField();
            duracionField = new JTextField();
            generoField = new JTextField();

            formPanel.add(new JLabel("Director:"));
            formPanel.add(directorField);
            formPanel.add(new JLabel("Duración (HH:MM:SS):"));
            formPanel.add(duracionField);
            formPanel.add(new JLabel("Género:"));
            formPanel.add(generoField);
            break;

        case "Libro":
            autorField = new JTextField();
            paginasField = new JTextField();
            editorialField = new JTextField();
            isbnField = new JTextField();
            anioField = new JTextField();
            unidadesLibroField = new JTextField();

            formPanel.add(new JLabel("Autor:"));
            formPanel.add(autorField);
            formPanel.add(new JLabel("Páginas:"));
            formPanel.add(paginasField);
            formPanel.add(new JLabel("Editorial:"));
            formPanel.add(editorialField);
            formPanel.add(new JLabel("ISBN:"));
            formPanel.add(isbnField);
            formPanel.add(new JLabel("Año(AAAA):"));
            formPanel.add(anioField);
            formPanel.add(new JLabel("Unidades:"));
            formPanel.add(unidadesLibroField);
            break;

        case "CD_Audio":
            artistaField = new JTextField();
            generoCDField = new JTextField();
            duracionCDField = new JTextField();
            numCancionesField = new JTextField();
            unidadesCDField = new JTextField();

            formPanel.add(new JLabel("Artista:"));
            formPanel.add(artistaField);
            formPanel.add(new JLabel("Género:"));
            formPanel.add(generoCDField);
            formPanel.add(new JLabel("Duración (HH:MM:SS):"));
            formPanel.add(duracionCDField);
            formPanel.add(new JLabel("N° Canciones:"));
            formPanel.add(numCancionesField);
            formPanel.add(new JLabel("Unidades:"));
            formPanel.add(unidadesCDField);
            break;

        case "Revista":
            editorialRevistaField = new JTextField();
            periodicidadField = new JTextField();
            fechaPublicacionField = new JTextField();
            unidadesRevistaField = new JTextField();

            formPanel.add(new JLabel("Editorial:"));
            formPanel.add(editorialRevistaField);
            formPanel.add(new JLabel("Periodicidad:"));
            formPanel.add(periodicidadField);
            formPanel.add(new JLabel("Fecha Publicación (AAAA/MM/DD):"));
            formPanel.add(fechaPublicacionField);
            formPanel.add(new JLabel("Unidades:"));
            formPanel.add(unidadesRevistaField);
            break;
    }

    formPanel.revalidate();
    formPanel.repaint();
}

private void guardarMaterial(ActionEvent e) {
    try {
        String tipo = (String) tipoCombo.getSelectedItem();
        String codigo = codigoField.getText().trim();
        String titulo = tituloField.getText().trim();

        if (codigo.isEmpty() || titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Código y título no pueden estar vacíos.");
            return;
        }

        switch (tipo) {
            case "DVD":
                String director = directorField.getText().trim();
                String duracion = duracionField.getText().trim();
                String genero = generoField.getText().trim();

                if (!duracion.matches("\\d{2}:\\d{2}:\\d{2}")) {
                    JOptionPane.showMessageDialog(this, "La duración debe tener el formato HH:MM:SS.");
                    return;
                }

                materialDAO.agregarMaterial(new DVD(codigo, titulo, director, duracion, genero));
                break;

            case "Libro":
                String autor = autorField.getText().trim();
                int paginas = Integer.parseInt(paginasField.getText().trim());
                String editorial = editorialField.getText().trim();
                String isbn = isbnField.getText().trim();
                int anio = Integer.parseInt(anioField.getText().trim());
                int unidadesLibro = Integer.parseInt(unidadesLibroField.getText().trim());

                materialDAO.agregarMaterial(new Libro(codigo, titulo, autor, paginas, editorial, isbn, anio, unidadesLibro));
                break;

            case "CD_Audio":
                String artista = artistaField.getText().trim();
                String generoCD = generoCDField.getText().trim();
                String duracionCD = duracionCDField.getText().trim();
                int canciones = Integer.parseInt(numCancionesField.getText().trim());
                int unidadesCD = Integer.parseInt(unidadesCDField.getText().trim());

                materialDAO.agregarMaterial(new CDAudio(codigo, titulo, artista, generoCD, duracionCD, canciones, unidadesCD));
                break;

            case "Revista":
                String editorialRev = editorialRevistaField.getText().trim();
                String periodicidad = periodicidadField.getText().trim();
                String fechaPublicacion = fechaPublicacionField.getText().trim();
                int unidadesRev = Integer.parseInt(unidadesRevistaField.getText().trim());

                materialDAO.agregarMaterial(new Revista(codigo, titulo, editorialRev, periodicidad, fechaPublicacion, unidadesRev));
                break;
        }

        JOptionPane.showMessageDialog(this, "Material agregado con éxito.");
        dispose();

    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(this, "Asegúrate de ingresar valores numéricos válidos donde corresponda.", "Error de formato", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al guardar el material: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

}