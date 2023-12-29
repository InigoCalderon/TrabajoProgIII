package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

import DeustoAcademy.*;

public class VentanaDocente extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton botonClases;
    protected JButton botonInfoD;
    protected JButton botonSalir;

    protected JButton botonGuardarInformacion;

    protected JTextField textoNombre;
    protected JTextField textoApellido;
    protected JTextField textoTitulacion;
    protected JTextField textoTelefono;
    protected JTextField textoCorreo;
    protected JTextField textoDni;

    public VentanaDocente(Academy academy, Rols Rol, Docente docente) {

		JFrame ventana = new JFrame(String.format(" Bienvenido %s %s ", docente.getNombre(), docente.getApellido()));

        JPanel panelBotones = new JPanel((LayoutManager) new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel panelInformacion = new JPanel(new GridLayout(6, 2, 10, 10));

       
        // Crea iconos para cada botón
        Icon iconoClases = new ImageIcon("res/Clase.png");
        Icon iconoGuardar = new ImageIcon("res/settings.png");
        Icon iconoSalir = new ImageIcon("res/exit.png");

        int altoDeseado =  100;
        int anchoDeseado =  100;

        botonClases = new JButton();
        botonSalir = new JButton();
        botonGuardarInformacion = new JButton();

        /*
        botonClases.setPreferredSize(new Dimension(150, 30));
        botonInfoD.setPreferredSize(new Dimension(150, 30));
        botonSalir.setPreferredSize(new Dimension(150, 30));
        botonGuardarInformacion.setPreferredSize(new Dimension(150, 30));
        */
        
        // Ajustar el tamaño de la fuente de los botones
        float fontSize = 20.0f;
        botonClases.setFont(botonClases.getFont().deriveFont(fontSize));
        botonSalir.setFont(botonSalir.getFont().deriveFont(fontSize));
        botonGuardarInformacion.setFont(botonGuardarInformacion.getFont().deriveFont(fontSize));
        
     // Asigna iconos a los botones
        botonClases.setIcon(iconoClases);
        botonGuardarInformacion.setIcon(iconoGuardar);
        botonSalir.setIcon(iconoSalir);

        Image image1 = ((ImageIcon) iconoClases).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image4 = ((ImageIcon) iconoGuardar).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image5 = ((ImageIcon) iconoSalir).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		
		// Crea un nuevo icono con la imagen escalada
		botonClases = new JButton(new ImageIcon(image1));
		botonGuardarInformacion = new JButton(new ImageIcon(image4));
		botonSalir = new JButton(new ImageIcon(image5));

		botonClases.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		botonGuardarInformacion.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		botonSalir.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));

		botonClases.setBackground(new Color(0, 247, 255));
		botonGuardarInformacion.setBackground(new Color(0, 247, 255));
		botonSalir.setBackground(new Color(0, 247, 255));
        
        // Agregar ActionListener para los botones
        botonGuardarInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Lógica para guardar la información
            }
        });

        botonClases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaClases ventanaClases = new VentanaClases();
                VentanaClases.createAndShowGUI();
                ventana.dispose();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                new Login(academy, Rol);
            }
        });

        // Agregar componentes a los paneles
        panelBotones.add(botonClases);
        panelBotones.add(botonGuardarInformacion);
        panelBotones.add(botonSalir);

        panelInformacion.add(new JLabel("Nombre:"));
        panelInformacion.add(new JTextField(docente.getNombre()));
        panelInformacion.add(new JLabel("Apellido:"));
        panelInformacion.add(new JTextField(docente.getApellido()));
        panelInformacion.add(new JLabel("Teléfono:"));
        panelInformacion.add(new JTextField(String.valueOf(docente.getTelefono())));
        panelInformacion.add(new JLabel("Correo:"));
        panelInformacion.add(new JTextField(docente.getCorreo()));
        panelInformacion.add(new JLabel("DNI:"));
        panelInformacion.add(new JTextField(docente.getDni()));
        
        panelBotones.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panelBotones.setBackground(new Color(88, 187, 240));
		panelBotones.setBackground(new Color(88, 214, 240));
		
		panelInformacion.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panelInformacion.setBackground(new Color(88, 187, 240));
		panelInformacion.setBackground(new Color(88, 214, 240));

        // Agregar componentes al JFrame
        ventana.add(panelBotones, BorderLayout.CENTER);
        ventana.add(panelInformacion, BorderLayout.SOUTH);

        ventana.setTitle("Ventana Docente");
        ventana.setSize(800, 500);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
    }
}
