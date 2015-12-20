package view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.CargarDatos;

public class MainViewCtrl implements Initializable {

	@FXML
	public ListView<String> listView;
	
	public static String sitioTuristico=null;

	public void jardines() {
		sitioTuristico="parques";
		ObservableList<String> data = FXCollections.observableArrayList();
		CargarDatos datos = new CargarDatos();
		List<String> lista = datos.cargarLista(sitioTuristico);
		for (int i = 0; i < lista.size(); i++) {
			data.add(lista.get(i));
		}
		listView.setItems(data);
	}

	public void monumentos() {
		sitioTuristico="monumentos";
		ObservableList<String> data = FXCollections.observableArrayList();
		CargarDatos datos = new CargarDatos();
		List<String> lista = datos.cargarLista(sitioTuristico);
		for (int i = 0; i < lista.size(); i++) {
			data.add(lista.get(i));
		}
		listView.setItems(data);
	}

	public void museos() {
		sitioTuristico="museos";
		ObservableList<String> data = FXCollections.observableArrayList();
		CargarDatos datos = new CargarDatos();
		List<String> lista = datos.cargarLista(sitioTuristico);
		for (int i = 0; i < lista.size(); i++) {
			data.add(lista.get(i));
		}
		listView.setItems(data);
	}
	
	public void teatros() {
		sitioTuristico="teatros";
		ObservableList<String> data = FXCollections.observableArrayList();
		CargarDatos datos = new CargarDatos();
		List<String> lista = datos.cargarLista(sitioTuristico);
		for (int i = 0; i < lista.size(); i++) {
			data.add(lista.get(i));
		}
		listView.setItems(data);
	}
	
	public void wifi() {
		sitioTuristico="wifi";
		ObservableList<String> data = FXCollections.observableArrayList();
		CargarDatos datos = new CargarDatos();
		List<String> lista = datos.cargarLista(sitioTuristico);
		for (int i = 0; i < lista.size(); i++) {
			data.add(lista.get(i));
		}
		listView.setItems(data);
	}
	
	public void oficinas() {
		sitioTuristico="oficinas";
		ObservableList<String> data = FXCollections.observableArrayList();
		CargarDatos datos = new CargarDatos();
		List<String> lista = datos.cargarLista(sitioTuristico);
		for (int i = 0; i < lista.size(); i++) {
			data.add(lista.get(i));
		}
		listView.setItems(data);
	}
	public static String getSitioTuristico (){
		return sitioTuristico;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> data = FXCollections.observableArrayList();
		listView.setItems(data);
	}
	public void detailButton(){

		String selected = listView.getSelectionModel().getSelectedItem();

		if (selected != null){
			Swap swap = new Swap();
			swap.changeSelected(selected);
			Parent root;
		        try {
		            root = FXMLLoader.load(getClass().getResource("DetailView.fxml"));
		            Stage stage = new Stage();
		            stage.setTitle("Detalles");
		            stage.setScene(new Scene(root));
		            stage.show();

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}
	}

}
