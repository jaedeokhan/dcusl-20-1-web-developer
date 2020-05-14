package action;

import java.util.Scanner;

import svc.MemberRemoveService;
import util.ConsoleUtil;

public class MemberRemoveAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		
		ConsoleUtil consoleUtil = new ConsoleUtil();
		// 삭제 대상의 id를 얻어오기.
		String removeMemberId = consoleUtil.getId("삭제할 회원의 아이디 : ", scan);
		
		// 삭제하는 작업은 Service 클래스에서 정의하기.
		MemberRemoveService memberRemoveService = new MemberRemoveService();
		
		// 삭제를 완료했다면, 응답을 해줘야한다!
		boolean removeSuccess = memberRemoveService.removeMember(removeMemberId);
		
		if (removeSuccess) {
			consoleUtil.printRemoveSuccesMessage(removeMemberId);
		}
		else {
			consoleUtil.printRemoveFailMessage(removeMemberId);			
		}
	}

}
