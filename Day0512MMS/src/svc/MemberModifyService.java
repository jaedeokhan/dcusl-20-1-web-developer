package svc;

import ui.MemberUI;
import vo.MemberVO;

public class MemberModifyService {

	public MemberVO getOldMemberVO(String memberModifyId) {
		
		MemberVO oldMemberVO = null;
		
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(memberModifyId.equals(MemberUI.memberList.get(i).getId())) {
				oldMemberVO = MemberUI.memberList.get(i);
				break;
			}
		}
		
		return oldMemberVO;
		// 화면에 보여주는 작업은 consoleUtil에서 처리
	}

	public boolean modifyMemberVO(MemberVO newMemberVO) {
		// 수정하기.
		boolean modifySuccess = false;
		// newMemberVO가 제대로 넘어온다면!
		if (newMemberVO != null) {
			// id 값이 똑같은거를 찾아서 
			for (int i = 0; i < MemberUI.memberList.size(); i++) { 
				// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
				// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
				if(newMemberVO.getId().equals(MemberUI.memberList.get(i).getId())) {
					// 해당 객체에 참조하는 객체에 해당하는 인덱스에 newMemberVO로 바꾸겠다.
					MemberUI.memberList.set(i, newMemberVO);
					break;
				}
			}
			modifySuccess = true;
		}
		
		return modifySuccess;
	}
}
