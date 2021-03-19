package com.example.demo.lectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectorsService {
    private final LectorRepository lectorRepository;
    @Autowired
    public LectorsService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public String avgSalary(String name){
        Optional<Long> avg = lectorRepository.avgSalary(name);
        if (avg.isPresent()) {
            StringBuilder mess = new StringBuilder();
            mess.append(" The average salary of ").append(name).append(" is ").append(avg.get());
            return mess.toString();
        }
        return "Sorry, average is 0 or check the correctness of the department name - " + name;

    }
    public String countOfEmpl(String name){
        return String.valueOf(lectorRepository.countLectorBy(name).orElse(0L));
    }

    public String getStatics(String name) {
        List<Object[]> objectListOfStats = lectorRepository.showStats(name);
        if (objectListOfStats.size()!=0) {
            StringBuilder stringBuilder = new StringBuilder();
            objectListOfStats.forEach(n -> stringBuilder.append(n[0]).append(" - ").append(n[1]).append("\n")
            );
            return stringBuilder.toString();
        }
        return "Does not have any statistics for " + name;
    }
    public String globalSearch(String word){
        List<Object[]> objects = lectorRepository.globalSearch('%' + word + '%');
        if(objects.size()!=0){
            StringBuilder stringBuilder=new StringBuilder();
            objects.forEach(n-> {
                for (Object obj:n) {
                    stringBuilder.append(obj).append(" ");
                }
                stringBuilder.append("\n");

            });
            return stringBuilder.toString();
        }return "No coincidence";
    }
}
