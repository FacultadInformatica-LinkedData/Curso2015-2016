$(document).ready(function () {
    var map;
    var elevator;
	
    var myOptions = {
        zoom: 12,
        center: new google.maps.LatLng(40.412092, -3.701273),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map($('#map_canvas')[0], myOptions);
	
d3.json("farmacias.json", function(error, farmacias) {
  var addresses = [];
  for(var i = 0; i < 100; i++){
	  addresses[i] = farmacias[i].Direccion + ", Madrid";
  }

var j = 0;
    for (var x = 0; x < addresses.length; x++) {
        $.getJSON('https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyCiYl-k7qayoURbO5Hiux1_jMHBoFvH1Ds&address='+addresses[x]+'&sensor=false', null, function (data) {
            var p = data.results[0].geometry.location
            var latlng = new google.maps.LatLng(p.lat, p.lng);
            new google.maps.Marker({
                position: latlng,
                map: map,
				title: farmacias[j].Horario + "\n" + farmacias[j].Direccion
			});
		
		j++;
        });
    }
});
}); 