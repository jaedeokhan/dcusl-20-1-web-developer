package action;

import java.util.Scanner;

// 각 요청을 처리하는 Action 클래스들의 규격을 정의하는 인터페이스

public interface Action{
	// 직접 요청을 처리하는 메소드!
	public void execute(Scanner scan) throws Exception ;
}
