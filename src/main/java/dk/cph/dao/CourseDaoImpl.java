package dk.cph.dao;

import dk.cph.enums.CourseName;
import dk.cph.model.Course;
import dk.cph.model.Student;
import dk.cph.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseDaoImpl implements GenericDAO<Course, Integer> {

    private static CourseDaoImpl instance;
    private static EntityManagerFactory emf;

    public static CourseDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CourseDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Course> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
            if (courses.isEmpty()) {
                Course c1 = Course.builder()
                        .description("Java")
                        .courseName(CourseName.HISTORY)
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.of(2035,12,31))
                        .build();
                persistEntity(c1);
                courses.add(c1);
                return courses;
            }
            return courses;
        }
    }

    @Override
    public void persistEntity(Course course) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Address address = emp.getAddress();
            if (address != null) {
                String street = address.getStreet();
                TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a WHERE a.street = :street", Address.class);
                query.setParameter("street", street);

                try {
                    Address found = query.getSingleResult();
                    emp.setAddress(found);
                } catch (NoResultException e) {
                    em.persist(address);
                }
            }
            em.persist(emp);
            em.getTransaction().commit();
        }

    }

    @Override
    public void removeEntity(Integer id) {

    }

    @Override
    public Course findEntity(Integer id) {
        return null;
    }

    @Override
    public Course updateEntity(Course entity, Integer id) {
        return null;
    }
}
