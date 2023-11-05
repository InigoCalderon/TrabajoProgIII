package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
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
	protected JTextField textoContraseña;
	protected HashMap<Rols, HashMap<String, String>> claves = new HashMap<>();
	protected HashMap<String, String> claves_primarias = new HashMap<>();
	protected VentanaAdministrador adminVentana;
	protected VentanaEstudiante estudianteVentana;
	protected VentanaDocente docenteVentana;
	
	public Login(Academy academy, Rols rol) {
		
		JFrame ventana = new JFrame("Login");
		
		JPanel panelLogin = new JPanel();
		JLabel etiquetaUsuario = new JLabel("Usuario:");
		textoUsuario = new JTextField(20);
		JLabel etiquetaContraseña = new JLabel("Contraseña:");
		textoContraseña = new JTextField(20);
		botonIngresar = new JButton("Ingresar");
		botonCancelar = new JButton("Cancelar");
		botonCrear = new JButton("Registrar");	

		/*/
		 * SOLO PUEDE HABER UN ÚNICO NOMBRE DE USUARIO POR ROL, PUEDE HABER EL MISMO NOMBRE DE USUSARIO EN OTROS ROLES
		 */
		
		for (Estudiante estudiante : academy.getEstudiantes()) {
			
			claves_primarias.put(estudiante.getUsuario(), estudiante.getContraseña());	
			
			}
		
		claves.put(Rols.ESTUDIANTE, claves_primarias);
		claves_primarias.clear();
		
		for (Administrador admin : academy.getAdministradores()) {
			
			claves_primarias.put(admin.getUsuario(), admin.getContraseña());		
			
			}
		
		claves.put(Rols.ADMINISTRADOR, claves_primarias);
		claves_primarias.clear();
		
		for (Docente docente : academy.getDocentes()) {
			
			claves_primarias.put(docente.getUsuario(), docente.getContraseña());	
			
			}
		
		claves.put(Rols.DOCENTE, claves_primarias);
		claves_primarias.clear();
		
		
		botonIngresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (rol == Rols.ADMINISTRADOR) {
					
					// ENTRAMOS EN EL MENU ADMIN
					
					autentificador(rol, claves);
					new VentanaAdministrador(new Administrador());
					
				} if (rol == Rols.ESTUDIANTE) {
					
					// ENTRAMOS EN EL MENU ESTUDIANTE
					
					autentificador(rol, claves);
					new VentanaEstudiante(new Estudiante());
					
				} if (rol == Rols.DOCENTE) {
					
					// ENTRAMOS EN EL MENU DOCENTE
					
					autentificador(rol, claves);
					new VentanaDocente(new Docente());
					
				}
				
			}
			
		});
		
		
		botonCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!(textoUsuario.getText().equalsIgnoreCase("") || textoContraseña.getText().equalsIgnoreCase(""))) {
					
					if (!claves.get(rol).keySet().contains(textoUsuario.getText())) {

						new DatosPersonales(academy, rol, textoUsuario.getText(), textoContraseña.getText());
						ventana.dispose();	
						
					} else {
						
						JOptionPane.showMessageDialog(null, "El Usuario ya existe, 	inserta otro nombre de Usuario", "Error",  JOptionPane.ERROR_MESSAGE);
					
					}
				
				} else {
					
					JOptionPane.showMessageDialog(null, "Debe rellenar las casillas Usuario y Contraseña", "Error",  JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			
		});
		
		
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 dispose();
				 new SelectRol(academy);
				 
			}
		});
		
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
	    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
	    panelPrincipal.setBackground(new Color(88,187,240));
	    
		
		panelLogin.add(etiquetaUsuario);
		panelLogin.add(textoUsuario);
		panelLogin.add(etiquetaContraseña);
		panelLogin.add(textoContraseña);
		
		panelLogin.add(botonIngresar);
		panelLogin.add(botonCrear);
		panelLogin.add(botonCancelar);
		panelLogin.setBackground(new Color(88,214,240));
		panelLogin.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		panelPrincipal.add(panelLogin, BorderLayout.CENTER);
	    ventana.add(panelPrincipal);
		
		ventana.pack();
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	
	
	boolean autentificador(Rols rol, HashMap<Rols, HashMap<String, String>> mapa) {
		
		if (!(textoUsuario.getText().equalsIgnoreCase("") || textoContraseña.getText().equalsIgnoreCase(""))) {
			
			if (mapa.get(rol).keySet().contains(textoUsuario.getText())) {
				
				if (mapa.get(rol).get(textoUsuario.getText()).equalsIgnoreCase(textoContraseña.getText())) {
					
					return true;
					
				} else {
					
					JOptionPane.showMessageDialog(null, "La Contraseña o el Usuario son incorrectos", "Error",  JOptionPane.ERROR_MESSAGE);
				}
					
			} else {
				
				JOptionPane.showMessageDialog(null, "El Usuario no existe, así que registrese para poder ingresar en la aplicación.", "Error",  JOptionPane.ERROR_MESSAGE);
			
			}
			
		} else {
			
			JOptionPane.showMessageDialog(null, "Debe rellenar las casillas Usuario y Contraseña", "Error",  JOptionPane.ERROR_MESSAGE);
			
		}
		
		return false;
		
	}
	
}
