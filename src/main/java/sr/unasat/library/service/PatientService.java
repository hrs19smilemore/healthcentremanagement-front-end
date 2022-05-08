package sr.unasat.library.service;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.dao.PatientDao;
import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Patient;

import java.util.List;

public class PatientService {
    PatientDao patientDao = new PatientDao(JPAConfiguration.getEntityManager());

    public List<Patient> getAllPatients(){
        return patientDao.retrievePatientList();
    }

    public Patient insertPatient(Patient patient){
        return patientDao.insertOneRecord(patient);
    }

    public Patient deletePatient(Patient patient) {
        int index = -1;

        for (int i = 0; i < getAllPatients().size(); i++) {
            if (getAllPatients().get(i).getId() == patient.getId()) {
                index = i;
                break;
            }
        }
        Patient patientTemp;
        patientTemp = getAllPatients().get(index);


        patientDao.deleteOneRecord(patient);
        return patientTemp;

    }

    public Patient findPatient(Patient patient) {
        Patient foundPatient = new Patient(patient.getId());
        for (int i = 0; i < getAllPatients().size(); i++){
            if(getAllPatients().get(i).getId() == patient.getId())
            {
                foundPatient = getAllPatients().get(i);
            }
        }
        return foundPatient;
    }

    public List<Patient> getAllPatientInfo(String patientId) {
        return patientDao.getPatientInfo(patientId);
    }

    public Patient updatePatient(Patient patient) {
        for (int i = 0; i < getAllPatients().size(); i++) {
            if (getAllPatients().get(i).getId() == patient.getId()) {
                patientDao.updatePatient(patient);
                break;
            }
        }
        return patient;


    }
}
