package Ventanas;

import DeustoAcademy.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class TemarioVentana extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TemarioVentana(Grupo grupo, ArrayList<Temario> data) {
		
		JFrame ventana = new JFrame(String.format("TEMARIO de %s del Grupo: %s .", grupo.getIdioma(), grupo.getNombre()));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		for (Temario temario : data) {
			
			if (temario.getGrupo() == grupo) {
				
				int counter = 0;
				
				for (String unit : temario.getData().keySet()) {
					
					JPanel nuevo_panel = new JPanel();
					
					for (String enlace : temario.getData().get(unit)) {
						
						JLabel nuevo_label = new JLabel(enlace);
						nuevo_panel.add(nuevo_label);
						
						nuevo_label.addMouseListener(new MouseListener() {
							
							@Override
							public void mouseClicked(MouseEvent e) {
								
								try {
			                        // Abre la URL en el navegador predeterminado del sistema
			                        Desktop.getDesktop().browse(new URI(enlace));
			                    } catch (Exception ex) {
			                        ex.printStackTrace();
			                    }
								
							}

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
						});
						
					}
					
					tabbedPane.addTab(String.format("Unit %s", counter++), nuevo_panel);
					
				}
				
			}
			
		}
		tabbedPane.addTab("Tab 1", new JPanel());
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JPanel panelInterno = new JPanel();
		
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panelPrincipal.setBackground(new Color(88, 187, 240));
		panelInterno.setBackground(new Color(88, 214, 240));
		
		// metemos tabla para ver el temario y metemos buscador si podemos
		
		panelInterno.add(tabbedPane);
		panelPrincipal.add(panelInterno, BorderLayout.CENTER);

		ventana.add(panelPrincipal);
		ventana.pack(); // tamaño grande, 960*560 y tamaño pequeño 720*480
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		
	}
	
}
