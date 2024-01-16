package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.Administrador;
import main.Academy;

import java.util.logging.Level;
import java.util.logging.Logger;



public class VentanaAdministradorAccesoAdministradores {
	
	private static Logger logger = Logger.getLogger(Academy.class.getName());
	 public DefaultListModel<Administrador> modeloLista = new DefaultListModel<>();
	    public JList<Administrador> listaAdministrador;
	    public TextField textoNombre;
	    public TextField textoApellido;
	    public TextField textoDni;
	    public TextField textoCorreo;
	    public TextField textoTelefono;
	    public TextField textoUsuario;
	    public TextField textoContraseña;
	    public Academy datos;

	    protected JTextArea textoAdministrador;
	    protected JButton botonModificar;
	    protected JButton botonEliminar;
	    protected JButton botonAñadir;

	    public VentanaAdministradorAccesoAdministradores(Academy datos) {
	        Logger logger = Logger.getLogger(Academy.class.getName());

	    	listaAdministrador = new JList<Administrador>(modeloLista);
	        JScrollPane scrollPlantilla = new JScrollPane(listaAdministrador);
	        actualizarCombos(datos);
	        
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

	        listaAdministrador.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                Administrador admin = listaAdministrador.getSelectedValue();
	               
	                if (admin != null) {
	                    textoNombre.setText(admin.getNombre());
	                    textoApellido.setText(admin.getApellido());
	                    textoDni.setText(admin.getDni());
	                    textoTelefono.setText(admin.getTelefono() + "");
	                    textoCorreo.setText(admin.getCorreo());
	                    textoUsuario.setText(admin.getUsuario());
	                    textoContraseña.setText(admin.getContrasena());
	                }else {
	                	logger.log(Level.SEVERE, "No has seleccionado ningún administrador");
	                	JOptionPane.showMessageDialog(null, "No has seleccionado ningún administrador", "Error",
	                            JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });

	        botonModificar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Administrador admin = listaAdministrador.getSelectedValue();
	                if (admin != null) {
	                    actualizarAdministrador(admin);
	                    listaAdministrador.clearSelection();
	                    actualizarCombos(datos);
	                    JOptionPane.showMessageDialog(null, "Administrador modificado", "Aviso",
	                            JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(null, "No has seleccionado ningún administrador", "Error",
	                            JOptionPane.ERROR_MESSAGE);
	                    logger.log(Level.SEVERE, "No has seleccionado ningún administrador");
	                }
	            }
	        });

	        botonEliminar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Administrador admin = listaAdministrador.getSelectedValue();
	                if (admin != null) {
	                    datos.getAdministradores().remove(admin);
	                    actualizarCombos(datos);
	                    limpiarCampos();
	                    JOptionPane.showMessageDialog(null, "Administrador eliminado", "Aviso",
	                            JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(null, "No has seleccionado ningún administrador", "Error",
	                            JOptionPane.ERROR_MESSAGE);
	                    logger.log(Level.SEVERE, "No has seleccionado ningún administrador");
	                }
	            }
	        });

	        botonAñadir.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (camposRellenos()) {
	                    Administrador admin = new Administrador();
	                    actualizarAdministrador(admin);
	                    datos.getAdministradores().add(admin);
	                    actualizarCombos(datos);
	                    limpiarCampos();
	                    JOptionPane.showMessageDialog(null, "Administrador añadido", "Aviso",
	                            JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(null, "No has rellenado todos los campos correctamente", "Error",
	                            JOptionPane.ERROR_MESSAGE);
	                    logger.log(Level.SEVERE, "No has rellenado todos los campos correctamente");
	                }
	            }
	        });
	        textoBusqueda.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	            	listaAdministrador.repaint();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	            	listaAdministrador.repaint();
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

	        JFrame ventana = new JFrame("Ventana Administrador acceso administradores");
	        JLabel panelBusqueda = new JLabel("Buscar administrador: ");
	        
	        panelBotones.add(panelBusqueda);
	        panelBotones.add(textoBusqueda);
	        
	        listaAdministrador.setCellRenderer(new MyCellRenderer(textoBusqueda));							// Render de la lista
	        
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

	    public void actualizarAdministrador(Administrador admin) {
	    	admin.setNombre(textoNombre.getText());
	    	admin.setApellido(textoApellido.getText());
	    	admin.setDni(textoDni.getText());
	    	admin.setCorreo(textoCorreo.getText());
	    	admin.setTelefono(Integer.parseInt(textoTelefono.getText()));
	    	admin.setUsuario(textoUsuario.getText());
	    	admin.setContrasena(textoContraseña.getText());
	    }

	    public void actualizarCombos(Academy datos) {
	        modeloLista.clear();
	        try {
	            datos.getAdministradores().sort(null);
	        } catch (ClassCastException e) {}  // Ignora error de ordenación
	        for (Administrador admin : datos.getAdministradores()) {
	            modeloLista.addElement(admin);
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
	    class MyCellRenderer extends JLabel implements ListCellRenderer<Administrador> {
	   	 private JTextField textoBusqueda; 

	   	    public MyCellRenderer(JTextField textoBusqueda) {
	   	        this.textoBusqueda = textoBusqueda;
	   	        setOpaque(true); 
	   	    }
			@Override
			public Component getListCellRendererComponent(JList<? extends Administrador> list, Administrador value, int index,
					boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Administrador admin =  value;
				if (!textoBusqueda.getText().isBlank() && textoBusqueda.getText() != null &&admin.getNombre().startsWith(textoBusqueda.getText()) ) {
					setForeground(Color.RED);
					setOpaque(true);
				}else {
					setForeground(Color.BLACK);
					setOpaque(true);
				}
				 setText("Administrador: " + admin.getNombre()+ " "+admin.getApellido() );
				return this;
			}
			
	   }
	
	
}
