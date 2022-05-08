package sr.unasat.library.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescription", schema = "healthcentremanagement-frontend")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescriptionId")
    private int prescriptionId;
    @Column(name = "dose", nullable = false)
    private int dose;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "prescription_date", nullable = false)
    private LocalDate prescription_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    @JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_id", referencedColumnName = "medicineId")
    private Medicine medicine;

    public Prescription() {
    }

    public Prescription(int dose, LocalDate prescription_date, Patient patient, Medicine medicine) {
        this.dose = dose;
        this.prescription_date = prescription_date;
        this.patient = patient;
        this.medicine = medicine;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int id) {
        this.prescriptionId = id;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public LocalDate getPrescription_date() {
        return prescription_date;
    }

    public void setPrescription_date(LocalDate prescription_date) {
        this.prescription_date = prescription_date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", dose=" + dose +
                ", prescription_date=" + prescription_date +
                ", patient=" + patient +
                ", medicine=" + medicine +
                '}';
    }
}
