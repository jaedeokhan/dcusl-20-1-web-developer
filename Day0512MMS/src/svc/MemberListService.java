package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import ui.MemberUI;
import vo.MemberVO;
import static db.JdbcUtil.*;

public class MemberListService {

	/*
	 * public ArrayList<MemberVO> getMemberList() {
	 * 
	 * return MemberUI.memberList;
	 * 
	 * }
	 */
	
	public ArrayList<MemberVO> getMemberList() {
	
	Connection con = getConnection();
	MemberDAO memberDAO = MemberDAO.getInstance();
	memberDAO.setConnection(con);
	
	ArrayList<MemberVO> memberList = memberDAO.selectMemberList();
	
	
	return memberList;
	}
	
}
