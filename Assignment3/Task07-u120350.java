package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author David Márquez Delgado
 *
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		//Debug: Imprimir modelo
//		System.out.println("Modelo actual. ns=["+ns+"]");
//		model.write(System.out, "RDF/XML-ABBREV");
//		System.out.println();

		
		// ** TASK 7.1: List all individuals of "Person" **
		String clase = "Person";
		System.out.println("===============Task 7.1===============");
		System.out.println("---------------Listado de todas las instancias de '"+clase+"'---------------");
		ImprimirInstancias(model,clase);
		System.out.println("===============FIN Task 7.1===========");
		System.out.println();

		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("===============Task 7.2===============");
		System.out.println("---------------Listado de todas las subclases de '"+clase+"'---------------");
		ImprimirSubclases(model,clase);
		System.out.println("===============FIN Task 7.2===========");
		System.out.println();

		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model);
		System.out.println("===============Task 7.3===============");
		System.out.println("---------------Listado de todas las instancias de '"+clase+"'---------------");
		ImprimirInstancias(model,clase);
		System.out.println();

		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("---------------Listado de todas las subclases de '"+clase+"'---------------");
		ImprimirSubclases(model,clase);
		System.out.println("===============FIN Task 7.3============");
		System.out.println();
		
	
	}
	
	/**
	 * Descripción:	Permite imprimir por la salida estándar el listado 
	 * 				de todas las instancias de la clase introducida para el modelo introducido.
	 * @param model	Modelo del que se quieren recuperar los datos.
	 * @param clase Clase de la que se quieren recuperar los datos.
	 */
	private static void ImprimirInstancias(OntModel model, String clase){
		 ExtendedIterator<? extends OntResource> iterador = model.getOntClass(ns + clase).listInstances();
		while(iterador.hasNext()){
			Individual individual = (Individual) iterador.next();
			System.out.println(individual);
		}
	}
	
	/**
	 * Descripción:	Permite imprimir por la salida estándar el listado 
	 * 				de todas las subclases de la clase introducida para el modelo introducido.
	 * @param model	Modelo del que se quieren recuperar los datos.
	 * @param clase Clase de la que se quieren recuperar los datos.
	 */
	private static void ImprimirSubclases(OntModel model,String clase){
		 ExtendedIterator<OntClass> iterador = model.getOntClass(ns + clase).listSubClasses();
		while(iterador.hasNext()){
			OntClass individual = (OntClass) iterador.next();
			System.out.println(individual);
		}
	}
}
