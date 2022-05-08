package sr.unasat.library.projections;

import sr.unasat.library.entity.Identification;

public class PatientProjection {
    private String adress;
    private String contactNumber;
    private String firstname;
    private String lastname;
    private Identification identification;

    public PatientProjection(String adress, String contactNumber, String firstname, String lastname, Identification identification) {
        this.adress = adress;
        this.contactNumber = contactNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.identification = identification;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }
}
