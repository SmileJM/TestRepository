package com.mycompany.myapp.dao;

import org.springframework.stereotype.Component;

@Component
public class Exam10DaoImple implements Exam10Dao{
    public void insert() {
        System.out.println("insert() 실행");
    }
    public void select() {
        System.out.println("select() 실행");
    }
}
