package ch09.homework.p09;

public class Button {
	OnClickListener listener;  
		
	public void setOnClickListener(OnClickListener listener) {
		this.listener = listener;
	}

	public void touch() {	
		listener.onClick();	
	}

	interface OnClickListener {
		void onClick();
	}
}