var val='';
            var selec = 0;
            cargarDatos();
            var aparcamiento = new Array();
            var estacionamiento = new Array();
            var acusticos = new Array();
            var servicios = new Array();
            var web = new Array();
            var calle = new Array();
            var calle3 = new Array();
            var i = 0;
            function cargarDatos(){
                $.ajax({
                    dataType: "json",
                    url: "json/aparcamiento.json",
                    mimeType: "application/json",
                    success: function(result){
                        calle = [];
                        $.each(result, function(i, obj) {
                            aparcamiento.push(obj);
                            $.each(obj, function(i2, obj2) {
                                aparcamiento[i2] = obj2;
                            })
                        })
                        merge(1,aparcamiento);
                    }
                });
                $.ajax({
                    dataType: "json",
                    url: "json/estacionamientoMR.json",
                    mimeType: "application/json",
                    success: function(result){
                        $.each(result, function(i, obj) {
                            estacionamiento.push(obj);
                            $.each(obj, function(i2, obj2) {
                                estacionamiento[i2] = obj2;
                            })
                            estacionamiento[i]['NOMBRE'] = 'Estacionamineto movilidad reducida';
                        })
                    merge(2,estacionamiento);
                    }
                });
                $.ajax({
                    dataType: "json",
                    url: "json/acusticos.json",
                    mimeType: "application/json",
                    success: function(result){
                        $.each(result, function(i, obj) {
                            acusticos.push(obj);
                            $.each(obj, function(i2, obj2) {
                                acusticos[i2] = obj2;
                            })
                            acusticos[i]['NOMBRE'] = 'Acusticos';
                        })
                        merge(3,acusticos);
                    }
                });
                $.ajax({
                    dataType: "json",
                    url: "json/serviciosSociales.json",
                    mimeType: "application/json",
                    success: function(result){
                        $.each(result, function(i, obj) {
                            servicios.push(obj);
                            $.each(obj, function(i2, obj2) {
                                servicios[i2] = obj2;
                            })
                        })
                        merge(4,servicios);
                    }
                });

            }
            function merge(id,valor){
                $.each(valor, function(i, obj) {
                    calle.push(obj);
                })
                if(id == 1)
                    aparcamiento = valor;
                else if(id == 2)
                    estacionamiento = valor;
                else if (id == 3)
                    acusticos = valor;
                else if (id == 4){
                    servicios = valor;
                    mergeDatos(calle);
                }
            }
            var aux = new Array();
            var aux2 = new Array();
            function mergeDatos(c){
                $.each(c, function(i, obj){
                    $.each(obj, function(i2, obj2){
                        if(i2=='NOMBRE-VIA'){
                            if($.inArray(obj['NOMBRE-VIA'],aux2) === -1){
                                titulos = {
                                  'NOMBRE-VIA':    obj['NOMBRE-VIA'],
                                  'CODIGO-POSTAL':  obj['CODIGO-POSTAL'],
                                  'BARRIO':  obj['BARRIO'],
                                  'DISTRITO':  obj['DISTRITO']
                                };
                                aux2.push(obj['NOMBRE-VIA']);
                                aux[obj['NOMBRE-VIA']] = titulos
                            }
                        }

                    })
                })
            }

            $('select').prop('selectedIndex',0);
            $('textarea').val('');
            $('button').on("click",function(){
                $('p').remove();
                $('ul').remove();
                $('table').remove();
                var cont = 0;
                if(val == '') $('.ibox-content').append("<p>Seleccione un área válido</p>");
                else{
                    var valT = $("textarea").val().toUpperCase();
                    if(valT == '') $('.ibox-content').append("<p>Busca algo</p>");
                    else{
                        if(selec == 2) web = aparcamiento
                        else if(selec == 3) web = estacionamiento
                        else if(selec == 4) web = acusticos
                        else if(selec == 5) web = servicios
                        else if(selec == 6) web = calle

                        $.each(web, function(i, obj){
                            $.each(obj, function(i2, obj2){
                                if(selec == 6) val2 = 'NOMBRE-VIA';
                                else val2 = $('select#2').val();
                                if(i2 == val2 && obj2 == valT){
                                    if(cont == 0) appendBody();
                                        cont++;
                                    if(selec == 2){
                                        $('table').append('<tr><td>'+obj['NOMBRE']+'</td><td>'+obj['NOMBRE-VIA']+'</td><td>'+obj['NUM']+'</td><td>'+obj['BARRIO']+'</td><td>'+obj['DISTRITO']+'</td><td>'+obj['CODIGO-POSTAL']+'</td><td>'+obj['ACCESIBILIDAD']+'</td></tr>')
                                    }
                                    else if(selec == 3){
                                        $('table').append('<tr><td>'+obj['NOMBRE-VIA']+'</td><td>'+obj['NUM']+'</td></tr>')
                                    }
                                    else if(selec == 4){
                                        $('table').append('<tr><td>'+obj['NOMBRE-VIA']+'</td><td>'+obj['NUM']+'</td></tr>')
                                    }
                                    else if(selec == 5){
                                        $('table').append('<tr><td>'+obj['NOMBRE']+'</td><td>'+obj['NOMBRE-VIA']+'</td><td>'+obj['NUM']+'</td><td>'+obj['BARRIO']+'</td><td>'+obj['DISTRITO']+'</td><td>'+obj['CODIGO-POSTAL']+'</td></tr>')
                                    }
                                    else if(selec == 6){
                                        $('table').append('<tr><td>'+obj['NOMBRE']+'</td><td>'+obj['NOMBRE-VIA']+'</td><td>'+obj['NUM']+'</td><td>'+aux[obj["NOMBRE-VIA"]]['BARRIO']+'</td><td>'+aux[obj["NOMBRE-VIA"]]['DISTRITO']+'</td><td>'+aux[obj["NOMBRE-VIA"]]['CODIGO-POSTAL']+'</td></tr>')
                                       
                                    }
                                    else{
                                        $('.ibox-content').append("<p>Seleccione un área válido</p>")
                                    }
                                }
                            })
                        })
                        if(cont == 0){
                            $('.ibox-content').append("<p>No se han encontrado resultados</p>")
                        }
                    }
                }
            });
            function appendBody(){
                $('.ibox-content').append('<table id="DataTables_Table_0" class="table table-striped table-bordered table-hover dataTables-example dataTable dtr-inline"></table>');
                if(selec == 2) $('table').append('<tr><th>NOMBRE</th><th>NOMBRE-VIA</th><th>NUM</th><th>BARRIO</th><th>DISTRITO</th><th>CODIGO-POSTAL</th><th>Accesbilidad</th></tr>')
                else if(selec == 3) $('table').append('<tr><th>NOMBRE-VIA</th><th>NUM</th></tr>')
                else if(selec == 4)$('table').append('<tr><th>NOMBRE-VIA</th><th>NUM</th></tr>')
                else if(selec == 5) $('table').append('<tr><th>NOMBRE-VIA</th><th>NUM</th><th>BARRIO</th><th>DISTRITO</th><th>CODIGO-POSTAL</th></tr>')
                else if(selec == 6) $('table').append('<tr><th>NOMBRE</th><th>NOMBRE-VIA</th><th>NUM</th><th>BARRIO</th><th>DISTRITO</th><th>CODIGO-POSTAL</th></tr>')
            }
            $("select#1").change(function() {
                val = $(this).val();
                $('select#2').css("display","inline");
                rellenarSelect();
            });
            function rellenarSelect(){
                $('p').remove();
                $('ul').remove();
                if(val == "aparcamiento"){
                    selec = 2;
                    $('#2').empty().append('<option value="*" selected>Seleccione el campo a buscar</option>'+
                        '<option value="NOMBRE">Nombre del aparcamiento</option>'+
                        '<option value="CODIGO-POSTAL">Código postal</option>'+
                        '<option value="NOMBRE-VIA">Nombre de la calle/avenida</option>');
                }
                else if(val == "estacionamientoMR"){
                    selec = 3;
                    $('#2').empty().append('<option value="*" selected>Seleccione el campo a buscar</option>'+
                        '<option value="NPlazas">Número de plazas</option>'+
                        '<option value="Distrito">Distrito</option>'+
                        '<option value="NOMBRE-VIA">Nombre de la calle/avenida</option>');
                }
                else if(val == "acusticos"){
                    selec = 4;
                    $('#2').empty().append('<option value="*" selected>Seleccione el campo a buscar</option>'+
                        '<option value="NOMBRE-VIA">Barrio</option>'+
                        '<option value="TIPO">Tipo de avisador</option>');
                }
                else if(val == "serviciosSociales"){
                    selec = 5;
                    $('#2').empty().append('<option value="*" selected>Seleccione el campo a buscar</option>'+
                        '<option value="NOMBRE">Nombre</option>'+
                        '<option value="CODIGO-POSTAL">Código postal</option>'+
                        '<option value="NOMBRE-VIA">Nombre de la calle/avenida</option>'+
                        '<option value="DISTRITO">Distrito</option>');
                }
                else if(val == "calle"){
                    selec = 6;
                    $('select#2').css("display","none");
                }
                else{
                    selec = 0;
                    $('#2').css('display','none');
                    $('.ibox-content').append("<p>Seleccione un área válido</p>");
                }
            }