package com.example.demo.lectors;
import com.example.demo.departments.Department;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Lector {
    @Id
    @SequenceGenerator(
            name = "lector_sequence",
            sequenceName = "lector_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lector_sequence"
    )
    private Long lector_id;
    private String name;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Department department;
    private Long salary;

    public Lector(String name, String lastName, Degree degree, Department department, Long salary) {
        this.name = name;
        this.lastName = lastName;
        this.degree = degree;
        this.department = department;
        this.salary = salary;
    }
}
