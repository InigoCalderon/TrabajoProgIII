package gui;

import javax.swing.*;

import domain.Grupo;
import main.Academy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaClases extends JFrame {


	protected static final long serialVersionUID = 1L;
	protected JComboBox<Grupo> comboGrupos;

    protected Academy datos;
    protected JButton botonEntrar;
    
    
    public VentanaClases(Academy datos) {
    	
    	JFrame ventana = new JFrame("VentanaClases");
    	
    	comboGrupos = new JComboBox<Grupo>();
    	botonEntrar = new JButton("Entrar");
    	actualizarCombo(datos);
    	
    	ventana.add(comboGrupos);
    	ventana.add(botonEntrar);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
    	
    	botonEntrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Grupo grupo = (Grupo) comboGrupos.getSelectedItem();
				String seleccion = (String) comboGrupos.getSelectedItem();
				
				if (!((String) seleccion).isEmpty()) {
					new VentanaNotas(datos);
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

    
    
}