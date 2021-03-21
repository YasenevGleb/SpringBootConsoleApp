package com.example.demo;

import com.example.demo.departments.Department;
import com.example.demo.departments.DepartmentRepository;
import com.example.demo.lectors.Degree;
import com.example.demo.lectors.Lector;
import com.example.demo.lectors.LectorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GeneralConfigToDatabase {

    @Bean
    public CommandLineRunner commandLineRunnerForDatabase(LectorRepository lectorRepository,
                                                          DepartmentRepository departmentRepository){
        Department secondDepartment=new Department("department2","Gleb");
        Department firstDepartment=new Department("department1","Stan");
        Lector lectorEx1=new Lector("Gleb","Yasenev", Degree.PROFESSOR,firstDepartment,100L);
        Lector lectorEx2=new Lector("Stan","Ivanov", Degree.PROFESSOR,firstDepartment,1000L);
        Lector lectorEx3=new Lector("Alloha","Petrov", Degree.PROFESSOR,firstDepartment,10000L);
        Lector lectorEx4=new Lector("Nem","Gluh", Degree.ASSOCIATE_PROFESSOR,firstDepartment,100000L);
        Lector lectorEx5=new Lector("LeBlanc","Smith", Degree.ASSISTANT,firstDepartment,100000L);
        Lector lectorEx6=new Lector("Ronin","Smith", Degree.ASSISTANT,secondDepartment,12312L);

        return  args -> {
            departmentRepository.saveAll(List.of(secondDepartment, firstDepartment));
            lectorRepository.saveAll(List.of(lectorEx1, lectorEx2, lectorEx3, lectorEx4, lectorEx5, lectorEx6));
        };

    }

}
