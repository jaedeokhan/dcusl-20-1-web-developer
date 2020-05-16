package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import ui.MemberUI;
import vo.MemberVO;

public class MemberViewService {

	public MemberVO getMemberVO(String memberViewId) {
		
		/*
		 * MemberVO memberVO = null;
		 * 
		 * for (int i = 0; i < MemberUI.memberList.size(); i++) { // 1,2 ~ 인덱스에서 같은 아이디를
		 * 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go // 새로 등록하는 id와 기존에 있는 id를
		 * 비교해서 만약, 동일한게 존재한다 if문으로 처리.
		 * if(memberViewId.equals(MemberUI.memberList.get(i).getId())) { memberVO =
		 * MemberUI.memberList.get(i); break; } }
		 * 
		 * return memberVO;
		 */
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		MemberVO memberVO = memberDAO.selectMemberVO(memberViewId);
		close(con);
		
		return memberVO;
		
	}
	
	
}
