package com.example.demo.lectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectorRepository extends JpaRepository<Lector,Long> {
    String sqlCountOfLectors="select count (l.name) " +
            "from lector l " +
            "join department d " +
            "on d.department_id=l.department_id " +
            "where d.name= :department_name ";

    String sqlStats="select l.degree,count(*) " +
            "from lector l " +
            "join department d " +
            "on d.department_id=l.department_id "+
            "where d.name= :department_name " +
            "group by l.degree";

    String sqlAvgSalary="select avg(l.salary) " +
            "from lector l " +
            "join department d "+
            "on l.department_id=d.department_id " +
            "where d.name= :department_name ";
    String sqlGlobalSearch="select name,last_name " +
            "from lector  " +
            "where name like %:word% or last_name like %:word% " ;

    @Query(value = sqlStats,nativeQuery = true)
    List<Object[]> showStats(@Param("department_name") String department_name);

    @Query(value = sqlCountOfLectors,nativeQuery = true)
    Optional<Long> countLectorBy(@Param("department_name") String department_name);

    @Query(value = sqlAvgSalary,nativeQuery = true )
    Optional<Long> avgSalary(@Param("department_name") String department_name);

    @Query(value = sqlGlobalSearch, nativeQuery = true)
    List<Object[]> globalSearch(@Param("word") String word );



}
