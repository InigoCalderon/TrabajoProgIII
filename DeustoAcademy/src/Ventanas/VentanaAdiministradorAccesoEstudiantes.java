package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import DeustoAcademy.*;


public class VentanaAdiministradorAccesoEstudiantes extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	protected JMenuBar barraMenu;
	protected JMenu menuCastellano;
	protected JMenu menuIngles;
	protected JMenu menuEuskera;
	protected JMenu menuFrances;

	protected DefaultListModel<Estudiante> modeloLista = new DefaultListModel<>();
	protected JList<Estudiante> listaEstudiante;

	protected TextField textoNombre;
	protected TextField textoApellido;
	protected TextField textoDni;
	protected TextField textoCorreo;
	protected TextField textoTelefono;
	protected TextField textoUsuario;
	protected TextField textoContraseña;

	protected JButton botonModificar;
	protected JButton botonEliminar;

	protected Academy datos;
	
	
	
	public VentanaAdiministradorAccesoEstudiantes(Academy datos) {

		modeloLista.addAll(datos.getEstudiantes());
		listaEstudiante = new JList<Estudiante>(modeloLista);
		listaEstudiante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPlantilla = new JScrollPane(listaEstudiante);
		
		barraMenu = new JMenuBar();
		menuCastellano = new JMenu("Castellano");
		menuIngles = new JMenu("Ingles");
		menuEuskera = new JMenu("Euskera");
		menuFrances = new JMenu("Frances");

		barraMenu.add(menuCastellano);
		barraMenu.add(menuIngles);
		barraMenu.add(menuEuskera);
		barraMenu.add(menuFrances);

		textoNombre = new TextField(20);
		textoApellido = new TextField(20);
		textoDni = new TextField(20);
		textoCorreo = new TextField(20);
		textoTelefono = new TextField(20);
		textoUsuario = new TextField(20);
		textoContraseña = new TextField(20);

		botonModificar = new JButton("Modificar");
		botonEliminar = new JButton("Eliminar");

		menuCastellano.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				
				for (String idioma : datos.actualizarMapaEstudiante().keySet()) {
					if( idioma.equals("Castellano")) {
						for (Estudiante estudiante : datos.actualizarMapaEstudiante().get("Castellano")) {
							modeloLista.addElement(estudiante);
						}
						
					}
				}
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub

			}

		});
		menuIngles.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				datos.actualizarMapaEstudiante();
				for (String idioma : datos.actualizarMapaEstudiante().keySet()) {
					if( idioma.equals("Ingles")) {
						for (Estudiante estudiante : datos.actualizarMapaEstudiante().get("Ingles")) {
							modeloLista.addElement(estudiante);
						}
						
					}
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub

			}
		});
		menuEuskera.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				datos.actualizarMapaEstudiante();
				for (String idioma : datos.actualizarMapaEstudiante().keySet()) {
					if( idioma.equals("Euskera")) {
						for (Estudiante estudiante : datos.actualizarMapaEstudiante().get("Euskera")) {
							modeloLista.addElement(estudiante);
						}
						
					}
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub

			}
		});
		menuFrances.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				datos.actualizarMapaEstudiante();
				for (String idioma : datos.actualizarMapaEstudiante().keySet()) {
					if( idioma.equals("Frances")) {
						for (Estudiante estudiante : datos.actualizarMapaEstudiante().get("Frances")) {
							modeloLista.addElement(estudiante);
						}
						
					}
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub

			}
		});

		listaEstudiante.addListSelectionListener(new ListSelectionListener() { // Al seleccionar un estudiante de la
																				// lista, se muestra en los textfield
																				// sus datos
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Estudiante estudiante = listaEstudiante.getSelectedValue();
				if (estudiante != null) {
					textoNombre.setText(estudiante.getNombre());
					textoApellido.setText(estudiante.getApellido());
					textoDni.setText(estudiante.getDni());
					textoTelefono.setText(estudiante.getTelefono() + "");
					textoCorreo.setText(estudiante.getCorreo());
					textoUsuario.setText(estudiante.getUsuario());
					textoContraseña.setText(estudiante.getContraseña());

				}
			}
		});
		
		botonModificar.addActionListener(new ActionListener() { // Al apretar el botón modificar y tener datos rellenos
																// en los textfield, se guardan los cambios en
																// estudiante
			@Override
			public void actionPerformed(ActionEvent e) {
				Estudiante estudiante = (Estudiante) listaEstudiante.getSelectedValue();
				if (estudiante != null) {
					actualizarEstudiante(estudiante); // Llama al método que modifica el estudiante y sus datos

					listaEstudiante.clearSelection();
				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún estudiante", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Estudiante estudiante = listaEstudiante.getSelectedValue();
				if (estudiante != null) {
					datos.getEstudiantes().remove(estudiante);

					JOptionPane.showMessageDialog(null, "Estudiante eliminado", "Ok", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún estudiante", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// FALTA AÑADIR TODO A PANELES, (campos de texto, botones, lista)

		JPanel panelMenu = new JPanel();

		JPanel panelBotones = new JPanel();
		JPanel panelModificarDatos = new JPanel();
		JPanel panelIzquierda = new JPanel();
		JFrame ventana = new JFrame("Ventana Administrador acceso estudiantes");

		panelMenu.add(barraMenu);
		panelBotones.add(botonEliminar);
		panelBotones.add(botonModificar);

		panelModificarDatos.setLayout(new GridLayout(5, 2));
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

		panelIzquierda.add(panelModificarDatos, BorderLayout.NORTH);

		ventana.add(panelIzquierda, BorderLayout.WEST);

		ventana.add(panelMenu, BorderLayout.NORTH);

		ventana.add(scrollPlantilla, BorderLayout.CENTER);
		ventana.add(panelBotones, BorderLayout.SOUTH);

		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public void actualizarEstudiante(Estudiante estudiante) {
		estudiante.setNombre(textoNombre.getText());
		estudiante.setApellido(textoApellido.getText());
		estudiante.setDni(textoDni.getText());
		estudiante.setCorreo(textoCorreo.getText());
		estudiante.setTelefono(Integer.parseInt(textoTelefono.getText()));
		estudiante.setUsuario(textoUsuario.getText());
		estudiante.setContraseña(textoContraseña.getText());
	}

	public static void main(String[] args) { // DATOS DE PRUEBA
		ArrayList<Docente> a = new ArrayList<Docente>();
		for (int i = 0; i < 5; i++) {
			a.add(new Docente("nombre" + i, "apellido" + i, "dni" + i, "correo" + i, i, "user" + i, "pass" + i));
		}
		ArrayList<Estudiante> b = new ArrayList<Estudiante>();
		for (int i = 0; i < 5; i++) {
			b.add(new Estudiante("nombre" + i, "apellido" + i, i, "correo" + i, "dni" + i, "user" + i, "pass" + i));
		}
		ArrayList<Administrador> c = new ArrayList<Administrador>();
		for (int i = 0; i < 5; i++) {
			c.add(new Administrador("nombre" + i, "apellido" + i, "dni" + i, "correo" + i, i, "user" + i, "pass" + i));
		}
		new VentanaAdiministradorAccesoEstudiantes(new Academy(c, b, a));

	}

}
