package Ventanas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

import javax.swing.AbstractCellEditor;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import org.jdatepicker.JDatePicker;

public class VentanaCalendario extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	class DateCellRenderer extends DefaultTableCellRenderer {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        // se reimplementa el comportamiento del renderer por defecto
        // para convertir el dato interno (Local Date) a un string con
        // un formato visual adecuado
        @Override
        public void setValue(Object value) {
            LocalDate localTime = (LocalDate) value;
            if (localTime != null) {
            	setText(localTime.format(formatter));
            }
        }
    }

    // esta clase implementa un editor específico para la columna de fechas de la tabla
    // en concreto se va a utilizar un JDatePicker para que se muestre un calendario
    // cuando el usuario quiere editar una celda de fecha.
    // para implementar este editor se extendiende una clase con funcionalidad ya
    // existente (AbstractCellEditor) y se implementa una interfaz necesaria (TableCellEditor).
    
    class DateCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private LocalDate currentValue;
        private JDatePicker datePicker;

        // este método debe devolver el valor que se va a mostrar en tabla
        // como resultado de la edición
        @Override
        public Object getCellEditorValue() {
            return currentValue;
        }

        // este método debe devolver el componente visual que se va a utilizar
        // cuando el usuario quiera editar la celda indicada
        @Override
        
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            // en este caso se devuelve un selector de fecha
            // como componente visual de edición
            datePicker = new JDatePicker();
            datePicker.addActionListener(this);
            return datePicker;
        }

        // este método es utilizado por el selector de fecha
        // cuando el usuario selecciona una fecha
        @Override
        public void actionPerformed(ActionEvent e) {
            // cuando el usuario selecciona la fecha en el componente visual
            // se obtiene la fecha seleccionada
            GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
            ZonedDateTime zonedDateTime = calendar.toZonedDateTime();

            // se actualiza el valor que será devuelto por getCellEditorValue()
            // para mostrarlo como resultado de la edición
            currentValue = zonedDateTime.toLocalDate();

            // se debe indicar que la edición ha terminado para lanzar
            // la actualización de la tabla y el modelo
            fireEditingStopped();
        }

    }
	
	public VentanaCalendario () {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new VentanaCalendario();
            }

        });
		
	}
	
}
