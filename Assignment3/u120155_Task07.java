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
		System.out.println("Solucion a Task 7.1");
		ExtendedIterator eIt = model.getOntClass(ns + "Person").listInstances();
		
		while (eIt.hasNext()) {
			System.out.println(model.getIndividual(eIt.next().toString()).getURI());
		}
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("Solucion a Task 7.2");
		eIt = model.getOntClass(ns + "Person").listSubClasses();
		
		while (eIt.hasNext()) {
			System.out.println(model.getOntClass(eIt.next().toString()).getURI());
		}
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("Solucion a Task 7.3");
		OntModel task7_3 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		eIt = task7_3.getOntClass(ns + "Person").listInstances();
		
		while (eIt.hasNext()) {
			System.out.println(task7_3.getIndividual(eIt.next().toString()).getURI());
		}
		
		eIt = task7_3.getOntClass(ns + "Person").listSubClasses();
	
		while (eIt.hasNext()) {
			System.out.println(task7_3.getOntClass(eIt.next().toString()).getURI());
		}
	}
}
