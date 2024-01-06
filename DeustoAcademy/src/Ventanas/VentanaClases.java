package Ventanas;

import javax.swing.*;

import DeustoAcademy.Academy;
import DeustoAcademy.Grupo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaClases extends JFrame {


	protected static final long serialVersionUID = 1L;
	protected JComboBox<Grupo> comboGrupos;

    protected Academy datos;
    protected JButton botonEntrar;
    
    
    public VentanaClases(Academy datos) {
    	
    	comboGrupos = new JComboBox<Grupo>();
    	botonEntrar = new JButton("Entrar");
    	actualizarCombo(datos);
    	
    	botonEntrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Grupo grupo = (Grupo) comboGrupos.getSelectedItem();
				String seleccion = (String) comboGrupos.getSelectedItem();
				
				if (!((String) seleccion).isEmpty()) {
					new VentanaNotas();
				}
			}
		
    	});
    }

    // Método para actualizar el JComboBox de grupos
    public void actualizarCombo(Academy datos) {
    	comboGrupos.removeAllItems();
    	for(Grupo grupo: datos.getGrupos()) {
    		comboGrupos.addItem(grupo);
    	}
    }

	public static void createAndShowGUI() {
		// TODO Auto-generated method stub
		
	}
}