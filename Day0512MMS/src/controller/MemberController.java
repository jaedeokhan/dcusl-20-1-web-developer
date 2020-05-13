package controller;

import java.util.Scanner;

import action.Action;

public class MemberController {
	// 사용자가 입력하는 모든 에러를 여기서 받는다.
	public static void processRequest(Action action, Scanner scan){
		// TODO Auto-generated method stub
		// 실질적으로 구현한 execute 메소드가 넘어온다.
		try {
			// 넘어오는 예외를 여기서 모두 처리한다!!
			action.execute(scan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
