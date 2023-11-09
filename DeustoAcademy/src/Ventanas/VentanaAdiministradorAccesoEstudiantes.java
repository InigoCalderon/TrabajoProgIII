package Ventanas;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

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

import DeustoAcademy.Academy;
import DeustoAcademy.Administrador;
import DeustoAcademy.Docente;
import DeustoAcademy.Estudiante;
import DeustoAcademy.Grupo;

public class VentanaAdiministradorAccesoEstudiantes extends JFrame{
		protected JMenuBar barraMenu;
		protected JMenu menuCastellano;
		protected JMenu menuIngles;
		protected JMenu menuEuskera;
		protected JMenu menuFrances;
		protected ArrayList<Estudiante> estudiantes;
		protected ArrayList<Grupo> grupos;
		
		protected DefaultListModel<Estudiante> modeloLista;
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
		
		public void actualizarLista() {					// de esta manera se actualiza la lista al llamarse la función
			((Collection<Estudiante>) listaEstudiante).clear();
			rellenarMenus();
		}
		
		public void rellenarMenus() {
			
			menuCastellano.addMenuListener(new MenuListener() {
				
				@Override
				public void menuSelected(MenuEvent e) {
					// TODO Auto-generated method stub
					for (Estudiante estudiante : estudiantes) {
						for (Grupo grupo:grupos) {
							if( grupo.getIdioma().equals("Castellano") && grupo.getEstudiantes().contains(estudiante)) {
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
					for (Estudiante estudiante : estudiantes) {
						for (Grupo grupo:grupos) {
							if( grupo.getIdioma().equals("Ingles") && grupo.getEstudiantes().contains(estudiante)) {
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
					for (Estudiante estudiante : estudiantes) {
						for (Grupo grupo:grupos) {
							if( grupo.getIdioma().equals("Euskera") && grupo.getEstudiantes().contains(estudiante)) {
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
					for (Estudiante estudiante : estudiantes) {
						for (Grupo grupo:grupos) {
							if( grupo.getIdioma().equals("Frances") && grupo.getEstudiantes().contains(estudiante)) {
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
		}
		
		public VentanaAdiministradorAccesoEstudiantes(Administrador administrdor) {
			
			
			
			barraMenu = new JMenuBar();
			menuCastellano = new JMenu("Castellano");
			menuIngles = new JMenu("Ingles");
			menuEuskera  =new JMenu("Euskera");
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
			
			
			listaEstudiante.addListSelectionListener(new ListSelectionListener() {		// Al seleccionar un estudiante de la lista, se muestra en los textfield sus datos
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					Estudiante estudiante = listaEstudiante.getSelectedValue();
					if (estudiante != null) { 
						textoNombre.setText(estudiante.getNombre());
						textoApellido.setText(estudiante.getApellido());
						textoDni.setText(estudiante.getDni());
						textoTelefono.setText(estudiante.getTelefono()+"");
						textoCorreo.setText(estudiante.getCorreo());
						textoUsuario.setText(estudiante.getUsuario());
						textoContraseña.setText(estudiante.getContraseña());
						
						
						
					}
				}
			});
			botonModificar.addActionListener(new ActionListener() {		// Al apretar el botón modificar y tener datos rellenos en los textfield, se guardan los cambios en estudiante
				@Override
				public void actionPerformed(ActionEvent e) {
					Estudiante estudiante = (Estudiante) listaEstudiante.getSelectedValue();
					if (estudiante != null) { 
						actualizarLista();
						estudiante.setNombre(textoNombre.getText());
						estudiante.setApellido(textoApellido.getText());
						estudiante.setDni(textoDni.getText());
						estudiante.setCorreo(textoCorreo.getText());
						estudiante.setTelefono(Integer.parseInt(textoTelefono.getText()));
						estudiante.setUsuario(textoUsuario.getText());
						estudiante.setContraseña(textoContraseña.getText());
						listaEstudiante.clearSelection();
					} else {
						JOptionPane.showMessageDialog(null, "No has seleccionado ningún estudiante", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			botonEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Estudiante estudiante = listaEstudiante.getSelectedValue();
					if (estudiante != null) { 
						datos.getEstudiantes().remove(estudiante);
						actualizarLista();
					} else {
						JOptionPane.showMessageDialog(null, "No has seleccionado ningún estudiante", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			modeloLista = new DefaultListModel<Estudiante>();
			listaEstudiante = new JList<Estudiante>(modeloLista);
			JScrollPane scrollPlantilla = new JScrollPane(listaEstudiante);
			
			// FALTA AÑADIR TODO A PANELES, (campos de texto, botones, lista)
			
			JLabel etiquetaTexto = new JLabel("Estudiantes por idiomas matriculados");
			JPanel panelTexto = new JPanel();
			JPanel panelMenu = new JPanel();
			JFrame ventana = new JFrame("Ventana Administrador acceso estudiantes");
			panelTexto.add(etiquetaTexto);
			panelMenu.add(barraMenu);
			ventana.add(panelMenu, BorderLayout.CENTER);
			ventana.add(etiquetaTexto,BorderLayout.NORTH);
			ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
	        ventana.setVisible(true);
			ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		}
		
	public static void main(String[] args) {
			
			new VentanaAdiministradorAccesoEstudiantes(new Administrador());
			
		}
	

}
