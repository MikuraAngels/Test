import java.sql.*;
import java.util.ArrayList;

// Test2 de commit avec eclipse (EGit) !

public class ConnexBDD {
	static Connection connect = null;
	private static ArrayList <Contact> mesContacts;
	
	public ConnexBDD() {

	}
	

	public static Connection connecterdb() {
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		
		connect = DriverManager.getConnection("jdbc:mysql://localhost/contacts" , "root", "");
		System.out.println("Connexion à la base réussi !");
		}
		catch (SQLException erreur) {
			System.out.println("Erreur de connexion à la base.");
		}
	
	catch (ClassNotFoundException e) {
		
		System.out.println("Driver non chargé, connexion impossible.");
	}
	
	
	return connect;
}
	public static boolean deconnecterdb() {
		boolean bool;
		try {
			connect.close();
			bool = true ;
		}
		catch (SQLException erreur) {
			bool = false;
			
		}
		return bool;
	}
	public static String ajouterContact(String nom, String prenom, String email, String commentaire){
		String result;
		try {
			PreparedStatement st1 = connect.prepareStatement("INSERT INTO contact (Nom, Prenom, Email, Commentaire) " +
			"VALUES(?, ?, ?, ?)");
			 
			st1.setNString(1, nom);
			st1.setNString(2, prenom);
			st1.setNString(3, email);
			st1.setNString(4, commentaire);
			
			int nbLignes = st1.executeUpdate();
		
			/*Statement st1 = connect.createStatement();
			st1.executeUpdate("INSERT INTO contact (Nom, Prenom, Email, Commentaire) " +
			"VALUES('"+nom+"' , '"+prenom+"' , '"+email+"' , '"+commentaire+"');" );*/
			
			result=("Requête réussi!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = ("Erreur requete");
		}
		return result;
		
		
	}
	
	public static ArrayList<Contact> afficherContact() {
		mesContacts = new ArrayList<Contact>();
		PreparedStatement st2;
		try {
			connect = connecterdb();		
			st2 = connect.prepareStatement("SELECT nom, prenom, email, commentaire FROM contact;");
			ResultSet rs = st2.executeQuery();
			while (rs.next()) {
				mesContacts.add(new Contact(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
		
			System.out.println("Erreur requete");
		}

		return mesContacts;
	}
}
