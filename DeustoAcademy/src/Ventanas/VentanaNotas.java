package Ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DeustoAcademy.Academy;
import DeustoAcademy.Estudiante;
import DeustoAcademy.Grupo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaNotas extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloTabla;
	public JComboBox<Estudiante> comboEstudiantes;
	public JComboBox<Grupo> comboGrupo;
	protected Academy datos;



    public VentanaNotas(Academy datos) {
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
        
		comboEstudiantes = new JComboBox<Estudiante>();
		comboGrupo = new JComboBox<Grupo>();

		
		actualizarCombos(datos);

        
        
        // Crear botón para agregar una nueva fila
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
    			Estudiante estudiante = (Estudiante) comboEstudiantes.getSelectedItem();
				Grupo grupo = (Grupo) comboGrupo.getSelectedItem();

    			if (estudiante != null && grupo!= null) {
    				grupo.getEstudiantes().add(estudiante);
    				modeloTabla.addRow(new Object[]{estudiante.getNombre(), "", "", "", ""});
    				actualizarCombos(datos);

    			}
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
                VentanaNotas ventanaNotas = new VentanaNotas(null);
				ventanaNotas.setVisible(true);
            }
        });
    }
    public void actualizarCombos(Academy datos) {
		comboEstudiantes.removeAllItems();
		for (Estudiante estudiante : datos.getEstudiantes()) {
			comboEstudiantes.addItem(estudiante);
		}
		comboGrupo.removeAllItems();
		for(Grupo grupo: datos.getGrupos()) {
			comboGrupo.addItem(grupo);
		}
	}

}
