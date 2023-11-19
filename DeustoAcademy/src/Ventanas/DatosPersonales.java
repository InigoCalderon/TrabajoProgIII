package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingUtilities;

import DeustoAcademy.*;

public class DatosPersonales {

	protected JButton botonGuardar = new JButton("Guardar");
	protected JButton botonCancelar = new JButton("Cancelar");
	protected JButton botonAdd = new JButton("Añadir");
	protected JTextField textoNombre = new JTextField();
	protected JTextField textoApellido = new JTextField();
	protected JTextField textoTelefono = new JTextField();
	protected JTextField textoCorreo = new JTextField();
	protected JTextField textoDni = new JTextField();
	protected JTextField textoUsuario = new JTextField();
	protected JTextField textoContrasena = new JTextField();
	protected JLabel labelVacio = new JLabel();
	protected JLabel usuario = new JLabel("Usuario: ");
	protected JLabel contrasena = new JLabel("Contraseña: ");
	protected JLabel nombre = new JLabel("Nombre: ");
	protected JLabel apellido = new JLabel("Apellido: ");
	protected JLabel telefono = new JLabel("Teléfono: ");
	protected JLabel correo = new JLabel("Correo: ");
	protected JLabel dni = new JLabel("Dni: ");
	protected JLabel idioma = new JLabel("Idioma: ");
	protected Set<Idioma> setIdiomas = new HashSet<>();

	public DatosPersonales(Academy academy, Rols rol, String nuevo_user, String nueva_passw) {

		SwingUtilities.invokeLater(() -> {

			JFrame ventana = new JFrame("Ventana Plantilla");
			
			Idioma[] idiomas = {Idioma.Castellano, Idioma.Euskera, Idioma.Frances, Idioma.Ingles};
	        SpinnerModel spinnerModel = new SpinnerListModel(idiomas);
	        JSpinner spinner = new JSpinner(spinnerModel);

			JPanel panelPrincipal = new JPanel(new BorderLayout());
			panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
			panelPrincipal.setBackground(new Color(88, 187, 240));

			JPanel panelInterno = new JPanel();
			panelInterno.setBackground(new Color(88, 214, 240));
			panelInterno.setLayout(new GridLayout(2, 1));
			panelInterno.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

			JPanel panelJLabels = new JPanel();
			panelJLabels.setBackground(new Color(88, 214, 240));
			panelJLabels.setLayout(new GridLayout(9, 2));
			panelJLabels.setBorder(BorderFactory.createEmptyBorder(0, 210, 0, 210));

			JPanel panelBotones = new JPanel();
			panelBotones.setBackground(new Color(88, 214, 240));
			panelBotones.setLayout(new GridLayout(1, 2));
			panelBotones.setBorder(BorderFactory.createEmptyBorder(50, 210, 50, 210));

			textoUsuario.setText(nuevo_user);
			textoContrasena.setText(nueva_passw);

			panelJLabels.add(usuario);
			panelJLabels.add(textoUsuario);
			panelJLabels.add(contrasena);
			panelJLabels.add(textoContrasena);
			panelJLabels.add(nombre);
			panelJLabels.add(textoNombre);
			panelJLabels.add(apellido);
			panelJLabels.add(textoApellido);
			panelJLabels.add(telefono);
			panelJLabels.add(textoTelefono);
			panelJLabels.add(dni);
			panelJLabels.add(textoDni);
			panelJLabels.add(correo);
			panelJLabels.add(textoCorreo);
			panelJLabels.add(idioma);
			panelJLabels.add(spinner);
			panelJLabels.add(labelVacio);
			panelJLabels.add(botonAdd);
			panelBotones.add(botonGuardar);
			panelBotones.add(botonCancelar);
			panelInterno.add(panelJLabels);
			panelInterno.add(panelBotones);

			botonGuardar.setEnabled(false);
			
			if (rol == Rols.ADMINISTRADOR) {
				
				idioma.setVisible(false);
				spinner.setVisible(false);
				botonAdd.setVisible(false);
				botonGuardar.setEnabled(true);
				
			}
			
			if (rol == Rols.DOCENTE) {
				
				botonGuardar.setEnabled(true);
				botonAdd.setVisible(false);
				
			}
			
			botonAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					setIdiomas.add((Idioma) spinner.getValue());
					botonGuardar.setEnabled(true);
					
				}
			});
			
			botonGuardar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!(textoUsuario.getText().equalsIgnoreCase(""))
							
							|| !(textoContrasena.getText().equalsIgnoreCase(""))
							|| !(textoNombre.getText().equalsIgnoreCase(""))
							|| !(textoNombre.getText().equalsIgnoreCase(""))
							|| !(textoApellido.getText().equalsIgnoreCase(""))
							|| !(textoDni.getText().equalsIgnoreCase(""))
							|| !(textoCorreo.getText().equalsIgnoreCase(""))
							|| !(textoTelefono.getText().equalsIgnoreCase("")))
					
					{
						
						try {

							if (rol == Rols.ADMINISTRADOR) {

								academy.getAdministradores()
										.add((Administrador) new Administrador(
												textoNombre.getText(),
												textoApellido.getText(), 
												textoDni.getText(), 
												textoCorreo.getText(),
												Integer.parseInt(textoTelefono.getText()),
												textoUsuario.getText(),
												textoContrasena.getText()));

							} if (rol == Rols.ESTUDIANTE) {

								ArrayList<Idioma> idiomas_demandados = new ArrayList<>(setIdiomas);
								ArrayList<Idioma> idiomas_asignados = new ArrayList<>();
								Estudiante nuevo_estudiante = new Estudiante(
										textoNombre.getText(), 
										textoApellido.getText(), 
										Integer.parseInt(textoTelefono.getText()), 
										textoCorreo.getText(), textoDni.getText(), 
										textoUsuario.getText(),
										textoContrasena.getText(), 
										idiomas_demandados
								);
								
								// SI HAY GRUPOS DISPONIBLES TE METE EN ELLOS
								for (Idioma idioma : idiomas_demandados) {
									
									for (Grupo grupo : academy.getGrupos()) {
										
										if (grupo.getIdioma() == idioma && grupo.capacidad_estudiantes < 20) {
											
											// añadimos estudiante al grupo
											grupo.getEstudiantes().add(nuevo_estudiante);
											grupo.capacidad_estudiantes += 1;
											idiomas_asignados.add(idioma);
											break;
										}
										
									}
									
								}
								
								//SINO HAY GRUPOS DISPONIBLES TE CREA UNO
								idiomas_demandados.removeAll(idiomas_asignados);
								
								if (!(idiomas_demandados.size() == 0)) {
									
									ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
									lista.add(nuevo_estudiante);
									
									for (Idioma idioma : idiomas_demandados) {
										new Grupo(idioma, String.format("GRUPO[%n]-(Docente Sin Asignar)", idioma), null, lista);
									}
								}
								
								academy.getEstudiantes().add(nuevo_estudiante);

							} if (rol == Rols.DOCENTE) {
								
								boolean asignado = false;

								Docente nuevo_docente = new Docente(
										textoNombre.getText(), 
										textoApellido.getText(),
										textoDni.getText(),
										textoCorreo.getText(),
										Integer.parseInt(textoTelefono.getText()),
										textoUsuario.getText(),
										textoContrasena.getText(),
										(Idioma) spinner.getValue()
								);
								
								for (Grupo grupo : academy.getGrupos()) {
									
									if (grupo.getIdioma() == (Idioma) spinner.getValue() && grupo.getDocente() == null) {
										
										grupo.setDocente(nuevo_docente);
										grupo.setNombre(String.format("GRUPO[%n]-(%n %n)", idioma, nuevo_docente.getNombre(), nuevo_docente.getApellido()));
										asignado = true;
										break;
										
									}
									
								}
								
								if (asignado == false) {
									
									new Grupo((Idioma) spinner.getValue(), String.format("GRUPO[%n]-(%n %n)", idioma, nuevo_docente.getNombre(), nuevo_docente.getApellido()), nuevo_docente, new ArrayList<>());
									
								}
								
								academy.getDocentes().add(nuevo_docente);

							}

							// AQUÍ HABRÁ QUE ACTUALIZAR LA BASE DE DATOS CON LOS NUEVOS DATOS INSERTADOS

							academy.actualizar_datos();
							academy.actualizar_claves();
							ventana.dispose();
							new Login(academy, rol);

						} catch (Exception e2) {

							JOptionPane.showMessageDialog(null,
									"Debes rellenar todas las casillas y recordar que en la casilla de Teléfono debes escribir solo números.",
									"Error", JOptionPane.ERROR_MESSAGE);
							textoUsuario.setText(nuevo_user);
							textoContrasena.setText(nueva_passw);
							textoNombre.setText("");
							textoApellido.setText("");
							textoDni.setText("");
							textoCorreo.setText("");
							textoTelefono.setText("");

						}

					} else {

						JOptionPane.showMessageDialog(null,
								"Debes rellenar todas las casillas y recordar que en la casilla de Teléfono debes escribir solo números.",
								"Error", JOptionPane.ERROR_MESSAGE);
						textoUsuario.setText(nuevo_user);
						textoContrasena.setText(nueva_passw);
						textoNombre.setText("");
						textoApellido.setText("");
						textoDni.setText("");
						textoCorreo.setText("");
						textoTelefono.setText("");

					}

				}
			});

			botonCancelar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ventana.dispose();
					new Login(academy, rol);

				}
			});

			panelPrincipal.add(panelInterno, BorderLayout.CENTER);
			ventana.add(panelPrincipal);

			ventana.setSize(720, 480); // tamaño grande, 960*560 y tamaño pequeño 720*480
			ventana.setVisible(true);
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		});

	}

}
