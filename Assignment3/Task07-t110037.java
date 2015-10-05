
import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.VCARD;

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
		ExtendedIterator personas = model.getOntClass(ns+"Person").listInstances();
		while(personas.hasNext()){
			System.out.println(((Individual)personas.next()));
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subClases = model.getOntClass(ns+"Person").listSubClasses();
		while(subClases.hasNext()){
			System.out.println(((OntClass)subClases.next()));
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		ExtendedIterator personas2 = model2.getOntClass(ns+"Person").listInstances();
		ExtendedIterator subClases2 = model2.getOntClass(ns+"Person").listSubClasses();
		while(personas2.hasNext()){
			System.out.println(((Individual)personas2.next()));
		}
		while(subClases2.hasNext()){
			System.out.println(((OntClass)subClases2.next()));
		}
	}
}
