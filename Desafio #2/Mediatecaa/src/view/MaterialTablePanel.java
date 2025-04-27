package view;

import dao.MaterialDAO;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.border.EmptyBorder;

public class MaterialTablePanel extends JPanel {
    private final JTable tabla;
    private final DefaultTableModel modeloTabla;
    private final MaterialDAO materialDAO;
    private final TableRowSorter<DefaultTableModel> sorter;

    public MaterialTablePanel(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
        setLayout(new BorderLayout());
        setBackground(Styles.COLOR_FONDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Panel de filtros
        JPanel filtroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filtroPanel.setBackground(Styles.COLOR_FONDO);

        JComboBox<String> criterioCombo = new JComboBox<>(new String[]{"Todos", "Código", "Título", "Tipo"});
        JTextField filtroField = new JTextField(20);
        JButton filtrarButton = new JButton("Filtrar");
        Styles.configurarBoton(filtrarButton);
        filtrarButton.addActionListener(e -> aplicarFiltro(filtroField.getText(), criterioCombo.getSelectedIndex()));

        filtroPanel.add(new JLabel("Filtrar:"));
        filtroPanel.add(criterioCombo);
        filtroPanel.add(filtroField);
        filtroPanel.add(filtrarButton);

        add(filtroPanel, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Título", "Tipo", "Detalles"}, 0);
        tabla = new JTable(modeloTabla);
        Styles.configurarTabla(tabla);
        sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
        
        cargarDatosEnTabla();

        //Botón de actualización y addMaterial
        JButton actualizarButton = new JButton("Actualizar Datos");
        Styles.configurarBoton(actualizarButton);
        actualizarButton.addActionListener(e -> actualizarTabla());
        
        JButton agregarMButton = new JButton("Agregar Material");
        Styles.configurarBoton(agregarMButton);
        agregarMButton.addActionListener(e -> {
          Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) {
        AddMaterialDialog dialog = new AddMaterialDialog((JFrame) window, materialDAO);
        dialog.setVisible(true);
        } else {
        JOptionPane.showMessageDialog(this, "No se pudo abrir el diálogo: la ventana padre no es un JFrame.");
        }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Styles.COLOR_FONDO);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(agregarMButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
    }
    
    private void aplicarFiltro(String filtro, int criterio) {
        if (filtro.isEmpty() || criterio == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtro, criterio - 1));
        }
    }

    private void actualizarTabla() {
    try {
        modeloTabla.setRowCount(0);  
        Object[][] datos = materialDAO.getDatosTabla();  
        for (Object[] fila : datos) {
            modeloTabla.addRow(fila); 
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    public void cargarDatosEnTabla() {
    try {
        modeloTabla.setRowCount(0);
        Object[][] datos = materialDAO.getDatosTabla();

        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar los datos", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
}