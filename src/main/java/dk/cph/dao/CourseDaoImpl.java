package dk.cph.dao;

import dk.cph.model.Student;
import dk.cph.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseDaoImpl implements GenericDAO<Student, Integer> {

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
    public List<Student> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            if (students.isEmpty()) {
                Student s1 = Student.builder()
                        .name("John Doe")
                        .email("john@doe.com")
                        .createdAt(LocalDateTime.now())
                        .build();
                persistEntity(s1);
                students.add(s1);
                return students;
            }
            em.getTransaction().commit();
            return students;
        }
    }

    @Override
    public void persistEntity(Student entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }

    }

    @Override
    public void removeEntity(Integer id) {

    }

    @Override
    public Student findEntity(Integer id) {
        return null;
    }

    @Override
    public Student updateEntity(Student entity, Integer id) {
        return null;
    }
}
