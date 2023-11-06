package Ventanas;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DeustoAcademy.*;

public class VentanaEstudiante extends JFrame{
	
	
	//protected JButton botonExamenes;
	//protected JButton botonUnidades = new JButton("Temario");
	
	protected JButton botonModificar;
	protected JButton botonNotas;
	protected JButton botonChat;
	protected JButton botonSalir;
	protected JButton botonCalendario;
	
	public VentanaEstudiante(Academy academy, Rols rol) {
		
		//JFrame ventana = new JFrame(String.format("MENU ESTUDIANTE %s %p", estudiante.getNombre(), estudiante.getApellido()));  // DA ERROR Y NO SÉ PORQUÉ
		JFrame ventana = new JFrame("MENU ESTUDIANTE");
		
		int anchoDeseado = 100;
        int altoDeseado = 82;
		
		Icon chatIcon = new ImageIcon("src/res/chat.png");
		Icon exitIcon = new ImageIcon("src/res/exit.png");
		Icon marksIcon = new ImageIcon("src/res/notas_academy.png");
		Icon settingsIcon = new ImageIcon("src/res/settings.png");
		Icon calendarIcon = new ImageIcon("src/res/calendar.png");
        
        Image image1 = ((ImageIcon) chatIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
        Image image2 = ((ImageIcon) exitIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
        Image image3 = ((ImageIcon) marksIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
        Image image4 = ((ImageIcon) settingsIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
        Image image5 = ((ImageIcon) calendarIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);


        // Crea un nuevo icono con la imagen escalada
        botonModificar = new JButton(new ImageIcon(image4));
        botonChat = new JButton(new ImageIcon(image1));
        botonNotas = new JButton(new ImageIcon(image3));
        botonCalendario = new JButton(new ImageIcon(image5));
        botonSalir = new JButton(new ImageIcon(image2));
        
        
        botonChat.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
        botonSalir.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
        botonNotas.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
        botonModificar.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
        botonCalendario.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
        
        botonCalendario.setBackground(new Color(0,247,255));
        botonChat.setBackground(new Color(0,247,255));
        botonSalir.setBackground(new Color(0,247,255));
        botonNotas.setBackground(new Color(0,247,255));
        botonModificar.setBackground(new Color(0,247,255));
        
        
        botonCalendario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelInterno = new JPanel();
        JPanel panelIzquierda = new JPanel();
		JPanel panelTareas = new JPanel();
		
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelPrincipal.setBackground(new Color(88,187,240));
        panelInterno.setBackground(new Color(88,214,240));
        panelPrincipal.add(panelInterno, BorderLayout.CENTER);
	
		/*/
		ZonedDateTime now = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		JLabel etiquetaFechaHora = new JLabel(formatter.format(now));
		/*/

		panelIzquierda.add(botonModificar);
		panelIzquierda.add(botonCalendario);
		panelIzquierda.add(botonChat);
		panelIzquierda.add(botonNotas);
		panelIzquierda.add(botonSalir);

		panelIzquierda.setLayout(new GridLayout(5,1));
		
		//panelTareas.
		
		panelInterno.add(panelIzquierda,BorderLayout.WEST);
		panelInterno.add(panelTareas, BorderLayout.EAST);
		
		
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
