package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import DeustoAcademy.*;

public class SelectRol extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	protected JComboBox<Rols> comboRol;
	protected JButton botonIngresar;
	protected JButton botonSalir;

	public SelectRol(Academy academy) throws HeadlessException {
		super();
		JFrame ventana = new JFrame("Roles");
		JPanel panelLogin1 = new JPanel();
		JLabel etiquetaRol = new JLabel("Escoge tu rol:");
		botonIngresar = new JButton("Entrar");
		botonSalir = new JButton("Salir");
		comboRol = new JComboBox<Rols>();
		comboRol.addItem(Rols.ESTUDIANTE);
		comboRol.addItem(Rols.DOCENTE);
		comboRol.addItem(Rols.ADMINISTRADOR);
		comboRol.setSelectedItem(null);

		botonIngresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (comboRol.getSelectedItem() == Rols.ADMINISTRADOR) {

					new Login(academy, (Rols) comboRol.getSelectedItem());
					ventana.dispose();

				}
				if (comboRol.getSelectedItem() == Rols.ESTUDIANTE) {

					new Login(academy, (Rols) comboRol.getSelectedItem());
					ventana.dispose();

				}
				if (comboRol.getSelectedItem() == Rols.DOCENTE) {

					new Login(academy, (Rols) comboRol.getSelectedItem());
					ventana.dispose();
				}
			}
		});

		botonSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();

			}
		});

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panelPrincipal.setBackground(new Color(88, 187, 240));

		panelLogin1.add(etiquetaRol);
		panelLogin1.add(comboRol);
		panelLogin1.add(botonIngresar);
		panelLogin1.add(botonSalir);
		panelLogin1.setBackground(new Color(88, 214, 240));
		panelLogin1.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

		panelPrincipal.add(panelLogin1, BorderLayout.CENTER);
		ventana.add(panelPrincipal);

		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ventana.pack();

	}

}
