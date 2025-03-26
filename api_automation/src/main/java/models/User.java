package models;


public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private PersonalIdDocument personalIdDocument;

    public User(String firstName, String lastName, String email, String dateOfBirth, PersonalIdDocument personalIdDocument) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.personalIdDocument = personalIdDocument;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public PersonalIdDocument getPersonalIdDocument() { return personalIdDocument; }
    public void setPersonalIdDocument(PersonalIdDocument personalIdDocument) { this.personalIdDocument = personalIdDocument; }
}
