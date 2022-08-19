package sr.unasat.library.service;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.adapter.MedicineAndPatientListAdapter;
import sr.unasat.library.adapter.MedicineAndPatientListAdapterImplementation;
import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Patient;
import sr.unasat.library.interfaces.PrescriptionInterface;
import sr.unasat.library.projections.PrescriptionProjection;
import sr.unasat.library.dao.PrescriptionDao;
import sr.unasat.library.entity.Prescription;
import sr.unasat.library.template.ConcretePrescriptionTemplate;
import sr.unasat.library.template.PrescriptionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrescriptionServiceImpl implements PrescriptionInterface {
    PrescriptionDao prescriptionDao = new PrescriptionDao(JPAConfiguration.getEntityManager());

    @Override
    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicinePerYear(int year){
        return prescriptionDao.getPrescriptionsByYear(year);
    }

    @Override
    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor1stHalfOfYear(int year){
        return prescriptionDao.getPrescriptionsFor1stHalfOfYear(year);
    }

    @Override
    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor2ndHalfOfYear(int year){
        return prescriptionDao.getPrescriptionsFor2ndHalfOfYear(year);
    }

    @Override
    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor1stQuarterOfYear(int year){
        return prescriptionDao.getPrescriptionsFor1stQuarterOfYear(year);
    }

    @Override
    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor2ndQuarterOfYear(int year){
        return prescriptionDao.getPrescriptionsFor2ndQuarterOfYear(year);
    }

    @Override
    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor3rdQuarterOfYear(int year){
        return prescriptionDao.getPrescriptionsFor3rdQuarterOfYear(year);
    }

    @Override
    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor4thQuarterOfYear(int year){
        return prescriptionDao.getPrescriptionsFor4thQuarterOfYear(year);
    }

    @Override
    public List<Prescription> getAllPrescriptions(){
        return prescriptionDao.retrieveList();
    }

    @Override
    public Prescription insertPrescription(Prescription prescription){
        PrescriptionTemplate prescriptionTemplate = new ConcretePrescriptionTemplate();
        Map<String, List<?>> map = prescriptionTemplate.check(prescription);
        MedicineAndPatientListAdapter medicineAndPatientListAdapter = new MedicineAndPatientListAdapterImplementation(map);
        List<Medicine> medicines = medicineAndPatientListAdapter.wildcardToMedicineList();
        List<Patient> patients = medicineAndPatientListAdapter.wildcardToPatientList();
        Prescription p = null;
        if (medicines.isEmpty() && patients.isEmpty()) {
            return p;
        } else {
            prescription.setMedicine(medicines.get(0));
            prescription.setPatient(patients.get(0));
        }
        return prescriptionDao.insert(prescription);
    }

    @Override
    public Prescription deletePrescription (Prescription prescription) {
        int index = -1;

        for (int i = 0; i < getAllPrescriptions().size(); i++) {
            if (getAllPrescriptions().get(i).getPrescriptionId() == prescription.getPrescriptionId()) {
                index = i;
                break;
            }
        }
        Prescription prescriptionTemp = new Prescription();
        prescriptionTemp = getAllPrescriptions().get(index);


        prescriptionDao.delete(prescription);
        return prescriptionTemp;

    }

    @Override
    public Prescription findPrescription(Prescription prescription) {
        Prescription foundPrescription = new Prescription();
        for (int i = 0; i < getAllPrescriptions().size(); i++){
            if(getAllPrescriptions().get(i).getPrescriptionId() == prescription.getPrescriptionId())
            {
                foundPrescription = getAllPrescriptions().get(i);
            }
        }
        return foundPrescription;
    }

    @Override
    public Prescription update(Prescription prescription) {
        for (int i = 0; i < getAllPrescriptions().size(); i++) {
            if (getAllPrescriptions().get(i).getPrescriptionId() == prescription.getPrescriptionId()) {
                prescriptionDao.update(prescription);
                break;
            }
        }
        return prescription;


    }
}
