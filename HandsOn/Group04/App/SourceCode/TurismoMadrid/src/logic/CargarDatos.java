package logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;

public class CargarDatos {

	public static List<String> cargarLista(String name) {
		String file = "rdf/" + name + ".ttl";
		String NL = System.getProperty("line.separator");
		List<String> lista = new ArrayList<String>();
		final Model m = ModelFactory.createDefaultModel();
		FileManager.get().readModel(m, file);
		final String url = "http://puntosinteres.madrid.es/group04/ontology/puntosinteres#";
		final String prolog1 = "PREFIX base: <" + url + ">";
		final String prolog2 = "PREFIX rdf: <" + RDF.getURI() + ">";
		final String prolog3 = "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>";
		final String prolog4 = "PREFIX owl: <http://www.w3.org/2002/07/owl#>";
		final String prolog5 = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
		final String prolog6 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>";
		final String prolog7 = "PREFIX vcard: <http://www.w3.org/2006/vcard/ns#>";
		final String prolog8 = "PREFIX locn: <http://www.w3.org/ns/locn#>";
		final String prolog9 = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>";
		final String queryString = prolog1 + NL + prolog2 + NL + prolog3 + NL + prolog4 + NL + prolog5 + NL + prolog6
				+ NL + prolog7 + NL + prolog8 + NL + prolog9 + NL
				+ "SELECT ?parque ?nombre WHERE {?parque a base:SitioTuristico; base:tieneNombre ?nombre. }";
		final Query query = QueryFactory.create(queryString);
		query.serialize(new IndentedWriter(System.out, true));
		final QueryExecution qexec = QueryExecutionFactory.create(query, m);
		try {
			final ResultSet rs = qexec.execSelect();
			for (int i = 0; rs.hasNext(); i++) {
				final QuerySolution rb = rs.nextSolution();
				final RDFNode y = rb.get("parque");
				final String s = rb.getLiteral("nombre").getString();
				System.out.println(s);
				lista.add(s);
			}
		} finally {

			qexec.close();
		}
		return lista;
	}

	public static DatosSitio cargarDatos(String name, String sitio) {
		DatosSitio datos = null;
		String file = "rdf/" + name + ".ttl";
		String NL = System.getProperty("line.separator");
		List<String> lista = new ArrayList<String>();
		final Model m = ModelFactory.createDefaultModel();
		FileManager.get().readModel(m, file);
		final String url = "http://puntosinteres.madrid.es/group04/ontology/puntosinteres#";
		final String prolog1 = "PREFIX base: <" + url + ">";
		final String prolog2 = "PREFIX rdf: <" + RDF.getURI() + ">";
		final String prolog3 = "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>";
		final String prolog4 = "PREFIX owl: <http://www.w3.org/2002/07/owl#>";
		final String prolog5 = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
		final String prolog6 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>";
		final String prolog7 = "PREFIX vcard: <http://www.w3.org/2006/vcard/ns#>";
		final String prolog8 = "PREFIX locn: <http://www.w3.org/ns/locn#>";
		final String prolog9 = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>";
		final String queryString = prolog1 + NL + prolog2 + NL + prolog3 + NL + prolog4 + NL + prolog5 + NL + prolog6
				+ NL + prolog7 + NL + prolog8 + NL + prolog9 + NL
				+ "SELECT ?nombre ?descripcion ?horario ?equipamiento ?metro ?renfe ?bus ?calle ?tipoVia ?numero ?codPost ?distrito ?latitud ?longitud ?telefono WHERE { ?parque a base:SitioTuristico; base:tieneNombre '"
				+ sitio
				+ "'. OPTIONAL { ?parque base:tieneDescripcion ?descripcion} OPTIONAL{?parque base:tieneHorario ?horario}  OPTIONAL{?parque base:tieneEquipamiento ?equipamiento} OPTIONAL{?parque vcard:tel ?telefono} OPTIONAL{?parque base:estaEnPosicion ?posicion} OPTIONAL{?posicion geo:lat ?latitud} OPTIONAL{?posicion geo:long ?longitud} OPTIONAL{?parque base:tieneDireccion ?direccion} OPTIONAL{?direccion base:tieneNumero ?numero} OPTIONAL{?direccion base:estaEnDistrito ?distrito1} OPTIONAL{?direccion locn:postCode ?codPost} OPTIONAL{?direccion locn:address ?calle} OPTIONAL{?direccion base:tieneTipoVia ?tipoVia} OPTIONAL{?distrito1 base:nombreDistrito ?distrito} OPTIONAL{?parque base:tieneMetro ?metro1} OPTIONAL{?metro1 base:hayMetro ?metro} OPTIONAL{?parque base:tieneBus ?bus1} OPTIONAL{?bus1 base:hayBus ?bus} OPTIONAL{?parque base:tieneRenfe ?renfe1} OPTIONAL{?renfe1 base:hayRenfe ?renfe}  }";
		final Query query = QueryFactory.create(queryString);
		query.serialize(new IndentedWriter(System.out, true));
		final QueryExecution qexec = QueryExecutionFactory.create(query, m);
		String nombre=sitio;
		String descripcion=" ";
		String horario=" ";
		String equipamiento=" ";
		String telefono=" ";
		String latitud=" ";
		String longitud=" ";
		int numero=0;
		String distrito=" ";
		int codPost=0;
		String calle=" ";
		String metro=" ";
		String bus=" ";
		String renfe=" ";
		try {
			final ResultSet rs = qexec.execSelect();
				final QuerySolution rb = rs.nextSolution();
				String s = sitio;
				try {
					descripcion = rb.getLiteral("descripcion").getString();
					System.out.println(s + " - " + descripcion);
				} catch (NullPointerException e) {

				}
				try {

					horario = rb.getLiteral("horario").getString();
					System.out.println(s + " - " + horario);
				} catch (NullPointerException e) {

				}
				try {

					equipamiento = rb.getLiteral("equipamiento").getString();
					System.out.println(s + " - " + equipamiento);
				} catch (NullPointerException e) {

				}
				try {

					telefono = rb.getLiteral("telefono").getString();
					System.out.println(s + " - " + telefono);
				} catch (NullPointerException e) {

				}
				try {
					latitud = rb.getLiteral("latitud").getString();
					System.out.println(s + " - " + latitud);
				} catch (NullPointerException e) {

				}
				try {
					longitud = rb.getLiteral("longitud").getString();
					System.out.println(s + " - " + longitud);
				} catch (NullPointerException e) {

				}
				try {
					numero = rb.getLiteral("numero").getInt();
					System.out.println(s + " - " + numero);
				} catch (NullPointerException e) {

				}
				try {
					distrito = rb.getLiteral("distrito").getString();
					System.out.println(s + " - " + distrito);
				} catch (NullPointerException e) {

				}
				try {
					codPost =rb.getLiteral("codPost").getInt();
					System.out.println(s + " - " + codPost);
				} catch (NullPointerException e) {

				}

				try {
					calle = rb.getLiteral("tipoVia").getString();
					System.out.println(s + " - " + calle);
				} catch (NullPointerException e) {

				}
				try {
					String calle1 = rb.getLiteral("calle").getString();
					calle = calle +" "+ calle1;
					System.out.println(s + " - " + calle);
				} catch (NullPointerException e) {

				}
				try {
					metro = rb.getLiteral("metro").getString();
					System.out.println(s + " - " + metro);
				} catch (NullPointerException e) {

				}
				try {
					bus = rb.getLiteral("bus").getString();
					System.out.println(s + " - " + bus);
				} catch (NullPointerException e) {

				}
				try {
					renfe = rb.getLiteral("renfe").getString();
					System.out.println(s + " - " + renfe);
				} catch (NullPointerException e) {

				}
				datos= new DatosSitio(nombre, descripcion, horario, equipamiento, telefono, latitud, longitud, numero, distrito, codPost, calle, metro, bus, renfe);
			
		} finally {
			qexec.close();
		}
		return datos;
	}

}
