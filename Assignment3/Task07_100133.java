package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iteratorator.Extendediteratorator;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author  Jonathan Rincon Calixto
 *
 */

public class Task07_s100133
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
		OntClass person = model.getOntClass(ns+"Person");
		Extendediteratorator iterator = person.listInstances();
		System.out.println("List all individuals of Person:");
		while(iterator.hasNext()){
			Individual next = (Individual) iterator.next();
			System.out.println(next.getURI());
		}

		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("Task 7.2\n");
		iterator = person.listSubClasses();
		System.out.println("List all subclasses of Person:");
		while(iterator.hasNext()){
			OntClass next = (OntClass) iterator.next();
			System.out.println(next.getURI());
		}		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses.
		// ** TIP: you need some inference... **
		System.out.println("Task 7.3\n");
		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass pinference = inference.getOntClass(ns+"Person");
		Extendediteratorator iInference = pinference.listInstances();
		System.out.println("Individuals of Person with Inferencia:");
		while(iInference.hasNext()){
			Individual next = (Individual) iInference.next();
			System.out.println(next.getURI());
		}
		
		iInference = pinference.listSubClasses();
		System.out.println("Subclasses of Person with Inferencia:");
		while(iInference.hasNext()){
			OntClass next = (OntClass) iInference.next();
			System.out.println(next.getURI());
		}
	
	}
}
