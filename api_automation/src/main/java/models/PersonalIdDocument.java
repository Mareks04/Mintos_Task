package models;

public class PersonalIdDocument {
    private String documentId;
    private String countryOfIssue;
    private String validUntil;


    public PersonalIdDocument(String documentId, String countryOfIssue, String validUntil) {
        this.documentId = documentId;
        this.countryOfIssue = countryOfIssue;
        this.validUntil = validUntil;
    }

    public String getDocumentId() { return documentId; }
    public void setDocumentId(String documentId) { this.documentId = documentId; }

    public String getCountryOfIssue() { return countryOfIssue; }
    public void setCountryOfIssue(String countryOfIssue) { this.countryOfIssue = countryOfIssue; }

    public String getValidUntil() { return validUntil; }
    public void setValidUntil(String validUntil) { this.validUntil = validUntil; }
}
