package Ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNotas extends JFrame {

    private DefaultTableModel modeloTabla;

    public VentanaNotas() {
        // Configuración de la ventana
        setTitle("Registro de Notas");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el modelo de la tabla con columnas por idioma
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Inglés");
        modeloTabla.addColumn("Euskera");
        modeloTabla.addColumn("Castellano");
        modeloTabla.addColumn("Francés");

        // Crear la tabla con el modelo
        JTable tabla = new JTable(modeloTabla);

        // Crear botón para agregar una nueva fila
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloTabla.addRow(new Object[]{"Nuevo Alumno", "", "", "", ""});
            }
        });

        // Crear botón para eliminar filas seleccionadas
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    modeloTabla.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(VentanaNotas.this,
                            "Selecciona una fila para eliminar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Crear un contenedor
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Agregar la tabla al contenedor
        container.add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Crear un panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        // Agregar el panel de botones al contenedor
        container.add(panelBotones, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaNotas().setVisible(true);
            }
        });
    }
}
