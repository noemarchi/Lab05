package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {
	
	// ATTRIBUTI
	private List<String> tutti;
	private AnagrammaDAO dao;
	private Set<String> errati;
	private Set<String> corretti;
	private int numero;
	
	// COSTRUTTORE
	public Model()
	{
		this.dao = new AnagrammaDAO();
		
	}
	
	// METODI RICORSIONE
	public void anagrammi(String parola)
	{
		errati = new HashSet<String>();
		corretti = new HashSet<String>();
		tutti = new LinkedList<String>();
		
		numero = 0;
		
		// devo chiamare il metodo ricorsivo
		this.anagrammi_ricorsivo("", 0, parola);
		
		// split della lista di tutti gli anagrammi
		for(String s: tutti)
		{
			boolean corretta = this.dao.isCorretta(s);
			
			numero++;
			
			if(corretta)
			{
				corretti.add(s);
			}
			else
			{
				errati.add(s);
			}
		}
	}
	
	private void anagrammi_ricorsivo(String parziale, int livello, String rimanenti)
	{
		// CASO TERMINALE:
		// non ho più lettere in rimanenti
		if(rimanenti.length() == 0)
		{
			// qui ho finito, parziale è anche totale
			tutti.add(parziale);
			return;
		}
		
		// CASO NORMALE
		for(int pos = 0; pos < rimanenti.length(); pos++)
		{
			String nuova_parziale = parziale + rimanenti.charAt(pos);
			String nuova_rimanenti = rimanenti.substring(0, pos) + rimanenti.substring(pos + 1);
			
			this.anagrammi_ricorsivo(nuova_parziale, livello + 1, nuova_rimanenti);
		}
	}
	
	// METODI PASSAGGIO DAO --> CONTROLLER
	public Set<String> corretti()
	{
		return corretti;
	}
	
	public Set<String> errati()
	{
		return errati;
	}
	public int getNumeroAnagrammi()
	{
		return numero;
	}
	
	

}
