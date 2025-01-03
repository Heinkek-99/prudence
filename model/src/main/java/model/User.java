package model;

public class User {
	private int idUser;
    private String username;
    private String password;
    private String role;
    private boolean archived;


	public User() {
		// TODO Auto-generated constructor stub
	}
	
	// Constructeur pour créer un nouvel utilisateur
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password; // Haché par DAO
        this.role = role;
        this.archived = false;
    }

 // Constructeur pour récupération complète
    public User(int idUser, String username, String password, String role, boolean archived) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.role = role;
        this.archived = archived;
    }

    
    // Constructor for full user retrieval
//    public User(int id, String username, String password, String role) {
//        this.setId(id);
//        this.setUsername(username);
//        this.setPassword(password);
//        this.setRole(role);
//        this.setArchived(false);
//    }
    
    // Getters and Setters


	public int getIdUser() {
		return idUser;
	}

	public void setIdUSer(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}   
}
