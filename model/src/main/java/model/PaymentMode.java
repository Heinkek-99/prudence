package model;

import java.util.Objects;

public class PaymentMode {
    private int idMode;
    private String modeName;
    private boolean archived;

    public PaymentMode(int idMode, String modeName, Boolean archived) {
        this.idMode = idMode;
        this.modeName = modeName;
        this.archived = false;
    }

    public PaymentMode() {
        //TODO Auto-generated constructor stub
    }

    public int getIdMode() {
        return idMode;
    }

    public void setIdMode(int idMode) {
        this.idMode = idMode;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "PaymentMode [idMode=" + idMode + ", modeName=" + modeName + ", archived=" + archived + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PaymentMode that = (PaymentMode) obj;
        return idMode == that.idMode && Objects.equals(modeName, that.modeName) && archived == that.archived;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMode, modeName, archived);
    }

}
