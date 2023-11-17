package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import DeustoAcademy.*;

public class VentanaChat extends JFrame {
	private static final long serialVersionUID = 1L;
	// protected DeustoRepara datos; Conseguir datos lista de alumnos por profesor
	// y lista de profesores según las clases que tenga un alumno
	protected JButton botonEnviar;
	protected JButton botonReenviar;
	protected JButton botonInsertarNuevoContacto;
	protected JButton botonBorrarContacto;
	protected JButton botonModificarContacto;
	protected JTextArea textAreaContenidoEmail;
	protected JLabel direccionLabel = new JLabel("DIRECCIONES: ");
	// protected
	// JComboBox<DatosFalsosParaProbarSiFuncionaAñadirLoQueNecesiteisPeroNoBorreisLoQueHalla>
	// direccionesComboBox = new JComboBox<>();
	// Habrá que sacar los datos de los Alumnos y los profesores dependiendo de
	// quienes sean para sacar los datos que necesiten

	public VentanaChat() {

		botonInsertarNuevoContacto = new JButton("Nuevo Contacto");
		botonBorrarContacto = new JButton("Borrar");
		botonModificarContacto = new JButton("Modificar");
		textAreaContenidoEmail = new JTextArea(20, 3);
		botonEnviar = new JButton("Enviar");
		botonReenviar = new JButton("Reenviar");

		JPanel panelPrincipal = new JPanel();
		JPanel panelTrasero = new JPanel();
		JPanel panelDeTop = new JPanel();

		panelDeTop.add(direccionLabel);
		// panelDeTop.add(direccionesComboBox);
		panelDeTop.add(botonInsertarNuevoContacto);
		panelDeTop.add(botonBorrarContacto);
		panelDeTop.add(botonModificarContacto);
		panelPrincipal.add(textAreaContenidoEmail);
		panelPrincipal.add(botonEnviar, BorderLayout.SOUTH);
		panelPrincipal.add(botonReenviar, BorderLayout.SOUTH);

		// panelPrincipal.setLocation(0, 0);
		panelPrincipal.setBackground(Color.red);

		panelPrincipal.add(panelDeTop);
		panelTrasero.add(panelPrincipal);

		panelTrasero.setBackground(Color.BLUE);
		this.add(panelTrasero);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Chat");
		this.pack();
		// this.setSize(1000, 500);
		this.setBackground(Color.blue);
		this.setVisible(true);

	}

}
