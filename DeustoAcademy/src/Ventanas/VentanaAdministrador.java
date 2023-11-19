package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup; // Importar ButtonGroup
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
    protected JButton botonSalir;
    protected Academy datos;

    public VentanaAdministrador(Academy academy, Rols rol) {

        JFrame ventana = new JFrame("Bienvenido admin");

        JLabel etiquetaApartados = new JLabel("Apartados de datos");
        botonIngresar = new JButton("Ingresar");
        botonSalir = new JButton("Salir");
        radioEstudiante = new JRadioButton("Estudiantes");
        radioDocente = new JRadioButton("Docentes");
        radioGrupos = new JRadioButton("Grupos y asignaciones");

        // Agrupar los botones de radio
        ButtonGroup group = new ButtonGroup();
        group.add(radioEstudiante);
        group.add(radioDocente);
        group.add(radioGrupos);

        JPanel panelRadio = new JPanel();
        JPanel panelBotones = new JPanel(); // Nuevo panel para botones
        JPanel panelTexto = new JPanel();

        botonIngresar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                new Login(academy, rol);
            }
        });

        // Establecer estilo y diseño
        Font font = new Font("Arial", Font.BOLD, 18);
        radioEstudiante.setFont(font);
        radioDocente.setFont(font);
        radioGrupos.setFont(font);

        radioEstudiante.setForeground(Color.WHITE);
        radioDocente.setForeground(Color.WHITE);
        radioGrupos.setForeground(Color.WHITE);

        radioEstudiante.setPreferredSize(new Dimension(250, 75));
        radioDocente.setPreferredSize(new Dimension(250, 75));
        radioGrupos.setPreferredSize(new Dimension(250, 75)); // Ajusté el tamaño de Grupos

        radioEstudiante.setBackground(new Color(88, 187, 240));
        radioDocente.setBackground(new Color(88, 187, 240));
        radioGrupos.setBackground(new Color(88, 187, 240));

        botonIngresar.setPreferredSize(new Dimension(120, 40));
        botonSalir.setPreferredSize(new Dimension(120, 40));

        // Agregar componentes a los paneles
        panelRadio.setLayout(new BoxLayout(panelRadio, BoxLayout.X_AXIS));
        panelRadio.add(Box.createHorizontalGlue()); // Centrar en la mitad horizontal
        panelRadio.add(radioEstudiante);
        panelRadio.add(radioDocente);
        panelRadio.add(radioGrupos);
        panelRadio.add(Box.createHorizontalGlue()); // Centrar en la mitad horizontal

        panelBotones.add(botonIngresar);
        panelBotones.add(botonSalir);

        panelTexto.add(etiquetaApartados);

        // Establecer color de fondo para los paneles
        panelRadio.setBackground(new Color(88, 187, 240));
        panelBotones.setBackground(new Color(88, 187, 240));
        panelTexto.setBackground(new Color(88, 187, 240));

        // Cambié la disposición para mejorar la apariencia visual
        ventana.setLayout(new BorderLayout());
        ventana.add(panelRadio, BorderLayout.CENTER);
        ventana.add(panelTexto, BorderLayout.NORTH);
        ventana.add(Box.createVerticalStrut(20), BorderLayout.WEST); // Espaciado a la izquierda
        ventana.add(Box.createVerticalStrut(20), BorderLayout.EAST); // Espaciado a la derecha
        ventana.add(panelBotones, BorderLayout.SOUTH);

        // Centrar en medio de la pantalla
        ventana.setLocationRelativeTo(null);

        ventana.setSize(800, 500);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
