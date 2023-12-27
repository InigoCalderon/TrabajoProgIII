package Ventanas;

import DeustoAcademy.Grupo;
import DeustoAcademy.Temario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.util.ArrayList;

public class TemarioVentana extends JFrame {

    private static final long serialVersionUID = 1L;

    public TemarioVentana(Grupo grupo, ArrayList<Temario> data) {

        JFrame ventana = new JFrame(String.format("TEMARIO de %s del Grupo: %s .", grupo.getIdioma(), grupo.getNombre()));

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelInterno = new JPanel();

        for (Temario temario : data) {

            if (temario.getGrupo() == grupo) {

                int units_counter = 0;
                JTabbedPane tabbedPane = new JTabbedPane();
                panelInterno.add(tabbedPane);
                
                for (String unit : temario.getData().keySet()) {

                    units_counter += 1;
                    
                    // Crea un panel común que se utilizará en todas las pestañas
                    JPanel commonPanel = new JPanel();
                    commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.Y_AXIS));

                    for (String enlace : temario.getData().get(unit)) {

                        JLabel nuevo_label = new JLabel(enlace);
                        commonPanel.add(nuevo_label);

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
                            public void mousePressed(MouseEvent e) {}

                            @Override
                            public void mouseReleased(MouseEvent e) {}

                            @Override
                            public void mouseEntered(MouseEvent e) {}

                            @Override
                            public void mouseExited(MouseEvent e) {}
                        });
                    }

                    if (temario.getData().keySet().size() >= units_counter) {

                    	tabbedPane.addTab(unit, commonPanel);
                    	
					} else {
						
						break;
						
					}
                    
                }

                break;
            }
        }

        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelPrincipal.setBackground(new Color(88, 187, 240));
        panelInterno.setBackground(new Color(88, 214, 240));

        panelPrincipal.add(panelInterno, BorderLayout.CENTER);

        ventana.add(panelPrincipal);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
    }
}
