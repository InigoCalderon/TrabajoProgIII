package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DeustoAcademy.Academy;
import DeustoAcademy.Docente;
import DeustoAcademy.Estudiante;
import DeustoAcademy.Grupo;
import DeustoAcademy.Idioma;

public class VentanaAdministradorCreaciónGrupos {
	
	protected JTextField textoNombre;
	protected JTextField textoIdioma;
	protected JButton botonAñadir;
	protected JButton botonModificar;
	protected JButton botonEliminar;
	
	protected DefaultListModel<Grupo> modeloLista = new DefaultListModel<>();
	protected JList<Grupo> listaGrupo;
	
	protected Academy datos;
	
	protected JComboBox<Idioma> comboIdioma;
	
	public VentanaAdministradorCreaciónGrupos(Academy datos) {
		
		
		listaGrupo = new JList<Grupo>(modeloLista);
		JScrollPane scrollPlantilla = new JScrollPane(listaGrupo);
		actualizarCombos(datos);
		
		textoNombre = new JTextField(20);
		textoIdioma = new JTextField(20);
		
		comboIdioma = new JComboBox<Idioma>(Idioma.values());
		
		botonAñadir = new JButton("Añadir");
		botonEliminar = new JButton("Eliminar");
		botonModificar = new JButton("Modificar");
		
		listaGrupo.addListSelectionListener(new ListSelectionListener() { // Al seleccionar un grupo de la lista, se
																			// muestar en los textfield sus datos
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Grupo grupo = listaGrupo.getSelectedValue();
				if (grupo != null) {
					textoNombre.setText(grupo.getNombre());
			
					comboIdioma.getModel().setSelectedItem(grupo.getIdioma());
				}
			}
		});
		
		botonModificar.addActionListener(new ActionListener() { // Al apretar el botón modificar y tener datos rellenos
																// en los textfield, se guardan los cambios en grupo
			@Override
			public void actionPerformed(ActionEvent e) {
				Grupo grupo = listaGrupo.getSelectedValue();
				if (grupo != null) {
					actualizarGrupo(grupo);

					listaGrupo.clearSelection();
					actualizarCombos(datos);
				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún grupo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		botonEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Grupo grupo = listaGrupo.getSelectedValue();
				if (grupo != null) {
					datos.getGrupos().remove(grupo);
					actualizarCombos(datos);
					textoNombre.setText("");
					textoIdioma.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún grupo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		botonAñadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!textoNombre.getText().isBlank() && comboIdioma.getSelectedItem() != null) {
					Grupo nuevo = new Grupo((Idioma) comboIdioma.getSelectedItem(), textoNombre.getText(), null, new ArrayList<Estudiante>());
			/*		actualizarGrupo(nuevo);		*/
					datos.getGrupos().add(nuevo);
					actualizarCombos(datos);
					textoNombre.setText("");
					comboIdioma.setSelectedIndex(0);
					
				}else {
					JOptionPane.showMessageDialog(null, "No has rellenado todos los campos correctamente", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		
		// FALTA AÑADIR TODO A PANELES, (campos de texto, botones, combos, lista)

		JFrame ventana = new JFrame("Ventana Administrador acceso docentes");

		JPanel panelBotones = new JPanel();
		JPanel panelModificarDatos = new JPanel();
		JPanel panelIzquierda = new JPanel();

		panelBotones.add(botonEliminar);
		panelBotones.add(botonModificar);
		panelBotones.add(botonAñadir);

		panelModificarDatos.setLayout(new GridLayout(5, 2));
		panelModificarDatos.add(new JLabel("Nombre: "));
		panelModificarDatos.add(textoNombre);
		panelModificarDatos.add(new JLabel("Idioma: "));
		panelModificarDatos.add(comboIdioma);
		

		panelIzquierda.add(panelModificarDatos, BorderLayout.NORTH);

		ventana.add(panelIzquierda, BorderLayout.WEST);


		ventana.add(scrollPlantilla, BorderLayout.SOUTH);
		ventana.add(panelBotones, BorderLayout.SOUTH);

		ventana.add(scrollPlantilla, BorderLayout.CENTER);

		
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamañAo pequeño 720*480
		ventana.setVisible(true);

	}

	public void actualizarGrupo(Grupo grupo) {
		grupo.setNombre(textoNombre.getText());
		Idioma idioma = (Idioma) comboIdioma.getSelectedItem();
		grupo.setIdioma(idioma);
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		grupo.setEstudiantes(estudiantes);
		grupo.setDocente(null);
	}
	
	public void actualizarCombos(Academy datos) {
		
		
		modeloLista.clear();
		try {
			datos.getGrupos().sort(null);
		} catch (ClassCastException e) {}  // Ignora error de ordenación
		for (Grupo grupo : datos.getGrupos()) {
			modeloLista.addElement(grupo);	
		}
	}
		
	
}
