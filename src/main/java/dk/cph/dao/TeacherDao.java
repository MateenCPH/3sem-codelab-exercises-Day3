package dk.cph.dao;

import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherDao {

    private static TeacherDao instance;
    private static EntityManagerFactory emf;

    public static TeacherDao getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TeacherDao();
        }
        return instance;
    }
}
