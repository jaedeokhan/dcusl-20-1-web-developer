package controller;

import java.util.Scanner;

import action.Action;

public class MemberController {

	public static void processRequest(Action action, Scanner scan){
		// TODO Auto-generated method stub
		try {
			// 넘어오는 예외를 여기서 모두 처리한다!!
			action.execute(scan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
