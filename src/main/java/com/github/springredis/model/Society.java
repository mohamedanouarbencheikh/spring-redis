package com.github.springredis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.HashMap;
/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */
@RedisHash("Society")
public class Society {

    @Id @Indexed
    private String id;
    @Indexed
    private String name;
    private HashMap<String, Employee> employees;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(HashMap<String, Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Society{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
