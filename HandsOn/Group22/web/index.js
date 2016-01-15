var centrosDeportivos = []; 

var actividadesDeportivas = [];

var mapCentros, mapActividades, sco;

(function () {
    var app = angular.module('cdm', []);
    
    
    
    app.controller('Centros', ['$scope','$http', function ($scope, $http) {
      //sco = $scope;
      $http({
        method: 'POST',
        url: 'http://130.206.119.173:8080/FiwareRepository/v2/services/query',
        data: 'PREFIX mdep: <http://www.madridDeporte.es/ontologia#> '+
              'PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> '+
              'PREFIX owl: <http://www.w3.org/2002/07/owl#> '+
              'PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> '+
              'PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> '+
              'PREFIX foaf: <http://xmlns.com/foaf/0.1/> '+
              'SELECT DISTINCT ?espdep ?lat ?lng '+
              'WHERE { ?espdep a mdep:EspacioDeportivo . '+
              '	?espdep mdep:hasLocation ?loc . '+
              ' ?loc geo:lat ?lat . '+
              ' ?loc geo:long ?lng '+
              '}',
        headers: {
            'Content-Type': 'text/plain',
            'Accept': 'application/json'
        }
        }).then(function successCallback(response) {
            var valores = {
              lat: 0,
              lng: 0,
              espdep: 0   
            };
            var i = 0;
            response.data.columns.forEach(function(element) {
                if (element.name === 'lat') {valores.lat = i; i++;}
                if (element.name === 'lng') {valores.lng = i; i++;}
                if (element.name === 'espdep') {valores.espdep = i; i++;}
            }, this);
            for (var index = 0; index < response.data.columns[0].values.length; index++) {
                var centro = {
                  nombre: response.data.columns[valores.espdep].values[index].replace('http://www.madridDeporte.es/centro/', ''),
                  "type": "Point", 
                  "coordinates": [parseFloat(response.data.columns[valores.lat].values[index].replace('^^http://www.w3.org/2001/XMLSchema#double', '')), parseFloat(response.data.columns[valores.lng].values[index].replace('^^http://www.w3.org/2001/XMLSchema#double', ''))],
                  uri: response.data.columns[valores.espdep].values[index]
                }
                centrosDeportivos.push(centro);
            }
            console.log(response);
        }, function errorCallback(response) {
            alert("Error al obtener los centros deportivos.");
            console.log(response);
        });
      this.centros = centrosDeportivos;   
    }]);
    
    app.controller('Actividades', ['$scope','$http', function ($scope, $http) {
      //$scope.$on('selec', function(event, espdp) 
      sco = function(espdp)
      {
        console.log(espdp);
        $http({
          method: 'POST',
          url: 'http://130.206.119.173:8080/FiwareRepository/v2/services/query',
          data: 'PREFIX mdep: <http://www.madridDeporte.es/ontologia#> '+
              'PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> '+
              'PREFIX owl: <http://www.w3.org/2002/07/owl#> '+
              'PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> '+
              'PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> '+
              'PREFIX foaf: <http://xmlns.com/foaf/0.1/> '+
              'SELECT DISTINCT ?actdep ?date ?time ?title '+
              'WHERE { ?actdep a mdep:Actividad . '+
              '  <'+espdp+'> a mdep:EspacioDeportivo . '+
              '  ?actdep mdep:hasCenter <'+espdp+'> . '+
              '  ?actdep mdep:hasDate ?date . '+
              '  ?actdep mdep:hasHour ?time . '+
              '  ?actdep mdep:hasTitle ?title '+
              '}',
          headers: {
            'Content-Type': 'text/plain',
            'Accept': 'application/json'
          }
        }).then(function successCallback(response) {
          var valores = {
            actdep: 0,
            date: 0,
            time: 0,
            title: 0   
          };
          var i = 0;
          response.data.columns.forEach(function(element) {
            if (element.name === 'actdep') {valores.actdep = i++;}
            if (element.name === 'date') {valores.date = i++;}
            if (element.name === 'time') {valores.time = i++;}
            if (element.name === 'title') {valores.title = i++;}
          }, this);
          for (var index = 0; index < response.data.columns[0].values.length; index++) {
            var actividad = {
              nombre: response.data.columns[valores.title].values[index],
              fecha: response.data.columns[valores.date].values[index].substring(0,9), 
              hora: response.data.columns[valores.time].values[index],
              uri: response.data.columns[valores.actdep].values[index]
            }
            actividadesDeportivas.push(actividad);
          }
          
        }, function errorCallback(response) {

      });
      //});
      }; 
       
      
      
      this.actividades = actividadesDeportivas;       
    }]);
    
})();



function initializeMap() {
    var mapCanvasCentros = document.getElementById('mapCentros');
    var mapCanvasActividades = document.getElementById('mapActividades');
    
    var mapOptions = {
        center: new google.maps.LatLng(40.433848, -3.695427),
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    mapCentros = new google.maps.Map(mapCanvasCentros, mapOptions);
    mapActividades = new google.maps.Map(mapCanvasActividades, mapOptions);
}

function cargarCentros() {
    centrosDeportivos.forEach(function(centro) {
        var marca = new google.maps.Marker({
            position: {lat: centro.coordinates[0], lng: centro.coordinates[1]},
            label: "C",
            map: mapCentros,
            centro: centro
        });
        var ventana = new google.maps.InfoWindow({
            content: generarVentana(centro)
        });
        marca.addListener('click', function () {
            ventana.open(mapCentros, marca)
        });
    }, this);
}

function cargarCentro (centro) {
    alert(JSON.stringify(centro));
}

function generarVentana(centro) {
    var contenido = centro.nombre + "<br>";
    contenido += "Latitud: " + centro.coordinates[0] + "<br>";
    contenido += "Longitud: " + centro.coordinates[1] + "<br>";
    contenido += "<a href=\"#\" onclick=\"cambiarDeTab('"+centro.uri+"')\">Actividades Deportivas</a>";
    return contenido;     
}

function cambiarDeTab(centro) {
    console.log(centro);
    sco(centro);
    
    $("#tab-centros").removeClass("is-active");
    $("#tab-centros-boton").removeClass("is-active");
    $("#tab-actividades").addClass("is-active");
    $("#tab-actividades-boton").addClass("is-active");
}

google.maps.event.addDomListener(window, 'load', cargarCentros);