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
import javax.swing.ComboBoxModel;
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
import javax.swing.SwingUtilities;
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
		protected JButton botonCrearGrupo;
			
		
		
		protected JTextArea textoAsignacion1;
		protected Academy datos;
		
		
		
		public VentanaAdministradorAccesoGrupos(Academy datos) {
			
			
			
			
			
			comboGrupos1 = new JComboBox<Grupo>();
			
			comboGrupos2 = new JComboBox<Grupo>();
			
			comboDocentes = new JComboBox<Docente>();
			
			comboEstudiantes = new JComboBox<Estudiante>();
			
			textoAsignacion1 = new JTextArea(10, 80);
			
			botonAsignar1 = new JButton("Asignar docente a grupo");
			botonAsignar2 = new JButton("Asignar estudiante a grupo");
			botonCrearGrupo = new JButton("Crear grupos");
			
			actualizarCombos(datos);
			
			
			
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
						Thread hilo = new Thread() {					
							public void run() {
								textoAsignacion1.append("Asignando docente " + docente.getNombre() + "al grupo " + grupo.getNombre() + "de idioma " + grupo.getIdioma()+ "el proceso se realizará en: ");
									for (int i = 0; i < 4; i++) {
										textoAsignacion1.append("    "+i);
										try { Thread.sleep(1000); } catch (InterruptedException e) {}
									}
								botonAsignar1.setEnabled(true);
								textoAsignacion1.append("  terminado!  ");
							}
						};
						botonAsignar1.setEnabled(false);
						hilo.start();
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido realizar la asignación, revisa que el grupo y docente comparten idioma", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					
					
					
					
					
				}
			});
			
			botonAsignar2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Grupo grupo = (Grupo) comboGrupos2.getSelectedItem();
					Estudiante estudiante = (Estudiante) comboEstudiantes.getSelectedItem();
					
					if (grupo != null && estudiante != null && esAptoGrupoEstudiante(grupo, estudiante) ) {
						grupo.getEstudiantes().add(estudiante);
						actualizarCombos(datos);
						JOptionPane.showMessageDialog(null, "Asignación realizada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						
						Thread hilo = new Thread() {
							
							public void run() {
								
				            	textoAsignacion1.append("Asignando estudiante " + estudiante.getNombre() + "al grupo " + grupo.getNombre() + "de idioma " + grupo.getIdioma() +"el proceso se realizará en: ");
								for (int i = 0; i < 4; i++) {
									
									final int time = i;
									
									SwingUtilities.invokeLater(new Runnable() {

							            @Override
							            public void run() {
										textoAsignacion1.append("    " + time);
										
							            }
							        }); 
									
									try { Thread.sleep(1000); } catch (InterruptedException e) {}
									
								}
								
								textoAsignacion1.append("  terminado!  ");
								botonAsignar2.setEnabled(true);
									
							}
						};
						botonAsignar2.setEnabled(false);
						hilo.start();
						
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido realizar la asignación, revisa que el grupo y estudiante comparten idioma, o que el estudiante ya es parte del grupo", "Error", JOptionPane.ERROR_MESSAGE);
						
						
					}
					
					
					
				}
			});
			
			
			botonCrearGrupo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new VentanaAdministradorCreaciónGrupos(datos);
				}
			});
			
			
			

			// FALTA AÑADIR TODO A PANELES, (campos de texto, botones, combos, lista)

			JFrame ventana = new JFrame("Ventana Administrador acceso docentes");

			JPanel panelAsignaciones1 = new JPanel();
			panelAsignaciones1.setLayout(new GridLayout(1,3));
			panelAsignaciones1.add(comboGrupos1);
			panelAsignaciones1.add(comboDocentes);
			panelAsignaciones1.add(botonAsignar1);
			
			JPanel panelAsignaciones2 = new JPanel();
			panelAsignaciones2.setLayout(new GridLayout(1,3));
			panelAsignaciones2.add(comboGrupos2);
			panelAsignaciones2.add(comboEstudiantes);
			panelAsignaciones2.add(botonAsignar2);
			
			
			
			
			JPanel panelBoton = new JPanel();
			panelBoton.add(botonCrearGrupo, BorderLayout.CENTER);
			
			JPanel panelTexto = new JPanel();
			panelTexto.add(textoAsignacion1);
			
			ventana.add(panelTexto, BorderLayout.SOUTH);
	
			ventana.add(panelBoton, BorderLayout.EAST);
			
			Border bordeAsigancion1 = BorderFactory.createTitledBorder("Asignación de docente con grupo");
			panelAsignaciones1.setBorder(bordeAsigancion1);
			Border bordeAsigancion2 = BorderFactory.createTitledBorder("Asignación de estudiantes con grupos");
			panelAsignaciones2.setBorder(bordeAsigancion2);
			Border bordeTest = BorderFactory.createTitledBorder("Últimas asignaciones...");
			panelTexto.setBorder(bordeTest);
			
			JPanel panelTodoNorte = new JPanel();
			panelTodoNorte.setLayout(new GridLayout(2,1));
			panelTodoNorte.add(panelAsignaciones1);
			panelTodoNorte.add(panelAsignaciones2);
			ventana.add(panelTodoNorte, BorderLayout.NORTH);
			
			ventana.setSize(960, 560); // tamaño grande, 960*560 y tamañAo pequeño 720*480
			ventana.setVisible(true);

		}

		
		public static boolean esAptoGrupoDocente(Grupo grupo, Docente docente) {
			
			if (grupo.getDocente().equals(null) && docente.getIdioma().equals(grupo.getIdioma())) {
				return true;
			}else {
				return false;
			}
		
		}

		public static boolean esAptoGrupoEstudiante(Grupo grupo, Estudiante estudiante) {
			for (Idioma idioma : estudiante.getIdiomas()) {
			
				if (idioma.equals(grupo.getIdioma())  && !grupo.getEstudiantes().contains(estudiante) ) {
					return true;
				}else {
					return false;
				}
			}
			return false;
			
		
		}

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
