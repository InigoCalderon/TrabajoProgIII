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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


import domain.Estudiante;
import domain.Idioma;
import main.Academy;



public class VentanaAdiministradorAccesoEstudiantes extends JFrame {
   
	private static Logger logger = Logger.getLogger(Academy.class.getName());
	
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
   protected JCheckBox checkCastellano;
   protected JCheckBox checkIngles;
   protected JCheckBox checkEuskera;
   protected JCheckBox checkFrances;
   
    public VentanaAdiministradorAccesoEstudiantes(Academy datos) {
    	
        modeloLista = new DefaultListModel<>();
        listaEstudiante = new JList<Estudiante>(modeloLista);
        listaEstudiante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPlantilla = new JScrollPane(listaEstudiante);
        
        actualizarLista(datos);
        JTextField textoBusqueda = new JTextField(20);
        textoNombre = new TextField(20);
        textoApellido = new TextField(20);
        textoDni = new TextField(20);
        textoCorreo = new TextField(20);
        textoTelefono = new TextField(20);
        textoUsuario = new TextField(20);
        textoContraseña = new TextField(20);
        
        checkCastellano = new JCheckBox("Castellano");
        checkIngles = new JCheckBox("Ingles");
        checkEuskera = new JCheckBox("Euskera");
        checkFrances = new JCheckBox("Frances");
        
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
        botonAñadir = new JButton("Añadir");

        listaEstudiante.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
            	logger.log(Level.FINE, "INICIA EL PROGRAMA");
            	 limpiarCampos();
                Estudiante estudiante = listaEstudiante.getSelectedValue();
                if (estudiante != null) {
                    textoNombre.setText(estudiante.getNombre());
                    textoApellido.setText(estudiante.getApellido());
                    textoDni.setText(estudiante.getDni());
                    textoTelefono.setText(estudiante.getTelefono() + "");
                    textoCorreo.setText(estudiante.getCorreo());
                    textoUsuario.setText(estudiante.getUsuario());
                    textoContraseña.setText(estudiante.getContrasena());
                   for (Idioma idioma : estudiante.getIdiomas()) {
                	   if (idioma.equals(Idioma.Castellano)) {
                		   checkCastellano.setSelected(true);
                	   } else if(idioma.equals(Idioma.Ingles)) {
                		   checkIngles.setSelected(true);
                	   }else if(idioma.equals(Idioma.Euskera)) {
                		   checkEuskera.setSelected(true);
                	   }else if(idioma.equals(Idioma.Frances)) {
                		   checkFrances.setSelected(true);
                	   }
                   }
                    
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
                    JOptionPane.showMessageDialog(null, "Estudiante modificado", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                   
                    logger.log(Level.FINE, "Estudiante modificado"); 
                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningún estudiante", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    logger.log(Level.SEVERE, "No has seleccionado ningún estudiante");
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
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    logger.log(Level.FINE, "Estudiante eliminado"); 
                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningún estudiante", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    logger.log(Level.SEVERE, "No has seleccionado ningún estudiante");
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
                    JOptionPane.showMessageDialog(null, "Estudiante añadido", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    logger.log(Level.FINE, "Estudiante añadido"); 
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
            	listaEstudiante.repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	listaEstudiante.repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });

        JPanel panelBotones = new JPanel();
        JPanel panelModificarDatos = new JPanel();
        JPanel panelIzquierda = new JPanel();
        JFrame ventana = new JFrame("Ventana Administrador acceso estudiantes");
        
       
        
        listaEstudiante.setCellRenderer(new MyCellRenderer(textoBusqueda));							// Render de la lista
        
        panelBotones.add(botonEliminar);
        panelBotones.add(botonModificar);
        panelBotones.add(botonAñadir);
        
        JLabel panelBusqueda = new JLabel("Buscar estudiante: ");
        panelBotones.add(panelBusqueda);
        panelBotones.add(textoBusqueda);

        panelModificarDatos.setLayout(new GridLayout(6, 30));
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
        panelModificarDatos.add(new JLabel(" "));
        panelModificarDatos.add(new JLabel(" "));
        ////////////////////////
        panelModificarDatos.add(new JLabel("Idiomas: "));
        panelModificarDatos.add(new JLabel(" "));
        panelModificarDatos.add(new JLabel(" "));
        panelModificarDatos.add(new JLabel(" "));
        panelModificarDatos.add(checkCastellano);
        panelModificarDatos.add(checkIngles);
        panelModificarDatos.add(checkEuskera);
        panelModificarDatos.add(checkFrances);
        

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
        	logger.log(Level.SEVERE, "No hay datos");
            
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
        estudiante.setContrasena(textoContraseña.getText());
        estudiante.getIdiomas().clear();
        if (checkCastellano.isSelected() == true) {
        	estudiante.getIdiomas().add(Idioma.Castellano);
        }
        if(checkIngles.isSelected() == true) {
        	estudiante.getIdiomas().add(Idioma.Ingles);
        }
        if(checkEuskera.isSelected() == true) {
        	estudiante.getIdiomas().add(Idioma.Euskera);
        }
        if(checkFrances.isSelected() == true) {
        	estudiante.getIdiomas().add(Idioma.Frances);
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
        checkCastellano.setSelected(false);
        checkIngles.setSelected(false);
        checkEuskera.setSelected(false);
        checkFrances.setSelected(false);
    }

    public boolean camposRellenos() {
        return !textoNombre.getText().isBlank() && !textoApellido.getText().isBlank() && !textoDni.getText().isBlank()
                && !textoCorreo.getText().isBlank() && !textoTelefono.getText().isBlank()
                && !textoUsuario.getText().isBlank() && !textoCorreo.getText().isBlank();
    }
    
    
    
    
    // RENDER DE LA LISTA -- > Para realizar este código se ha usado ejemplos del "render" en el git de la asignatura de Programación lll
    
    
    
    class MyCellRenderer extends JLabel implements ListCellRenderer<Estudiante> {
    	 private JTextField textoBusqueda; 

    	    public MyCellRenderer(JTextField textoBusqueda) {
    	        this.textoBusqueda = textoBusqueda;
    	        setOpaque(true); 
    	    }
		@Override
		public Component getListCellRendererComponent(JList<? extends Estudiante> list, Estudiante value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			Estudiante estudiante =  value;
			if (!textoBusqueda.getText().isBlank() && textoBusqueda.getText() != null &&estudiante.getNombre().startsWith(textoBusqueda.getText()) ) {
				setForeground(Color.RED);
				setOpaque(true);
			}else {
				setForeground(Color.BLACK);
				setOpaque(true);
			}
			 setText("Estudiante: " +estudiante.getNombre()+ " "+estudiante.getApellido());
			return this;
		}
		
    }
    
    
}
