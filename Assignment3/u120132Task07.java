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

 * @author Abraham Lominchar Jimenez u120132

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

		System.out.println("1-----------------");

		OntClass person = model.getOntClass(ns+"Person");

		ExtendedIterator iterator1 = model.listIndividuals(person);

		while(iterator1.hasNext()){

			Individual persona1 = (Individual) iterator1.next();

			System.out.println(persona1.getLocalName());

		}

		

		// ** TASK 7.2: List all subclasses of "Person" **

		System.out.println("2------------------");

		ExtendedIterator iterator2 = person.listSubClasses();

		while(iterator2.hasNext()){

			OntClass persona2 = (OntClass) iterator2.next();

			System.out.println(persona2.getLocalName());

		}

		

		

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **

		System.out.println("3---------------------");

		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);

		OntClass person3 = inference.getOntClass(ns+"Person");

		ExtendedIterator iterator3 = person3.listInstances();

		while(iterator3.hasNext()){

			Individual persona3 = (Individual) iterator3.next();

			System.out.println(persona3.getLocalName());

		}

		ExtendedIterator iterator4 = person3.listSubClasses();

		while(iterator4.hasNext()){

			OntClass persona4 = (OntClass) iterator4.next();

			System.out.println(persona4.getLocalName());

		}

	}

}