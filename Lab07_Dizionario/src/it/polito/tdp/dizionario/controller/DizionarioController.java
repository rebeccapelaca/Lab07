package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	
	Model model;

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;
    @FXML
    private Button btnTrovaTuttiVicini;

	@FXML
	void doReset(ActionEvent event) {
		txtResult.setText("Reset!");
		txtResult.clear();
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {

		try {
			txtResult.setText("Controller -- TODO!\n");
			
			for(String vertice : model.createGraph(Integer.parseInt(inputNumeroLettere.getText())))
				txtResult.appendText(vertice + "\n");
			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		try {
			txtResult.setText("Controller -- TODO!\n");
			txtResult.appendText(model.findMaxDegree());

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		
		try {
			txtResult.setText("Controller -- TODO!\n");
			for(String vicino : model.displayNeighbours(inputParola.getText()))
				txtResult.appendText(vicino + "\n");

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}
	
    @FXML
    void doTrovaTuttiVicini(ActionEvent event) {
    	
    	try {
			txtResult.setText("Controller -- TODO!\n");
			for(String vicino : model.trovaTuttiVicini(inputParola.getText()))
				txtResult.appendText(vicino + "\n");

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
    	

    }

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaTuttiVicini != null : "fx:id=\"btnTrovaTuttiVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}

	public void setModel(Model model) {
		this.model = model;
	}
}