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
 * @author jcfatjo
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
		System.out.println("TASK 7.1");
		OntClass person = model.getOntClass(ns+"Person");

		ExtendedIterator<Individual> indIter = model.listIndividuals(person);
		
		while(indIter.hasNext()) {
			Individual individual = indIter.next();
			
			System.out.println("Individual: "+individual.getURI());
		}
		System.out.println();
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("TASK 7.2");
		ExtendedIterator<OntClass> scIter = person.listSubClasses();
		
		while(scIter.hasNext()) {
			OntClass subClass = scIter.next();
			
			System.out.println("SubClass: "+subClass.getURI());
		}
		System.out.println();

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("TASK 7.3");
		OntModel modelInf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		
		OntClass personInf = modelInf.getOntClass(ns+"Person");
		
		ExtendedIterator<? extends OntResource> insIter = personInf.listInstances();
		
		System.out.println("Instances");
		while (insIter.hasNext()) {
			OntResource instance = insIter.next();
			
			System.out.println("Instance: "+instance.getURI());
		}
		System.out.println();
		
		ExtendedIterator<? extends OntResource> sciIter = personInf.listSubClasses();
		
		System.out.println("SubClasses");
		while (sciIter.hasNext()) {
			OntResource subClassInf = sciIter.next();
			
			System.out.println("SubClass: "+subClassInf.getURI());
		}
	
	}
}
