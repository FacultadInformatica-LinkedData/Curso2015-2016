package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.CargarDatos;
import logic.DatosSitio;

public class DetailViewCtrl implements Initializable{

	@FXML
	public ListView<String> concept;

	@FXML
	public ListView<String> detail;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Swap sw= new Swap();
		System.out.println(sw.getSelected());	
		CargarDatos datos = new CargarDatos();
		DatosSitio sitio = datos.cargarDatos(MainViewCtrl.getSitioTuristico(), sw.getSelected());
		ObservableList<String> data = FXCollections.observableArrayList();
		data.add("Nombre");
		data.add("Descripción");
		data.add("Horario");
		data.add("Equipamiento");
		data.add("Metro");
		data.add("Cercanías");
		data.add("Bus");
		data.add("Calle");
		data.add("Número");
		data.add("Cód postal");
		data.add("Distrito");
		data.add("Latitud");
		data.add("Longitud");
		data.add("Teléfono");
		concept.setItems(data);
		Integer i= sitio.getNumero();
		String numero = i.toString();
		i= sitio.getCodPost();
		String codPost = i.toString();
		ObservableList<String> data1 = FXCollections.observableArrayList();
		data1.add(sitio.getNombre());
		data1.add(sitio.getDescripcion());
		data1.add(sitio.getHorario());
		data1.add(sitio.getEquipamiento());
		data1.add(sitio.getMetro());
		data1.add(sitio.getRenfe());
		data1.add(sitio.getBus());
		data1.add(sitio.getCalle());
		data1.add(numero);
		data1.add(codPost);
		data1.add(sitio.getDistrito());
		data1.add(sitio.getLatitud());
		data1.add(sitio.getLongitud());
		data1.add(sitio.getTelefono());
		detail.setItems(data1);
	}
}
