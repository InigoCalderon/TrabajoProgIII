package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import DeustoAcademy.Estudiante;
import DeustoAcademy.Idioma;


public class VentanaCalendarioEstudiante extends JFrame{
	protected JButton botonSalir;
	
	private static List<Idioma> idiomas = List.of(
	        new Idioma("Ingles"), new Idioma("Castellano"), new Idioma("Euskera"), new Idioma("Frances") );
	
	
	class MyTableModel extends AbstractTableModel{
		String[] etiquetas = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
		String[] horas = {"09:00","10:00","11:00","12:00","13:00"};
		 
		 
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return horas.length;
		}
		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return etiquetas[column];
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return etiquetas.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return columnIndex;
			// TODO Auto-generated method stub
		/*	Idioma i = idiomas.get(rowIndex);

            switch (columnIndex) {
                case 0: return i.getIdioma();
                case 1: return i.getIdioma();
                case 2: return i.getIdioma();
                default: return null;
          } */
		}
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return rootPaneCheckingEnabled ;
		}
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			Idioma i = idiomas.get(rowIndex);

	            // teniendo en cuenta el valor de la columna recibida
	            // actualizamos la propiedad correspondiente de la Persona
	            // teniendo en cuenta el tipo concreto de dato
	            switch (columnIndex) {
	                case 0:
	                    i.setIdioma((String) aValue);
	                    break;
	                case 1:
	                	 i.setIdioma((String) aValue);
	                    break;
	                case 3:
	                	 i.setIdioma((String) aValue);
	                    break;
	                case 4:
	                	 i.setIdioma((String) aValue);
	                    break;
	                default: break;
	            }
		}
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			switch (columnIndex) {
			case 0:return String.class;
			case 1:return String.class;
			case 2:return String.class;
			case 3:return String.class;
			case 4:return String.class;
			default: return null;
			}
			
		}
		
	}
	class EditorCalendario extends AbstractCellEditor implements TableCellEditor{
		private JComboBox<String> ComboAsiganturas;

        public void ComboAsiganturas() {
            // creamos el componente con los países a seleccionar
        	ComboAsiganturas = new JComboBox<>(new String[] { "Castellano", "Ingles", "Euskera", "Frances"});
            //Font currentFont = ComboAsiganturas.getFont();
           // ComboAsiganturas.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 9));
            
            ComboAsiganturas.addActionListener(e -> fireEditingStopped());
        }
		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			 return ComboAsiganturas.getSelectedItem();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return ComboAsiganturas;
		}
		
	}
	
	public VentanaCalendarioEstudiante(Estudiante estudiante) throws HeadlessException {
		JFrame ventana = new JFrame("CALENDARIO ESTUDIANTE");
		
		MyTableModel modelo = new MyTableModel();
		JTable tabla = new JTable(modelo);
		TableColumn columna0 = tabla.getColumnModel().getColumn(0);
		TableColumn columna1 = tabla.getColumnModel().getColumn(1);
		TableColumn columna2 = tabla.getColumnModel().getColumn(2);
		TableColumn columna3 = tabla.getColumnModel().getColumn(3);
		TableColumn columna4 = tabla.getColumnModel().getColumn(4);
		columna0.setCellEditor(new EditorCalendario());
		columna1.setCellEditor(new EditorCalendario());
		columna2.setCellEditor(new EditorCalendario());
		columna3.setCellEditor(new EditorCalendario());
		columna4.setCellEditor(new EditorCalendario());
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelPrincipal.setBackground(new Color(88,187,240));
        panelPrincipal.add(tabla);
        ventana.add(panelPrincipal);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
        ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		
		new VentanaCalendarioEstudiante(new Estudiante());
		
	}
}
