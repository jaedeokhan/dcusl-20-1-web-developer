package svc;

import java.util.ArrayList;

import ui.MemberUI;
import vo.MemberVO;

public class MemberSearchService {

	public MemberVO searchMemberVOById(String memberSearchId) {

		MemberVO memberVO = null;
		
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(memberSearchId.equals(MemberUI.memberList.get(i).getId())) {
				memberVO = MemberUI.memberList.get(i);
				break;
			}
		}
		
		return memberVO;
	}

	public ArrayList<MemberVO> searchMemberVOByName(String memberSearchName) {
		
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(memberSearchName.equals(MemberUI.memberList.get(i).getName())) {
				memberList.add(MemberUI.memberList.get(i));
				break;
			}
		}
		
		return memberList;
	}


	
		

	}
	
	
