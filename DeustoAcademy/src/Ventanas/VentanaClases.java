package Ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaClases extends JFrame{
	
	protected JButton botonSalir;

	
	public VentanaClases() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    static void createAndShowGUI() {
        JFrame frame = new JFrame("Clases");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 940);

        JPanel panel = new JPanel();

        // Crear la primera JComboBox
        JComboBox<String> comboBox1 = new JComboBox<>();
        comboBox1.addItem("Clase 1");
        comboBox1.addItem("Clase 2");

        // Crear la segunda JComboBox (se llenará dinámicamente)
        JComboBox<String> comboBox2 = new JComboBox<>();

        // Etiqueta para mostrar la selección actual
        JLabel label = new JLabel("Seleccion actual: ");

        // Agregar ActionListener para la primera JComboBox
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la selección de la primera JComboBox
                String seleccion = comboBox1.getSelectedItem().toString();

                // Llenar la segunda JComboBox dinámicamente
                fillSecondComboBox(comboBox2, seleccion);

                // Actualizar la etiqueta
                label.setText("Seleccion actual: " + seleccion);
            }
        });

        // Agregar componentes al panel
        panel.add(comboBox1);
        panel.add(comboBox2);
        panel.add(label);

        // Agregar panel al JFrame
        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }

    // Método para llenar la segunda JComboBox dinámicamente
    private static void fillSecondComboBox(JComboBox<String> comboBox, String category) {
        // Limpiar la JComboBox antes de agregar nuevos elementos
        comboBox.removeAllItems();

        // Agregar elementos según la categoría seleccionada
        if (category.equals("Clase 1")) {
            comboBox.addItem("Meter aqui los datos de estudiantes");
        } else if (category.equals("Clase 2")) {
            comboBox.addItem("Meter aqui los datos de estudiantes");
            
        }
    }
}

