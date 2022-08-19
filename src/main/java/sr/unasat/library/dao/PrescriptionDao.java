package sr.unasat.library.dao;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.interfaces.DaoInterface;
import sr.unasat.library.projections.PrescriptionProjection;
import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Patient;
import sr.unasat.library.entity.Prescription;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrescriptionDao implements DaoInterface<Prescription> {
    private EntityManager entityManager;
    MedicineDao medicineDao = new MedicineDao(JPAConfiguration.getEntityManager());
    PatientDao patientDao = new PatientDao(JPAConfiguration.getEntityManager());

    public PrescriptionDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Prescription> retrieveList() {
        entityManager.getTransaction().begin();
        String jpql = "select p from Prescription p";
        TypedQuery<Prescription> query = entityManager.createQuery(jpql, Prescription.class);
        List<Prescription> prescriptionList = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptionList;
    }

    @Override
    public Prescription insert(Prescription prescription){
        entityManager.getTransaction().begin();
        entityManager.persist(prescription);
        entityManager.getTransaction().commit();
        return prescription;
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

    @Override
    public void delete(Prescription prescription) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Prescription p" +
                " where  p.prescriptionId = :id");
        query.setParameter("id", prescription.getPrescriptionId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Prescription prescription) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Prescription p SET p.dose = :dose where p.prescriptionId = :id");
        query.setParameter("dose", prescription.getDose());
        query.setParameter("id", prescription.getPrescriptionId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

}
