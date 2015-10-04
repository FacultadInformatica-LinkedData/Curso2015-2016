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
 * Juan Gómez Trapero - t110021
 */
public class Task07
{
	public static String ns = "http://somewhere#";

  private static void printInstances(OntModel customModel) {
    ExtendedIterator customIterator = customModel.getOntClass(ns+"Person").listInstances();
    while(customIterator.hasNext()){
     Individual ind = (Individual) customIterator.next();
     System.out.println(ind.getURI());
    }
  }

  private static void printSubclasses(OntModel customModel) {
    ExtendedIterator customIterator = customModel.getOntClass(ns+"Person").listSubClasses();
		while(customIterator.hasNext()){
		 OntClass nextOnt = (OntClass) customIterator.next();
		 System.out.println(nextOnt.getURI());
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
    System.out.println("Individuals");
    printInstances(model);

		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("Subclasses of Person");
    printSubclasses(model);

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("Indirect Instances and Subclasses");
		OntModel inferModel = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		printInstances(inferModel);
		printSubclasses(inferModel);
	}
}
