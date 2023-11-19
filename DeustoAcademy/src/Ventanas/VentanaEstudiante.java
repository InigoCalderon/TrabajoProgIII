package Ventanas;

//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;

import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import DeustoAcademy.*;


public class VentanaEstudiante extends JFrame {

	// protected JButton botonExamenes;
	// protected JButton botonUnidades = new JButton("Temario");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton botonModificar;
	protected JButton botonChat;
	protected JButton botonSalir;
	protected JButton botonCalendario;
	protected JMenu menuTemario;
	protected JMenu menuNotas;
	
	protected Icon idiomasIcon = new ImageIcon("res/idiomas.png");
	protected Icon chatIcon = new ImageIcon("res/chat.png");
	protected Icon exitIcon = new ImageIcon("res/exit.png");
	protected Icon marksIcon = new ImageIcon("res/notas_academy.png");
	protected Icon settingsIcon = new ImageIcon("res/settings.png");
	protected Icon calendarIcon = new ImageIcon("res/calendar.png");

	public VentanaEstudiante(Academy academy, Rols rol, Estudiante estudiante) {
		
		JFrame ventana = new JFrame(String.format(" Bienvenido %s %s ", estudiante.getNombre(), estudiante.getApellido()));
		
		
		class MyTableModel extends AbstractTableModel {

	        /**
	         *
	         */
	        private static final long serialVersionUID = 1L;

	        // array con los nombres de las columnas
	        private String[] headers = { "Fecha de Subida", "Fecha de Entrega", "Título", "Comentario"};
	        private List<Tarea> tareas;

	        // Este constructor recibe una lista con los objetos Persona
	        // que van a ser mostrados en la tabla
	        public MyTableModel(List<Tarea> tareas) {
	            this.tareas = tareas;
	        }

	        // este método es usado por la tabla para obtener los nombres
	        // de cada columna. En este caso se obtienen del array interno
	        @Override
	        public String getColumnName(int index) {
	            return headers[index];
	        }

	        // este método devuelve la clase de cada columna
	        // es utilizado por el JTable para elegir el componente visual
	        // o el renderer/editor adecuado para cada tipo de datos
	        @Override
	        public Class<?> getColumnClass(int column) {
	            // en este caso, las dos primeras columnas son LocalDate
	            // y las dos últimas son Strings
	            if (column < 2) {
	                return LocalDate.class;
	            } else {
	                return String.class;
	            }
	        }

	        // este método es utilizado por el JTable para saber el total
	        // de filas que tiene que mostrar en la tabla
	        @Override
	        public int getRowCount() {
	            // en este caso es la longitud de la lista de datos
	            return tareas.size();
	        }

	        // este método es utilizado por el JTable para obtener el valor
	        // de una celda (row, column) concreta
	        @Override
	        public Object getValueAt(int row, int column) {
	            // como el modelo de datos es una lista de objetos en este caso
	            // se obtiene la fila/objeto correspondiente
	            // en función del número de columna se obtiene la propiedad
	            Tarea t = tareas.get(row);
	            switch (column) {
	                case 0:
	                    return t.getFecha_creacion();
	                case 1:
	                    return t.getFecha_entrega();
	                case 2:
	                    return t.getTitulo();
	                case 3:
	                    return t.getComentario();
	            }

	            return null;
	        }

	        // utilizado por el JTable para saber el número de columnas
	        // a representar
	        @Override
	        public int getColumnCount() {
	            // en este caso el número de columnas es la longitud
	            // del array de títulos de columnas
	            return headers.length;
	        }

	        // el JTable utiliza este método para determinar si una
	        // celda concreta de la tabla es editable
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            // para este ejemplo, son editables todas menos
	            // la primera columna, que es la 0
	            //return column >= 1;
	        	
	        	// NINGUNA ES EDITABLE
	        	return false;
	        }

	        // este método es utilizado por el JTable para actualizar
	        // el modelo de datos asociado. 
	        // Recibe un Object por lo que, sabiendo el dato interno
	        // es necesario hacer un cast
	        @Override
	        public void setValueAt(Object value, int row, int column) {
	            // Obtenemos el objeto del modelo interno (lista) que
	            // que se debe actualizar en función del valor de fila recibido
	            Tarea t = tareas.get(row);

	            // teniendo en cuenta el valor de la columna recibida
	            // actualizamos la propiedad correspondiente de la Persona
	            // teniendo en cuenta el tipo concreto de dato
	            switch (column) {
	                case 0:
	                    t.setFecha_creacion((String) value);
	                    break;
	                case 1:
	                	t.setFecha_entrega((String) value);
	                    break;
	                case 2:
	                	t.setTitulo((String) value);
	                    break;
	                case 3:
	                	t.setComentario((String) value);
	                    break;
	            }

	            // este método se utiliza para notificar que el modelo de datos
	            // se ha actualizado y se debe repintar la celda visual
	            fireTableCellUpdated(row, column);
	        }

	    }
		
		// datos de la tabla
		
		List<Tarea> tareas = new ArrayList<>();
		
		for (Grupo grupo : academy.getGrupos()) {
			if (grupo.getEstudiantes().contains(estudiante)){
				for (Tarea tarea : grupo.getTareas()) {
		            tareas.add(tarea);
		        }
			}
		}

        // se crea el modelo de la tabla con los datos
        //MyTableModel tableModel = new MyTableModel(Arrays.asList(persons));
		
		MyTableModel tableModel = new MyTableModel(tareas);

        // se crea la tabla y se asigna el modelo/datos
        JTable table = new JTable(tableModel);

        // se establecen el renderer de la columna de nombres
        //TableColumn nameColumn = table.getColumnModel().getColumn(0);
        //nameColumn.setCellRenderer(new NameCellRenderer());

        // se establece el renderer y el editor de la columna de fechas
        //TableColumn birthdateColumn = table.getColumnModel().getColumn(2);
        //birthdateColumn.setCellRenderer(new DateCellRenderer());
        //birthdateColumn.setCellEditor(new DateCellEditor());

        // la tabla se añade en un scroll pane para poder
        // navegar por las filas
        JScrollPane scrollPane = new JScrollPane(table);
		
		JMenuBar menuBar = new JMenuBar();
        ventana.setJMenuBar(menuBar);  
        
		int anchoDeseado = 960 / 14;
		int altoDeseado = (int) (560 * 0.0975);
		
		Image image1 = ((ImageIcon) chatIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image2 = ((ImageIcon) exitIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image3 = ((ImageIcon) marksIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image4 = ((ImageIcon) settingsIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image5 = ((ImageIcon) calendarIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		Image image6 = ((ImageIcon) idiomasIcon).getImage().getScaledInstance(anchoDeseado, altoDeseado,
				Image.SCALE_SMOOTH);
		
		
		// Crea un nuevo icono con la imagen escalada
		botonModificar = new JButton(new ImageIcon(image4));
		botonChat = new JButton(new ImageIcon(image1));
		botonCalendario = new JButton(new ImageIcon(image5));
		botonSalir = new JButton(new ImageIcon(image2));
		menuTemario = new JMenu();
		menuTemario.setIcon(new ImageIcon(image6));
		menuTemario.setBackground(new Color(0, 247, 255));
		menuNotas = new JMenu();
		menuNotas.setIcon(new ImageIcon(image3));
		menuNotas.setBackground(new Color(0, 247, 255));
		
		for (Idioma idioma : estudiante.getIdiomas()) {
			
			
			JMenuItem nuevo_menu1 = new JMenuItem(idioma.toString());
			nuevo_menu1.setBackground(new Color(0, 247, 255));
			JMenuItem nuevo_menu2 = new JMenuItem(idioma.toString());
			nuevo_menu2.setBackground(new Color(0, 247, 255));
			
			nuevo_menu1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					new Temario(idioma);
					
				}
			});
			
        	menuTemario.add(nuevo_menu1);
        	
        	nuevo_menu2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					new VentanaCalificaciones(idioma);
					
				}
			});
        	
        	menuNotas.add(nuevo_menu2);
        	
		}
		
		botonChat.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		botonSalir.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		menuNotas.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		botonModificar.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));
		botonCalendario.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));		
		menuTemario.setPreferredSize(new Dimension(anchoDeseado, altoDeseado));		
		
		menuTemario.setBackground(new Color(0, 247, 255));
		botonCalendario.setBackground(new Color(0, 247, 255));
		botonChat.setBackground(new Color(0, 247, 255));
		botonSalir.setBackground(new Color(0, 247, 255));
		menuNotas.setBackground(new Color(0, 247, 255));
		botonModificar.setBackground(new Color(0, 247, 255));
		
		menuTemario.setBorder(null);
		botonSalir.setBorder(null);
		botonCalendario.setBorder(null);
		botonModificar.setBorder(null);
		menuNotas.setBorder(null);
		botonChat.setBorder(null);
		
		menuBar.add(botonModificar);
		menuBar.add(menuTemario);
        menuBar.add(menuNotas);
        menuBar.add(botonCalendario);
        menuBar.add(botonChat);
        menuBar.add(botonSalir);
        
        menuBar.setBackground(new Color(0, 247, 255));
        
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JPanel panelInterno = new JPanel();
		JPanel panelTareas = new JPanel();

		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panelPrincipal.setBackground(new Color(88, 187, 240));
		panelInterno.setBackground(new Color(88, 214, 240));
		panelInterno.add(table, BorderLayout.CENTER);
		panelInterno.add(scrollPane, BorderLayout.CENTER);
		panelPrincipal.add(panelInterno, BorderLayout.CENTER);

		/*
		 * / ZonedDateTime now = ZonedDateTime.now(); DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); JLabel etiquetaFechaHora
		 * = new JLabel(formatter.format(now)); /
		 */

		panelInterno.add(panelTareas);
		
		botonCalendario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		botonChat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		botonModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		botonSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new Login(academy, rol);
				ventana.dispose();

			}
		});

		ventana.add(panelPrincipal);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ventana.setLocationRelativeTo(null);

	}

}
