package Ventanas;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.*;

import DeustoAcademy.*;

public class VentanaEstudiante extends JFrame{
	
	
	//protected JButton botonExamenes;
	//protected JButton botonUnidades = new JButton("Temario");
	
	protected JButton botonModificar;
	protected JButton botonNotas;
	protected JButton botonChat;
	protected JButton botonSalir;
	
	public VentanaEstudiante(Estudiante estudiante) {
		
		//JFrame ventana = new JFrame(String.format("MENU ESTUDIANTE %s %p", estudiante.getNombre(), estudiante.getApellido()));  // DA ERROR Y NO SÉ PORQUÉ
		JFrame ventana = new JFrame("MENU ESTUDIANTE");
		
		Icon chatIcon = new ImageIcon("res/chat.png");
		Icon exitIcon = new ImageIcon("res/exit.png");
		Icon marksIcon = new ImageIcon("res/marks.png");
		Icon settingsIcon = new ImageIcon("res/settings.png");
		//botonModificar.setIcon(settingsIcon);
		//botonChat.setIcon(chatIcon);
		//botonNotas.setIcon(marksIcon);
		//botonSalir.setIcon(exitIcon);
        
		int anchoDeseado = 100;
        int altoDeseado = 100;
        Image image1 = ((ImageIcon) chatIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
        Image image2 = ((ImageIcon) exitIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
        Image image3 = ((ImageIcon) marksIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
        Image image4 = ((ImageIcon) settingsIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);


        // Crea un nuevo icono con la imagen escalada
        botonModificar = new JButton(new ImageIcon(image4));
        botonChat = new JButton(new ImageIcon(image1));
        botonNotas = new JButton(new ImageIcon(image3));
        botonSalir = new JButton(new ImageIcon(image2));
        
        botonChat.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
        botonSalir.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
        botonNotas.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
        botonModificar.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		
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
		panelIzquierda.add(botonNotas);
		panelIzquierda.add(botonChat);
		panelIzquierda.add(botonSalir);
		
		panelInterno.add(panelIzquierda,BorderLayout.WEST);
		panelInterno.add(panelTareas, BorderLayout.EAST);
		
		ventana.add(panelPrincipal);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
        ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		
		new VentanaEstudiante(new Estudiante());
		
	}
	
}
