package ontologyapi;

import java.io.InputStream;
import java.util.Iterator;

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
 * 
 * @author Ismael González Oviedo
 *
 */
public class Task07 {
	public static String ns = "http://somewhere#";

	public static void main(String args[]) {
		String filename = "example6.rdf";

		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);

		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);

		if (in == null)
			throw new IllegalArgumentException("File: " + filename + " not found");

		// Read the RDF/XML file
		model.read(in, null);

		// ** TASK 7.1: List all individuals of "Person" **
		OntClass people = model.getOntClass(ns + "Person");
		ExtendedIterator<Individual> it = model.listIndividuals();
		while (it.hasNext()) {
			Individual individualElement = it.next();
			System.out.println("Individual person: " + individualElement.getURI());
		}

		// ** TASK 7.2: List all subclasses of "Person" **
		OntClass people2 = model.getOntClass(ns + "Person");
		ExtendedIterator<OntClass> it2 = people2.listSubClasses();
		while (it2.hasNext()) {
			OntClass subClaseElement = it2.next();
			System.out.println("SubClass: " + subClaseElement.getURI());
		}

		// ** TASK 7.3: Make the necessary changes to get as well indirect
		// instances and subclasses. TIP: you need some inference... **
		OntModelSpec spec = OntModelSpec.RDFS_MEM_RDFS_INF;
		OntModel model2 = ModelFactory.createOntologyModel(spec, model);
		OntClass people3 = model2.getOntClass(ns + "Person");

		ExtendedIterator<? extends OntResource> it3 = people3.listInstances();
		
		while (it3.hasNext()) {
			System.out.println("Individual person(2): " + it3.next().getURI());
		}
		
		ExtendedIterator<OntClass> it4 = people3.listSubClasses();

		while (it4.hasNext()) {
			System.out.println("SubClass(2): " +it4.next().getURI());
		}

	}
}
