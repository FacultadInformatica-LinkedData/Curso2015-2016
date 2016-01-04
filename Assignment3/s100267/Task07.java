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
		
		ExtendedIterator<Individual> iterator = model.listIndividuals(model.getResource(ns+"Person"));
		
		while (iterator.hasNext())
		{
		    
		    System.out.println("Individual --- "+iterator.next().getURI());
		}

	
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> iterator2 = model.getOntClass(ns+"Person").listSubClasses();

		while(iterator2.hasNext()){

			System.out.println("Subclase --- "+ iterator2.next().getURI());


		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, model);
		
		OntClass Person = model.getOntClass(ns+"Person");
		
		ExtendedIterator<OntResource> iterator3 = (ExtendedIterator<OntResource>) Person.listInstances();
		ExtendedIterator<OntClass> iterator4 = (ExtendedIterator<OntClass>) Person.listSubClasses();
		
		

		while (iterator3.hasNext())
		{
		    System.out.println("Instancias de Persona: "+(Individual)iterator3.next());
		}
		 	
		
		while(iterator4.hasNext())
			
			System.out.println("Subclase de persona: "+ iterator4.next().getURI());
		
	}
}
