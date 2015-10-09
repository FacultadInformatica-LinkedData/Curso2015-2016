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
 * @author Alvaro Toledano Martin
 * 
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
		System.out.println("List of individuals of Person:");
		
		OntClass persona = model.getOntClass(ns+"Person");
		
		ExtendedIterator it = persona.listInstances();
		
		
		while(it.hasNext())
		{
			Individual indiv = (Individual) it.next();
			System.out.println(indiv.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("List of subclasses of Person:");
		
		it = persona.listSubClasses();
		
		
		while(it.hasNext())
		{
			OntClass subC = (OntClass) it.next();
			System.out.println(subC.getURI());
		}	
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		System.out.println("Indirect instances of person:");
		
		OntModel modelInf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model);
		OntClass personInf = modelInf.getOntClass(ns+"Person");
		ExtendedIterator it2 = personInf.listInstances();
		
		while(it2.hasNext())
		{
			Individual insta = (Individual) it2.next();
			System.out.println(insta.getURI());
		}
		
		System.out.println("Indirect subclasses of Person:");
		
		it2 = personInf.listSubClasses();
		
		while(it2.hasNext())
		{
			OntClass subCl = (OntClass) it2.next();
			System.out.println(subCl.getURI());
		}
	
	}
}
