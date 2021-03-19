package com.example.demo.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String headOfDepartmentService(String name) {
        Optional<String> headOfDepartment = departmentRepository.headOfDepartment(name);
        if (headOfDepartment.isPresent()){
            StringBuilder mess=new StringBuilder();
            mess.append("Head of ")
                    .append(name)
                    .append(" department is ")
                    .append(headOfDepartment.get());
            return mess.toString();
        }
        return "No head for this department - " + name;
    }

}
