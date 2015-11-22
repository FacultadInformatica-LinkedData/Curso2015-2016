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
		OntClass person = model.getOntClass(ns+"Person");
		
		ExtendedIterator<Individual> it = model.listIndividuals(person);
		
		while (it.hasNext()) {
			Individual individual =  ind.next();
			System.out.println(individual.getLocalName());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		
		ExtendedIterator<OntClass> sub = person.listSubClasses();
		
		while (sub.hasNext()) {
			Individual clase =  sub.next();
			System.out.println(clase.getLocalName());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		
		person = inference.getOntClass(ns+"Person");
		
		it = person.listSubClasses();
		while(it.hasNext()){
			OntClass next = (OntClass) it.next();
			System.out.println(next.getLocalName());
		}
		it = person.listInstances();
		while(it.hasNext()){
			Individual next = (Individual) it.next();
			System.out.println(next.getLocalName());
		}
	       
		
		
	
	}
}
