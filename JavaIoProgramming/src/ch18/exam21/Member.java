package ch18.exam21;

import java.io.Serializable;

public class Member implements Serializable{
    private String name;
    private int age;
    public static String nation = "한국";

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setMname(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
