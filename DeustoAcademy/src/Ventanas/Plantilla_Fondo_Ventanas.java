package Ventanas;

import javax.swing.*;
import java.awt.*;

public class Plantilla_Fondo_Ventanas {

	public Plantilla_Fondo_Ventanas() {

		SwingUtilities.invokeLater(() -> {

			JFrame ventana = new JFrame("Ventana Plantilla");

			JPanel panelPrincipal = new JPanel(new BorderLayout());
			JPanel panelInterno = new JPanel();
			panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
			panelPrincipal.setBackground(new Color(88, 187, 240));
			panelInterno.setBackground(new Color(88, 214, 240));
			panelPrincipal.add(panelInterno, BorderLayout.CENTER);

			// AQUÍ SE METERÍAN LOS COMPONENTES DE LAS VENTANAS

			ventana.add(panelPrincipal);
			ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
			ventana.setVisible(true);
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		});

	}

	public static void main(String[] args) {

		new Plantilla_Fondo_Ventanas();

	}

}
