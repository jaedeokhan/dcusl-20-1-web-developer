// 회원등록 요청을 처리하는 클래스
package action;

import java.util.Scanner;

import svc.MemberRegistService;
import util.ConsoleUtil;
import vo.MemberVO;

public class MemberRegistAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		// 예외를 throws로 상위로 하나 던져주고! => MemberController
		// 별도의 클래스들을 만들어서 작업을 하겠다. => 콘솔 작업하는 부분!!
		ConsoleUtil consoleutil = new ConsoleUtil();
		
		// 사용자의 데이터 입력 처리 => 비지니스 로직을 처리하기 전에 전처리 작업을 한 것.
		// getNewMemberVO에서 새로운 사용자의 정보를 입력 받아서 newMemberVO에 return을 해준다.
		MemberVO newMemberVO = consoleutil.getNewMemberVO(scan);
		
		// 비지니스로직 호출 => 서비스 클래스에 정의가 되야한다.
		MemberRegistService memberRegistService = new MemberRegistService(); 
		
		// 사용자가 등록 요청을 하고 나면 항상 응답을 해줘야 한다.
		// 예) 회원 가입을 요청을 하면, 결과를 리턴 해줘야 한다.
		// MemberVO 객체를 담을 수 있는 공간이 필요하다. 회원 가입과 회원 탈퇴를 회원별로 받아줘야 하기 때문이다.
		// 즉 데이터베이스에 그러한 공간을 만들어 줘야한다.
		// 지금은 메모리상에 나두고 관리를 한다.
		// collection으로  만들고 관리한다.
		boolean registSuccess = memberRegistService.registMember(newMemberVO);
		
		if (registSuccess) {
			// 등록 성공
			consoleutil.printRegistSuccessMessage(newMemberVO.getId());
		}
		else {
			// 등록 실패
			consoleutil.printRegistFailMessage(newMemberVO.getId());			
		}
		
	}
	

}
