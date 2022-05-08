package sr.unasat.library.dao;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.projections.PrescriptionProjection;
import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Patient;
import sr.unasat.library.entity.Prescription;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrescriptionDao {
    private EntityManager entityManager;
    MedicineDao medicineDao = new MedicineDao(JPAConfiguration.getEntityManager());
    PatientDao patientDao = new PatientDao(JPAConfiguration.getEntityManager());

    public PrescriptionDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Prescription> retrievePrescriptionList() {
        entityManager.getTransaction().begin();
        String jpql = "select p from Prescription p";
        TypedQuery<Prescription> query = entityManager.createQuery(jpql, Prescription.class);
        List<Prescription> prescriptionList = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptionList;
    }

    public Prescription insertOneRecord(Prescription prescription){
        Medicine medicine = medicineDao.findStockByNameAndBrand(prescription.getMedicine().getName(), prescription.getMedicine().getBrand());
        List<Patient> patients = patientDao.getPatientInfo(prescription.getPatient().getIdentification().getNumber());
        entityManager.getTransaction().begin();
        prescription.setMedicine(medicine);
        prescription.setPatient(patients.get(0));
        //Prescription p = new Prescription(prescription.getDose(), prescription.getPrescription_date(), patients.get(0), medicine);
        entityManager.persist(prescription);
        entityManager.getTransaction().commit();
        return prescription;
    }

    public List<Prescription> findPrescriptionByIdentification(Patient patient) {
        entityManager.getTransaction().begin();
        String jpql = "select pr from Prescription pr  join Patient  p on pr.patient.id = p.id" +
                " where p.identification.number = :identification";
        TypedQuery<Prescription> query = entityManager.createQuery(jpql, Prescription.class);
        query.setParameter("identification", patient.getIdentification().getNumber());
        List<Prescription> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public List<PrescriptionProjection> getPrescriptionsByYear(int year){
        entityManager.getTransaction().begin();
        String jpql = "select new sr.unasat.library.projections.PrescriptionProjection(sum(p.dose), count(p.prescriptionId), p.medicine.name, p.medicine.brand)" +
                " from Prescription p " +
                "where YEAR(p.prescription_date) = :year " +
                "group by p.medicine.name, p.medicine.brand";
        TypedQuery<PrescriptionProjection> query = entityManager.createQuery(jpql, PrescriptionProjection.class);
        query.setParameter("year", year);
        List<PrescriptionProjection> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public List<PrescriptionProjection> getPrescriptionsFor1stHalfOfYear(int year){
        entityManager.getTransaction().begin();
        String jpql = "select new sr.unasat.library.projections.PrescriptionProjection(sum(p.dose), count(p.prescriptionId), p.medicine.name, p.medicine.brand)" +
                " from Prescription p " +
                "where QUARTER(p.prescription_date) in (1,2) and YEAR(p.prescription_date) = :year " +
                "group by p.medicine.name, p.medicine.brand";
        TypedQuery<PrescriptionProjection> query = entityManager.createQuery(jpql, PrescriptionProjection.class);
        query.setParameter("year", year);
        List<PrescriptionProjection> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public List<PrescriptionProjection> getPrescriptionsFor2ndHalfOfYear(int year){
        entityManager.getTransaction().begin();
        String jpql = "select new sr.unasat.library.projections.PrescriptionProjection(sum(p.dose), count(p.prescriptionId), p.medicine.name, p.medicine.brand)" +
                " from Prescription p " +
                "where QUARTER(p.prescription_date) in (3,4) and YEAR(p.prescription_date) = :year " +
                "group by p.medicine.name, p.medicine.brand";
        TypedQuery<PrescriptionProjection> query = entityManager.createQuery(jpql, PrescriptionProjection.class);
        query.setParameter("year", year);
        List<PrescriptionProjection> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public List<PrescriptionProjection> getPrescriptionsFor1stQuarterOfYear(int year){
        entityManager.getTransaction().begin();
        String jpql = "select new sr.unasat.library.projections.PrescriptionProjection(sum(p.dose), count(p.prescriptionId), p.medicine.name, p.medicine.brand)" +
                " from Prescription p " +
                "where QUARTER(p.prescription_date) in (1) and YEAR(p.prescription_date) = :year " +
                "group by p.medicine.name, p.medicine.brand";
        TypedQuery<PrescriptionProjection> query = entityManager.createQuery(jpql, PrescriptionProjection.class);
        query.setParameter("year", year);
        List<PrescriptionProjection> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public List<PrescriptionProjection> getPrescriptionsFor2ndQuarterOfYear(int year){
        entityManager.getTransaction().begin();
        String jpql = "select new sr.unasat.library.projections.PrescriptionProjection(sum(p.dose), count(p.prescriptionId), p.medicine.name, p.medicine.brand)" +
                " from Prescription p " +
                "where QUARTER(p.prescription_date) in (2) and YEAR(p.prescription_date) = :year " +
                "group by p.medicine.name, p.medicine.brand";
        TypedQuery<PrescriptionProjection> query = entityManager.createQuery(jpql, PrescriptionProjection.class);
        query.setParameter("year", year);
        List<PrescriptionProjection> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public List<PrescriptionProjection> getPrescriptionsFor3rdQuarterOfYear(int year){
        entityManager.getTransaction().begin();
        String jpql = "select new sr.unasat.library.projections.PrescriptionProjection(sum(p.dose), count(p.prescriptionId), p.medicine.name, p.medicine.brand)" +
                " from Prescription p " +
                "where QUARTER(p.prescription_date) in (3) and YEAR(p.prescription_date) = :year " +
                "group by p.medicine.name, p.medicine.brand";
        TypedQuery<PrescriptionProjection> query = entityManager.createQuery(jpql, PrescriptionProjection.class);
        query.setParameter("year", year);
        List<PrescriptionProjection> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public List<PrescriptionProjection> getPrescriptionsFor4thQuarterOfYear(int year){
        entityManager.getTransaction().begin();
        String jpql = "select new sr.unasat.library.projections.PrescriptionProjection(sum(p.dose), count(p.prescriptionId), p.medicine.name, p.medicine.brand)" +
                " from Prescription p " +
                "where QUARTER(p.prescription_date) in (4) and YEAR(p.prescription_date) = :year " +
                "group by p.medicine.name, p.medicine.brand";
        TypedQuery<PrescriptionProjection> query = entityManager.createQuery(jpql, PrescriptionProjection.class);
        query.setParameter("year", year);
        List<PrescriptionProjection> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public int deleteOneRecord(Prescription prescription) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Prescription p" +
                " where  p.prescriptionId = :id");
        query.setParameter("id", prescription.getPrescriptionId());
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public void updatePrescriptionDose(Prescription prescription) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Prescription p SET p.dose = :dose where p.prescriptionId = :id");
        //query.setParameter("brand", prescription.getMedicine().getBrand());
        //query.setParameter("name", prescription.getMedicine().getName());
        //query.setParameter("date", prescription.getPrescription_date());
        query.setParameter("dose", prescription.getDose());
        query.setParameter("id", prescription.getPrescriptionId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

}
