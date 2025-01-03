/**
 * 
 */
package model;

/**
 * Author : Heidy KENGNE
 */
public class ClassLevel {
	private int idLevel;
    private String levelName; // Ex: CE1, CE2, etc.
    private String description;
    private int idReferent;
    private boolean archived;
    private Teacher referent;

    // Constructeur
    public ClassLevel(int idLevel, String levelName, String description, int idReferent) {
        this.idLevel = idLevel;
        this.levelName = levelName;
        this.description = description;
        this.idReferent = idReferent;
        this.archived = false;
    }

    public ClassLevel() {
        //TODO Auto-generated constructor stub
    }

    // Getters et Setters
    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    } 

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getidReferent(){
        return idReferent;
    }

    public void setidReferent(int idReferent){
         this.idReferent = idReferent;
    }

    public Teacher getReferent() {
        return referent;
    }

    public void setReferent(Teacher referent) {
        this.referent = referent;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
