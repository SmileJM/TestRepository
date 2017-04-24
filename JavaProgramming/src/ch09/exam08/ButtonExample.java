package ch09.exam08;

public class ButtonExample {

	public static void main(String[] args) {
		Button button = new Button();
		button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick() {
				System.out.println("서버에 접속합니다.");
			}			
		});
		button.touch();
	}

}
