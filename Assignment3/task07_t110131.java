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

		

		System.out.println("-------7.1-------");

		OntClass person = model.getOntClass(ns+"Person");

		ExtendedIterator it = person.listInstances();

		while(it.hasNext()){

			Individual next = (Individual) it.next();

			System.out.println(next.getURI());

		}

		

		

		// ** TASK 7.2: List all subclasses of "Person" **

		

		System.out.println("-------7.2-------");

		it = person.listSubClasses();

		while(it.hasNext()){

			OntClass next = (OntClass) it.next();

			System.out.println(next.getURI());

		}

		

		

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **

		

		System.out.println("-------7.3-------");

		OntModel ontology = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);

		

		OntClass personO = ontology.getOntClass(ns+"Person");

		

		ExtendedIterator<? extends OntResource> iterator = personO.listInstances();

		



		while(iterator.hasNext()){

			Individual next = (Individual) iterator.next();

			System.out.println(next.getURI());

		}

		

		iterator = personO.listSubClasses();

		while(iterator.hasNext()){

			OntClass next = (OntClass) iterator.next();

			System.out.println(next.getURI());

		}

		

	

	}

}

