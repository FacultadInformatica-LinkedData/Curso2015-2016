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
 * 
 * @author Miguel Alejandro Smurawski Taboada
 *
 */
public class Task07 {
	public static String ns = "http://somewhere#";

	public static void main(String args[]) {
		String filename = "example6.rdf";

		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);

		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);

		if (in == null)
			throw new IllegalArgumentException("File: " + filename + " not found");

		// Read the RDF/XML file
		model.read(in, null);

		// ** TASK 7.1: List all individuals of "Person" **
		
		OntClass person = model.getOntClass(ns + "Person");
		
		ExtendedIterator <? extends OntResource> iterator = person.listInstances();
		
		Individual individual=null;
		
		while (iterator.hasNext()) {
			individual = (Individual) iterator.next();
			System.out.println(individual);
		}
		
		System.out.println("\n");

		
		// ** TASK 7.2: List all subclasses of "Person" **
		
		iterator = person.listSubClasses();
		
		OntClass next=null;
		
		while (iterator.hasNext()) {
			next = (OntClass) iterator.next();
			System.out.println(next);
		}
		
		System.out.println("\n");

		// ** TASK 7.3: Make the necessary changes to get as well indirect
		// instances and subclasses. TIP: you need some inference... **
		
		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		
		person = inference.getOntClass(ns + "Person");
		
		iterator = person.listInstances();
		
		while (iterator.hasNext()) {
			individual = (Individual) iterator.next();
			System.out.println(next);
		}
		
		System.out.println("\n");
		
		iterator = person.listSubClasses();
		
		while (iterator.hasNext()) {
			next = (OntClass) iterator.next();
			System.out.println(next);
		}

	}
}
