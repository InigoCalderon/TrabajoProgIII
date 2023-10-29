package DeustoAcademy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.print.Doc;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	public Login(Academy academy, Rols rol) {
		
		JPanel panelLogin = new JPanel();
		JLabel etiquetaUsuario = new JLabel("Usuario:");
		textoUsuario = new JTextField(20);
		JLabel etiquetaContraseña = new JLabel("Contraseña:");
		textoContraseña = new JTextField(20);
		botonIngresar = new JButton("Ingresar");
		botonCancelar = new JButton("Cancelar");
		botonCrear = new JButton("Registrar");	
				
		
		botonIngresar.addActionListener(new ActionListener() {   // Al dar al boton se podrá ingresar si los campos existen (la cuenta), en caso nulo no se podrá ingresar
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!(textoUsuario.getText().equalsIgnoreCase("") || textoContraseña.getText().equalsIgnoreCase(""))) {
					
					if (academy.Claves(academy).keySet().contains(textoUsuario.getText())) {
						
						if (academy.Claves(academy).get(textoUsuario.getText()) == textoContraseña.getText()) {
							if (rol == Rols.ADMINISTRADOR) {
								// ENTRAMOS EN EL MENU ADMIN
							} if (rol == Rols.ESTUDIANTE) {
								// ENTRAMOS EN EL MENU ESTUDIANTE
							} if (rol == Rols.DOCENTE) {
								// ENTRAMOS EN EL MENU DOCENTE
							}
						} else {
							JOptionPane.showMessageDialog(null, "La Contraseña o el Usuario son incorrectos", "Error",  JOptionPane.ERROR_MESSAGE);
						}
							
					} else {
						JOptionPane.showMessageDialog(null, "El Usuario no existe", "Error",  JOptionPane.ERROR_MESSAGE);
					}
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Debe rellenar las casillas Usuario y Contraseña", "Error",  JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			
		});
		
		
		botonCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		// Al darle al botonCrear se creará un administrador con el usuario y contraseña ingresado en los campos, siempre que no haya uno ya existente con esos datos
				
				if (!(textoUsuario.getText().equalsIgnoreCase("") || textoContraseña.getText().equalsIgnoreCase(""))) {
					
					if (!academy.Claves(academy).keySet().contains(textoUsuario.getText())) {

						new DatosPersonales(academy, rol, textoUsuario.getText(), textoContraseña.getText());
							
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
	    this.add(panelPrincipal);
		
		this.setTitle("Login");
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	
}
