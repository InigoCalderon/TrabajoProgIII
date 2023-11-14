package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import DeustoAcademy.Administrador;


public class VentanaAdministrador extends JFrame{
	protected JRadioButton radioEstudiante;
	protected JRadioButton radioDocente;
	protected JRadioButton radioCuentas;
	protected JButton botonIngresar;
	public VentanaAdministrador(Administrador administrdor) { // UNAI CAMBIA EL Administrador por DEUSTOACADEMY PARA LLEVAR LA INFO QUE HAY EN LA CLASE
		
		JLabel etiquetaApartados = new JLabel("Apartados de datos");
		botonIngresar = new JButton("Ingresar");
		radioEstudiante = new JRadioButton("Estudiantes");
		radioDocente = new JRadioButton("Docentes");
		radioCuentas = new JRadioButton("Cuentas Academy");
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioEstudiante);
		bg.add(radioDocente);
		bg.add(radioCuentas);
		JPanel panelRadio = new JPanel();
		JPanel panelIngresar = new JPanel();
		JPanel panelTexto = new JPanel();
		
		
		botonIngresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (radioEstudiante.isSelected()) {
					System.out.println("estudiante");
					new VentanaAdiministradorAccesoEstudiantes(administrdor);
				}else if(radioDocente.isSelected()) {
					System.out.println("Docente");
					new VentanaAdministradorAccesoDocente(administrdor);
				}else if(radioCuentas.isSelected()) {
					System.out.println("cuentas");
				}else {
					JOptionPane.showMessageDialog(null, "Has de seleccionar algún apartado ", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
		
		
		
		
		panelRadio.add(radioEstudiante);
		panelRadio.add(radioDocente);
		panelRadio.add(radioCuentas);
		panelIngresar.add(botonIngresar);
		panelTexto.add(etiquetaApartados);
		
		
		JFrame ventana = new JFrame("Ventana Administrador");
		ventana.add(panelRadio, BorderLayout.CENTER);
		ventana.add(panelIngresar, BorderLayout.SOUTH);
		ventana.add(panelTexto, BorderLayout.NORTH);
		ventana.setSize(960, 560); // tamaño grande, 960*560 y tamaño pequeño 720*480
        ventana.setVisible(true);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
public static void main(String[] args) {
		
		new VentanaAdministrador(new Administrador());
		
	}
}
