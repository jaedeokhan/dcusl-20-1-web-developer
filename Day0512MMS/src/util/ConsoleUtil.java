// 콘솔상에서 사용자로부터 데이터를 입력받거나 요청처리 결과를 사용자에게 출력해주는 역할을 하는 클래스
package util;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;

import vo.MemberVO;

public class ConsoleUtil {

	public MemberVO getNewMemberVO(Scanner scan) {
		
		System.out.println("====회원등록====");
		System.out.print("회원 아이디 : ");
		String id = scan.next();
		
		System.out.print("회원 비밀번호 : ");
		String passwd = scan.next();

		System.out.print("회원 이름 : ");
		String name = scan.next();
		
		System.out.print("회원 이메일 : ");
		String email = scan.next();
		
		System.out.print("회원 주소 : ");
		String addr = scan.next();

		System.out.print("회원 성별 : ");
		String gender = scan.next();

		System.out.print("회원 전화번호 : ");
		String tell = scan.next();
		
		System.out.print("회원 나이 : ");
		int age = scan.nextInt();
		
		// 위의 속성들을 하나의 객체로 묶기.
		MemberVO newMemberVO = new MemberVO();
		newMemberVO.setAddr(addr);
		newMemberVO.setAge(age);
		newMemberVO.setEmail(email);
		newMemberVO.setGender(gender);
		newMemberVO.setId(id);
		newMemberVO.setName(name);
		newMemberVO.setPasswd(passwd);
		newMemberVO.setTell(tell);
		
		// 모든 속성을 set 해주고 return 으로 넘겨준다.
		return newMemberVO;
	}

	public void printRegistSuccessMessage(String id) {
		// TODO Auto-generated method stub
		System.out.println(id + " 님 회원 등록이 성공되었습니다.");
		
	}
	public void printRegistFailMessage(String id) {
		// TODO Auto-generated method stub
		System.out.println(id + " 님 회원 등록이 실패되었습니다.");
		
	}

	public void printMemberList(ArrayList<MemberVO> memberList) {
		
		// 회원등록한 회원이 없으면, 
		if (memberList.size() == 0) {
			System.out.println("등록된 회원이 없습니다.");
		}
		else {
			// 사이즈가 0이 아니면 회원이 한 명이라도 등록이 되어 있는 것이다.
			for (int i = 0; i < memberList.size(); i++) {
				// toStirng 객체가 호출이 되면서 실행이 된다.
				System.out.println(memberList.get(i));
			}
		}
	}

	public String getId(String label, Scanner scan) {
		// 검색, 수정, 삭제 3개의 요청 처리를 위해 하나로 작성
		System.out.print(label);
		return scan.next();
	}

	public void printMemberVOInfo(MemberVO memberVO) {
		
		System.out.println(memberVO.getId() + " 아이디 회원의 정보");
		System.out.println(memberVO);
		
	}

	public MemberVO getNewMemberVO(MemberVO oldMemberVO, Scanner scan) {
		// TODO Auto-generated method stub
		MemberVO newMemberVO = null;
		// 수정할려는 작업이 없으면 null이 나오고 실패를 하게 만든다.
		
		// null이 아니다. 즉 회원이 정상적으로 넘어왔다면
		if (oldMemberVO != null) {
			newMemberVO = new MemberVO();
			System.out.println("====정보 수정====");
			System.out.println("이전 회원 이름 : " + oldMemberVO.getName());
			System.out.print("회원 이름 : ");
			String name = scan.next();
			
			System.out.println("이전 비밀번호 : " + oldMemberVO.getPasswd());
			System.out.print("회원 비밀번호 : ");
			String passwd = scan.next();
			
			System.out.println("이전 이메일 : " + oldMemberVO.getEmail());
			System.out.print("회원 이메일 : ");
			String email = scan.next();
			
			System.out.println("이전 주소  : " + oldMemberVO.getAddr());
			System.out.print("회원 주소 : ");
			String addr = scan.next();

			System.out.println("이전 성별  : " + oldMemberVO.getGender());
			System.out.print("회원 성별 : ");
			String gender = scan.next();

			System.out.println("이전 전화번호 : " + oldMemberVO.getTell());
			System.out.print("회원 전화번호 : ");
			String tell = scan.next();
			
			System.out.println("이전 나이 : " + oldMemberVO.getAge());
			System.out.print("회원 나이 : ");
			int age = scan.nextInt();
			
			// 위의 속성들을 하나의 객체로 묶기.
			newMemberVO.setId(oldMemberVO.getId());
			newMemberVO.setAddr(addr);
			newMemberVO.setAge(age);
			newMemberVO.setEmail(email);
			newMemberVO.setGender(gender);
			newMemberVO.setName(name);
			newMemberVO.setPasswd(passwd);
			newMemberVO.setTell(tell);
		}
		
		return newMemberVO;
	}

	public void printModifySuccessMessage(String id) {
		
		System.out.println(id + " 아이디 회원의 정보가 수정이 완료되었습니다.");
		
	}

	public void printModifyFailMessage(String id) {
		System.out.println(id + " 아이디 회원의 정보가 수정이 실패되었습니다.");		

	}


	
}
