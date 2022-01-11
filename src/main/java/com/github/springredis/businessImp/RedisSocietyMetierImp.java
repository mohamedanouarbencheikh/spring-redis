package com.github.springredis.businessImp;


import com.github.springredis.dao.SocietyRepository;
import com.github.springredis.business.RedisSocietyMetier;
import com.github.springredis.model.Society;
import com.github.springredis.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */
@Service
public class RedisSocietyMetierImp implements RedisSocietyMetier {

    @Autowired
    SocietyRepository societyRepository;
    
    Society society;
    HashMap<String, Employee> travees;
    Employee travee;

    @PostConstruct
    public void start(){
        society = new Society();
        society.setId("s1");
        society.setName("society1");
        travees = new HashMap<>();
        travee = new Employee();
        travee.setName("employee1");
        travee.setFunction("engineer");
        travees.put("employee1", travee);
        travee = new Employee();
        travee.setName("employee2");
        travee.setFunction("director");
        travees.put("employee2", travee);
        society.setEmployees(travees);
        societyRepository.save(society);

        society = new Society();
        society.setId("s2");
        society.setName("society2");
        travees = new HashMap<>();
        travee = new Employee();
        travee.setName("employee3");
        travee.setFunction("security agent");
        travees.put("employee3", travee);
        travee = new Employee();
        travee.setName("employee4");
        travee.setFunction("accountant");
        travees.put("employee4", travee);
        society.setEmployees(travees);
        societyRepository.save(society);

        System.out.println("------------------ society id and name " + societyRepository.findByIdAndName("s1","society1"));
        System.out.println("------------------ society id " + societyRepository.findById("s1"));


    }
}
