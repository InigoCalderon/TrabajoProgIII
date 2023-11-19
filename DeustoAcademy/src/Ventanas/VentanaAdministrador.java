package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import DeustoAcademy.*;

public class VentanaAdministrador extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JRadioButton radioEstudiante;
	protected JRadioButton radioDocente;
	protected JRadioButton radioGrupos;
	protected JButton botonIngresar;
	protected Academy datos;

	public VentanaAdministrador(Academy academy, Rols rol) {

		JLabel etiquetaApartados = new JLabel("Apartados de datos");
		botonIngresar = new JButton("Ingresar");
		radioEstudiante = new JRadioButton("Estudiantes");
		radioDocente = new JRadioButton("Docentes");
		radioGrupos = new JRadioButton("Grupos y asignaciones");
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioEstudiante);
		bg.add(radioDocente);
		bg.add(radioGrupos);
		JPanel panelRadio = new JPanel();
		JPanel panelIngresar = new JPanel();
		JPanel panelTexto = new JPanel();

		botonIngresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (radioEstudiante.isSelected()) {
					System.out.println("estudiante");
					new VentanaAdiministradorAccesoEstudiantes(academy);
				} else if (radioDocente.isSelected()) {
					System.out.println("Docente");
					new VentanaAdministradorAccesoDocente(academy);
				} else if (radioGrupos.isSelected()) {
					System.out.println("grupos");
					new VentanaAdministradorAccesoGrupos(academy);
				} else {
					JOptionPane.showMessageDialog(null, "Has de seleccionar algún apartado ", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panelRadio.add(radioEstudiante);
		panelRadio.add(radioDocente);
		panelRadio.add(radioGrupos);
		panelIngresar.add(botonIngresar);
		panelTexto.add(etiquetaApartados);

		JFrame ventana = new JFrame("Ventana Administrador");

		ventana.add(panelRadio, BorderLayout.CENTER);
		ventana.add(panelIngresar, BorderLayout.SOUTH);
		ventana.add(panelTexto, BorderLayout.NORTH);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ventana.setLocationRelativeTo(null);

	}
	
}
