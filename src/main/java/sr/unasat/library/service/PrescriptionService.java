package sr.unasat.library.service;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.projections.PrescriptionProjection;
import sr.unasat.library.dao.PrescriptionDao;
import sr.unasat.library.entity.Prescription;

import java.util.List;

public class PrescriptionService {
    PrescriptionDao prescriptionDao = new PrescriptionDao(JPAConfiguration.getEntityManager());

    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicinePerYear(int year){
        System.out.println(prescriptionDao.getPrescriptionsByYear(year));
        return prescriptionDao.getPrescriptionsByYear(year);
    }

    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor1stHalfOfYear(int year){
        //System.out.println(prescriptionDao.getPrescriptionsByYear(year));
        return prescriptionDao.getPrescriptionsFor1stHalfOfYear(year);
    }

    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor2ndHalfOfYear(int year){
        //System.out.println(prescriptionDao.getPrescriptionsByYear(year));
        return prescriptionDao.getPrescriptionsFor2ndHalfOfYear(year);
    }

    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor1stQuarterOfYear(int year){
        //System.out.println(prescriptionDao.getPrescriptionsByYear(year));
        return prescriptionDao.getPrescriptionsFor1stQuarterOfYear(year);
    }

    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor2ndQuarterOfYear(int year){
        //System.out.println(prescriptionDao.getPrescriptionsByYear(year));
        return prescriptionDao.getPrescriptionsFor2ndQuarterOfYear(year);
    }

    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor3rdQuarterOfYear(int year){
        //System.out.println(prescriptionDao.getPrescriptionsByYear(year));
        return prescriptionDao.getPrescriptionsFor3rdQuarterOfYear(year);
    }

    public List<PrescriptionProjection> returnPrescriptionsGroupedByMedicineFor4thQuarterOfYear(int year){
        //System.out.println(prescriptionDao.getPrescriptionsByYear(year));
        return prescriptionDao.getPrescriptionsFor4thQuarterOfYear(year);
    }

    public List<Prescription> getAllPrescriptions(){
        return prescriptionDao.retrievePrescriptionList();
    }

    public Prescription insertPrescription(Prescription prescription){
        System.out.println("Inserted prescriptions: " + prescriptionDao.insertOneRecord(prescription));
        return prescriptionDao.insertOneRecord(prescription);
    }

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


        prescriptionDao.deleteOneRecord(prescription);
        return prescriptionTemp;

    }

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

    public Prescription update(Prescription prescription) {
        for (int i = 0; i < getAllPrescriptions().size(); i++) {
            if (getAllPrescriptions().get(i).getPrescriptionId() == prescription.getPrescriptionId()) {
                prescriptionDao.updatePrescriptionDose(prescription);
                break;
            }
        }
        return prescription;


    }
}
