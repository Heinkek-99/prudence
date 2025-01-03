package model;

import java.util.ArrayList;
import java.util.List;

public class Family {
	
	private int idFamily;
    private String nomParent;
    private String prenomParent;
    private String titre;
    private String adresse1;
    private String codeVille;
    private String telephone;
    private String cnie;

    private List<Student> students; // Liste des élèves dans la famille
    private boolean archived;

    // Constructeur
    public Family(int idFamily, String nomParent, String prenomParent, String titre, String adresse1, 
                  String codeVille, String telephone, String cnie) {
        this.idFamily = idFamily;
        this.nomParent = nomParent;
        this.prenomParent = prenomParent;
        this.titre = titre;
        this.adresse1 = adresse1;
        this.codeVille = codeVille;
        this.telephone = telephone;
        this.cnie = cnie;
        this.students = new ArrayList<>();
        this.archived = false;
    }

    // Getters et Setters
    public int getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(int idFamily) {
        this.idFamily = idFamily;
    }

    public String getNomParent() {
        return nomParent;
    }

    public void setNomParent(String nomParent) {
        this.nomParent = nomParent;
    }

    public String getPrenomParent() {
        return prenomParent;
    }

    public void setPrenomParent(String prenomParent) {
        this.prenomParent = prenomParent;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getCodeVille() {
        return codeVille;
    }

    public void setCodeVille(String codeVille) {
        this.codeVille = codeVille;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCnie() {
        return cnie;
    }

    public void setCnie(String cnie) {
        this.cnie = cnie;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
