package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;


/**
 * Task 07: Querying ontologies (RDFs)
 * @author elozano
 * @author isantana
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
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		System.out.println("Task 7.1\n");
		//Se crea la clase persona para después el iterador hacerlo solo
		//sobre la lista de inviduales que sean una persona
		OntClass persona = model.getOntClass(ns+"Person");
		ExtendedIterator uno = model.listIndividuals(persona);
		
		//Se recorre el iterador y se imprime el nombre
		while(uno.hasNext()){
			Individual person =  (Individual) uno.next();
			System.out.println(person.getLocalName());
		}
		
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\n\nTask 7.2\n");
		/*En la función auxiliar se recorren las ramas completas 
		 * de los hijos de la clase Person*/
		hijos(model, persona);				
		
		
	
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("\n\nTask 7.3\n");
		/*En la función auxiliar se recorren las ramas completas 
		 * de los hijos de la clase Person y se mencionan los individuos
		 * que forman parte de las subclases*/
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass perso = model2.getOntClass(ns+"Person");
		ExtendedIterator tres = perso.listInstances();
		while(tres.hasNext()){
			Individual per = (Individual) tres.next();
			System.out.println("Instance of Person: "+per.getLocalName());
		}
		ExtendedIterator cuatro = perso.listSubClasses();
		while(cuatro.hasNext()){
			OntClass subcla = (OntClass) cuatro.next();
			System.out.println("Subclass of Person: "+subcla.getLocalName());
		}
		
	}
private static void hijos(OntModel model, OntClass sub){
	ExtendedIterator dos = sub.listSubClasses();
	while(dos.hasNext()){
		OntClass puede =  model.getOntClass(ns+sub.getLocalName());
		puede = (OntClass) dos.next();
		System.out.println(puede.getLocalName().toString());
		hijos(model,puede);
		}
	}
}
