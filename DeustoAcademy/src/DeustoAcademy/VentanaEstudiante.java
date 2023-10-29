package DeustoAcademy;

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
import javax.swing.border.Border;

public class VentanaEstudiante extends JFrame{
	
	protected JButton botonClases;
	protected JButton botonNotas;
	protected JButton botonExamenes;
	protected JButton botonTareas;
	protected JButton botonInformacion;
	
	protected JButton botonGuardarInformacion;
	
	protected JTextField textoNombre;
	protected JTextField textoApellido;
	protected JTextField textoEdad;
	protected JTextField textoTelefono;
	protected JTextField textoCorreo;
	protected JTextField textoDni;
	
	public VentanaEstudiante(Estudiante estudiante) {
		
		// TODO Auto-generated constructor stub
		JPanel panelBotones = new JPanel();
		JPanel panelInformacion = new JPanel();
		JLabel etiquetaBienvenido = new JLabel("Bienvenido"+ estudiante.getUsuario());
		
		JLabel etiquetaNombre = new JLabel("Nombre");
		textoNombre = new JTextField(estudiante.getNombre());
		JLabel etiquetaApellido = new JLabel("Apellido");
		textoApellido = new JTextField(estudiante.getApellido());
		JLabel etiquetaTelefono = new JLabel("Telefono");
		textoTelefono = new JTextField(estudiante.getTelefono());
		JLabel etiquetaCorreo = new JLabel("Correo");
		textoCorreo = new JTextField(estudiante.getCorreo());
		JLabel etiquetaDni = new JLabel("Dni");
		textoDni = new JTextField(estudiante.getDni());
		
		ZonedDateTime now = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		JLabel etiquetaFechaHora = new JLabel(formatter.format(now));
		
		botonClases = new JButton("Clases");
		botonNotas = new JButton("Notas");
		botonExamenes = new JButton("Examenes");
		botonTareas = new JButton("Tareas");
		botonInformacion = new JButton("Información del estudiante");
		
		botonGuardarInformacion = new JButton("Guardar");
		
		
		
		botonGuardarInformacion.addActionListener(new ActionListener() {  // Con este botón los estudiantes actualizan su información personal
																			// Cuando se crean la cuenta en la ventana anterior, los datos personales no son introducidos, aqui se da la oportunidad a hacerlo
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ( textoNombre != null && textoApellido != null && textoEdad != null && textoTelefono != null && textoCorreo != null && textoDni != null) {
					estudiante.setNombre(textoNombre.getText());
					estudiante.setApellido(textoApellido.getText());
					estudiante.setTelefono(Integer.parseInt(textoTelefono.getText()));
					estudiante.setCorreo(textoCorreo.getText());
					estudiante.setDni(textoDni.getText());
				}else {
					JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error",  JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonClases.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		// Este botón creará nueva ventana para ver las clases del estudiante
				// TODO Auto-generated method stub
				
			}
		});
		botonNotas.addActionListener(new ActionListener() {		// Este botón creará nueva ventana para ver las notas del estudiante
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		botonExamenes.addActionListener(new ActionListener() {	// Este botón creará nueva ventana para ver los examenes y sus fechas del estudiante
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		botonTareas.addActionListener(new ActionListener() {   // Este botón creará nueva ventana para ver las tareas del estudiante
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		botonInformacion.addActionListener(new ActionListener() {  // Este botón creará nueva ventana para ver la información  del estudiante
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.add(etiquetaBienvenido,BorderLayout.NORTH);
		this.add(etiquetaFechaHora, BorderLayout.NORTH);
		
		panelBotones.add(botonClases);
		panelBotones.add(botonNotas);
		panelBotones.add(botonExamenes);
		panelBotones.add(botonTareas);
		panelBotones.add(botonInformacion);
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
		
		this.setTitle("Ventana Estudiante");
		this.setSize(600, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
}
