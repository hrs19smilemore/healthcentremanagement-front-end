package sr.unasat.library.interfaces;

import sr.unasat.library.entity.Patient;

import java.util.List;

public interface PatientInterface {

    List<Patient> getAllPatients();
    Patient insertPatient(Patient patient);
    Patient deletePatient(Patient patient);
    Patient findPatient(Patient patient);
    List<Patient> getAllPatientInfo(String patientId);
    Patient updatePatient(Patient patient);
}
