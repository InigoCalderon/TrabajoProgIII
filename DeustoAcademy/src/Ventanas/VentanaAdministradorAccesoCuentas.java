package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextArea;

import javax.swing.SwingUtilities;


import DeustoAcademy.Academy;

import DeustoAcademy.Estudiante;



public class VentanaAdministradorAccesoCuentas {
	 
	    public Academy datos;

	    protected JButton botonMostrarCuentas;
	    protected JTextArea textoCuentas;
	    
	    public VentanaAdministradorAccesoCuentas(Academy datos) {

	        
	    	botonMostrarCuentas = new JButton("Mostrar cuentas");
	    	textoCuentas = new JTextArea(10, 80);


	        botonMostrarCuentas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					// VENTANA POR TERMINAR
					// Una lista con todos los estudiantes, al seleccionar uno que se muestren sus tarifas
					// Una lista con todos los docentes, al seleccionar uno que se muestren sus suelods
				
						Thread hilo = new Thread() {					
							public void run() {
								
								for (Estudiante estudiante :datos.getEstudiantes()){
									if (datos.mapaTarifaEstudiante(datos).containsKey(estudiante)) {
										textoCuentas.append("Estudiante " + estudiante + "Tarifa mensual: " + datos.mapaTarifaEstudiante(datos).get(estudiante));
									}
									
								}
								
									for (int i = 0; i < 4; i++) {
										final int time = i;
										SwingUtilities.invokeLater(new Runnable() {

								            @Override
								            public void run() {
								            	textoCuentas.append("    "+time);
								            }
								        });
										try { Thread.sleep(1000); } catch (InterruptedException e) {}
									}
								SwingUtilities.invokeLater(new Runnable() {

							        @Override
							        public void run() {
							        	botonMostrarCuentas.setEnabled(true);
							        	textoCuentas.append("  terminado!  ");
							        }
						        });
							}
						};
						SwingUtilities.invokeLater(new Runnable() {

					        @Override
					        public void run() {
					        	botonMostrarCuentas.setEnabled(false);
					        }
				        });
						hilo.start();
					
					
					
					
					
					
				}
			});
	        

	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        JFrame ventana = new JFrame("Ventana Administrador acceso cuentas");
	        
	        JPanel panelBotones = new JPanel();
	        

	        JPanel panelTexto = new JPanel();
			panelTexto.add(textoCuentas);
			ventana.add(panelTexto, BorderLayout.SOUTH);

	        
	        JLabel panelBusqueda = new JLabel("Buscar docente: ");
	        
	        panelBotones.add(panelBusqueda);
	        
	        
	       
	        
	       
	        ventana.add(panelBotones, BorderLayout.SOUTH);
	        

	        // Color de fondo
	        Color colorFondo = new Color(88, 187, 240);
	        
	        panelBotones.setBackground(colorFondo);
	        ventana.getContentPane().setBackground(colorFondo);

	        
	        

	        ventana.setSize(960, 560);
	        ventana.setVisible(true);
	        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    }

}
