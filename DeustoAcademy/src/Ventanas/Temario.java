package Ventanas;

import DeustoAcademy.*;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Temario extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Temario(Idioma idioma, Estudiante estudiante) {
		
		JFrame ventana = new JFrame(String.format(" Calificaciones de %s %s ", estudiante.getNombre(), estudiante.getApellido()));
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JPanel panelInterno = new JPanel();
		
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panelPrincipal.setBackground(new Color(88, 187, 240));
		panelInterno.setBackground(new Color(88, 214, 240));
		
		// metemos tabla para ver el temario y metemos buscador si podemos
		
		panelPrincipal.add(panelInterno, BorderLayout.CENTER);
		
		ventana.add(panelPrincipal);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		
	}
	
}
