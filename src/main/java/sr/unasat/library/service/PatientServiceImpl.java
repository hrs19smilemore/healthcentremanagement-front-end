package sr.unasat.library.service;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.dao.PatientDao;
import sr.unasat.library.entity.Patient;
import sr.unasat.library.interfaces.PatientInterface;

import java.util.List;

public class PatientServiceImpl implements PatientInterface {
    PatientDao patientDao = new PatientDao(JPAConfiguration.getEntityManager());

    @Override
    public List<Patient> getAllPatients(){
        return patientDao.retrieveList();
    }

    @Override
    public Patient insertPatient(Patient patient){
        return patientDao.insert(patient);
    }

    @Override
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


        patientDao.delete(patient);
        return patientTemp;

    }

    @Override
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

    @Override
    public List<Patient> getAllPatientInfo(String patientId) {
        return patientDao.getPatientInfo(patientId);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        for (int i = 0; i < getAllPatients().size(); i++) {
            if (getAllPatients().get(i).getId() == patient.getId()) {
                patientDao.update(patient);
                break;
            }
        }
        return patient;


    }
}
