package Ventanas;

import DeustoAcademy.*;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TemarioVentana extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public TemarioVentana(Idioma idioma, Estudiante estudiante, Academy academy) {
		
		for (Grupo grupo : academy.getGrupos()) {
			
			if (grupo.getEstudiantes().contains(estudiante) && grupo.getIdioma() == idioma) {
				
				Docente profe = grupo.getDocente();
				String nombreDelGrupo = grupo.getNombre();
				
			}
			
		}
		
		JFrame ventana = new JFrame(String.format("TEMARIO de %s .", idioma));
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JPanel panelInterno = new JPanel();
		
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panelPrincipal.setBackground(new Color(88, 187, 240));
		panelInterno.setBackground(new Color(88, 214, 240));
		
		// metemos tabla para ver el temario y metemos buscador si podemos
		
		panelPrincipal.add(panelInterno, BorderLayout.CENTER);
		
		//Make dragging a little faster but perhaps uglier.

		ventana.add(panelPrincipal);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		
	}
	
}
