package task7

import scala.collection.JavaConversions.asScalaIterator

import org.apache.jena.ontology.OntModelSpec
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.util.FileManager

object Task7 extends App {
  val ns = "http://somewhere#"
  val filename = "example6.rdf"

  // Create an empty model 
  val model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM)

  // Use the FileManager to find the input file
  val in = FileManager.get().open(filename)

  if (in == null)
    throw new IllegalArgumentException(s"File: $filename not found")

  // Read the RDF/XML file
  model.read(in, null)

  println("Without inference:\n")
  // get person ontology class :)
  val person = model.getOntClass(s"${ns}Person")
  
  // ** TASK 7.1: List all individuals of "Person" **
  println("Individuals of Person:")
  person.listInstances() foreach println 

  // ** TASK 7.2: List all subclasses of "Person" **
  println("\nSubclasses of Person:")
  person.listSubClasses() foreach println
  
  // ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
  println("\nWith inference:\n")
  // We just need to declare the model with RDFS_MEM_RDFS_INF, we can create again (like before but with different spec)
  // or just create from the previous model. That's what we are going to do :)
  val modelinf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model)
  
  // get the new person with inference!
  val personinf = modelinf.getOntClass(s"${ns}Person")
  println("Instances of person")
  personinf.listInstances() foreach println
  println("\nSubclasses of person")
  personinf.listSubClasses() foreach println
}