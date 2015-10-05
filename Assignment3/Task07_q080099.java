package ontologyapi;



import java.io.InputStream;



import org.apache.jena.ontology.Individual;

import org.apache.jena.ontology.OntClass;

import org.apache.jena.ontology.OntModel;

import org.apache.jena.ontology.OntModelSpec;

import org.apache.jena.ontology.OntResource;

import org.apache.jena.rdf.model.Model;

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

		

		OntClass Person = model.getOntClass(ns+"Person");

		// ** TASK 7.1: List all individuals of "Person" **

		System.out.println("All individuals: ");

		ExtendedIterator<OntResource> iterador1 = (ExtendedIterator<OntResource>) Person.listInstances();

		while (iterador1.hasNext()) {

			Individual temp = (Individual) iterador1.next();

			System.out.println(temp);

		}

		System.out.println("");

		// ** TASK 7.2: List all subclasses of "Person" **

		System.out.println("All subclasses: ");

		ExtendedIterator<OntClass> iterador2 = Person.listSubClasses();

		while (iterador2.hasNext()) {

			OntClass temp = (OntClass) iterador2.next();

			System.out.println(temp.toString());

		}

		System.out.println("");

		

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **

		//Creamos un modelo declarandolo con el parámetro adecuado a partir del modelo anterior

		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, model);

		//Debería haberme hecho un método que imprimiese... Esto de copiar y pegar código... En fin.

		System.out.println("Inferencia");

		OntClass personWI = model.getOntClass(ns+"Person");

		System.out.println("instancias de persona");

		ExtendedIterator<OntResource> iterador31 = (ExtendedIterator<OntResource>) personWI.listInstances();

		while (iterador31.hasNext()) {

			Individual temp = (Individual) iterador31.next();

			System.out.println(temp);

		}

		System.out.println("subclases de persona");

		ExtendedIterator<OntClass> iterador32 = (ExtendedIterator<OntClass>) personWI.listSubClasses();

		while (iterador32.hasNext()) {

			OntClass temp = (OntClass) iterador32.next();

			System.out.println(temp);

		}

	}

}

