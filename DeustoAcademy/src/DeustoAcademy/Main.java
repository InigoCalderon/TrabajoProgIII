package DeustoAcademy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {

	
	public ArrayList<?> rapid_return1(ArrayList<?> x, FileInputStream y, ObjectInputStream z) {
		
		try {
			z.close();
			y.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return x;
		
	}
	
	public void cargar_datos(String fichero, Rols rol) {
		
		try {
			
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			if (rol == Rols.ESTUDIANTE) {
				ArrayList<Estudiante> datos = (ArrayList<Estudiante>) ois.readObject();
				rapid_return1(datos, fis, ois);
			} if (rol == Rols.DOCENTE) {
				ArrayList<Docente> datos = (ArrayList<Docente>) ois.readObject();
				rapid_return1(datos, fis, ois);
			} if (rol == Rols.ADMINISTRADOR) {
				ArrayList<Administrador> datos = (ArrayList<Administrador>) ois.readObject();
				rapid_return1(datos, fis, ois);
			} else {
				System.out.println("Rol no encontrado en carga de datos.");
			}
			
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error de lectura de datos en " + fichero);
		}
		
	}
	
	public void guardar_datos(String fichero, ArrayList<?> datos) {
		
		try {
			
			FileOutputStream fos = new FileOutputStream(fichero);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(datos);
			oos.close();
			fos.close();
			
		} catch (IOException e) {
			System.err.println("Error guardando pedidos en " + fichero);
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		
		VentanaLogin1 v = new VentanaLogin1();
		//VentanaChat v2 = new VentanaChat();
		
	}

}