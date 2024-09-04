package dk.cph.dto;

import dk.cph.model.Course;
import lombok.*;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class TeacherDTO {
    private String name;
    private String zoom;
    private Set<Course> courses;

}
