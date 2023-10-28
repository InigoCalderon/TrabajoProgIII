package DeustoAcademy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaLogin2Docente extends JFrame{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	protected ArrayList<Docente> docentes;
	protected JButton botonIngresar;
	protected JButton botonCancelar;
	protected JButton botonCrear;
	protected JTextField textoUsuario;
	protected JTextField textoContraseña;
	public VentanaLogin2Docente() {
		
	
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
					
					for (Docente docente : docentes) {
						if (textoUsuario.getText() == docente.getUsuario() && textoContraseña.getText() == docente.getContraseña()) {
							// Crear nueva ventana para este usuario
							VentanaDocente nueva = new VentanaDocente();
						} else {
							JOptionPane.showMessageDialog(null, "Usuario inexistente", "Error",  JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			botonCrear.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {		// Al darle al botonCrear se creará un administrador con el usuario y contraseña ingresado en los campos, siempre que no haya uno ya existente con esos datos
					
					for (Docente docente : docentes) {								// Los campos como nombre, apellido y datos personales tendrán que ser creados en la ventana al Ingresar con su cuenta,
						if (textoUsuario.getText() != docente.getUsuario()) {		//  tendrá que haber un apartado para modificar/añadir datos en esa ventana nueva
							Docente nuevo = new Docente(null, null, null, null, null, textoUsuario.getText(), textoContraseña.getText());
							docentes.add(nuevo);
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
			
			
			JPanel panelPrincipal = new JPanel(new BorderLayout());
		    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		    panelPrincipal.setBackground(new Color(88,187,240));    
			
			panelLogin.add(etiquetaUsuario);
			panelLogin.add(textoUsuario);
			panelLogin.add(etiquetaContraseña);
			panelLogin.add(textoContraseña);
			panelLogin.setBackground(new Color(88,214,240));
			panelLogin.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
			
			panelLogin.add(botonIngresar);
			panelLogin.add(botonCrear);
			panelLogin.add(botonCancelar);
			
			panelPrincipal.add(panelLogin, BorderLayout.CENTER);
		    this.add(panelPrincipal);
			
			
			this.setTitle("Login Docente");
			this.pack();
			this.setVisible(true);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}

}
