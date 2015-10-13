package ontologyapi;



import java.io.InputStream;



import org.apache.jena.ontology.Individual;

import org.apache.jena.ontology.OntClass;

import org.apache.jena.ontology.OntModel;

import org.apache.jena.ontology.OntModelSpec;

import org.apache.jena.ontology.OntResource;

import org.apache.jena.rdf.model.ModelFactory;

import org.apache.jena.rdf.model.ResIterator;

import org.apache.jena.util.FileManager;

import org.apache.jena.util.iterator.ExtendedIterator;

import org.apache.jena.util.iterator.Filter;

import org.apache.jena.vocabulary.RDFS;



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

		System.out.println("\nTask 7.1");

		ExtendedIterator<Individual> it = model.listIndividuals(model.getResource(ns+"Person"));

		

		while(it.hasNext()){

			Individual ind = it.next();

			System.out.println(ind.getURI());

			

		}	

		

		

		// ** TASK 7.2: List all subclasses of "Person" **

		System.out.println("\nTask 7.2");

				

		ExtendedIterator<OntClass> it2 = model.getOntClass(ns+"Person").listSubClasses();

		

		while(it2.hasNext()){

			OntClass ind = it2.next();

			System.out.println(ind.getURI());

			

		}

		

		

		

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **

		System.out.println("\nTask 7.3");

		

		

		OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);

		OntClass personOnt = ontModel.getOntClass(ns+"Person");

		ExtendedIterator<? extends OntResource> it3 = personOnt.listInstances();

		

		System.out.println();

		

		while(it3.hasNext())

			System.out.println(it3.next().getURI());

		

		it3 = personOnt.listSubClasses();

		System.out.println();

		

		while(it3.hasNext())

			System.out.println(it3.next().getURI());

	

	}

}