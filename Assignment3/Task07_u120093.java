package ontologyapi;

/**
 * @author Pau Maravi Busquets
 * @matricula u120093
 */


import java.io.InputStream;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;


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
		System.out.println("Task 7.1");
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator iterator = person.listInstances();
		while(iterator.hasNext()){
			Individual next = (Individual) iterator.next();
			System.out.println(((Resource) next).getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("Task 7.2");
		iterator = person.listSubClasses();
		while(iterator.hasNext()){
			OntClass next = (OntClass) iterator.next();
			System.out.println(next.getURI());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("Task 7.3");
		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		person = inference.getOntClass(ns+"Person");
		iterator = person.listInstances();
		while(iterator.hasNext()){
			Individual next = (Individual) iterator.next();
			System.out.println(next.getURI());
		}
		iterator = person.listSubClasses();
		while(iterator.hasNext()){
			OntClass next = (OntClass) iterator.next();
			System.out.println(next.getURI());
		}
	
	}
}
