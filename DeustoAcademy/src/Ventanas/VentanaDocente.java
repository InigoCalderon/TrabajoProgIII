package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DeustoAcademy.*;

public class VentanaDocente extends JFrame{
	protected JButton botonClases;
	protected JButton botonCalificaciones;
	protected JButton botonInfoD;
		
	protected JButton botonGuardarInformacion;
	
	protected JTextField textoNombre;
	protected JTextField textoApellido;
	protected JTextField textoTitulacion;
	protected JTextField textoTelefono;
	protected JTextField textoCorreo;
	protected JTextField textoDni;
	
	public VentanaDocente(Docente docente) {
		
		// TODO Auto-generated constructor stub
		JPanel panelBotones = new JPanel();
		JPanel panelInformacion = new JPanel();
		JLabel etiquetaBienvenido = new JLabel("Bienvenido"+ docente.getUsuario());
		
		JLabel etiquetaNombre = new JLabel("Nombre");
		textoNombre = new JTextField(docente.getNombre());
		JLabel etiquetaApellido = new JLabel("Apellido");
		textoApellido = new JTextField(docente.getApellido());
		JLabel etiquetaTelefono = new JLabel("Telefono");
		textoTelefono = new JTextField(docente.getTelefono());
		JLabel etiquetaCorreo = new JLabel("Correo");
		textoCorreo = new JTextField(docente.getCorreo());
		JLabel etiquetaDni = new JLabel("Dni");
		textoDni = new JTextField(docente.getDni());
		
		ZonedDateTime now = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		JLabel etiquetaFechaHora = new JLabel(formatter.format(now));
		
		botonClases = new JButton("Clases");
		botonCalificaciones = new JButton("Calificaciones");
		botonInfoD = new JButton("Información del profesor");
		
		botonGuardarInformacion = new JButton("Guardar");
		
		
		
		botonGuardarInformacion.addActionListener(new ActionListener() {  // Con este botón los profesores pueden actualizar la información personal
																			// Cuando se crean la cuenta en la ventana anterior, los datos personales no son introducidos, aqui se da la oportunidad a hacerlo
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ( textoNombre != null && textoApellido != null && textoTitulacion != null && textoTelefono != null && textoCorreo != null && textoDni != null) {
					docente.setNombre(textoNombre.getText());
					docente.setApellido(textoApellido.getText());
					docente.setTelefono(Integer.parseInt(textoTelefono.getText()));
					docente.setCorreo(textoCorreo.getText());
					docente.setDni(textoDni.getText());
				}else {
					JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error",  JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonClases.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		// Este botón creará nueva ventana para ver las clases del estudiante
				VentanaClases ventanaClases = new VentanaClases(); 
			}
		});
		botonCalificaciones.addActionListener(new ActionListener() {		// Este botón creará nueva ventana para ver las calificaciones de sus alumnos
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCalificaciones ventanaCalificaciones = new VentanaCalificaciones();
			}
		});
		botonInfoD.addActionListener(new ActionListener() {	// Este botón creará nueva ventana para ver la informacion del profesor
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaInfoDocente ventanaInfoDocente = new VentanaInfoDocente();
			}
		});
		
		this.add(etiquetaBienvenido,BorderLayout.NORTH);
		this.add(etiquetaFechaHora, BorderLayout.NORTH);
		
		panelBotones.add(botonClases);
		panelBotones.add(botonCalificaciones);
		panelBotones.add(botonInfoD);
		this.add(panelBotones, BorderLayout.CENTER);
		
		panelInformacion.add(etiquetaNombre);
		panelInformacion.add(textoNombre);
		panelInformacion.add(etiquetaApellido);
		panelInformacion.add(textoApellido);
		panelInformacion.add(etiquetaTelefono);
		panelInformacion.add(textoTelefono);
		panelInformacion.add(etiquetaCorreo);
		panelInformacion.add(textoCorreo);
		panelInformacion.add(etiquetaDni);
		panelInformacion.add(textoDni);
		this.add(panelInformacion,BorderLayout.SOUTH);
		
		this.setTitle("Ventana Docente");
		this.setSize(600, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		

	}
	
}
