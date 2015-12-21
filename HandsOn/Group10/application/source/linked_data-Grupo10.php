<?php
genesis();

include_once('arc/ARC2.php'); /* ARC2 static class inclusion */

  $dbpconfig = array(
  "remote_store_endpoint" => "http://demo.openlinksw.com/sparql/",
   );

  $store = ARC2::getRemoteStore($dbpconfig); 

  if ($errs = $store->getErrors()) {
     echo "<h1>getRemoteSotre error<h1>" ;
  }

$barrio = "http://linkeddata.es/grupo10/resource/distrito/" . htmlspecialchars($_GET["barrio"]);
$barrio = str_replace(" ", "_", $barrio);

$fechaini = htmlspecialchars($_GET["fechaini"]);
if($fechaini == ""){
	$fechaini = "2015-10-01";
}

$fechafin = htmlspecialchars($_GET["fechafin"]);
if($fechafin == ""){
	$fechafin = "2015-10-31";
}

$horaini = htmlspecialchars($_GET["horaini"]);
if($horaini == ""){
	$horaini = "00:00";
}
$horaini = str_replace("%3A",":",$horaini);

$horafin = htmlspecialchars($_GET["horafin"]);
if($horafin == ""){
	$horafin = "23:59";
}
$horafin = str_replace("%3A",":",$horafin);

  $query = '
	  PREFIX grafo: <http://destinoc-cp159.wordpresstemporal.com/ld/wp-content/plugins/lh-tools/rdf/turnosmadrid_guardia-csv-updated.ttl>
PREFIX schema: <http://schema.org/>
PREFIX farmont: <http://linkeddata.es/grupo10/ontology/Farmacias#>
PREFIX time: <http://www.w3.org/2006/time#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
select distinct ?farmacia ?direccion ?telefono { GRAPH grafo: { 
   ?farmacia a farmont:FarmaciaDeGuardia ;
             schema:containedInPlace <'.$barrio.'>;
             schema:address ?direccion ;
		schema:telephone ?telefono ;
             farmont:tieneTurnoDeGuardia ?turno .
   ?turno time:hasBeginning ?inicio ;
          time:hasEnd ?fin .
   ?inicio time:inXSDDateTime ?inicioDT .
   ?fin time:inXSDDateTime ?finDT .
   FILTER (?inicioDT <= "'.$fechafin.'T'.$horafin.':59"^^xsd:dateTime) .
   FILTER (?finDT >= "'.$fechaini.'T'.$horaini.':00"^^xsd:dateTime) .
} }
  ';

  $rows = $store->query($query, 'rows'); /* execute the query */
  
  if ($errs = $store->getErrors()) {
     echo "Query errors" ;
     print_r($errs);
  }
 
  /* display the results in an HTML table */
  echo "<table border='1'>" ;
  foreach( $rows as $row ) { /* loop for each returned row */
        echo "<tr><td>";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Dirección: " . $row['direccion'] . "<br>";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Teléfono: " . $row['telefono'];
	$dir = explode("(",$row['direccion']);
	$dir = explode(" LC ",$dir[0]);
	echo "</td><td>";
	echo do_shortcode('[google_map]'.$dir[0]." madrid".'[/google_map]');
	echo "</td></tr>";
  }
  echo "</table>";

?>