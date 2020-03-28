package it.polito.tdp.esempioSQL.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeggiBabs {
	public void run() {
		//PASSO 1
		String jbdcURL= "jdbc:mysql://localhost/babs?user=root&password=root";
		
		try {
			//PASSO 2, creo la connessione tramite il driver manager
			Connection conn= DriverManager.getConnection(jbdcURL);
			
			//PASSO 3, creo un nuovo oggetto di tipo statement(capsula)
			//che collega programma e server tramite la connessione
			
			String sql= "SELECT NAME FROM station WHERE landmark = ? ";
			
			
			PreparedStatement st= conn.prepareStatement(sql);
			
			st.setString(1, "Palo Alto");
			
			
			//PASSO 4, faccio eseguire allo statement una query
			
			ResultSet res= st.executeQuery();
			
			//PASSO 5, ho chiesto qualcosa ma non mi restituirà il risultato
			//ma un rifermento al risultato.
			
			//parto col 'cursore' prima della prima riga, richiamo next
			//passa all prima riga, itera finchè non arriva 
			//alla riga dopo l'ultima
			
			while(res.next()) {
				
				String nomeStazione= res.getString("name");
				System.out.println(nomeStazione);
				
			}
			
			st.close();
			
			//PASSO 6, chiudo la connessione
			
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//FACTORY: creazione di un oggetto di una classe,
		//senza conoscere il tipo della classe(NON potevo usare new)
		//uso il metodo fortnito da un'altra classe che internamente
		// farà new e conoscerà il tipo di classe.
	}

	public static void main(String args[]) {
		LeggiBabs babs= new LeggiBabs();
		babs.run();
	}
}
