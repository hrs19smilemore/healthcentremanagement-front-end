package sr.unasat.library.interfaces;

import sr.unasat.library.entity.Prescription;
import sr.unasat.library.projections.PrescriptionProjection;

import java.util.List;

public interface PrescriptionInterface {

    List<PrescriptionProjection> returnPrescriptionsGroupedByMedicinePerYear(int year);
    List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor1stHalfOfYear(int year);
    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor2ndHalfOfYear(int year);
    List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor1stQuarterOfYear(int year);
    List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor2ndQuarterOfYear(int year);
    List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor3rdQuarterOfYear(int year);
    List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor4thQuarterOfYear(int year);
    List<Prescription> getAllPrescriptions();
    Prescription insertPrescription(Prescription prescription);
    Prescription deletePrescription (Prescription prescription);
    Prescription findPrescription(Prescription prescription);
    Prescription update(Prescription prescription);
}
