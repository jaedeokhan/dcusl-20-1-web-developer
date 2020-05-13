package action;

import java.util.Scanner;

import svc.MemberModifyService;
import util.ConsoleUtil;
import vo.MemberVO;

public class MemberModifyAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		
		// 사용자의 입력 처리 => 전처리라고 생각
		ConsoleUtil consoleutil = new ConsoleUtil();
		
		String memberModifyId = consoleutil.getId("수정하고 싶은 회원 아이디 입력 : ", scan);
		
		// 비지니스 로직 처리 
		MemberModifyService memberModifyService = new MemberModifyService();
		
		// 식별자를 넣어줘야한다.
		// oldMember 가져오고
		MemberVO oldMemberVO = memberModifyService.getOldMemberVO(memberModifyId);
		
		// 메소드 오버로딩으로 구현하기.
		MemberVO newMemberVO = consoleutil.getNewMemberVO(oldMemberVO ,scan);
		System.out.println(newMemberVO);
		
		// 수정을 했으면 응답을 줘야한다.
		boolean modifySuccess = memberModifyService.modifyMemberVO(newMemberVO);
		
		if (modifySuccess) {
			// . 을 찍으면 hip에 포인팅하는 것이다. String 변수로 잡아준다.
			consoleutil.printModifySuccessMessage(memberModifyId);
		}
		else {
			consoleutil.printModifyFailMessage(memberModifyId);
		}
		
		
		
	}

}
