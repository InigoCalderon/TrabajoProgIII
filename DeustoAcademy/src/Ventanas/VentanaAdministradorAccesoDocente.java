package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import DeustoAcademy.*;
import Ventanas.VentanaAdiministradorAccesoEstudiantes.MyCellRenderer;

public class VentanaAdministradorAccesoDocente {

    public DefaultListModel<Docente> modeloLista = new DefaultListModel<>();
    public JList<Docente> listaDocente;
    public TextField textoNombre;
    public TextField textoApellido;
    public TextField textoDni;
    public TextField textoCorreo;
    public TextField textoTelefono;
    public TextField textoUsuario;
    public TextField textoContraseña;
    public Academy datos;

    protected JTextArea textoDocente;
    protected JButton botonModificar;
    protected JButton botonEliminar;
    protected JButton botonAñadir;

    public VentanaAdministradorAccesoDocente(Academy datos) {

        listaDocente = new JList<Docente>(modeloLista);
        JScrollPane scrollPlantilla = new JScrollPane(listaDocente);
        actualizarCombos(datos);
        JLabel etiquetaDocente = new JLabel("Docentes");
        JTextField textoBusqueda = new JTextField(20);
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
        textoBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	listaDocente.repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	listaDocente.repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
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
        JLabel panelBusqueda = new JLabel("Buscar docente: ");
        
        panelBotones.add(panelBusqueda);
        panelBotones.add(textoBusqueda);
        
        listaDocente.setCellRenderer(new MyCellRenderer(textoBusqueda));							// Render de la lista
        
        ventana.add(scrollPlantilla, BorderLayout.SOUTH);
        ventana.add(panelIzquierda, BorderLayout.WEST);
        ventana.add(panelBotones, BorderLayout.SOUTH);
        ventana.add(scrollPlantilla, BorderLayout.CENTER);

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
    class MyCellRenderer extends JLabel implements ListCellRenderer<Docente> {
   	 private JTextField textoBusqueda; 

   	    public MyCellRenderer(JTextField textoBusqueda) {
   	        this.textoBusqueda = textoBusqueda;
   	        setOpaque(true); 
   	    }
		@Override
		public Component getListCellRendererComponent(JList<? extends Docente> list, Docente value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			Docente docente =  value;
			if (!textoBusqueda.getText().isBlank() && textoBusqueda.getText() != null &&docente.getNombre().startsWith(textoBusqueda.getText()) ) {
				setForeground(Color.RED);
				setOpaque(true);
			}else {
				setForeground(Color.BLACK);
				setOpaque(true);
			}
			 setText("Docente: " + docente.getNombre()+ " "+docente.getApellido() );
			return this;
		}
		
   }
}
