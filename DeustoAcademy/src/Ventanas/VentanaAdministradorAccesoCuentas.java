package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import DeustoAcademy.Academy;
import DeustoAcademy.Docente;
import DeustoAcademy.Estudiante;
import Ventanas.VentanaAdiministradorAccesoEstudiantes.MyCellRenderer;



public class VentanaAdministradorAccesoCuentas {
	    private static Logger logger = Logger.getLogger(Academy.class.getName());
	    public Academy datos;

	   
	    protected JTextArea textoCuentas;
	    protected JTextField textoActivo;
	    
	    public DefaultListModel<Estudiante> modeloLista1 = new DefaultListModel<>();
	    public JList<Estudiante> listaEstudiante;
	    
	    public DefaultListModel<Docente> modeloLista2 = new DefaultListModel<>();
	    public JList<Docente> listaDocente;
	    
	    public VentanaAdministradorAccesoCuentas(Academy datos) {

	    	modeloLista1 = new DefaultListModel<>();
	        listaEstudiante = new JList<Estudiante>(modeloLista1);
	        listaEstudiante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        JScrollPane scrollPlantilla1 = new JScrollPane(listaEstudiante);
	        actualizarLista(datos);
	        
	        modeloLista2 = new DefaultListModel<>();
	        listaDocente = new JList<Docente>(modeloLista2);
	        listaDocente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        JScrollPane scrollPlantilla2 = new JScrollPane(listaDocente);
	        actualizarCombos(datos);
	        
	    	
	    	textoCuentas = new JTextArea(10, 80);
	    	textoCuentas.setEditable(false);
	    	
	    	textoActivo = new JTextField();
	    	textoActivo.setEditable(false);
	    	
	    	
	    	textoActivo.setText("Activo total de la academia: " +datos.metodoActivoAcadaemia(datos) + " €");
	    	
	    	
	    	
	    	listaEstudiante.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					Estudiante estudiante = listaEstudiante.getSelectedValue();
	                if (estudiante != null) {
	        //        	textoCuentas.setText("");   Esto hace que el texto no salga repetido, pero elimina el historial anterior ...
	                	if (datos.mapaTarifaEstudiante(datos).containsKey(estudiante)) {
							
	                		textoCuentas.append("Estudiante " + estudiante.getNombre() + " , Dni: "+ estudiante.getDni()+ " , Tarifa mensual: " + datos.mapaTarifaEstudiante(datos).get(estudiante) + " €"+"\n");
						}
	                }else {
	                	logger.log(Level.SEVERE, "No has seleccionado ningún estudiante");
	                	JOptionPane.showMessageDialog(null, "No has seleccionado ningún estudiante", "Error",
	                            JOptionPane.ERROR_MESSAGE);
	                }
				}
			});

	       
	        listaDocente.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					 Docente docente = listaDocente.getSelectedValue();
		                if (docente != null) {
		      //          	textoCuentas.setText("");   
		                	if(datos.mapaSueldoDocente(datos).containsKey(docente)) {
		                		textoCuentas.append("Docente " + docente.getNombre()+ " , Dni: "+ docente.getDni() + " , Sueldo mensual: " + datos.mapaSueldoDocente(datos).get(docente)+ " €" + "\n");
		                	}
		                }else {
		                	logger.log(Level.SEVERE, "No has seleccionado ningún docente");
		                	JOptionPane.showMessageDialog(null, "No has seleccionado ningún docente", "Error",
		                            JOptionPane.ERROR_MESSAGE);
		                }
				}
			});

	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        listaEstudiante.setCellRenderer(new MyCellRenderer(datos));	
	        listaDocente.setCellRenderer(new MyCellRenderer2(datos));
	        JFrame ventana = new JFrame("Ventana Administrador acceso cuentas");
	        JPanel panelListas = new JPanel();
	        panelListas.add(scrollPlantilla1 , BorderLayout.EAST);
	        panelListas.add(scrollPlantilla2, BorderLayout.WEST);
	        ventana.add(panelListas, BorderLayout.CENTER);
	        JPanel panelTexto = new JPanel();
			panelTexto.add(textoCuentas);
			ventana.add(panelTexto, BorderLayout.SOUTH);
			JPanel panelActivo = new JPanel();
			panelActivo.add(textoActivo);
			ventana.add(panelActivo, BorderLayout.NORTH);
			
			Border bordeLista = BorderFactory.createTitledBorder("Listas de Estudiantes & Docentes ");
	        panelListas.setBorder(bordeLista);
	       
	        Border bordeTexto = BorderFactory.createTitledBorder("Información sobre sueldos/tarifas ");
	        panelTexto.setBorder(bordeTexto);
	        // Color de fondo
	        Color colorFondo = new Color(88, 187, 240);
	        
	        panelListas.setBackground(colorFondo);
	        panelTexto.setBackground(colorFondo);
	        panelActivo.setBackground(colorFondo);
	        ventana.getContentPane().setBackground(colorFondo);

	        ventana.setSize(960, 560);
	        ventana.setVisible(true);
	        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    }
	    
	    public void actualizarCombos(Academy datos) {
	        modeloLista2.clear();
	        try {
	            datos.getDocentes().sort(null);
	        } catch (ClassCastException e) {}  
	        for (Docente docente : datos.getDocentes()) {
	            modeloLista2.addElement(docente);
	        }
	    }
	    
	    public void actualizarLista(Academy datos) {
	        modeloLista1.clear();
	        try {
	            datos.getEstudiantes().sort(null);
	        } catch (ClassCastException ignored) {
	        	logger.log(Level.SEVERE, "Lista vacia");
	        }
	        for (Estudiante estudiante : datos.getEstudiantes()) {
	            modeloLista1.addElement(estudiante);
	        }
	    }
	    
	    class MyCellRenderer extends JLabel implements ListCellRenderer<Estudiante> {
	    	private Academy datos;
			public MyCellRenderer(Academy datos) {
	    		this.datos = datos;
    	    }
			@Override
			public Component getListCellRendererComponent(JList<? extends Estudiante> list, Estudiante value, int index,
					boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Estudiante estudiante =  value;
				if (datos.mapaTarifaEstudiante(datos).containsKey(estudiante)) {
					setText("Estudiante: " +estudiante.getNombre()+ " "+estudiante.getApellido());
					setOpaque(true);
				} else {
		            setText("Datos no disponibles para " + estudiante.getNombre() + " " + estudiante.getApellido());
		            setOpaque(true);
		        }
				 
				return this;
			}
			
	    }
	    
	    class MyCellRenderer2 extends JLabel implements ListCellRenderer<Docente> {
	    	private Academy datos;
			public MyCellRenderer2(Academy datos) {
	    		this.datos = datos;
    	    }
			@Override
			public Component getListCellRendererComponent(JList<? extends Docente> list, Docente value, int index,
					boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Docente docente =  value;
				if (datos.mapaSueldoDocente(datos).containsKey(docente)) {
					setText("Docente: " +docente.getNombre()+ " "+docente.getApellido() );
					setOpaque(true);
				} else {
		            setText("Datos no disponibles para " + docente.getNombre() + " " + docente.getApellido());
		            setOpaque(true);
		        }
				 
				return this;
			}
			
	    }
}
