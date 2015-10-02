package task6

import org.apache.jena.ontology.OntModelSpec
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.util.FileManager
import org.apache.jena.vocabulary.VCARD

object Task6 extends App {
  val ns = "http://somewhere#"
  val foafNS = "http://xmlns.com/foaf/0.1/"
  val foafEmailURI = foafNS + "email"
  val foafKnowsURI = foafNS + "knows"
  val stringTypeURI = "http://www.w3.org/2001/XMLSchema#string"

  val filename = "example5.rdf"

  // Create an empty model
  val model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM)
  model.setNsPrefix("foaf", foafNS)
  // Use the FileManager to find the input file
  val in = FileManager.get().open(filename)

  if (in == null)
    throw new IllegalArgumentException(s"File: $filename not found")

  // Read the RDF/XML file
  model.read(in, null)

  // Create a new class named "Researcher"
  val researcher = model.createClass(s"${ns}Researcher")

  // ** TASK 6.1: Create a new class named "University" **
  val university = model.createClass(s"${ns}University")

  // ** TASK 6.2: Add "Researcher" as a subclass of "Person" **
  val person = model.getOntClass(s"${ns}Person")
  person.addSubClass(researcher)
  // the result is the same with researcher.addSuperClass(person)

  // ** TASK 6.3: Create a new property named "worksIn" **
  val worksIn = model.createOntProperty(s"${ns}worksIn")
  worksIn.addDomain(person)
  worksIn.addRange(university)
  worksIn.addLabel("Works in", "en")

  // ** TASK 6.4: Create a new individual of Researcher named "Jane Smith" **
  val janeSmith = researcher.createIndividual(s"${ns}JaneSmith")

  // ** TASK 6.5: Add to the individual JaneSmith the fullName, given and family names **
  janeSmith.addLiteral(VCARD.FN, model.createTypedLiteral("Jane Smith", stringTypeURI))
    .addLiteral(VCARD.Family, model.createTypedLiteral("Smith", stringTypeURI))
    .addLiteral(VCARD.Given, model.createTypedLiteral("Jane", stringTypeURI))

  // ** TASK 6.6: Add UPM as the university where John Smith works **
  // val upm = model.getIndividual(s"${ns}UPM")
  // I had to create it, I don't know why I can't get the individual, I don't even see it in:
  // println(model.listIndividuals().toList())
  val upm = university.createIndividual(s"${ns}UPM")
  val johnSmith = model.getIndividual(s"${ns}JohnSmith")
  johnSmith.addProperty(worksIn, upm)

  model.write(System.out, "RDF/XML-ABBREV")
  // model.write(System.out, "TURTLE") // I like to read it in TURTLE :)

}
