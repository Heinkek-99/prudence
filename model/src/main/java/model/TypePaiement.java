package model;

public class TypePaiement {
	private Integer idTypePaiement;
    private Integer idPayment;  // Foreign key to Payments
    private String typePaiement;
    private boolean archived;
    
    private Payment payment;  // Many-to-One relationship with Payment

    // Constructors
    public TypePaiement() {}

    public TypePaiement(Integer idPayment, String typePaiement) {
        this.setIdPayment(idPayment);
        this.setTypePaiement(typePaiement);
        this.setArchived(false);
    }

	public Integer getIdTypePaiement() {
		return idTypePaiement;
	}

	public void setIdTypePaiement(Integer idTypePaiement) {
		this.idTypePaiement = idTypePaiement;
	}

	public Integer getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(Integer idPayment) {
		this.idPayment = idPayment;
	}

	public String getTypePaiement() {
		return typePaiement;
	}

	public void setTypePaiement(String typePaiement) {
		this.typePaiement = typePaiement;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
