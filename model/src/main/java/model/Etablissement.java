package model;

public class Etablissement {
	private int idEtablissement;
    private int idInscription;  // Foreign key to Inscription
    private String codeGresa;
    private String etablissement;
    private String nAutorisation;
    private boolean archived;
    
    private Inscription inscription;  // One-to-One relationship with Inscription

    // Constructors
    public Etablissement() {}

    public Etablissement(int idInscription, String codeGresa, String etablissement, String nAutorisation) {
        this.setIdInscription(idInscription);
        this.setCodeGresa(codeGresa);
        this.setEtablissement(etablissement);
        this.setnAutorisation(nAutorisation);
        this.setArchived(false);
    }

	public int getIdEtablissement() {
		return idEtablissement;
	}

	public void setIdEtablissement(int idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	public int getIdInscription() {
		return idInscription;
	}

	public void setIdInscription(int idInscription) {
		this.idInscription = idInscription;
	}

	public String getCodeGresa() {
		return codeGresa;
	}

	public void setCodeGresa(String codeGresa) {
		this.codeGresa = codeGresa;
	}

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getnAutorisation() {
		return nAutorisation;
	}

	public void setnAutorisation(String nAutorisation) {
		this.nAutorisation = nAutorisation;
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
