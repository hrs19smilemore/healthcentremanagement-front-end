package sr.unasat.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "identification", schema = "healthcentremanagement-frontend")
public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @Column(name = "sex", nullable = false)
    private String sex;

    @OneToOne(mappedBy = "identification")
    @JsonIgnore
    private Patient patient;

    public Identification() {
    }

    public Identification(String number) {
        this.number = number;
    }

    public Identification(String number, String sex) {
        this.number = number;
        this.sex = sex;
    }

    public Identification(int id, String number, String sex, Patient patient) {
        this.id = id;
        this.number = number;
        this.sex = sex;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Identification{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
