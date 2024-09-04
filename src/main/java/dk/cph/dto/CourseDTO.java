package dk.cph.dto;

import dk.cph.model.Student;
import dk.cph.model.Teacher;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CourseDTO {

    private String name;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Student> students;
    private Teacher teacher;
}
