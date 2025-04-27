package view;

import dao.MaterialDAO;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MaterialDAO materialDAO;

    public MainFrame() {
        materialDAO = new MaterialDAO();
        setTitle("Mediateca");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new MaterialTablePanel(materialDAO), BorderLayout.CENTER);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}