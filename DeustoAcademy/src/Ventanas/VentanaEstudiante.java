package Ventanas;

//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;

import javax.swing.*;
//import javax.swing.border.EmptyBorder;

import javax.swing.table.AbstractTableModel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;


import DeustoAcademy.*;


public class VentanaEstudiante extends JFrame {

	// protected JButton botonExamenes;
	// protected JButton botonUnidades = new JButton("Temario");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//protected JButton botonModificar;
	protected JButton botonSalir;
	protected JButton botonCalendario;
	protected JMenu menuIdiomas;
	protected JMenuItem menuNotas;
	
	protected Icon idiomasIcon = new ImageIcon("res/idiomas.png");
	protected Icon exitIcon = new ImageIcon("res/exit.png");
	protected Icon marksIcon = new ImageIcon("res/notas_academy.png");
	protected Icon bookIcon = new ImageIcon("res/book.png");
	protected Icon calendarIcon = new ImageIcon("res/calendar.png");

	public VentanaEstudiante(Academy academy, Rols rol, Estudiante estudiante) {
		
		JFrame ventana = new JFrame(String.format(" Bienvenido %s %s ", estudiante.getNombre(), estudiante.getApellido()));
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JPanel panelInterno = new JPanel();
		
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
		
		// ME INVENTO TAREAS
		System.out.println();
		for (Grupo grupo : academy.getGrupos()) {
			if (grupo.getEstudiantes().contains(estudiante)){
				grupo.getTareas().add(
					new Tarea(
							"26/11/23",
							"PRÁCTICA 69",
							"Esta práctica no es teórica"
					)
				);
			}
		}
		
		JMenuBar menuBar = new JMenuBar();
        ventana.setJMenuBar(menuBar);  
        
		int anchoDeseado1 = 960 / 10;
		int altoDeseado1 = (int) (560 * 0.15);
		
		int anchoDeseado2 = 960 / 17;
		int altoDeseado2 = (int) (560 * 0.1);
		
		Image image2 = ((ImageIcon) exitIcon).getImage().getScaledInstance(anchoDeseado1, altoDeseado1,
				Image.SCALE_SMOOTH);
		Image image3 = ((ImageIcon) marksIcon).getImage().getScaledInstance(anchoDeseado2, altoDeseado2,
				Image.SCALE_SMOOTH);
		Image image4 = ((ImageIcon) bookIcon).getImage().getScaledInstance(anchoDeseado2, altoDeseado2,
				Image.SCALE_SMOOTH);
		Image image5 = ((ImageIcon) calendarIcon).getImage().getScaledInstance(anchoDeseado1, altoDeseado1,
				Image.SCALE_SMOOTH);
		Image image6 = ((ImageIcon) idiomasIcon).getImage().getScaledInstance(anchoDeseado1, altoDeseado1,
				Image.SCALE_SMOOTH);
		
		
		// Crea un nuevo icono con la imagen escalada
		//botonModificar = new JButton(new ImageIcon(image4));
		botonCalendario = new JButton(new ImageIcon(image5));
		botonSalir = new JButton(new ImageIcon(image2));
		menuIdiomas = new JMenu();
		menuIdiomas.setIcon(new ImageIcon(image6));
		
		for (Idioma idioma : estudiante.getIdiomas()) {
			
			JMenu menuIdioma = new JMenu(idioma.toString());
						
			JMenuItem nuevo_menu1 = new JMenuItem("Temario");
			nuevo_menu1.setIcon(new ImageIcon(image4));
			
			JMenuItem nuevo_menu2 = new JMenuItem("Calificaciones");
			nuevo_menu2.setIcon(new ImageIcon(image3));
			
			JCheckBox botonInscribirse = new JCheckBox("Inscrito para realizar el examen Final");	
			
			JLabel itemNotaFinal = new JLabel(String.format("      Calificación Final: %s", academy.getNotasExamenFinal().get(estudiante).get(idioma)));
			
			JMenuItem menuTareas = new JMenuItem("Calificaciones de Tareas");
			
			botonInscribirse.setSelected(academy.getInscritosExamenFinal().get(estudiante).get(idioma));
			
			menuIdioma.add(nuevo_menu1);
			menuIdioma.add(nuevo_menu2);
			menuIdioma.add(botonInscribirse);
			menuIdioma.add(itemNotaFinal);
			menuIdioma.add(menuTareas);
			
			for (Grupo grupo :  academy.getGrupos()) {
				
				if (grupo.getEstudiantes().contains(estudiante)) {
					
					JMenu menuGrupo = new JMenu(grupo.getNombre());
					
					for (Tarea tarea : grupo.getTareas()) {
						
						menuGrupo.add(new JLabel(String.format("Tarea: %s tiene una calificación: %s", tarea.getTitulo(), academy.getNotasTareas().get(estudiante).get(grupo).get(tarea))));
						
					}
					
					menuTareas.add(menuGrupo);
					
				}
				
			}

			menuIdiomas.add(menuIdioma);
			
			menuIdioma.setBackground(new Color(0, 247, 255));
			
			nuevo_menu1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					new Temario(idioma, estudiante);
					System.out.println("ENTRARIAMOS AL TEMARIO");
					
				}
			});
        	
        	nuevo_menu2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					new VentanaCalificaciones(idioma, estudiante);
					System.out.println("ENTRARIAMOS A LAS CALIFICACIONES");
					
				}
			});
        	
        	botonInscribirse.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					academy.getInscritosExamenFinal().get(estudiante).remove(idioma);
					academy.getInscritosExamenFinal().get(estudiante).put(idioma, botonInscribirse.isSelected());
					
					academy.actualizar_datos(Rols.ESTUDIANTE);
					
				}
			});
        	
		}
		
		botonSalir.setPreferredSize(new Dimension(anchoDeseado1, altoDeseado1));
		botonCalendario.setPreferredSize(new Dimension(anchoDeseado1, altoDeseado1));		
		menuIdiomas.setPreferredSize(new Dimension(anchoDeseado1, altoDeseado1));		
		
		menuIdiomas.setBackground(new Color(0, 247, 255));
		botonCalendario.setBackground(new Color(0, 247, 255));
		botonSalir.setBackground(new Color(0, 247, 255));
		
		menuIdiomas.setBorder(null);
		botonSalir.setBorder(null);
		botonCalendario.setBorder(null);

		menuBar.add(botonSalir);
		menuBar.add(menuIdiomas);
        menuBar.add(botonCalendario);
        
        JLabel fechaProximasTareas = new JLabel("  Tareas pendientes en los próximos: ");
        JComboBox<Integer> fechas = new JComboBox<Integer>();
        
        fechas.addItem(15);
        fechas.addItem(30);
        fechas.addItem(60);
        fechas.addItem(90);
        
        JLabel dias = new JLabel(" días.                                                                                                              ");
        
        menuBar.add(fechaProximasTareas);
        menuBar.add(fechas);
        menuBar.add(dias);
        menuBar.setBackground(new Color(0, 247, 255));

		/*
		 * / ZonedDateTime now = ZonedDateTime.now(); DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); JLabel etiquetaFechaHora
		 * = new JLabel(formatter.format(now)); /
		 */
		
		botonCalendario.addActionListener(new ActionListener() {

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

        //TableColumn birthdateColumn = table.getColumnModel().getColumn(2);
        //birthdateColumn.setCellRenderer(new DateCellRenderer());
        
        //birthdateColumn.setCellEditor(new DateCellEditor());

        // la tabla se añade en un scroll pane para poder
        // navegar por las filas
        JScrollPane scrollPane = new JScrollPane(table);
        
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panelPrincipal.setBackground(new Color(88, 187, 240));
		panelInterno.setBackground(new Color(88, 214, 240));
		panelInterno.add(table, BorderLayout.CENTER);
		panelInterno.add(scrollPane, BorderLayout.CENTER);
		panelPrincipal.add(panelInterno, BorderLayout.CENTER);

		ventana.add(panelPrincipal);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ventana.setLocationRelativeTo(null);

	}
}
