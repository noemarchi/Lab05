package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammaDAO {
	
	public boolean isCorretta(String anagramma)
	{
		String sql = "SELECT nome "
				+ "FROM parola "
				+ "WHERE nome = ?";
		
		boolean corretta = false;
		
		try 
		{
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, anagramma);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				String parola = rs.getString("nome");
				
				if(parola.length() > 0)
				{
					corretta = true;
				}
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return corretta;
		}
		catch(SQLException e)
		{
			System.out.println("ERRORE nel DAO");
			e.printStackTrace();
			
			return false;
		}
	}

}
