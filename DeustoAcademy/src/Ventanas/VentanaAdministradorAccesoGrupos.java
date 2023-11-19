package Ventanas;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

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



	public class VentanaAdministradorAccesoGrupos {

		protected JComboBox<Grupo> comboGrupos1;
		protected JComboBox<Grupo> comboGrupos2;
		protected JComboBox<Docente> comboDocentes;
		protected JComboBox<Estudiante> comboEstudiantes;

		protected JButton botonAsignar1;
		protected JButton botonAsignar2;
		
		protected JTextArea textoAsignacion1;
		protected Academy datos;
		
		
		
		public VentanaAdministradorAccesoGrupos(Academy datos) {
			
			
			
			
			
			comboGrupos1 = new JComboBox<Grupo>();
			
			comboGrupos2 = new JComboBox<Grupo>();
			
			comboDocentes = new JComboBox<Docente>();
			
			comboEstudiantes = new JComboBox<Estudiante>();
			
			textoAsignacion1 = new JTextArea(20, 10);
			
			botonAsignar1 = new JButton("Asignar docente a grupo");
			botonAsignar2 = new JButton("Asignar estudiante a grupo");
			
			
			
			
			
			
			botonAsignar1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Grupo grupo = (Grupo) comboGrupos1.getSelectedItem();
					Docente docente = (Docente) comboDocentes.getSelectedItem();
					
					if (grupo != null && docente != null && esAptoGrupoDocente(grupo, docente)) {
						grupo.setDocente(docente);
						comboGrupos1.removeItem(grupo);
						comboDocentes.removeItem(docente);
						actualizarCombos(datos);
						JOptionPane.showMessageDialog(null, "Asignación realizada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido realizar la asignación, revisa que el grupo y docente comparten idioma", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					
					/*
					Thread hilo = new Thread() {					
						public void run() {
							textoAsignacion1.append("Asignando docente" + docente.getNombre() + "al grupo" + grupo.getNombre() + "de idioma" + grupo.getIdioma()+ "\n");
								for (int i = 0; i < 5; i++) {
									textoAsignacion1.append(i+"\n");
									try { Thread.sleep(1000); } catch (InterruptedException e) {}
								}
							botonAsignar1.setEnabled(true);
						}
					};
					botonAsignar1.setEnabled(false);
					hilo.start();
					*/
				}
			});
			
			botonAsignar2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Grupo grupo = (Grupo) comboGrupos2.getSelectedItem();
					Estudiante estudiante = (Estudiante) comboEstudiantes.getSelectedItem();
					
					if (grupo != null && estudiante != null /* && esAptoGrupoEstudiante(grupo, estudiante) */) {
						grupo.getEstudiantes().add(estudiante);
						actualizarCombos(datos);
						JOptionPane.showMessageDialog(null, "Asignación realizada", "Aviso", JOptionPane.INFORMATION_MESSAGE);

						
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido realizar la asignación, revisa que el grupo y estudiante comparten idioma", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					/*
					Thread hilo = new Thread() {					
						public void run() {
							textoAsignacion1.append("Asignando estudiante" + estudiante.getNombre() + "al grupo" + grupo.getNombre() + "de idioma" + grupo.getIdioma()+ "\n");
								for (int i = 0; i < 5; i++) {
									textoAsignacion1.append(i+"\n");
									try { Thread.sleep(1000); } catch (InterruptedException e) {}
								}
							botonAsignar1.setEnabled(true);
						}
					};
					botonAsignar1.setEnabled(false);
					hilo.start();
					*/
				}
			});
			
			
			
			
			
			

			// FALTA AÑADIR TODO A PANELES, (campos de texto, botones, combos, lista)

			JFrame ventana = new JFrame("Ventana Administrador acceso docentes");

			JPanel panelAsignaciones1 = new JPanel();
			panelAsignaciones1.setLayout(new GridLayout(1,3));
			panelAsignaciones1.add(comboGrupos1, BorderLayout.NORTH);
			panelAsignaciones1.add(comboDocentes, BorderLayout.NORTH);
			JPanel panelAsignaciones2 = new JPanel();
			panelAsignaciones2.setLayout(new GridLayout(1,3));
			panelAsignaciones2.add(comboGrupos1, BorderLayout.NORTH);
			panelAsignaciones2.add(comboDocentes, BorderLayout.NORTH);
			
			
			JPanel panelTexto = new JPanel();
			panelTexto.setLayout(new BorderLayout());
			
			
			
			Border bordeGrupos1 = BorderFactory.createTitledBorder("Grupos disponibles");
			comboGrupos1.setBorder(bordeGrupos1);
			
			
			
			Border bordeDocentes = BorderFactory.createTitledBorder("Docentes disponibles");
			comboDocentes.setBorder(bordeDocentes);

			Border bordeGrupos2 = BorderFactory.createTitledBorder("Grupos disponibles");
			comboGrupos2.setBorder(bordeGrupos2);
			
			
			
			Border bordeEstudiantes = BorderFactory.createTitledBorder("Estudiantes disponibles");
			comboEstudiantes.setBorder(bordeEstudiantes);
			
			
			
			panelTexto.add(textoAsignacion1, BorderLayout.CENTER);
			
			JPanel panelBotones = new JPanel();
			
			panelBotones.add(botonAsignar1);
			
			
			Border bordeAsignaciones1 = BorderFactory.createTitledBorder("Asignaciones de docentes y grupos");
			panelBotones.setBorder(bordeAsignaciones1);
			
			panelBotones.add(panelBotones);
			
			
			
			ventana.add(panelAsignaciones1, BorderLayout.NORTH);
			ventana.add(panelAsignaciones2, BorderLayout.CENTER);
			ventana.add(panelTexto, BorderLayout.SOUTH);

			
			ventana.setSize(960, 560); // tamaño grande, 960*560 y tamañAo pequeño 720*480
			ventana.setVisible(true);

		}

		
		public static boolean esAptoGrupoDocente(Grupo grupo, Docente docente) {
			
			if (grupo.getDocente().equals("") /* docente.getIdioma().equals(grupo.getIdioma())*/) {
				return true;
			}else {
				return false;
			}
		
		}
/*
		public static boolean esAptoGrupoEstudiante(Grupo grupo, Estudiante estudiante) {
			
			if ( estudiante.getIdioma().equals(grupo.getIdioma())) {
				return true;
			}else {
				return false;
			}
		
		}
*/
		public void actualizarCombos(Academy datos) {
			comboGrupos1.removeAllItems();
			for(Grupo grupo: datos.getGrupos()) {
				comboGrupos1.addItem(grupo);
			}
			comboGrupos2.removeAllItems();
			for (Grupo grupo: datos.getGrupos()) {
				comboGrupos2.addItem(grupo);	
			}
			comboDocentes.removeAllItems();
			for (Docente docente : datos.getDocentes()) {
				comboDocentes.addItem(docente);
			}
			comboEstudiantes.removeAllItems();
			for (Estudiante estudiante : datos.getEstudiantes()) {
				comboEstudiantes.addItem(estudiante);
			}
		}

}
