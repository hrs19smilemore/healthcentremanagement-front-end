package sr.unasat.library.dao;

import sr.unasat.library.entity.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PatientDao {
    private EntityManager entityManager;

    public PatientDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Patient> retrievePatientList() {
        entityManager.getTransaction().begin();

        String jpql = "select p from Patient p";
        TypedQuery<Patient> query = entityManager.createQuery(jpql, Patient.class);
        List<Patient> patientList = query.getResultList();
        entityManager.getTransaction().commit();
        return patientList;
    }

    public Patient insertOneRecord(Patient patient){
        entityManager.getTransaction().begin();
        entityManager.persist(patient.getIdentification());
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        return patient;
    }

    public void deleteOneRecord(Patient patient) {
        entityManager.getTransaction().begin();
        String jpql = "select p from Patient p where p.id = :id";
        TypedQuery<Patient> query1 = entityManager.createQuery(jpql, Patient.class);
        query1.setParameter("id", patient.getId());
        Patient patient1 = query1.getSingleResult();
        Query query2 = entityManager.createQuery("delete from Patient p" +
                " where p.id = :id");
        query2.setParameter("id", patient.getId());
        query2.executeUpdate();
        Query query3 = entityManager.createQuery("DELETE from Identification i " +
                "where i.number = :identification");
        query3.setParameter("identification", patient1.getIdentification().getNumber());
        query3.executeUpdate();
        System.out.println("entities deleted: ");
        entityManager.getTransaction().commit();
    }

    public List<Patient> getPatientInfo(String idNumber) {
        entityManager.getTransaction().begin();
        String jpql = "select p from Patient p where p.identification.number = :idNumber";
        TypedQuery<Patient> query = entityManager.createQuery(jpql, Patient.class);
        query.setParameter("idNumber", idNumber);
        List<Patient> patientInfoList = query.getResultList();
        entityManager.getTransaction().commit();
        return patientInfoList;
    }

    public void updatePatient(Patient patient) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Patient p SET p.adress = :adress, p.contactnumber = :contactnumber," +
                " p.firstname = :firstname, p.lastname = :lastname " +
                "where p.id = :id");
        query.setParameter("id", patient.getId());
        query.setParameter("firstname", patient.getFirstname());
        query.setParameter("lastname", patient.getLastname());
        query.setParameter("adress", patient.getAdress());
        query.setParameter("contactnumber", patient.getContactnumber());
        query.executeUpdate();
        Query query2 = entityManager.createQuery("UPDATE Identification i SET i.sex = :sex where i.number = :idNumber");
        query2.setParameter("idNumber", patient.getIdentification().getNumber());
        query2.setParameter("sex", patient.getIdentification().getSex());
        query2.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
