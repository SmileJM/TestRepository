package ch09.problem.p06;

public class Chatting {
	void startChat(String chatId) {
		String nickName = null;
//		nickName = chatId;
		// 로컬 클래스에서 사용시 final 특성이 생기기 때문에 오류 발생
		
		Chat chat = new Chat() {
			@Override
			void start() {
				while(true) {
					String inputData = "안녕하세요";
					String message = "[" + nickName + "] " + inputData;
					sendMessage(message);
				}
			}
		};
	}
	class Chat{
		void start() {}
		void sendMessage(String message) {}
	}
}
