package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
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
		System.out.println("Individuals of Person");
		System.out.println("---------------------");
		ExtendedIterator it = model.getOntClass(ns+"Person").listInstances();
		while (it.hasNext())
		{
			Object ind = it.next();
			System.out.println(ind);
		}
		System.out.println("                     ");
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("Subclasses of Person ");
		System.out.println("---------------------");
		ExtendedIterator<OntClass> itcl = model.getOntClass(ns+"Person").listSubClasses();
		while (itcl.hasNext())
		{
			OntClass ont = itcl.next();
			System.out.println(ont);
		}
		System.out.println("                     ");
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel ont = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model);
		System.out.println("Indirect subclasses and instances of Person");
		System.out.println("-------------------------------------------------");
		
		it = ont.getOntClass(ns+"Person").listInstances();
		while (it.hasNext())
		{
			Object ind = it.next();
			System.out.println(ind);
		}
		itcl = ont.getOntClass(ns+"Person").listSubClasses();
		while (itcl.hasNext())
		{
			OntClass on = itcl.next();
			System.out.println(on);
		}
		System.out.println("                     ");
		
	}
}
