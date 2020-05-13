// 회원등록 요청을 처리하는 비지니스 로직이 구현되는 클래스!! => 사용자의 입력이 아니라!
package svc;

import ui.MemberUI;
import vo.MemberVO;

public class MemberRegistService {

	public boolean registMember(MemberVO newMemberVO) {
		
		// false를 반환을 하는 경우는 회원 가입을 할려고 하는데 식별자 변수인 id가 이미 존재한다면 false를 반환시킨다.
		boolean registSuccess = true;
		
		// MemberUI에 있는 memberList의 요소들을 체크해야한다.
		// memberList를 static으로 줘서 바로 접근이 가능하다.
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(newMemberVO.getId().equals(MemberUI.memberList.get(i).getId())) {
				registSuccess = false;
				break;
			}
		}
		
		if (registSuccess) {
			// registSuccess
			MemberUI.memberList.add(newMemberVO);	
		}
		return registSuccess;
	}
	
	
}
