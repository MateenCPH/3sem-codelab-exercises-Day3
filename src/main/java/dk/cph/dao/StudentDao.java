package dk.cph.dao;

import dk.cph.model.Student;
import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentDao {

    private static StudentDao instance;
    private static EntityManagerFactory emf;

    public static StudentDao getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentDao();
        }
        return instance;
    }
}
