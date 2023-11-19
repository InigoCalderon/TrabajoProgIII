package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

import DeustoAcademy.*;

public class VentanaDocente extends JFrame {
    protected JButton botonClases;
    protected JButton botonCalificaciones;
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

        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));
        JPanel panelInformacion = new JPanel(new GridLayout(6, 2, 10, 10));

        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        JLabel etiquetaFechaHora = new JLabel(formatter.format(now));

        botonClases = new JButton("Clases");
        botonCalificaciones = new JButton("Calificaciones");
        botonInfoD = new JButton("Info Prof.");
        botonSalir = new JButton("Volver");
        botonGuardarInformacion = new JButton("Guardar");

        // Ajustar el tamaño de la fuente de los botones
        float fontSize = 14.0f;
        botonClases.setFont(botonClases.getFont().deriveFont(fontSize));
        botonCalificaciones.setFont(botonCalificaciones.getFont().deriveFont(fontSize));
        botonInfoD.setFont(botonInfoD.getFont().deriveFont(fontSize));
        botonSalir.setFont(botonSalir.getFont().deriveFont(fontSize));
        botonGuardarInformacion.setFont(botonGuardarInformacion.getFont().deriveFont(fontSize));

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
                dispose();
            }
        });

        botonCalificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCalificaciones ventanaCalificaciones = new VentanaCalificaciones();
                dispose();
            }
        });

        botonInfoD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInfoDocente ventanaInfoDocente = new VentanaInfoDocente();
                dispose();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login(docente, Rol, academy);
            }
        });

        // Agregar componentes a los paneles
        panelBotones.add(botonClases);
        panelBotones.add(botonCalificaciones);
        panelBotones.add(botonInfoD);
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

        // Agregar componentes al JFrame
        this.add(panelBotones, BorderLayout.CENTER);
        this.add(panelInformacion, BorderLayout.SOUTH);

        this.setTitle("Ventana Docente");
        this.setSize(600, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
    }
}
