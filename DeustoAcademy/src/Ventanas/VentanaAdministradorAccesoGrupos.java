package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import DeustoAcademy.*;

public class VentanaAdministradorAccesoGrupos {

    protected JComboBox<Grupo> comboGrupos1;
    protected JComboBox<Grupo> comboGrupos2;
    protected JComboBox<Docente> comboDocentes;
    protected JComboBox<Estudiante> comboEstudiantes;

    protected JButton botonAsignar1;
    protected JButton botonAsignar2;
    protected JButton botonCrearGrupo;

    protected JTextArea textoAsignacion1;
    protected Academy datos;

    public VentanaAdministradorAccesoGrupos(Academy datos) {

        comboGrupos1 = new JComboBox<Grupo>();

        comboGrupos2 = new JComboBox<Grupo>();

        comboDocentes = new JComboBox<Docente>();

        comboEstudiantes = new JComboBox<Estudiante>();

        textoAsignacion1 = new JTextArea(10, 80);

        botonAsignar1 = new JButton("Asignar docente a grupo");
        botonAsignar2 = new JButton("Asignar estudiante a grupo");
        botonCrearGrupo = new JButton("Crear grupos");

        actualizarCombos(datos);

        botonAsignar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para asignar docente a grupo
            }
        });

        botonAsignar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para asignar estudiante a grupo
            }
        });

        botonCrearGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para crear grupos
            }
        });

        JFrame ventana = new JFrame("Ventana Administrador acceso docentes");

        JPanel panelAsignaciones1 = new JPanel();
        panelAsignaciones1.setLayout(new GridLayout(1, 3));
        panelAsignaciones1.add(comboGrupos1);
        panelAsignaciones1.add(comboDocentes);
        panelAsignaciones1.add(botonAsignar1);

        JPanel panelAsignaciones2 = new JPanel();
        panelAsignaciones2.setLayout(new GridLayout(1, 3));
        panelAsignaciones2.add(comboGrupos2);
        panelAsignaciones2.add(comboEstudiantes);
        panelAsignaciones2.add(botonAsignar2);

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonCrearGrupo, BorderLayout.CENTER);

        JPanel panelTexto = new JPanel();
        panelTexto.add(textoAsignacion1);

        ventana.add(panelTexto, BorderLayout.SOUTH);

        ventana.add(panelBoton, BorderLayout.EAST);

        // Color de fondo
        Color colorFondo = new Color(88, 187, 240);
        panelAsignaciones1.setBackground(colorFondo);
        panelAsignaciones2.setBackground(colorFondo);
        panelBoton.setBackground(colorFondo);
        panelTexto.setBackground(colorFondo);
        ventana.getContentPane().setBackground(colorFondo);

        Border bordeAsigancion1 = BorderFactory.createTitledBorder("Asignación de docente con grupo");
        panelAsignaciones1.setBorder(bordeAsigancion1);
        Border bordeAsigancion2 = BorderFactory.createTitledBorder("Asignación de estudiantes con grupos");
        panelAsignaciones2.setBorder(bordeAsigancion2);
        Border bordeTest = BorderFactory.createTitledBorder("Últimas asignaciones...");
        panelTexto.setBorder(bordeTest);

        JPanel panelTodoNorte = new JPanel();
        panelTodoNorte.setLayout(new GridLayout(2, 1));
        panelTodoNorte.add(panelAsignaciones1);
        panelTodoNorte.add(panelAsignaciones2);
        ventana.add(panelTodoNorte, BorderLayout.NORTH);

        ventana.setSize(960, 560);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actualizarCombos(Academy datos) {
        // Código para actualizar los combos
    }
}
