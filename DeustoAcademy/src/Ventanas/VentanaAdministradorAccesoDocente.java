package Ventanas;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import DeustoAcademy.Academy;
import DeustoAcademy.Administrador;
import DeustoAcademy.Docente;
import DeustoAcademy.Grupo;





public class VentanaAdministradorAccesoDocente {
	
	
	protected DefaultListModel<Docente> modeloLista;
	protected JList<Docente> listaDocente;
	protected TextField textoNombre;
	protected TextField textoApellido;
	protected TextField textoDni;
	protected TextField textoCorreo;
	protected TextField textoTelefono;
	protected TextField textoUsuario;
	protected TextField textoContraseña;
	
	
	protected Academy datos;
	protected JComboBox<Docente> comboCastellano;
	protected JComboBox<Docente> comboIngles;
	protected JComboBox<Docente> comboEuskera;
	protected JComboBox<Docente> comboFrances;
	protected ArrayList<Docente> docentes;
	protected ArrayList<Grupo> grupos;
	protected JTextArea textoDocente;
	protected JButton botonModificar;
	protected JButton botonEliminar;
	
	public void actualizarLista() {					// de esta manera se actualiza la lista al llamarse la función
		((Collection<Docente>) listaDocente).clear();
		try {
			datos.getDocentes().sort(null);
		} catch (ClassCastException e) {}  // Ignora error de ordenación
		for (Docente docente : datos.getDocentes()) {
			listaDocente.addElement(docente);	
		}
	}
	
	public VentanaAdministradorAccesoDocente(Administrador administrdor) {
		JLabel etiquetaDocente = new JLabel("Docentes");
		
		JLabel etiquetaCastellano = new JLabel("Castellano");
		comboCastellano = new JComboBox<Docente>();
		JLabel etiquetaIngles = new JLabel("Ingles");
		comboIngles = new JComboBox<Docente>();
		JLabel etiquetaEuskera = new JLabel("Euskera");
		comboEuskera  =new JComboBox<Docente>();
		JLabel etiquetaFrances = new JLabel("Frances");
		comboFrances = new JComboBox<Docente>();		
		botonModificar = new JButton("Modificar");
		botonEliminar = new JButton("Eliminar");
		textoNombre = new TextField(20);
		textoApellido = new TextField(20);
		textoDni = new TextField(20);
		textoCorreo = new TextField(20);
		textoTelefono = new TextField(20);
		textoUsuario = new TextField(20);
		textoContraseña = new TextField(20);
		
		// Rellenamos las combos con los docentes que ejercen cada idioma
		/*for (Grupo grupo: grupos) {
			if (grupo.getIdioma().equals("Castellano")) {
				
				comboCastellano.add(grupo.getDocente());
			} else if( grupo.getIdioma().equals("Ingles")) {
				comboIngles.add(grupo.getDocente());
			}else if (grupo.getIdioma().equals("Euskera")) {
				comboEuskera.add(grupo.getDocente());
			}else if(grupo.getIdioma().equals("Frances")) {
				comboFrances.add(grupo.getDocente());
			}
		} */
		
		
		
		
		comboCastellano.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {					// Al seleccionar un docente de una de la comboCastellano, se añade a la lista
				// TODO Auto-generated method stub
				Docente seleccionado = (Docente) comboCastellano.getSelectedItem();
				
				listaDocente.add(seleccionado);
			}
		});
		comboIngles.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {					// Al seleccionar un docente de una de la comboIngles, se añade a la lista
				// TODO Auto-generated method stub
				Docente seleccionado = (Docente) comboIngles.getSelectedItem();
				
				listaDocente.add(seleccionado);
			}
		});
		comboEuskera.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {					// Al seleccionar un docente de una de la comboEuskera, se añade a la lista
				// TODO Auto-generated method stub
				Docente seleccionado = (Docente) comboEuskera.getSelectedItem();
				
				listaDocente.add(seleccionado);
			}
		});
		comboFrances.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {					// Al seleccionar un docente de una de la comboFrances, se añade a la lista
				// TODO Auto-generated method stub
				Docente seleccionado = (Docente) comboFrances.getSelectedItem();
				
				listaDocente.add(seleccionado);
			}
		});
		
		
		listaDocente.addListSelectionListener(new ListSelectionListener() {		// Al seleccionar un docente de la lista, se muestar en los textfield sus datos
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Docente docente = listaDocente.getSelectedValue();
				if (docente != null) { 
					textoNombre.setText(docente.getNombre());
					textoApellido.setText(docente.getApellido());
					textoDni.setText(docente.getDni());
					textoTelefono.setText(docente.getTelefono()+"");
					textoCorreo.setText(docente.getCorreo());
					textoUsuario.setText(docente.getUsuario());
					textoContraseña.setText(docente.getContraseña());
					
					
					
				}
			}
		});
		botonModificar.addActionListener(new ActionListener() {		// Al apretar el botón modificar y tener datos rellenos en los textfield, se guardan los cambios en docente
			@Override
			public void actionPerformed(ActionEvent e) {
				Docente docente = listaDocente.getSelectedValue();
				if (docente != null) { 
					actualizarLista();
					listaDocente.clearSelection();
				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún docente", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Docente docente = listaDocente.getSelectedValue();
				if (docente != null) { 
					datos.getDocentes().remove(docente);
					actualizarLista();
				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún docente", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
		modeloLista = new DefaultListModel<Docente>();
		listaDocente = new JList<Docente>(modeloLista);
		JScrollPane scrollPlantilla = new JScrollPane(listaDocente);
		
		
		// FALTA AÑADIR TODO A PANELES, (campos de texto, botones, combos, lista)
		
		
		
		JFrame ventana = new JFrame("Ventana Administrador acceso estudiantes");
		
		ventana.add(scrollPlantilla, BorderLayout.CENTER);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamañAo pequeño 720*480
        ventana.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		
		new VentanaAdministradorAccesoDocente(new Administrador());
		
	}
}
