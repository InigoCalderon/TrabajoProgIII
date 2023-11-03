package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import DeustoAcademy.Administrador;
import DeustoAcademy.Estudiante;

public class VentanaAdiministradorAccesoEstudiantes extends JFrame{
		protected JMenuBar barraMenu;
		protected JMenu menuCastellano;
		protected JMenu menuIngles;
		protected JMenu menuEuskera;
		protected JMenu menuFrances;
		protected ArrayList<Estudiante> estudiantes;
		
		
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
			
			
			menuCastellano.addMenuListener(new MenuListener() {
				
				@Override
				public void menuSelected(MenuEvent e) {
					// TODO Auto-generated method stub
					for (Estudiante estudiante : estudiantes) {
						
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
