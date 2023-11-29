package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import DeustoAcademy.*;

public class VentanaAdiministradorAccesoEstudiantes extends JFrame {
    private static final long serialVersionUID = 1L;
    public DefaultListModel<Estudiante> modeloLista = new DefaultListModel<>();
    public JList<Estudiante> listaEstudiante;
    public TextField textoNombre;
    public TextField textoApellido;
    public TextField textoDni;
    public TextField textoCorreo;
    public TextField textoTelefono;
    public TextField textoUsuario;
    public TextField textoContraseña;
    protected JButton botonModificar;
    protected JButton botonEliminar;
    protected JButton botonAñadir;
    protected Academy datos;

    public VentanaAdiministradorAccesoEstudiantes(Academy datos) {
        modeloLista = new DefaultListModel<>();
        listaEstudiante = new JList<Estudiante>(modeloLista);
        listaEstudiante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPlantilla = new JScrollPane(listaEstudiante);
        actualizarLista(datos);

        textoNombre = new TextField(20);
        textoApellido = new TextField(20);
        textoDni = new TextField(20);
        textoCorreo = new TextField(20);
        textoTelefono = new TextField(20);
        textoUsuario = new TextField(20);
        textoContraseña = new TextField(20);

        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
        botonAñadir = new JButton("Añadir");

        listaEstudiante.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                Estudiante estudiante = listaEstudiante.getSelectedValue();
                if (estudiante != null) {
                    textoNombre.setText(estudiante.getNombre());
                    textoApellido.setText(estudiante.getApellido());
                    textoDni.setText(estudiante.getDni());
                    textoTelefono.setText(estudiante.getTelefono() + "");
                    textoCorreo.setText(estudiante.getCorreo());
                    textoUsuario.setText(estudiante.getUsuario());
                    textoContraseña.setText(estudiante.getContraseña());
                }
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estudiante estudiante = (Estudiante) listaEstudiante.getSelectedValue();
                if (estudiante != null) {
                    actualizarEstudiante(estudiante);
                    listaEstudiante.clearSelection();
                }
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estudiante estudiante = listaEstudiante.getSelectedValue();
                if (estudiante != null) {
                    datos.getEstudiantes().remove(estudiante);
                    actualizarLista(datos);
                    limpiarCampos();
                }
            }
        });

        botonAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (camposRellenos()) {
                    Estudiante nuevo = new Estudiante();
                    actualizarEstudiante(nuevo);
                    datos.getEstudiantes().add(nuevo);
                    actualizarLista(datos);
                    limpiarCampos();
                }
            }
        });

        JPanel panelBotones = new JPanel();
        JPanel panelModificarDatos = new JPanel();
        JPanel panelIzquierda = new JPanel();
        JFrame ventana = new JFrame("Ventana Administrador acceso estudiantes");

        panelBotones.add(botonEliminar);
        panelBotones.add(botonModificar);
        panelBotones.add(botonAñadir);

        panelModificarDatos.setLayout(new GridLayout(4, 30));
        panelModificarDatos.add(new JLabel("Nombre: "));
        panelModificarDatos.add(textoNombre);
        panelModificarDatos.add(new JLabel("Apellido: "));
        panelModificarDatos.add(textoApellido);
        panelModificarDatos.add(new JLabel("DNI: "));
        panelModificarDatos.add(textoDni);
        panelModificarDatos.add(new JLabel("Correo: "));
        panelModificarDatos.add(textoCorreo);
        panelModificarDatos.add(new JLabel("Teléfono: "));
        panelModificarDatos.add(textoTelefono);
        panelModificarDatos.add(new JLabel("Usuario: "));
        panelModificarDatos.add(textoUsuario);
        panelModificarDatos.add(new JLabel("Contraseña: "));
        panelModificarDatos.add(textoContraseña);

        panelIzquierda.add(panelModificarDatos, BorderLayout.CENTER);

        ventana.add(panelIzquierda, BorderLayout.WEST);
        ventana.add(scrollPlantilla, BorderLayout.CENTER);
        ventana.add(panelBotones, BorderLayout.SOUTH);

        // Color de fondo
        Color colorFondo = new Color(88, 187, 240);
        panelIzquierda.setBackground(colorFondo);
        panelModificarDatos.setBackground(colorFondo);
        panelBotones.setBackground(colorFondo);
        ventana.getContentPane().setBackground(colorFondo);

        Border bordeBotones = BorderFactory.createTitledBorder("Botones");
        panelBotones.setBorder(bordeBotones);

        Border bordeDatos = BorderFactory.createTitledBorder("Datos");
        panelModificarDatos.setBorder(bordeDatos);

        ventana.setSize(960, 560);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void actualizarLista(Academy datos) {
        modeloLista.clear();
        try {
            datos.getEstudiantes().sort(null);
        } catch (ClassCastException ignored) {
        }
        for (Estudiante estudiante : datos.getEstudiantes()) {
            modeloLista.addElement(estudiante);
        }
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        estudiante.setNombre(textoNombre.getText());
        estudiante.setApellido(textoApellido.getText());
        estudiante.setDni(textoDni.getText());
        estudiante.setCorreo(textoCorreo.getText());
        estudiante.setTelefono(Integer.parseInt(textoTelefono.getText()));
        estudiante.setUsuario(textoUsuario.getText());
        estudiante.setContraseña(textoContraseña.getText());
    }

    public void limpiarCampos() {
        textoNombre.setText("");
        textoApellido.setText("");
        textoDni.setText("");
        textoTelefono.setText("");
        textoCorreo.setText("");
        textoUsuario.setText("");
        textoContraseña.setText("");
    }

    public boolean camposRellenos() {
        return !textoNombre.getText().isBlank() && !textoApellido.getText().isBlank() && !textoDni.getText().isBlank()
                && !textoCorreo.getText().isBlank() && !textoTelefono.getText().isBlank()
                && !textoUsuario.getText().isBlank() && !textoCorreo.getText().isBlank();
    }
}
