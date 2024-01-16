package main;


import java.util.ArrayList;
import java.util.Random;

import domain.Estudiante;
import domain.Idioma;

public class MetodoRecursivo {
	protected Academy datos;
	protected static ArrayList<Idioma> idiomas;
	
	
	
	public static void main(String[] args) {
		idiomas = new ArrayList<Idioma>();
		for (Idioma i : Idioma.values()) {
			idiomas.add(i);
		}
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		for (int i = 0; i < 100; i++) {
			ArrayList<Idioma> idiomasRandom = IdiomasRandom(idiomas.size());
			Estudiante estudiante = new Estudiante("Nombre"+i, "Apellido"+i, 644440240+i,  "estudiante"+i+"@gmail.com", i+"6092625", "usuario"+i, "contraseña"+i, idiomasRandom);
			estudiantes.add(estudiante);
		}
		
		MetodoRecursivo clase = new MetodoRecursivo();
		clase.agrupacionRecursiva(estudiantes, 3);
		
	}
	
	// Recursividad
	// Recibimos una lista con estudiantes la cual es dividida en grupos diferentes mediante métodos recursivos, dependiendo de las solicitudes del usuario en cuanto al tamaño de estos grupos
	public void agrupacionRecursiva(ArrayList<Estudiante> estudiantes, int tamaño) {
		
		ArrayList<ArrayList<Estudiante>> grupos = new ArrayList<ArrayList<Estudiante>>();
		ArrayList<Estudiante> grupo = new ArrayList<Estudiante>();
		recur(estudiantes, tamaño, grupos , grupo);
	
		for (ArrayList<Estudiante> g: grupos) {
			System.out.println(g +"\n");
		}
		System.out.println("Combinaciones : " + grupos.size());
		
	}
	public void recur(ArrayList<Estudiante> estudiantes, int tamaño, ArrayList<ArrayList<Estudiante>> Grupos, ArrayList<Estudiante> grupo) {
		 if (estudiantes.isEmpty()) { // CB --> No queda ningún estudiante sin asignar de la lista inicial
	            if (!Grupos.isEmpty()) {
	            	Grupos.add(new ArrayList<>(grupo)); // Se añade la última asignación por si fuera impar el tamaño solicitado
	            }
	            return; // termina
	        }else {
	        	
	   	        if (grupo.size() == tamaño) { // CB --> Al alcanzar el tamaño límite, se añade la asignación al resultado
	   	        	Grupos.add(new ArrayList<>(grupo));
	   	            grupo.clear();
	   	        }
	   	     grupo.add(estudiantes.remove(0)); // vamos añadiendo estudiantes a un nuevo grupo, a la vez que vaciamos la lista de estudiantes proporcionada inicialmente
	   	       
	   	     recur(estudiantes, tamaño, Grupos, grupo);  // CR --> llamos nuevamente al método tras añadir un nuevo estudiante a la nueva asignación
	        }

	        
		
	        
		
	}
	
	
	
	
	 public static ArrayList<Idioma> IdiomasRandom(int cantidadIdiomas) {
	        
	        Random random = new Random();
	        Idioma[] Arrayidiomas = Idioma.values();
	        ArrayList<Idioma> idiomas = new ArrayList<>();
	        for (int i = 0; i < cantidadIdiomas; i++) {
	            int indice = random.nextInt(Arrayidiomas.length);
	            Idioma idiomaAleatorio = Arrayidiomas[indice];
	            idiomas.add(idiomaAleatorio);
	        }

	        return idiomas;
	    }
}
