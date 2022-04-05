package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="txtCorretti"
    private TextArea txtCorretti; // Value injected by FXMLLoader
    @FXML // fx:id="txtErrati"
    private TextArea txtErrati; // Value injected by FXMLLoader
    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader
    @FXML // fx:id="txtNumeri"
    private TextField txtNumeri; // Value injected by FXMLLoader
    @FXML // fx:id="txtTempo"
    private TextField txtTempo; // Value injected by FXMLLoader

    @FXML
    void doFind(ActionEvent event) 
    {
    	// pulizia interfaccia grafica
    	this.txtCorretti.clear();
    	this.txtErrati.clear();
    	this.txtNumeri.clear();
    	this.txtTempo.clear();
    	
    	// acquisizione dati
    	String parola = this.txtParola.getText().toLowerCase();
    	
    	// controllo dati
    	if(parola.length() == 0)
    	{
    		this.txtErrati.setText("INSERISCI UNA PAROLA!");
    		this.txtCorretti.setText("INSERISCI UNA PAROLA!");
    		
    		return;
    	}
    	if(parola.contains(" "))
    	{
    		this.txtErrati.setText("INSERISCI UNA SOLA PAROLA!");
    		this.txtCorretti.setText("INSERISCI UNA SOLA PAROLA!");
    		
    		return;
    	}
    	
    	// esecuzione operazione --> chiedo al model
    	long start = System.currentTimeMillis();
    	model.anagrammi(parola);
    	long end = System.currentTimeMillis();

    	// visualizzazione risultato
    	for(String s: model.corretti())
    	{
    		this.txtCorretti.appendText(s + "\n");
    	}
    	
    	for(String s: model.errati())
    	{
    		this.txtErrati.appendText(s + "\n");
    	}
    	
    	int numeroAnagrammi = model.getNumeroAnagrammi();
    	
    	this.txtNumeri.setText("Parola: " + parola.toUpperCase() + ". " + numeroAnagrammi + " anagrammi (compresi doppioni)");
    	
    	long diff = end -start;
    	
    	this.txtTempo.setText("Tempo esecuzione: " + diff + "ms");
    }

    @FXML
    void doReset(ActionEvent event) 
    {
    	this.txtCorretti.clear();
    	this.txtErrati.clear();
    	this.txtParola.clear();
    	this.txtNumeri.clear();
    	this.txtTempo.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumeri != null : "fx:id=\"txtNumeri\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) 
	{
		this.model = model;
	}

}
