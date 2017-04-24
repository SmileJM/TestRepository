package ch05.exam03;

public class ReadKeyCodeExample {

	public static void main(String[] args) throws Exception{
		System.out.println("----------------------------------");
		System.out.println("1. ���� | 2. �б� | 3. ����");
		System.out.println("----------------------------------");
		int keyCode = 0;
		while(true) {
			if(keyCode!=10 && keyCode!=13) {				
				System.out.print("��ȣ ����: ");
			}
			keyCode = System.in.read();
//			System.out.println(keyCode);
			
			switch(keyCode) {
			case 49:
				System.out.println("������ �����ϼ̽��ϴ�.");
				break;
			case 50:
				System.out.println("�б⸦ �����ϼ̽��ϴ�.");
				break;
			case 51:
				System.out.println("���Ḧ �����ϼ̽��ϴ�.");
//				System.exit(0);
//				break;
				return ;				
			}

		}
		
	}
	
}