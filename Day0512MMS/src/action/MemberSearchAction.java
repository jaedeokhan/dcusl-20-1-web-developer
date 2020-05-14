package action;

import java.util.ArrayList;
import java.util.Scanner;

import svc.MemberSearchService;
import util.ConsoleUtil;
import vo.MemberVO;
import vo.SerachData;

public class MemberSearchAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		// select 할려면 select하고 싶은 id를 체크하기.
		ConsoleUtil consoleUtil  = new ConsoleUtil();
		
		SerachData searchData = consoleUtil.getSearchData(scan);
		
		MemberSearchService memberSearchService =  new MemberSearchService();
		
		// searchData 클래스를 받아서 존재하는지 보고 반환해주기.
		// id, name 인지 확인하고, 출력을 다르게 해줘야한다. id는 하나만, name은 여러 명이면 여러 명 출력하기!
		
		if (searchData.getSearchCondition().equals("아이디")) {
			MemberVO memberVO = memberSearchService.searchMemberVOById(searchData.getSearchValue());
			consoleUtil.printSearchMemberVO(memberVO);
		}
		
		else {
			// 회원이 1명이 아닐수도 있으니 collection 타입으로 받아야 한다.
			ArrayList<MemberVO> memberList = memberSearchService.searchMemberVOByName(searchData.getSearchValue());
			consoleUtil.printSearchMemberList(memberList);
			
		}
		
		
		
	}

}
