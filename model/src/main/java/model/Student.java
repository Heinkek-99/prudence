/**
 * 
 */
package model;

import java.time.LocalDate;
import java.util.List;

public class Student {
    private int idStudent;
    private int idInscription;  // Foreign key to Inscription
    private int idFamily;      // Foreign key to Famille
    private int idClassLevel; // Foreign key to ClassLevel
    private String matricule;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate date_of_birth; // Date de naissance
    private String phone;   // Numéro de téléphone
    private String address; // Adresse
    private String nationality;
    private String photoPath; // Chemin de la photo
    private boolean archived;
    
    private Family family; 
    private ClassLevel level; // Clé étrangère vers ClassLevel
    private Inscription inscription;    // One-to-One relationship with Inscription
    private List<Document> documents;   // One-to-Many relationship with Document
    private List<Payment> payments;     // One-to-Many relationship with Payment


    // Constructeur
    public Student() {}

    public Student(String matricule, String firstName, String lastName, String gender, 
                  LocalDate date_of_birth, String address, String phone, 
                  String nationality, String photoPath, int idFamily, int idInscription, int idClasslevel) {
        this.matricule = matricule;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.nationality = nationality;
        this.photoPath=photoPath;
        this.idFamily = idFamily;
        this.idInscription = idInscription;
        this.idClassLevel = idClasslevel;
        this.archived = false;
    }
    
    
    // Getters et Setters
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ClassLevel getLevel() {
        return level;
    }

    public void setLevel(ClassLevel level) {
        this.level = level;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

	public int getIdInscription() {
		return idInscription;
	}

	public void setIdInscription(int idInscription) {
		this.idInscription = idInscription;
	}

	public int getIdFamily() {
		return idFamily;
	}

	public void setIdFamily(int idFamily) {
		this.idFamily = idFamily;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

    public int getIdClassLevel() {
        return idClassLevel;
    }

    public void setIdClassLevel(int idClassLevel) {
        this.idClassLevel = idClassLevel;
    }


}
