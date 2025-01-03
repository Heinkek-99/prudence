/**
 * 
 */
package model;

/**
 * Author : Heidy KENGNE
 */
public class Document {
	private int idDocument;
    private int idStudent;
    private String type;
    private String path;
    private boolean archived;
    private Student student;  // Many-to-One relationship with Eleve


    // Constructeur
    public Document(int idDocument, int idStudent, String type, String path) {
        this.idDocument = idDocument;
        this.idStudent = idStudent;
        this.type = type;
        this.path = path;
        this.archived = false;
    }

    // Getters et Setters
    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
}