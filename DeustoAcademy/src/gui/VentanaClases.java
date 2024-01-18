package gui;

import javax.swing.*;

import domain.Grupo;
import main.Academy;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaClases extends JFrame {


	protected static final long serialVersionUID = 1L;
	protected JComboBox<Grupo> comboGrupos;

    protected Academy datos;
    protected JButton botonEntrar;
    
    
    public VentanaClases(Academy datos) {
    	this.datos = datos;
    	
    	setTitle("VentanaClases");
        setSize(300, 150); // Tamaño ajustado para mayor simplicidad
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);   
        
    	comboGrupos = new JComboBox<Grupo>();
    	botonEntrar = new JButton("Entrar");
    	actualizarCombo(datos);
    	
        setLayout(new FlowLayout());
    	add(comboGrupos);
    	add(botonEntrar);
		    	
    	botonEntrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Grupo grupoSeleccionado = (Grupo) comboGrupos.getSelectedItem();
				
				if (grupoSeleccionado != null) {
					new VentanaNotas(null);
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