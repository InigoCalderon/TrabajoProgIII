package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import DeustoAcademy.*;

public class Login extends JFrame{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	protected ArrayList<Administrador> administrador;
	protected JButton botonIngresar;
	protected JButton botonCancelar;
	protected JButton botonCrear;
	protected JTextField textoUsuario;
	protected JPasswordField textoContraseña;
	
	
	public Login(Academy academy, Rols rol) {
		
		JFrame ventana = new JFrame("Login");
		
		JPanel panelLogin = new JPanel();
		
		JLabel etiquetaUsuario = new JLabel("Usuario:");
		textoUsuario = new JTextField(20);
		JLabel etiquetaContraseña = new JLabel("Contraseña:");
		textoContraseña = new JPasswordField(20);
		botonIngresar = new JButton("Iniciar");
		botonCancelar = new JButton("Atrás");
		botonCrear = new JButton("Registrar");	
		
		
		botonIngresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (rol == Rols.ADMINISTRADOR && autentificador(academy, rol)) {
					
					// ENTRAMOS EN EL MENU ADMIN
					
					new VentanaAdministrador(academy, rol);
					ventana.dispose();	
					
				} if (rol == Rols.ESTUDIANTE && autentificador(academy, rol)) {
					
					// ENTRAMOS EN EL MENU ESTUDIANTE
					
					new VentanaEstudiante(academy, rol);
					ventana.dispose();	
					
				} if (rol == Rols.DOCENTE && autentificador(academy, rol)) {
					
					// ENTRAMOS EN EL MENU DOCENTE
					
					new VentanaDocente(academy, rol, new Docente());
					ventana.dispose();	
					
				}
				
			}
			
		});
		
		
		botonCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!(textoUsuario.getText().equalsIgnoreCase("") || textoContraseña.getText().equalsIgnoreCase(""))) {
					
					try {
						
						if (!academy.getClaves().get(rol).keySet().contains(textoUsuario.getText())) {

							new DatosPersonales(academy, rol, textoUsuario.getText(), textoContraseña.getText());
							ventana.dispose();	
							
						} else {
							
							JOptionPane.showMessageDialog(null, "El Usuario ya existe, 	inserta otro nombre de Usuario", "Error",  JOptionPane.ERROR_MESSAGE);
						
						}
						
					} catch (Exception e2) {
						
						new DatosPersonales(academy, rol, textoUsuario.getText(), textoContraseña.getText());
						ventana.dispose();
						
					}
				
				} else {
					
					JOptionPane.showMessageDialog(null, "Debe rellenar las casillas Usuario y Contraseña", "Error",  JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			
		});
		
		
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new SelectRol(academy);
				ventana.dispose(); 
				 
			}
		});
		
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
	    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
	    panelPrincipal.setBackground(new Color(88,187,240));
	    
	    JPanel panelLabels = new JPanel();
	    panelLabels.setBackground(new Color(88,214,240));
	    
	    JPanel panelFondo1 = new JPanel();
	    panelFondo1.setBackground(new Color(88,214,240));
	    
	    JPanel panelFondo2 = new JPanel();
	    panelFondo2.setBackground(new Color(88,214,240));
	    
	    JPanel panelBotones = new JPanel();
	    panelBotones.setBackground(new Color(88,214,240));
		
		panelLabels.add(etiquetaUsuario);
		panelLabels.add(textoUsuario);
		panelLabels.add(panelFondo1);
		panelLabels.add(panelFondo2);
		panelLabels.add(etiquetaContraseña);
		panelLabels.add(textoContraseña);
		panelLabels.setLayout(new GridLayout(3,2));
		
		panelBotones.add(botonIngresar);
		panelBotones.add(botonCrear);
		panelBotones.add(botonCancelar);
		panelBotones.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		
		panelLogin.add(panelLabels, BorderLayout.NORTH);
		panelLogin.add(panelBotones, BorderLayout.SOUTH);
		panelLogin.setLayout(new GridLayout(2,1));

		panelLogin.setBackground(new Color(88,214,240));
		panelLogin.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		panelPrincipal.add(panelLogin, BorderLayout.CENTER);
	    ventana.add(panelPrincipal);
		
		ventana.pack();
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	
	
	public Login(Docente docente, Rols rol, Academy academy) {
		// TODO Auto-generated constructor stub
	}


	boolean autentificador(Academy academy, Rols rol) {
		
		if (!(textoUsuario.getText().equalsIgnoreCase("") || textoContraseña.getText().equalsIgnoreCase(""))) {
			
			try {
				
				if (academy.getClaves().get(rol).keySet().contains(textoUsuario.getText())) {
					
					if (academy.getClaves().get(rol).get(textoUsuario.getText()).equalsIgnoreCase(textoContraseña.getText())) {
						
						return true;
						
					} else {
						
						JOptionPane.showMessageDialog(null, "La Contraseña o el Usuario son incorrectos", "Error",  JOptionPane.ERROR_MESSAGE);
					}
						
				} else {
					
					JOptionPane.showMessageDialog(null, "El Usuario no existe, así que registrese para poder ingresar en la aplicación.", "Error",  JOptionPane.ERROR_MESSAGE);

				}
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "El Usuario no existe, así que registrese para poder ingresar en la aplicación.", "Error",  JOptionPane.ERROR_MESSAGE);
			}
			
		} else {
			
			JOptionPane.showMessageDialog(null, "Debe rellenar las casillas Usuario y Contraseña", "Error",  JOptionPane.ERROR_MESSAGE);
			
		}
		
		return false;
		
	}
	
}
