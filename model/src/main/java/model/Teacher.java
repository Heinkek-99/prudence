package model;


public class Teacher {

    private int idTeacher;
    private String firstName;
    private String lastName;
    private String email;
    private String subject;
    private String phone;
    private boolean archived;

    public Teacher(int idTeacher, String firstName, String lastName, String email, String subject, String phone) {
        this.idTeacher = idTeacher;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subject = subject;
        this.phone = phone;
        this.archived = false;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "Teacher [idTeacher=" + idTeacher + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
                + email + ", subject=" + subject + ", phone=" + phone + ", archived=" + archived + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher other = (Teacher) obj;
        return idTeacher == other.idTeacher && firstName.equals(other.firstName) && lastName.equals(other.lastName) && email.equals(other.email) && subject.equals(other.subject) && phone.equals(other.phone) && archived == other.archived;
    }

}
