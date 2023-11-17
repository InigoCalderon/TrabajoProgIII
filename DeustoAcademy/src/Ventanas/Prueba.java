package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Prueba {

	public class BotonConImagen {
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					// Crea un marco
					JFrame frame = new JFrame("Botón con imagen");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLayout(new FlowLayout());

					// Carga una imagen (asegúrate de que la ruta sea correcta)
					ImageIcon icono = new ImageIcon("res/chat.png");

					// Escala la imagen al tamaño deseado
					int anchoDeseado = 693;
					int altoDeseado = 542;
					Image imagen = icono.getImage().getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);

					// Crea un botón y asigna el icono escalado
					JButton miBoton = new JButton(new ImageIcon(imagen));

					// Establece el tamaño del botón para que coincida con el tamaño del icono
					miBoton.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));

					// Agrega el botón al marco
					frame.add(miBoton);

					// Configura el marco
					frame.setSize(693, 542);
					frame.setVisible(true);
				}
			});
		}
	}

	public static void main(String[] args) {
		new Prueba();
	}

}
