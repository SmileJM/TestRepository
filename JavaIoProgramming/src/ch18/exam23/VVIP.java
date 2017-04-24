package ch18.exam23;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class VVIP extends Person implements Serializable{
    private int memberShipNo;
    private String grade;

    public VVIP() {
    }
    
    public VVIP(int memberShipNo, String grade, String name, int age) {
       super(name, age);
       this.memberShipNo = memberShipNo;
       this.grade = grade;
    }

    public int getMemberShipNo() {
        return memberShipNo;
    }

    public void setMemberShipNo(int memberShipNo) {
        this.memberShipNo = memberShipNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    // ObjectWriteStream 의 write 메소드가 호출되면 자동으로 호출됨        
    private void writeObject(ObjectOutputStream out) throws IOException {
        // 부모의 값을 별도로 저장하고 
        out.writeUTF(getName());
        out.write(getAge());        
        // 자기 자신의 필드를 직렬화함
        out.defaultWriteObject();
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        setName(in.readUTF());
        setAge(in.read());
        // 자기 자신의 필드를 역직렬화함
        in.defaultReadObject();
    }    
}
