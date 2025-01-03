package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaiementStudent {
	   	private int idPaiementStudent;
	    private int idInscription;  // Foreign key to Inscription
	    private String typePaiementStudent;
	    private LocalDate datePaiementStudent;
	    private BigDecimal montant;
	    private boolean archived;
	    
	    private Inscription inscription;  // Many-to-One relationship with Inscription

	    // Constructors
	    public PaiementStudent() {}

	    public PaiementStudent(int idInscription, String typePaiementStudent, LocalDate datePaiementStudent, BigDecimal montant) {
	        this.setIdInscription(idInscription);
	        this.setTypePaiementStudent(typePaiementStudent);
	        this.setDatePaiementStudent(datePaiementStudent);
	        this.setMontant(montant);
	        this.setArchived(false);
	    }

		public int getIdPaiementStudent() {
			return idPaiementStudent;
		}

		public void setIdPaiementStudent(int idPaiementStudent) {
			this.idPaiementStudent = idPaiementStudent;
		}

		public int getIdInscription() {
			return idInscription;
		}

		public void setIdInscription(int idInscription) {
			this.idInscription = idInscription;
		}

		public String getTypePaiementStudent() {
			return typePaiementStudent;
		}

		public void setTypePaiementStudent(String typePaiementStudent) {
			this.typePaiementStudent = typePaiementStudent;
		}

		public LocalDate getDatePaiementStudent() {
			return datePaiementStudent;
		}

		public void setDatePaiementStudent(LocalDate datePaiementStudent) {
			this.datePaiementStudent = datePaiementStudent;
		}

		public BigDecimal getMontant() {
			return montant;
		}

		public void setMontant(BigDecimal montant) {
			this.montant = montant;
		}

		public boolean isArchived() {
			return archived;
		}

		public void setArchived(boolean archived) {
			this.archived = archived;
		}

		public Inscription getInscription() {
			return inscription;
		}

		public void setInscription(Inscription inscription) {
			this.inscription = inscription;
		}

}
