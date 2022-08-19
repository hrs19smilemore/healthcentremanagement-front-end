package sr.unasat.library.template;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.dao.MedicineDao;
import sr.unasat.library.dao.PatientDao;
import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Patient;
import sr.unasat.library.entity.Prescription;
import java.util.List;

public class ConcretePrescriptionTemplate extends  PrescriptionTemplate{
    MedicineDao medicineDao = new MedicineDao(JPAConfiguration.getEntityManager());
    PatientDao patientDao = new PatientDao(JPAConfiguration.getEntityManager());
    @Override
    public List<Medicine> checkIfMedicineExist(Prescription prescription) {
        return medicineDao.findStockByNameAndBrand(prescription.getMedicine().getName(), prescription.getMedicine().getBrand());

    }

    @Override
    public List<Patient> checkIfPatientExist(Prescription prescription) {
        return patientDao.getPatientInfo(prescription.getPatient().getIdentification().getNumber());
    }
}
