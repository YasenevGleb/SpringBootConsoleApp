package com.example.demo.departments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    String sqlHead="select head_of_department  " +
            "from department " +
            "where name = :department_name";
    @Query(value = sqlHead,nativeQuery = true)
    Optional<String> headOfDepartment(@Param("department_name") String department_name);
}
