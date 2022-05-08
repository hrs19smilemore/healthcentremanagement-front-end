package sr.unasat.library.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient", schema = "healthcentremanagement-frontend")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientId")
    private int id;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "adress")
    private String adress;
    @Column(name = "contactnumber", nullable = false)
    private String contactnumber;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identification_id", unique = true, nullable = false)
    private Identification identification;

    @OneToMany(mappedBy = "patient")
    @JsonIgnoreProperties(value = "patient", allowSetters = true)
    private Set<Prescription> prescriptions;


    public Patient(int id, String firstname, String lastname, String adress, String contactnumber, Identification identification) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.contactnumber = contactnumber;
        this.identification = identification;
    }

    public Patient(int id){
        this.id = id;
    }

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", adress='" + adress + '\'' +
                ", contactnumber='" + contactnumber + '\'' +
                ", identification=" + identification +
                '}';
    }
}
