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
 * @author Emilio San José
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
		ExtendedIterator person = model.getOntClass(ns+"Person").listInstances();
		while (person.hasNext()) {
			Individual per = (Individual) person.next();
			System.out.println("Individual person: " + per.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subperson = model.getOntClass(ns+"Person").listSubClasses();
		while (subperson.hasNext()) {
			OntClass per = (OntClass) subperson.next();
			System.out.println("Subclasses: " + per);
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel subclasses = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass subclassesPerson = subclasses.getOntClass(ns+"Person");
		ExtendedIterator iteratorIns = subclassesPerson.listInstances();
		ExtendedIterator iteratorClas = subclassesPerson.listSubClasses();
		
		while(iteratorIns.hasNext()) {
			Individual ind = (Individual)iteratorIns.next();
			System.out.println((ind).getURI());
		}		
		while(iteratorClas.hasNext()) {
			OntClass ont = (OntClass)iteratorClas.next();
			System.out.println((ont).getURI());
		}
	}
}
