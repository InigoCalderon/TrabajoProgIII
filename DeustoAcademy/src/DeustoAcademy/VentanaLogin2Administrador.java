package DeustoAcademy;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaLogin2Administrador extends JFrame{
	
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
	
	
	
	public VentanaLogin2Administrador() {
		
		JPanel panelLogin = new JPanel();
		JLabel etiquetaUsuario = new JLabel("Usuario:");
		textoUsuario = new JTextField(20);
		JLabel etiquetaContraseña = new JLabel("Contraseña:");
		textoContraseña = new JTextField(20);
		botonIngresar = new JButton("Ingresar");
		botonCancelar = new JButton("Cancelar");
		botonCrear = new JButton("Crear usuario");
		
		botonIngresar.addActionListener(new ActionListener() {   // Al dar al boton se podrá ingresar si los campos existen (la cuenta), en caso nulo no se podrá ingresar
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (Administrador admin : administrador) {
					if (textoUsuario.getText() == admin.getUsuario() && textoContraseña.getText() == admin.getContraseña()) {
						// Crear nueva ventana para este usuario
					} else {
						JOptionPane.showMessageDialog(null, "Usuario inexistente", "Error",  JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		botonCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		// Al darle al botonCrear se creará un administrador con el usuario y contraseña ingresado en los campos, siempre que no haya uno ya existente con esos datos
				
				for (Administrador admin : administrador) {						// Los campos como nombre, apellido y datos personales tendrán que ser creados en la ventana al Ingresar con su cuenta,
					if (textoUsuario.getText() != admin.getUsuario()) {			//  tendrá que haber un apartado para modificar/añadir datos en esa ventana nueva
						Administrador nuevo = new Administrador(getName(), getTitle(), textoUsuario.getText(), textoContraseña.getText());
						administrador.add(nuevo);
					} else {
						JOptionPane.showMessageDialog(null, "Usuario existe", "Error",  JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 dispose();
			}
		});
		
		panelLogin.add(etiquetaUsuario);
		panelLogin.add(textoUsuario);
		panelLogin.add(etiquetaContraseña);
		panelLogin.add(textoContraseña);
		
		panelLogin.add(botonIngresar);
		panelLogin.add(botonCrear);
		panelLogin.add(botonCancelar);
		
		this.add(panelLogin, BorderLayout.CENTER);
		
		this.setTitle("Ventana Login Administrador");
		this.setSize(600, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
