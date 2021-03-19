package com.example.demo.departments;

import com.example.demo.lectors.Lector;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Department {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long department_id;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL )
    private List<Lector> lectors;
    private String headOfDepartment;

    public Department(String name, String headOfDepartment) {
        this.name = name;
        this.headOfDepartment = headOfDepartment;
    }
}
