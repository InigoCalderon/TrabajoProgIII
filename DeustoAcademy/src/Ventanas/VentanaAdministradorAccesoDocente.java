package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import DeustoAcademy.*;

public class VentanaAdministradorAccesoDocente {

    protected DefaultListModel<Docente> modeloLista = new DefaultListModel<>();
    protected JList<Docente> listaDocente;
    protected TextField textoNombre;
    protected TextField textoApellido;
    protected TextField textoDni;
    protected TextField textoCorreo;
    protected TextField textoTelefono;
    protected TextField textoUsuario;
    protected TextField textoContraseña;
    protected Academy datos;

    protected JTextArea textoDocente;
    protected JButton botonModificar;
    protected JButton botonEliminar;
    protected JButton botonAñadir;

    public VentanaAdministradorAccesoDocente(Academy datos) {

        listaDocente = new JList<Docente>(modeloLista);
        JScrollPane scrollPlantilla = new JScrollPane(listaDocente);
        actualizarCombos(datos);
        JLabel etiquetaDocente = new JLabel("Docentes");

        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
        botonAñadir = new JButton("Añadir");
        textoNombre = new TextField(20);
        textoApellido = new TextField(20);
        textoDni = new TextField(20);
        textoCorreo = new TextField(20);
        textoTelefono = new TextField(20);
        textoUsuario = new TextField(20);
        textoContraseña = new TextField(20);

        listaDocente.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                Docente docente = listaDocente.getSelectedValue();
                if (docente != null) {
                    textoNombre.setText(docente.getNombre());
                    textoApellido.setText(docente.getApellido());
                    textoDni.setText(docente.getDni());
                    textoTelefono.setText(docente.getTelefono() + "");
                    textoCorreo.setText(docente.getCorreo());
                    textoUsuario.setText(docente.getUsuario());
                    textoContraseña.setText(docente.getContraseña());
                }
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Docente docente = listaDocente.getSelectedValue();
                if (docente != null) {
                    actualizarDocente(docente);
                    listaDocente.clearSelection();
                    actualizarCombos(datos);
                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningún docente", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Docente docente = listaDocente.getSelectedValue();
                if (docente != null) {
                    datos.getDocentes().remove(docente);
                    actualizarCombos(datos);
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningún docente", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botonAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (camposRellenos()) {
                    Docente nuevo = new Docente();
                    actualizarDocente(nuevo);
                    datos.getDocentes().add(nuevo);
                    actualizarCombos(datos);
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "No has rellenado todos los campos correctamente", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panelBotones = new JPanel();
        JPanel panelModificarDatos = new JPanel();
        JPanel panelIzquierda = new JPanel();

        panelBotones.add(botonAñadir);
        panelBotones.add(botonModificar);
        panelBotones.add(botonEliminar);

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

        JFrame ventana = new JFrame("Ventana Administrador acceso docentes");

        ventana.add(scrollPlantilla, BorderLayout.SOUTH);
        ventana.add(panelIzquierda, BorderLayout.WEST);
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
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actualizarDocente(Docente docente) {
        docente.setNombre(textoNombre.getText());
        docente.setApellido(textoApellido.getText());
        docente.setDni(textoDni.getText());
        docente.setCorreo(textoCorreo.getText());
        docente.setTelefono(Integer.parseInt(textoTelefono.getText()));
        docente.setUsuario(textoUsuario.getText());
        docente.setContraseña(textoContraseña.getText());
    }

    public void actualizarCombos(Academy datos) {
        modeloLista.clear();
        try {
            datos.getDocentes().sort(null);
        } catch (ClassCastException e) {}  // Ignora error de ordenación
        for (Docente docente : datos.getDocentes()) {
            modeloLista.addElement(docente);
        }
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
        return !textoNombre.getText().isBlank() && !textoApellido.getText().isBlank()
                && !textoDni.getText().isBlank() && !textoCorreo.getText().isBlank()
                && !textoTelefono.getText().isBlank() && !textoUsuario.getText().isBlank()
                && !textoCorreo.getText().isBlank();
    }
}
