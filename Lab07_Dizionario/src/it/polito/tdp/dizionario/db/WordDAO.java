package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WordDAO {

	/*
	 * Ritorna tutte le parole di una data lunghezza che differiscono per un solo carattere
	 * */
	 
	public List<String> getAllSimilarWords(String parola, int numeroLettere) {
		
		List<String> similarWords = new ArrayList<String>();
		
		System.out.println("WordDAO -- TODO");
		
		for(String s : this.getAllWordsFixedLength(numeroLettere))
			if(this.isSimilar(s.toLowerCase(), parola.toLowerCase()))
				similarWords.add(s);
		
		return similarWords;
	}
	
    public List<String> getAllSimilarWordsVertex(String parola, Set<String> verticiInseriti) {
		
		List<String> similarWords = new ArrayList<String>();
		
		//System.out.println("WordDAO -- TODO");
		
		for(String s : verticiInseriti)
			if(this.isSimilar(s.toLowerCase(), parola.toLowerCase()))
				similarWords.add(s);
		
		return similarWords;
	}

	private boolean isSimilar(String parolaDizionario, String parolaInput) {
		
		int numeroLettere = parolaInput.length();
		int lettereDiverse = 0;
		
		for(int i=0; i<numeroLettere; i++)
			if(parolaDizionario.charAt(i)!=parolaInput.charAt(i))
				lettereDiverse++;
		
		if(lettereDiverse==1)
			return true;
		return false;
	}

	/*
	 * Ritorna tutte le parole di una data lunghezza
	 */
	public List<String> getAllWordsFixedLength(int numeroLettere) {

		Connection conn = DBConnect.getInstance().getConnection();
		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?;";
		PreparedStatement st;

		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, numeroLettere);
			ResultSet res = st.executeQuery();

			List<String> parole = new ArrayList<String>();

			while (res.next())
				parole.add(res.getString("nome"));

			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

}
