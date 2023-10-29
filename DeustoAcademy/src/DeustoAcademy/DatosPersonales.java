package DeustoAcademy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DatosPersonales {

	protected JButton botonGuardar = new JButton("Guardar");
	protected JButton botonCancelar = new JButton("Cancelar");
	protected JTextField textoNombre = new JTextField();
	protected JTextField textoApellido = new JTextField();
	protected JTextField textoTelefono = new JTextField();
	protected JTextField textoCorreo = new JTextField();
	protected JTextField textoDni = new JTextField();
	protected JLabel nombre = new JLabel("Nombre: ");
	protected JLabel apellido = new JLabel("Apellido: ");
	protected JLabel telefono = new JLabel("Teléfono: ");
	protected JLabel correo = new JLabel("Correo: ");
	protected JLabel dni = new JLabel("Dni: ");
	
	public DatosPersonales(Academy academy, Rols rol, String nuevo_user, String nueva_passw) {
		
		SwingUtilities.invokeLater(() -> {
      
            JFrame ventana = new JFrame("Ventana Plantilla");
          
            JPanel panelPrincipal = new JPanel(new BorderLayout());
            panelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
            panelPrincipal.setBackground(new Color(88,187,240));

            JPanel panelInterno = new JPanel();
            panelInterno.setBackground(new Color(88,214,240));

            
            panelInterno.add(nombre);
            panelInterno.add(textoNombre);
            panelInterno.add(apellido);
            panelInterno.add(textoApellido);
            panelInterno.add(telefono);
            panelInterno.add(textoTelefono);
            panelInterno.add(dni);
            panelInterno.add(textoDni);
            panelInterno.add(correo);
            panelInterno.add(textoCorreo);
            panelInterno.add(botonGuardar);
            panelInterno.add(botonCancelar);
            panelInterno.setLayout(new GridLayout(6,1));            
            
            
            botonGuardar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (rol == Rols.ADMINISTRADOR) {
						academy.administradores.add(new Administrador(
									textoNombre.getText(),
									textoApellido.getText(),
									textoDni.getText(),
									textoCorreo.getText(),
								    Integer.parseInt(textoTelefono.getText()),
									nuevo_user,
									nueva_passw));
					} if (rol == Rols.ESTUDIANTE) {
						academy.administradores.addAll((Collection<? extends Administrador>) new Estudiante(
									textoNombre.getText(),
									textoApellido.getText(),
									Integer.parseInt(textoTelefono.getText()),
									textoDni.getText(),
									textoCorreo.getText(),
									nuevo_user,
									nueva_passw));
					} if (rol == Rols.DOCENTE) {
						academy.administradores.addAll((Collection<? extends Administrador>) new Docente(
									textoNombre.getText(),
									textoApellido.getText(),
									textoDni.getText(),
									textoCorreo.getText(),
								    Integer.parseInt(textoTelefono.getText()),
									nuevo_user,
									nueva_passw));
					}
					ventana.dispose();
				}
			});
            
            
            botonCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ventana.dispose();
					
				}
			});
            
            
            panelPrincipal.add(panelInterno, BorderLayout.CENTER);
            ventana.add(panelPrincipal);

            
            ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
            ventana.setVisible(true);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        });

    }
	
}
