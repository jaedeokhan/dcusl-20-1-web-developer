package svc;

import java.util.ArrayList;

import ui.MemberUI;
import vo.MemberVO;

public class MemberListService {

	public ArrayList<MemberVO> getMemberList() {
		
		return MemberUI.memberList;
		
	}
	
	
}
