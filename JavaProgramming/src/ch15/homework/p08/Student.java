package ch15.homework.p08;

public class Student {
	public int studentNum;
	public String name;
	public Student(int studentNum, String name) {
		this.studentNum = studentNum;
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return studentNum;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Student) {
			Student s = (Student) obj;
			if(s.studentNum == studentNum) {
				return true;
			}
		}
		return false;
	}
}
