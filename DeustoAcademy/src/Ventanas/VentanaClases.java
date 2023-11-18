package Ventanas;

import javax.swing.*;
import DeustoAcademy.Academy;
import DeustoAcademy.Docente;
import DeustoAcademy.Rols;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaClases extends JFrame {

    protected static final Rols rol = null;
    protected static final Academy academy = null;
    protected JButton botonSalir;
    protected JButton botonEntrar;

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

        // Botón para abrir una nueva ventana
        JButton botonEntrar = new JButton("Entrar");
        botonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la selección de la primera JComboBox
                String seleccion = comboBox1.getSelectedItem().toString();

                // Llenar la segunda JComboBox dinámicamente
                fillSecondComboBox(comboBox2, seleccion);

                // Lógica condicional para abrir diferentes ventanas según la selección
                if (seleccion.equals("Clase 1")) {
                    // Abre una ventana específica para Clase 1
                    new VentanaClase1();
                } else if (seleccion.equals("Clase 2")) {
                    // Abre una ventana específica para Clase 2
                    new VentanaClase2();
                }
            }
        });

        JButton botonSalir = new JButton("Salir");
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaDocente(academy, rol, new Docente());
            }
        });

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
        panel.add(botonEntrar);
        panel.add(botonSalir);
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
            comboBox.addItem("Datos de estudiantes para Clase 1");
        } else if (category.equals("Aqui apareceran los datos de la primera clase")) {
            comboBox.addItem("Aqui apareceran los datos de la segunda clase");
        }
    }
}
