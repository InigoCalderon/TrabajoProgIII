package DeustoAcademy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class VentanaLogin1 extends JFrame{
	protected Estudiante estudiante;
	protected Docente docente;
	protected Administrador administrador;
	protected Rol rol;
	protected JComboBox<Rol> comboRol;
	protected JButton botonIngresar;
	
	
	
	public VentanaLogin1(Rol rol) throws HeadlessException {
		super();
		
		
		
		JPanel panelLogin1 = new JPanel();
		JLabel etiquetaRol = new JLabel("Escoge tu rol:");
		botonIngresar = new JButton("Ingresar");
		comboRol = new JComboBox<Rol>();
		
		botonIngresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {					// Al seleccionar un rol de la combo box y apretar el botonIngresar,se creará una ventana "VentanaLogin2" dónde se meterá el usuario & contraseña 
				// TODO Auto-generated method stub							// Dependiendo del "rol" seleccionado de la comboBox, la ventana será para Estudiantes / Docentes u profesores
				Rol seleccionado = (Rol) comboRol.getSelectedItem();
				if (seleccionado.equals(estudiante)) {
					VentanaLogin2Estudiante nueva = new VentanaLogin2Estudiante(seleccionado);
				}else if(seleccionado.equals(docente)){
					VentanaLogin2Docente nueva = new VentanaLogin2Docente(seleccionado);
					
				}else if( seleccionado.equals(administrador) ) {
					VentanaLogin2Administrador nueva = new VentanaLogin2Administrador(seleccionado);
				}
				
			}
		});
		
		
		
		
		panelLogin1.add(etiquetaRol);
		panelLogin1.add(comboRol);
		panelLogin1.add(botonIngresar);
		this.add(panelLogin1, BorderLayout.CENTER);
		
		this.setTitle("Ventana Login");
		this.setSize(600,800);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	

}
