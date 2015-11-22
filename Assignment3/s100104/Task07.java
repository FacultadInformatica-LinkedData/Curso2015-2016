package ontologyapi;

import java.io.InputStream;

import javax.management.modelmbean.ModelMBeanOperationInfo;

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
 * @author elozano
 * @author isantana
 * @author Jorge Munioz Pedrazuela - s100104
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
		ExtendedIterator<Individual> iter = model.listIndividuals(model.getOntClass(ns+"Person"));
		
		System.out.println("-------- All Individuals of Person --------\n");
		while(iter.hasNext())
			System.out.println(" -> " + iter.next().getURI());
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> iter2 = model.getOntClass(ns+"Person").listSubClasses();
		
		System.out.println("\n-------- All the Subclasses of Person --------\n");
		while(iter2.hasNext())
			System.out.println(" -> " + iter2.next().getURI());
			
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel modelOnt = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass personOnt = modelOnt.getOntClass(ns+"Person");
		ExtendedIterator iter3 = personOnt.listInstances();
		
		System.out.println("\n-------- All Individuals of Person (Inference) --------\n");
		
		while(iter3.hasNext())
			System.out.println(((Individual)iter3.next()).getURI());
		
		iter3 = personOnt.listSubClasses();
		System.out.println("\n-------- All the Subclasses of Person (Inference) --------\n");
		
		while(iter3.hasNext())
			System.out.println(((OntClass)iter3.next()).getURI());
		
		
	}
}
