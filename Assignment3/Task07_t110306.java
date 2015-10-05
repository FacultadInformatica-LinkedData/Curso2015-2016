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

		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator< ? extends OntResource> it=person.listInstances();
		while(it.hasNext()){

			Individual aux=(Individual)it.next();
			System.out.println("7.1:  "+aux.getLocalName());
		}

		System.out.println();System.out.println();
		// ** TASK 7.2: List all subclasses of "Person" **

		ExtendedIterator< ? extends OntResource> it2= person.listSubClasses();
		while(it2.hasNext()){

			OntClass subclass= (OntClass)it2.next();
			System.out.println("7.2:  "+ subclass+"--"+subclass.getLocalName() );
		}


		System.out.println();System.out.println();


		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		InputStream in2 = FileManager.get().open(filename);

		OntModel modelInf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		modelInf.read(in2, null);
		OntClass personWI = modelInf.getOntClass(ns+"Person");

		ExtendedIterator<Individual> aux1 = modelInf.listIndividuals(personWI);
		ExtendedIterator<OntClass> aux2 = personWI.listSubClasses();


		while (aux1.hasNext()) {

			Individual i = aux1.next();
			System.out.println("7.3:  "+i.getURI());
		}

		while(aux2.hasNext()) {

			OntClass i = aux2.next();
			System.out.println("7.3:  "+i.getURI());
		}



	}
}
