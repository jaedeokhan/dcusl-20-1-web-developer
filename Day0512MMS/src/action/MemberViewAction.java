package action;

import java.util.Scanner;

import svc.MemberViewService;
import util.ConsoleUtil;
import vo.MemberVO;

public class MemberViewAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {

		ConsoleUtil consoleUtil = new ConsoleUtil();
		// 검색, 수정, 삭제도 아이디 요청이 필요하다.
		// 비지니스 로직 처리 전 전처리 작업!
		String memberViewId = consoleUtil.getId("정보를 볼 회원 아이디 :", scan);
		
		// 비지니스 로직 처리하기.
		MemberViewService memberViewService = new MemberViewService();
		MemberVO memberVO = memberViewService.getMemberVO(memberViewId); 
		
		consoleUtil.printMemberVOInfo(memberVO);
				
				
	}

}
