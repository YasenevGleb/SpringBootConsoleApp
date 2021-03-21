package com.example.demo.dao;

import com.example.demo.departments.Department;
import com.example.demo.departments.DepartmentRepository;
import com.example.demo.lectors.Degree;
import com.example.demo.lectors.Lector;
import com.example.demo.lectors.LectorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
public class DaoTest {
    private LectorRepository lectorRepository;
    private DepartmentRepository departmentRepository;
    @Autowired
    public DaoTest(LectorRepository lectorRepository, DepartmentRepository departmentRepository) {
        this.lectorRepository = lectorRepository;
        this.departmentRepository = departmentRepository;
    }

    @Test
    void countLectorBy_ShouldReturnLectorsGroupedByDepartments() {
        Department department=new Department("department1","gleb");
        Lector lector=new Lector("gleb","yasenev", Degree.ASSISTANT,department,100L);
        departmentRepository.save(department);
        lectorRepository.save(lector);
        Optional<Long> countLectorBy = lectorRepository.countLectorBy("department1");
        assertThat(countLectorBy.get()).isEqualTo(1L);
    }
    @Test
    void countLectorBy_ShouldReturnZero_WhenLectorsDontExist(){

        Optional<Long> countLectorBy = lectorRepository.countLectorBy("department1");
        assertThat(countLectorBy.get()).isEqualTo(0);
    }
    @Test
    void avgSalary_ShouldReturnAverageForAllLectors(){
        Department department=new Department("department1","gleb");
        Lector lector=new Lector("Lector","Smith", Degree.ASSISTANT,department,100L);
        Lector lector1=new Lector("Lector","Smith", Degree.ASSISTANT,department,200L);
        Lector lector2=new Lector("Lector","Smith", Degree.ASSISTANT,department,300L);
        departmentRepository.save(department);
        lectorRepository.saveAll(List.of(lector1,lector2,lector));
        Optional<Long> avgSalary = lectorRepository.avgSalary("department1");
        assertThat(avgSalary.get()).isEqualTo(200);
    }
    @Test
    void avgSalary_ShouldNotReturnAverageForZeroLectors(){
        Optional<Long> avgSalary = lectorRepository.avgSalary("department1");
        assertThat(avgSalary.isEmpty()).isTrue();
    }

    @Test
    void getHeadOfDepartment_ShouldSelectHeadOfDepartment(){
        Department department=new Department("department1","gleb");
        departmentRepository.save(department);
        Optional<String> headOfDepartment = departmentRepository.headOfDepartment("department1");
        assertThat(headOfDepartment.get()).isEqualTo(department.getHeadOfDepartment());
    }

    @Test
    void showStats_ShouldSelectStatistic(){
        List<Object[]> list=Arrays.asList(new Object[]{"ASSISTANT", new BigInteger("1")}, new Object[]{"ASSOCIATE_PROFESSOR", new BigInteger("1")}, new Object[]{"PROFESSOR", new BigInteger("2")});
        Department department=new Department("department1","gleb");
        Lector lector1=new Lector("Lector","Smith1", Degree.ASSISTANT,department,100L);
        Lector lector2=new Lector("Lector1","Smith2", Degree.PROFESSOR,department,100L);
        Lector lector3=new Lector("Lector2","Smith3", Degree.PROFESSOR,department,100L);
        Lector lector4=new Lector("Lector3","Smith4", Degree.ASSOCIATE_PROFESSOR,department,100L);
        departmentRepository.save(department);
        lectorRepository.saveAll(List.of(lector1,lector2,lector3,lector4));
        List<Object[]> stats = lectorRepository.showStats("department1");
        assertThat(Arrays.equals(list.get(0), stats.get(0)));
        assertThat(Arrays.equals(list.get(1), stats.get(1)));
        assertThat(Arrays.equals(list.get(2), stats.get(2)));
    }
    @Test
    void showStats_ShouldSelectNull_WhenStatisticsIsEmpty(){
        List<Object[]> list = lectorRepository.showStats("department1");
        assertThat(list.isEmpty()).isTrue();
    }
    @Test
    void globalSearch_ShouldGlobalSearchWorksByNameAndLastnames(){
        List<Object[]> list=Arrays.asList(new Object[]{"Gleb","Yasenev"},
                new Object[]{"Stan","Smith"},
                new Object[]{"Mith","Stan"},
                new Object[]{"Leila","Leila"});
        Department department=new Department("department1","gleb");
        Lector lector1=new Lector("Gleb","Yasenev", Degree.ASSISTANT,department,100L);
        Lector lector2=new Lector("Stan","Smith", Degree.PROFESSOR,department,100L);
        Lector lector3=new Lector("Mith","Stan", Degree.PROFESSOR,department,100L);
        Lector lector4=new Lector("Leila","Leila", Degree.ASSOCIATE_PROFESSOR,department,100L);
        departmentRepository.save(department);
        lectorRepository.saveAll(List.of(lector1,lector2,lector3,lector4));
        List<Object[]> globalSearch = lectorRepository.globalSearch("a");
        assertThat(Arrays.equals(list.get(0), globalSearch.get(0)));
        assertThat(Arrays.equals(list.get(1), globalSearch.get(1)));
        assertThat(Arrays.equals(list.get(2), globalSearch.get(2)));
        assertThat(Arrays.equals(list.get(3), globalSearch.get(3)));

    }
    @Test
    void globalSearch_ShouldGlobalSearchByOnlyLastnames(){
        List<Object[]> list=Arrays.asList(new Object[]{"Gleb","Yasenevw"},
                new Object[]{"Stan","Smitwh"},
                new Object[]{"Mith","Stanw"}
                );
        Department department=new Department("department1","gleb");
        Lector lector1=new Lector("Gleb","Yasenevw", Degree.ASSISTANT,department,100L);
        Lector lector2=new Lector("Stan","Smitwh", Degree.PROFESSOR,department,100L);
        Lector lector3=new Lector("Mith","Stanw", Degree.PROFESSOR,department,100L);
        Lector lector4=new Lector("Leila","Leila", Degree.ASSOCIATE_PROFESSOR,department,100L);
        departmentRepository.save(department);
        lectorRepository.saveAll(List.of(lector1,lector2,lector3,lector4));
        List<Object[]> globalSearch = lectorRepository.globalSearch("w");
        assertThat(Arrays.equals(list.get(0), globalSearch.get(0)));
        assertThat(Arrays.equals(list.get(1), globalSearch.get(1)));
        assertThat(Arrays.equals(list.get(2), globalSearch.get(2)));
    }
    @Test
    void globalSearch_ShouldGlobalSearchByOnlyNames() {
        List<Object[]> list=Arrays.asList(
                new Object[]{"Stanx","Smitwh"},
                new Object[]{"Mithx","Stan"},
                new Object[]{"Leilax","Leila"}
                );
        Department department=new Department("department1","gleb");
        Lector lector1=new Lector("Gleb","Yasenev", Degree.ASSISTANT,department,100L);
        Lector lector2=new Lector("Stanx","Smitwh", Degree.PROFESSOR,department,100L);
        Lector lector3=new Lector("Mithx","Stanw", Degree.PROFESSOR,department,100L);
        Lector lector4=new Lector("Leilax","Leila", Degree.ASSOCIATE_PROFESSOR,department,100L);
        departmentRepository.save(department);
        lectorRepository.saveAll(List.of(lector1,lector2,lector3,lector4));
        List<Object[]> globalSearch = lectorRepository.globalSearch("x");
        assertThat(Arrays.equals(list.get(0), globalSearch.get(0)));
        assertThat(Arrays.equals(list.get(1), globalSearch.get(1)));
        assertThat(Arrays.equals(list.get(2), globalSearch.get(2)));
    }

}
