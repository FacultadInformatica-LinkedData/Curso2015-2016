package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

import sun.security.jca.GetInstance.Instance;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author elozano
 * @author isantana
 * By Javier Caballero Abenza u110340
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	private static void getInstances(OntModel m){
		ExtendedIterator ir = m.getOntClass(ns+"Person").listInstances();
		int c=1;
		while(ir.hasNext()){
		 Individual i = (Individual) ir.next();
		 System.out.println("Individual "+c+": "+i.getLocalName());
		 c++;
		}
	}
	
	private static void getSubClasses(OntModel m){
		ExtendedIterator ir = m.getOntClass(ns+"Person").listSubClasses();
		int c=1;
		while(ir.hasNext()){
		 OntClass oc = (OntClass) ir.next();
		 System.out.println("SubClass "+c+": "+oc.getLocalName());
		 c++;
		}
	}
	
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
		System.out.println("------------------ Task 7.1 ------------------");
		getInstances(model);

		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("------------------ Task 7.2 ------------------");
		getSubClasses(model);
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("------------------ Task 7.3 ------------------");
		OntModel infModel = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		getInstances(infModel);
		getSubClasses(infModel);
	}
}
