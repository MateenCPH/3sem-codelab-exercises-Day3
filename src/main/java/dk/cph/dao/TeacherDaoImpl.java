package dk.cph.dao;

import dk.cph.model.Course;
import dk.cph.model.Teacher;
import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

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
    public void persistEntity(Teacher entity) {

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
}
