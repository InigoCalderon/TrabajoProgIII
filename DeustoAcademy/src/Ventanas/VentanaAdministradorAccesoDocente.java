package Ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import DeustoAcademy.*;

public class VentanaAdministradorAccesoDocente {

	protected DefaultListModel<Docente> modeloLista = new DefaultListModel<>();
	protected JList<Docente> listaDocente;
	protected TextField textoNombre;
	protected TextField textoApellido;
	protected TextField textoDni;
	protected TextField textoCorreo;
	protected TextField textoTelefono;
	protected TextField textoUsuario;
	protected TextField textoContraseña;

	protected Academy datos;
/*	protected JComboBox<Docente> comboCastellano;
	protected JComboBox<Docente> comboIngles;
	protected JComboBox<Docente> comboEuskera;
	protected JComboBox<Docente> comboFrances;
*/	
	protected JTextArea textoDocente;
	protected JButton botonModificar;
	protected JButton botonEliminar;
	protected JButton botonAñadir;

	public VentanaAdministradorAccesoDocente(Academy datos) {
		
		
		listaDocente = new JList<Docente>(modeloLista);
		JScrollPane scrollPlantilla = new JScrollPane(listaDocente);
		actualizarCombos(datos);
		JLabel etiquetaDocente = new JLabel("Docentes");

/*		JLabel etiquetaCastellano = new JLabel("Castellano");
		comboCastellano = new JComboBox<Docente>();
		JLabel etiquetaIngles = new JLabel("Ingles");
		comboIngles = new JComboBox<Docente>();
		JLabel etiquetaEuskera = new JLabel("Euskera");
		comboEuskera = new JComboBox<Docente>();
		JLabel etiquetaFrances = new JLabel("Frances");
		comboFrances = new JComboBox<Docente>();
*/
		botonModificar = new JButton("Modificar");
		botonEliminar = new JButton("Eliminar");
		botonAñadir = new JButton("Añadir");
		textoNombre = new TextField(20);
		textoApellido = new TextField(20);
		textoDni = new TextField(20);
		textoCorreo = new TextField(20);
		textoTelefono = new TextField(20);
		textoUsuario = new TextField(20);
		textoContraseña = new TextField(20);
/*
		comboCastellano.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) { // Al seleccionar un docente de una de la comboCastellano, se añade
														// a la lista

				Docente seleccionado = (Docente) comboCastellano.getSelectedItem();

				modeloLista.addElement(seleccionado);
			}
		});
		
		comboIngles.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) { // Al seleccionar un docente de una de la comboIngles, se añade a
														// la lista

				Docente seleccionado = (Docente) comboIngles.getSelectedItem();

				modeloLista.addElement(seleccionado);
			}
		});
		
		comboEuskera.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) { // Al seleccionar un docente de una de la comboEuskera, se añade a
														// la lista

				Docente seleccionado = (Docente) comboEuskera.getSelectedItem();

				modeloLista.addElement(seleccionado);
			}
		});
		
		comboFrances.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) { // Al seleccionar un docente de una de la comboFrances, se añade a
														// la lista

				Docente seleccionado = (Docente) comboFrances.getSelectedItem();

				modeloLista.addElement(seleccionado);
			}
		});
*/
		listaDocente.addListSelectionListener(new ListSelectionListener() { // Al seleccionar un docente de la lista, se
																			// muestar en los textfield sus datos
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Docente docente = listaDocente.getSelectedValue();
				if (docente != null) {
					textoNombre.setText(docente.getNombre());
					textoApellido.setText(docente.getApellido());
					textoDni.setText(docente.getDni());
					textoTelefono.setText(docente.getTelefono() + "");
					textoCorreo.setText(docente.getCorreo());
					textoUsuario.setText(docente.getUsuario());
					textoContraseña.setText(docente.getContraseña());
				}
			}
		});
		
		botonModificar.addActionListener(new ActionListener() { // Al apretar el botón modificar y tener datos rellenos
																// en los textfield, se guardan los cambios en docente
			@Override
			public void actionPerformed(ActionEvent e) {
				Docente docente = listaDocente.getSelectedValue();
				if (docente != null) {
					actualizarDocente(docente);

					listaDocente.clearSelection();
					actualizarCombos(datos);
				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún docente", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		botonEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Docente docente = listaDocente.getSelectedValue();
				if (docente != null) {
					datos.getDocentes().remove(docente);
					actualizarCombos(datos);
					textoNombre.setText("");
					textoApellido.setText("");
					textoDni.setText("");
					textoTelefono.setText( "");
					textoCorreo.setText("");
					textoUsuario.setText("");
					textoContraseña.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún docente", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		botonAñadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!textoNombre.getText().isBlank() && !textoApellido.getText().isBlank() && !textoDni.getText().isBlank() && !textoCorreo.getText().isBlank() && !textoTelefono.getText().isBlank() && !textoUsuario.getText().isBlank() && !textoCorreo.getText().isBlank())  {
					Docente nuevo = new Docente();
					actualizarDocente(nuevo);
					datos.getDocentes().add(nuevo);
					actualizarCombos(datos);
					textoNombre.setText("");
					textoApellido.setText("");
					textoDni.setText("");
					textoTelefono.setText( "");
					textoCorreo.setText("");
					textoUsuario.setText("");
					textoContraseña.setText("");
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
/*		JPanel panelCombos = new JPanel();

		panelCombos.add(etiquetaCastellano);
		panelCombos.add(comboCastellano);
		panelCombos.add(etiquetaIngles);
		panelCombos.add(comboIngles);
		panelCombos.add(etiquetaEuskera);
		panelCombos.add(comboEuskera);
		panelCombos.add(etiquetaFrances);
		panelCombos.add(comboFrances);
*/
		panelBotones.add(botonAñadir);
		panelBotones.add(botonModificar);
		panelBotones.add(botonEliminar);

		panelModificarDatos.setLayout(new GridLayout(4,30));

		
		
		panelModificarDatos.add(new JLabel("Nombre: "));
		panelModificarDatos.add(textoNombre);
		panelModificarDatos.add(new JLabel("Apellido: "));
		panelModificarDatos.add(textoApellido);
		panelModificarDatos.add(new JLabel("DNI: "));
		panelModificarDatos.add(textoDni);
		panelModificarDatos.add(new JLabel("Correo: "));
		panelModificarDatos.add(textoCorreo);
		panelModificarDatos.add(new JLabel("Teléfono: "));
		panelModificarDatos.add(textoTelefono);
		panelModificarDatos.add(new JLabel("Usuario: "));
		panelModificarDatos.add(textoUsuario);
		panelModificarDatos.add(new JLabel("Contraseña: "));
		panelModificarDatos.add(textoContraseña);

		panelIzquierda.add(panelModificarDatos, BorderLayout.CENTER);

		ventana.add(panelIzquierda, BorderLayout.WEST);

/*		ventana.add(panelCombos, BorderLayout.NORTH);			*/
		ventana.add(scrollPlantilla, BorderLayout.SOUTH);
		ventana.add(panelBotones, BorderLayout.SOUTH);

		Border bordeBotones = BorderFactory.createTitledBorder("Botones");
		panelBotones.setBorder(bordeBotones);
		
		Border bordeDatos = BorderFactory.createTitledBorder("Datos");
		panelModificarDatos.setBorder(bordeDatos);
		
		
		
		ventana.add(scrollPlantilla, BorderLayout.CENTER);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamañAo pequeño 720*480
		ventana.setVisible(true);

	}

	public void actualizarDocente(Docente docente) {
		docente.setNombre(textoNombre.getText());
		docente.setApellido(textoApellido.getText());
		docente.setDni(textoDni.getText());
		docente.setCorreo(textoCorreo.getText());
		docente.setTelefono(Integer.parseInt(textoTelefono.getText()));
		docente.setUsuario(textoUsuario.getText());
		docente.setContraseña(textoContraseña.getText());
	}
	
	public void actualizarCombos(Academy datos) {
		/*ArrayList<Docente> docenteCastellano = new ArrayList<Docente>();
		ArrayList<Docente> docenteIngles = new ArrayList<Docente>();
		ArrayList<Docente> docenteEuskera = new ArrayList<Docente>();
		ArrayList<Docente> docenteFrances = new ArrayList<Docente>();
		*/
		
		modeloLista.clear();
		try {
			datos.getDocentes().sort(null);
		} catch (ClassCastException e) {}  // Ignora error de ordenación
		for (Docente docente : datos.getDocentes()) {
			modeloLista.addElement(docente);	
		}
/*		
		comboCastellano.removeAllItems();
		comboEuskera.removeAllItems();
		comboFrances.removeAllItems();
		comboIngles.removeAllItems();
		for (Grupo grupo : datos.getGrupos()) {
			for (Docente docente : datos.getDocentes()) {
				if (grupo.getIdioma().equals(Idioma.Castellano) && grupo.getDocente() == docente) {
					comboCastellano.addItem(docente);
					docenteCastellano.add(docente);
				} else if (grupo.getIdioma().equals(Idioma.Ingles) && grupo.getDocente() == docente) {
					comboIngles.addItem(docente);
					docenteIngles.add(docente);
				} else if (grupo.getIdioma().equals(Idioma.Euskera) && grupo.getDocente() == docente) {
					comboEuskera.addItem(docente);
					docenteEuskera.add(docente);
				} else if (grupo.getIdioma().equals(Idioma.Frances) && grupo.getDocente() == docente) {
					comboFrances.addItem(docente);
					docenteFrances.add(docente);
				}

			}
		}
*/	}

}
