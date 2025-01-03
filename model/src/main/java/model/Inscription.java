package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Inscription {
	private int idInscription;
    private int idStudent;  // Foreign key to Student
    private LocalDate dateDebutInscription;
    private LocalDate dateFinInscription;
    private BigDecimal fraisInscription;
    private BigDecimal fraisTransport;
    private BigDecimal montantInscription;
    private boolean archived;
    
    private Student student;                          // One-to-One relationship with Student
    private List<PaiementStudent> paiementStudent;  // One-to-Many relationship with PaiementStudents
    private Etablissement etablissement;          // One-to-One relationship with Etablissement
	
    
    public int getIdInscription() {
		return idInscription;
	}
	public void setIdInscription(int idInscription) {
		this.idInscription = idInscription;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public LocalDate getDateDebutInscription() {
		return dateDebutInscription;
	}
	public void setDateDebutInscription(LocalDate dateDebutInscription) {
		this.dateDebutInscription = dateDebutInscription;
	}
	public LocalDate getDateFinInscription() {
		return dateFinInscription;
	}
	public void setDateFinInscription(LocalDate dateFinInscription) {
		this.dateFinInscription = dateFinInscription;
	}
	public BigDecimal getFraisInscription() {
		return fraisInscription;
	}
	public void setFraisInscription(BigDecimal fraisInscription) {
		this.fraisInscription = fraisInscription;
	}
	public BigDecimal getFraisTransport() {
		return fraisTransport;
	}
	public void setFraisTransport(BigDecimal fraisTransport) {
		this.fraisTransport = fraisTransport;
	}
	public BigDecimal getMontantInscription() {
		return montantInscription;
	}
	public void setMontantInscription(BigDecimal montantInscription) {
		this.montantInscription = montantInscription;
	}
	public boolean isArchived() {
		return archived;
	}
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<PaiementStudent> getPaiementStudent() {
		return paiementStudent;
	}
	public void setPaiementStudent(List<PaiementStudent> paiementStudent) {
		this.paiementStudent = paiementStudent;
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

}
