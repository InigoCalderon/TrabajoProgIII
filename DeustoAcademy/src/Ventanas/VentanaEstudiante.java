package Ventanas;

//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;

import javax.swing.*;
//import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DeustoAcademy.*;

public class VentanaEstudiante extends JFrame {

	// protected JButton botonExamenes;
	// protected JButton botonUnidades = new JButton("Temario");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton botonModificar;
	protected JButton botonNotas;
	protected JButton botonChat;
	protected JButton botonSalir;
	protected JButton botonCalendario;
	protected JMenu menuTemario;
	
	protected Icon idiomasIcon = new ImageIcon("res/idiomas.png");
	protected Icon chatIcon = new ImageIcon("res/chat.png");
	protected Icon exitIcon = new ImageIcon("res/exit.png");
	protected Icon marksIcon = new ImageIcon("res/notas_academy.png");
	protected Icon settingsIcon = new ImageIcon("res/settings.png");
	protected Icon calendarIcon = new ImageIcon("res/calendar.png");

	public VentanaEstudiante(Academy academy, Rols rol, Estudiante estudiante) {
		
		JFrame ventana = new JFrame(String.format(" Bienvenido %s %s ", estudiante.getNombre(), estudiante.getApellido()));
		
		JMenuBar menuBar = new JMenuBar();
        ventana.setJMenuBar(menuBar);  
        
		int anchoDeseado = 960 / 14;
		int altoDeseado = (int) (560 * 0.0975);
		
		Image image1 = ((ImageIcon) chatIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image2 = ((ImageIcon) exitIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image3 = ((ImageIcon) marksIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image4 = ((ImageIcon) settingsIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image5 = ((ImageIcon) calendarIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image6 = ((ImageIcon) idiomasIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		
		
		// Crea un nuevo icono con la imagen escalada
		botonModificar = new JButton(new ImageIcon(image4));
		botonChat = new JButton(new ImageIcon(image1));
		botonNotas = new JButton(new ImageIcon(image3));
		botonCalendario = new JButton(new ImageIcon(image5));
		botonSalir = new JButton(new ImageIcon(image2));
		menuTemario = new JMenu();
		menuTemario.setIcon(new ImageIcon(image6));
		menuTemario.setBackground(new Color(0, 247, 255));
		
		for (Idioma idioma : estudiante.getIdiomas()) {
			
			JMenuItem nuevo_menu = new JMenuItem(idioma.toString());
			nuevo_menu.setBackground(new Color(0, 247, 255));
			
			nuevo_menu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					new Temario(idioma);
					
				}
			});
			
        	menuTemario.add(nuevo_menu);
        	
		}
		
		botonChat.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		botonSalir.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		botonNotas.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		botonModificar.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		botonCalendario.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));		
		menuTemario.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));		
		
		menuTemario.setBackground(new Color(0, 247, 255));
		botonCalendario.setBackground(new Color(0, 247, 255));
		botonChat.setBackground(new Color(0, 247, 255));
		botonSalir.setBackground(new Color(0, 247, 255));
		botonNotas.setBackground(new Color(0, 247, 255));
		botonModificar.setBackground(new Color(0, 247, 255));
		
		menuTemario.setBorder(null);
		botonSalir.setBorder(null);
		botonCalendario.setBorder(null);
		botonModificar.setBorder(null);
		botonNotas.setBorder(null);
		botonChat.setBorder(null);
		
		menuBar.add(botonModificar);
		menuBar.add(menuTemario);
        menuBar.add(botonNotas);
        menuBar.add(botonCalendario);
        menuBar.add(botonChat);
        menuBar.add(botonSalir);
        
        menuBar.setBackground(new Color(0, 247, 255));

        for (Idioma idioma : estudiante.getIdiomas()) {
			
        	
        	
		}
        
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JPanel panelInterno = new JPanel();
		JPanel panelTareas = new JPanel();

		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panelPrincipal.setBackground(new Color(88, 187, 240));
		panelInterno.setBackground(new Color(88, 214, 240));
		panelPrincipal.add(panelInterno, BorderLayout.CENTER);

		/*
		 * / ZonedDateTime now = ZonedDateTime.now(); DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); JLabel etiquetaFechaHora
		 * = new JLabel(formatter.format(now)); /
		 */

		panelInterno.add(panelTareas);
		
		botonCalendario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		botonChat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		botonNotas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		botonModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		botonSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new Login(academy, rol);
				ventana.dispose();

			}
		});

		ventana.add(panelPrincipal);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

}
