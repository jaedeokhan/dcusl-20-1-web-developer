package action;

import java.util.ArrayList;
import java.util.Scanner;

import svc.MemberListService;
import util.ConsoleUtil;
import vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		// 현재까지 저장하고 있는 memeberList를 가져와야한다.
		MemberListService memberListService = new MemberListService();
		
		// getMemerList 메소드에게 memberList가 ArrayList를 받고
		ArrayList<MemberVO> memberList = memberListService.getMemberList();
		
		ConsoleUtil consoleUtil = new ConsoleUtil();
		consoleUtil.printMemberList(memberList);
		
		
	}

}
