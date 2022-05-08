package sr.unasat.library.projections;


public class PrescriptionProjection {
    private long dose;
    private long prescriptions;
    private String medicineName;
    private String medicineBrand;

    public PrescriptionProjection(long dose, long prescriptions, String medicineName, String medicineBrand) {
        this.dose = dose;
        this.prescriptions = prescriptions;
        this.medicineName = medicineName;
        this.medicineBrand = medicineBrand;
    }

    public String getMedicineBrand() {
        return medicineBrand;
    }

    public void setMedicineBrand(String medicineBrand) {
        this.medicineBrand = medicineBrand;
    }

    public long getDose() {
        return dose;
    }

    public void setDose(long dose) {
        this.dose = dose;
    }

    public long getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(long prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Override
    public String toString() {
        return "PrescriptionProjection{" +
                "dose=" + dose +
                ", prescriptions=" + prescriptions +
                ", medicineName='" + medicineName + '\'' +
                ", medicineBrand='" + medicineBrand + '\'' +
                '}';
    }
}
