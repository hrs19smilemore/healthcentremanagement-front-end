package sr.unasat.library.dao;

import sr.unasat.library.interfaces.DaoInterface;
import sr.unasat.library.projections.MedicineProjection;
import sr.unasat.library.entity.Medicine;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class MedicineDao implements DaoInterface<Medicine> {
    private EntityManager entityManager;

    public MedicineDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Medicine> retrieveList() {
        entityManager.getTransaction().begin();
        String jpql = "select m from Medicine m";
        TypedQuery<Medicine> query = entityManager.createQuery(jpql, Medicine.class);
        List<Medicine> medicineList = query.getResultList();
        entityManager.getTransaction().commit();
        return medicineList;
    }

    @Override
    public Medicine insert(Medicine medicine) {
        entityManager.getTransaction().begin();
        entityManager.persist(medicine);
        entityManager.getTransaction().commit();
        return medicine;
    }

    @Override
    public void delete(Medicine medicine) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Medicine m" +
                " where m.id = :id");
        query.setParameter("id", medicine.getId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Medicine medicine) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Medicine m SET m.name = :name, m.brand = :brand, m.description = :description," +
                " m.stock = :stock where m.id = :id");
        query.setParameter("stock", medicine.getStock());
        query.setParameter("name", medicine.getName());
        query.setParameter("brand", medicine.getBrand());
        query.setParameter("description", medicine.getDescription());
        query.setParameter("id", medicine.getId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public List<MedicineProjection> getMedicineReport() {
        entityManager.getTransaction().begin();
        String jpql = "select new sr.unasat.library.projections.MedicineProjection(m.name, m.brand, m.stock,(m.stock - sum(p.dose)))" +
                "from Medicine m join Prescription p on m.id = p.medicine.id " +
                "group by m.name, m.brand, m.stock";
        TypedQuery<MedicineProjection> query = entityManager.createQuery(jpql, MedicineProjection.class);
        List<MedicineProjection> medicineReport = query.getResultList();
        entityManager.getTransaction().commit();
        return medicineReport;
    }

    public List<Medicine> findStockByNameAndBrand(String name, String brand) {
        entityManager.getTransaction().begin();
        String jpql = "select m from Medicine m  where m.name = :name and m.brand = :brand";
        TypedQuery<Medicine> query = entityManager.createQuery(jpql, Medicine.class);
        query.setParameter("name", name);
        query.setParameter("brand", brand);
        List<Medicine> medicines = query.getResultList();
        entityManager.getTransaction().commit();
        return medicines;
    }
}
