package svc;

import ui.MemberUI;

public class MemberRemoveService {

	public boolean removeMember(String removeMemberId) {
		// TODO Auto-generated method stub
		boolean removeSccuess = false;
		
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(removeMemberId.equals(MemberUI.memberList.get(i).getId())) {
				MemberUI.memberList.remove(i);
				removeSccuess = true;
				break;
			}
		}
		
		return removeSccuess;
	}
	
}
