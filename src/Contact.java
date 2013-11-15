public class Contact {
	
	private String nom;
	private String prenom;
	private String email;
	private String commentaire;
	
	public Contact(String unNom, String unPrenom, String unEmail, String unCommentaire){
		this.nom=unNom;
		this.prenom=unPrenom;
		this.email=unEmail;
		this.commentaire=unCommentaire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
		
}
