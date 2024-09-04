package dk.cph.dao;

import dk.cph.config.HibernateConfig;
import dk.cph.dto.TeacherDTO;
import dk.cph.enums.CourseName;
import dk.cph.model.Course;
import dk.cph.model.Teacher;
import jakarta.persistence.EntityManager;
import dk.cph.model.Course;
import dk.cph.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherDaoImpl implements GenericDAO<Teacher, Integer>{

    private static TeacherDaoImpl instance;
    private static EntityManagerFactory emf;

    public static TeacherDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TeacherDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Teacher> findAll() {
        return List.of();
    }

    @Override
    public void persistEntity(Teacher teacher) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Course course = teacher.getCourses().iterator().next();

            if (course != null) {
                CourseName courseName = course.getCourseName();
                TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.courseName = :course_name", Course.class);
                query.setParameter("course_name", courseName);
                try {
                    Course found = query.getSingleResult();
                    teacher.getCourses().add(found);
                } catch(Exception e){
                    em.persist(course);
                }
            }
            em.persist(teacher);
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeEntity(Integer id) {

    }

    @Override
    public Teacher findEntity(Integer id) {
        return null;
    }

    @Override
    public Teacher updateEntity(Teacher entity, Integer id) {
        return null;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        TeacherDaoImpl teacherDao = TeacherDaoImpl.getInstance(emf);;

        Teacher t1 = Teacher.builder()
                .name("William")
                .email("willymail@gmail.com")
                .zoom("zoom.com")
                .build();
        Course c1 = Course.builder()
                .description("Math course 2+2")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(1))
                .courseName(CourseName.MATH)
                .build();

        t1.setCourses(Set.of(c1));
        teacherDao.persistEntity(t1);
    }
}
