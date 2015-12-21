package sportsMadrid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;


public class Consultas {

	public static void main(String args[]) throws FileNotFoundException {
        
		/*String id = "<http://smartcity.linkeddata.es/lcc/resource/District/CHAMARTIN>";
		String nombre = "";
		String abstrac = "";
		String postalCode = "";
		String queryString = " PREFIX dcterms: <http://purl.org/dc/terms/> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX dbp: <http://dbpedia.org/ontology/> "
				+ "PREFIX dbo: <http://dbpedia.org/ontology/> "
				+ "SELECT ?district  ?name ?abs ?pc "
				+ "WHERE { "
				+ "?district dcterms:subject <http://dbpedia.org/resource/Category:Districts_of_Madrid> ; "
				+ "rdfs:label ?name ;"
				+ "dbo:abstract ?abs ;"
				+ "dbo:postalCode ?pc ."
				+ "FILTER (LANG(?name) = 'es') "
				+ "FILTER (LANG(?abs) = 'es')}";
		Query query = QueryFactory.create(queryString);
		// Inicializacion de queryExecution factory con el servicio remoto
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet results = qexec.execSelect();
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource subj = (Resource) binding.get("district");
			//Resource id = (Resource) binding.get("y");
			Literal ee = binding.getLiteral("name");
			Literal abs = binding.getLiteral("abs");
			Literal pc = binding.getLiteral("pc");
			System.out.println(ee.toString());
			if (ee.toString().indexOf("Chamartín") != -1 && id.indexOf("CHAMARTIN") != -1){
				nombre = "Chamartín";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString();//.replaceAll("^^<http://www.w3.org/2001/XMLSchema#string>", "");
				break;
			}
		}
		System.out.println("Nombre: "+nombre);
		System.out.println("Abs: "+abstrac);
		System.out.println("PC: "+postalCode);*/
		
		
	}
	
	public ResultSet consultaID (String name){
		String filename = "Instalaciones_Deportivas.ttl";
		String filename2 = "Actividades_Deportivas (1).ttl";
		
		// Create an empty model
		Model model=ModelFactory.createDefaultModel();		
		model.read(filename, "TURTLE");
		Model model2=ModelFactory.createDefaultModel();		
		model2.read(filename2, "TURTLE");
		
		String queryString = "PREFIX schema: <http://smartcity.linkeddata.es/lcc/resource/>" +
				"PREFIX onto: <http://ontology.tno.nl/logico#>"+
				"SELECT *"+"WHERE { ?x <http://dbpedia.org/ontology/Name> ?y ."
				+"?x <http://ontology.tno.nl/logico#hasID> ?id }";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		ResultSet results = qexec.execSelect();
		
		String iD = null;
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource subj = (Resource) binding.get("x");
			//Resource id = (Resource) binding.get("y");
			Literal ee = binding.getLiteral("y");
			//Resource id = (Resource) binding.get("id");
			Literal id = binding.getLiteral("id");
			if (ee.toString().equals(name)){
				iD = id.toString();
				break;
			}
		}
		
		if (iD != null) {
			queryString = "PREFIX schema: <http://smartcity.linkeddata.es/lcc/resource/>" +
					"PREFIX onto: <http://ontology.tno.nl/logico#>"+
					"SELECT * "+"WHERE { ?x <http://smartcity.linkeddata.es/lcc/ontology/inCenter> <http://smartcity.linkeddata.es/lcc/resource/CentroDeportivo/"+iD+"> ."
							+ "?x <http://dbpedia.org/ontology/title> ?y ."
							+ "?x <http://www.w3.org/2006/time#day> ?z}";
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.create(query, model2) ;
			results = qexec.execSelect();
			
		}
		
		return results;
	}
	
	public ResultSet consultaFecha(String id) {
		
		
		String filename = "Instalaciones_Deportivas.ttl";
		String filename2 = "Actividades_Deportivas (1).ttl";
		
		// Create an empty model
		Model model=ModelFactory.createDefaultModel();		
		model.read(filename, "TURTLE");
		Model model2=ModelFactory.createDefaultModel();		
		model2.read(filename2, "TURTLE");
		
		String queryString = "PREFIX schema: <http://smartcity.linkeddata.es/lcc/resource/>" +
				"PREFIX onto: <http://ontology.tno.nl/logico#>"+
				"SELECT * "+"WHERE { ?x <http://dbpedia.org/ontology/title> '"+id+"' ."
						+ "?x <http://www.w3.org/2006/time#day> ?y ."
						+ "?x <http://www.w3.org/2006/time#hour> ?z ."
						+ "?x <http://smartcity.linkeddata.es/lcc/ontology/inDistrict> ?dis ."
						+ "?dis a ?diss }";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model2) ;
		ResultSet results = qexec.execSelect();
		

	
		
		return results;
	}
	
public ResultSet consultaTransporte(String id) {
		
		
		String filename = "Instalaciones_Deportivas.ttl";
		String filename2 = "Actividades_Deportivas (1).ttl";
		
		// Create an empty model
		Model model=ModelFactory.createDefaultModel();		
		model.read(filename, "TURTLE");
		Model model2=ModelFactory.createDefaultModel();		
		model2.read(filename2, "TURTLE");
		
		String queryString = "PREFIX schema: <http://smartcity.linkeddata.es/lcc/resource/>" +
				"PREFIX onto: <http://ontology.tno.nl/logico#>"+
				"SELECT * "+"WHERE { ?x <http://dbpedia.org/ontology/Name> '"+id+"' ."
						+ "?x <http://dbpedia.org/ontology/MeanOfTransportation> ?tr }";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		ResultSet results = qexec.execSelect();
		
		return results;
	}

public ResultSet consultaTlf(String id) {
	
	
	String filename = "Instalaciones_Deportivas.ttl";
	String filename2 = "Actividades_Deportivas (1).ttl";
	
	// Create an empty model
	Model model=ModelFactory.createDefaultModel();		
	model.read(filename, "TURTLE");
	Model model2=ModelFactory.createDefaultModel();		
	model2.read(filename2, "TURTLE");
	
	String queryString = "PREFIX schema: <http://smartcity.linkeddata.es/lcc/resource/>" +
			"PREFIX onto: <http://ontology.tno.nl/logico#>"+
			"SELECT * "+"WHERE { ?x <http://dbpedia.org/ontology/Name> '"+id+"' ."
					+ "?x <http://ontology.tno.nl/logico/#hasPhoneNr> ?tlf }";
	Query query = QueryFactory.create(queryString);
	QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
	ResultSet results = qexec.execSelect();
	
	return results;
}
public ResultSet consultaEmail(String id) {
	
	
	String filename = "Instalaciones_Deportivas.ttl";
	String filename2 = "Actividades_Deportivas (1).ttl";
	
	// Create an empty model
	Model model=ModelFactory.createDefaultModel();		
	model.read(filename, "TURTLE");
	Model model2=ModelFactory.createDefaultModel();		
	model2.read(filename2, "TURTLE");
	
	String queryString = "PREFIX schema: <http://smartcity.linkeddata.es/lcc/resource/>" +
			"PREFIX onto: <http://ontology.tno.nl/logico#>"+
			"SELECT * "+"WHERE { ?x <http://dbpedia.org/ontology/Name> '"+id+"' ."
					+ "?x <http://ontology.tno.nl/logico#hasEmail> ?email }";
	Query query = QueryFactory.create(queryString);
	QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
	ResultSet results = qexec.execSelect();
	
	return results;
}

public ResultSet consultaCalle(String id) {
	
	
	String filename = "Instalaciones_Deportivas.ttl";
	String filename2 = "Actividades_Deportivas (1).ttl";
	
	// Create an empty model
	Model model=ModelFactory.createDefaultModel();		
	model.read(filename, "TURTLE");
	Model model2=ModelFactory.createDefaultModel();		
	model2.read(filename2, "TURTLE");
	
	String queryString = "PREFIX schema: <http://smartcity.linkeddata.es/lcc/resource/>" +
			"PREFIX onto: <http://ontology.tno.nl/logico#>"+
			"SELECT * "+"WHERE { ?x <http://dbpedia.org/ontology/Name> '"+id+"' ."
					+ "?x <http://ontology.tno.nl/logico#hasStreetName> ?calle }";
	Query query = QueryFactory.create(queryString);
	QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
	ResultSet results = qexec.execSelect();
	
	return results;
}
	
	public String [] consultaDistrito(String id) {
		
		String nombre = "";
		String abstrac = "";
		String postalCode = "";
		String queryString = " PREFIX dcterms: <http://purl.org/dc/terms/> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX dbp: <http://dbpedia.org/property/> "
				+ "PREFIX dbo: <http://dbpedia.org/ontology/> "
				+ "SELECT ?district  ?name ?abs ?pc "
				+ "WHERE { "
				+ "?district dcterms:subject <http://dbpedia.org/resource/Category:Districts_of_Madrid> ; "
				+ "rdfs:label ?name ;"
				+ "dbo:abstract ?abs ;"
				+ "dbp:postalCode ?pc ."
				+ "FILTER (LANG(?name) = 'es') "
				+ "FILTER (LANG(?abs) = 'es')}";
		Query query = QueryFactory.create(queryString);
		// Inicializacion de queryExecution factory con el servicio remoto
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet results = qexec.execSelect();
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource subj = (Resource) binding.get("district");
			//Resource id = (Resource) binding.get("y");
			Literal ee = binding.getLiteral("name");
			Literal abs = binding.getLiteral("abs");
			Literal pc = binding.getLiteral("pc");
			if (ee.toString().indexOf("Arganzuela") != -1 && id.indexOf("ARGANZUELA") != -1){
				nombre = "Arganzuela";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Chamberí") != -1 && id.indexOf("CHAMBERI") != -1){
				nombre = "Chamberí";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Moncloa-Aravaca") != -1 && id.indexOf("MONCLOAARAVACA") != -1){
				nombre = "Moncloa-Aravaca";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Salamanca") != -1&& id.indexOf("SALAMANCA") != -1){
				nombre = "Salamanca";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Usera") != -1 && id.indexOf("USERA") != -1){
				nombre = "Usera";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Retiro") != -1 && id.indexOf("RETIRO") != -1){
				nombre = "Retiro";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Carabanchel") != -1 && id.indexOf("CARABANCHEL") != -1){
				nombre = "Carabanchel";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Centro") != -1 && id.indexOf("CENTRO") != -1){
				nombre = "Centro";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Latina") != -1 && id.indexOf("LATINA") != -1){
				nombre = "Latina";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("San Blas-Canillejas") != -1 && id.indexOf("SANBLASCANILLEJAS") != -1){
				nombre = "San Blas-Canillejas";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Barajas") != -1 && id.indexOf("BARAJAS") != -1){
				nombre = "Barajas";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Villaverde") != -1 && id.indexOf("VILLAVERDE") != -1){
				nombre = "Villaverde";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Chamartín") != -1 && id.indexOf("CHAMARTIN") != -1){
				nombre = "Chamartín";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Ciudad Lineal") != -1 && id.indexOf("CIUDADLINEAL") != -1){
				nombre = "Ciudad Lineal";
				break;
			}
			if (ee.toString().indexOf("Hortaleza") != -1 && id.indexOf("HORTALEZA") != -1){
				nombre = "Hortaleza";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Moratalaz") != -1 && id.indexOf("MORATALAZ") != -1){
				nombre = "Moratalaz";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Puente de Vallecas") != -1 && id.indexOf("PUENTEDEVALLECAS") != -1){
				nombre = "Puente de Vallecas";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Tetuán") != -1 && id.indexOf("TETUAN") != -1){
				nombre = "Tetuán";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Vicálvaro") != -1 && id.indexOf("VICALVARO") != -1){
				nombre = "Vicálvaro";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Fuencarral-El Pardo") != -1 && id.indexOf("FUENCARRAL") != -1){
				nombre = "Fuencarral-El Pardo";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}
			if (ee.toString().indexOf("Villa de Vallecas") != -1 && id.indexOf("VILLADEVALLECAS") != -1){
				nombre = "Villa de Vallecas";
				abstrac = abs.toString().replaceAll("@es", "");
				postalCode = pc.toString().substring(0,5);
				break;
			}

		}
		
		String [] res = new String [10];
		res[0] = nombre;
		res[1] = abstrac;
		res[2] = postalCode;
		return res;
	}
	
	public String [] consultalatylong(String id) {
		
		
		String queryString = " PREFIX dcterms: <http://purl.org/dc/terms/> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX dbp: <http://dbpedia.org/property/> "
				+ "PREFIX dbo: <http://dbpedia.org/ontology/> "
				+ "SELECT ?name ?lat ?long "
				+ "WHERE { "
				+ "?district dcterms:subject <http://dbpedia.org/resource/Category:Districts_of_Madrid> ; "
				+ "rdfs:label ?name ;"
				+ "<http://www.w3.org/2003/01/geo/wgs84_pos#long> ?long ;"
				+ "<http://www.w3.org/2003/01/geo/wgs84_pos#lat> ?lat .}";
		Query query = QueryFactory.create(queryString);
		// Inicializacion de queryExecution factory con el servicio remoto
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet results = qexec.execSelect();
		
		String [] a = new String [2];
		String latitud = "-";
		String longitud = "-";
		
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource subj = (Resource) binding.get("district");
			//Resource id = (Resource) binding.get("y");
			Literal ee = binding.getLiteral("name");
			Literal lat = binding.getLiteral("lat");
			Literal lon = binding.getLiteral("long");
			if (ee.toString().indexOf("Arganzuela") != -1 && id.indexOf("ARGANZUELA") != -1){
				longitud = lon.toString().replaceAll("^^http://www.w3.org/2001/XMLSchema#float", "");
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Chamberí") != -1 && id.indexOf("CHAMBERI") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Moncloa-Aravaca") != -1 && id.indexOf("MONCLOAARAVACA") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Salamanca") != -1&& id.indexOf("SALAMANCA") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Usera") != -1 && id.indexOf("USERA") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Retiro") != -1 && id.indexOf("RETIRO") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Carabanchel") != -1 && id.indexOf("CARABANCHEL") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Centro") != -1 && id.indexOf("CENTRO") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Latina") != -1 && id.indexOf("LATINA") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("San Blas-Canillejas") != -1 && id.indexOf("SANBLASCANILLEJAS") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Barajas") != -1 && id.indexOf("BARAJAS") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Villaverde") != -1 && id.indexOf("VILLAVERDE") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Chamartín") != -1 && id.indexOf("CHAMARTIN") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Ciudad Lineal") != -1 && id.indexOf("CIUDADLINEAL") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Hortaleza") != -1 && id.indexOf("HORTALEZA") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Moratalaz") != -1 && id.indexOf("MORATALAZ") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Puente de Vallecas") != -1 && id.indexOf("PUENTEDEVALLECAS") != -1){
				longitud = lon.toString().replaceAll("^^http://www.w3.org/2001/XMLSchema#float", "");
				latitud =lat.toString().replaceAll("^^http://www.w3.org/2001/XMLSchema#float", "");
				break;
			}
			if (ee.toString().indexOf("Tetuán") != -1 && id.indexOf("TETUAN") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Vicálvaro") != -1 && id.indexOf("VICALVARO") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Fuencarral-El Pardo") != -1 && id.indexOf("FUENCARRAL") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			if (ee.toString().indexOf("Villa de Vallecas") != -1 && id.indexOf("VILLADEVALLECAS") != -1){
				longitud = lon.toString();
				latitud =lat.toString();
				break;
			}
			
		}
		a[0] = longitud;
		a[1] = latitud;
		
		return a;
	}
}
