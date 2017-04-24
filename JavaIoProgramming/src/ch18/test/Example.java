package ch18.test;

public class Example {
    public static void main(String[] args) {
        A a = new A();
        a.method1();
        a.method2();
        System.out.println();
        
        A ab = new B();
        ab.method1();
        ab.method2();        
        System.out.println();
        
        B b = new B();
        b.method1();
        b.method2();
        b.method3();
    }
    
}
