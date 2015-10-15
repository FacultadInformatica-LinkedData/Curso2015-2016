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
		
		ExtendedIterator<Individual> individuals = model.listIndividuals(model.getResource(ns+"Person"));
		
		while (individuals.hasNext())
		{
		    Individual indi = individuals.next();
		    System.out.println("Individual: "+indi.getURI());
		}

	
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> subclase = model.getOntClass(ns+"Person").listSubClasses();

		while(subclase.hasNext()){

			OntClass indi = subclase.next();

			System.out.println("subclase: "+ indi.getURI());


		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, model);
		
		OntClass Person1 = model.getOntClass(ns+"Person");
		
		ExtendedIterator<OntResource> iterInd = (ExtendedIterator<OntResource>) Person1.listInstances();
		ExtendedIterator<OntClass> iterSub = (ExtendedIterator<OntClass>) Person1.listSubClasses();
		
		System.out.println("dsa");

		while (iterInd.hasNext())
		{
		    Individual indi = (Individual) iterInd.next();
		    System.out.println("Instancias de Persona: "+indi);
		}
		 	
		
		while(iterSub.hasNext())
			
			System.out.println("Subclase de persona: "+ iterSub.next().getURI());
		
	}
}
