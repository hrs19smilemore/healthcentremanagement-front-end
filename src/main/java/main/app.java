package main;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.dao.PatientDao;
import sr.unasat.library.entity.Patient;

public class app {
    public static void main(String[] args) {
        PatientDao patientDao = new PatientDao(JPAConfiguration.getEntityManager());
        patientDao.deleteOneRecord(new Patient(4));
    }
}
