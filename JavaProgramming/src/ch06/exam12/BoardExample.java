package ch06.exam12;

public class BoardExample {

	public static void main(String[] args) {
		Board board = new Board();
		
		board.setBno(1);
		board.setTitle("봄이다.");
		board.setContents("놀자");
		board.setWriter("나");
		board.setOpen(true);

		System.out.println(board.getBno());
		System.out.println(board.getTitle());
		System.out.println(board.getContents());
		System.out.println(board.getWriter());
		System.out.println(board.isOpen());
	}

}
