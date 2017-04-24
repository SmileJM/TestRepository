package ch18.homework2;

import java.io.Serializable;

public class ClassA implements Serializable{
    int field1;
    ClassB field2 = new ClassB();
    static int field3;
    transient int field4;
}
