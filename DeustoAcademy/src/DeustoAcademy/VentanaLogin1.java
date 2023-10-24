package DeustoAcademy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class VentanaLogin1 extends JFrame{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	protected JComboBox<Rols> comboRol;
	protected JButton botonIngresar;
	
	
	public VentanaLogin1() throws HeadlessException {
		super();
		JPanel panelLogin1 = new JPanel();
		JLabel etiquetaRol = new JLabel("Escoge tu rol:");
		botonIngresar = new JButton("Ingresar");
		comboRol = new JComboBox<Rols>();
		comboRol.addItem(Rols.ESTUDIANTE);
		comboRol.addItem(Rols.DOCENTE);
		comboRol.addItem(Rols.ADMINISTRADOR);
		comboRol.setSelectedItem(null);
		
		botonIngresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {					 
			
				// Al seleccionar un rol de la combo box y apretar el botonIngresar,se creará una ventana "VentanaLogin2" dónde se meterá el usuario & contraseña
				// Dependiendo del "rol" seleccionado de la comboBox, la ventana será para Estudiantes / Docentes u profesores
				
				Rols seleccionado = (Rols) comboRol.getSelectedItem();
				if (seleccionado == Rols.ESTUDIANTE) {
					VentanaLogin2Estudiante nueva = new VentanaLogin2Estudiante();
				}else if(seleccionado == Rols.DOCENTE){
					VentanaLogin2Docente nueva = new VentanaLogin2Docente();
					
				}else if( seleccionado == Rols.ADMINISTRADOR) {
					VentanaLogin2Administrador nueva = new VentanaLogin2Administrador();
				}
				
			}
		});
		
		
		
		
		panelLogin1.add(etiquetaRol);
		panelLogin1.add(comboRol);
		panelLogin1.add(botonIngresar);
		this.add(panelLogin1, BorderLayout.CENTER);
				
		this.setTitle("Ventana Login");
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(this.MAXIMIZED_BOTH);;
	}
	
	

}
