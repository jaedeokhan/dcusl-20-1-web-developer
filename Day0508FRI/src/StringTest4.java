// 다양한 String 클래스의 메소드들
public class StringTest4 {

	public static void main(String[] args) {
		
		String source1 = "aaabbbcccdddfffaaabbbcccddd";
		String source2 = "AAAbbbcccdddfffaaabbbcccddd";
		
		// source2의 대문자를 체크하는 String API
		System.out.println("원본 문자열 : " + source1);
		System.out.println();
		
		// char charAt(int index) => 문자열은 char 배열로 구성되어있다. 그래서 다음과 같은 메소드들 사용 가능
		// 원본 문자열에서 인자로 지정된 인덱스 위치의 문자 하나를 반환하는 메소드이다.
		System.out.println("첫 번째 문자 : source1.charAt(0) : " + source1.charAt(0));
		System.out.println("첫 번째 문자 : source2.charAt(0) : " + source2.charAt(0));
		System.out.println();
		
		// int indexOf(String searchString)
		// 원본 문자열에서 인자로 지정된 문자열이 있는 인덱스 번호를 반환
		System.out.println("첫 번째 인덱스 : source1.indexOf(\"a\") : " + source1.indexOf("a"));
		System.out.println("첫 번째 인덱스 : source2.indexOf(\"A\") : " + source2.indexOf("A"));
		System.out.println();
		
		// String substring(int startIndex)
		// 원본문자열에서 인자로 지정된 인덱스 위치의 문자부터 마지막 문자까지 반환
		System.out.println("3번 인덱스 문자부터 마지막 문자까지 출력 : source1.substring(3)" 
						+ source1.substring(3));
		System.out.println();
		
		// String substring(int startIndex, int endIndex)
		// 원본문자열에서 인자로 지정된 startIndex, 위치 부터 (endIndex => -1) 인덱스 위치 문자까지 반환
		System.out.println("3번 인덱스 문자부터 마지막 문자까지 출력 : source1.substring(3, n)" 
				+ source1.substring(3, 6));
		System.out.println();
		
		// String replace(String oldString, String newString)
		// 원본 문자열에서 OldString을 검색해서 newString으로 치환!
		System.out.println("원본문자열에서 aaa 문자열을 ggg 문자열로 변경 : source1.replace(\"aaa\", \"ggg\") : " 
				+ source1.replace("aaa", "ggg"));
		System.out.println();
		
		// boolean endsWith(String endString)
		// 원본 문자열이 인자로 지정된 문자열로 끝나는지 판단! =>  true, false
		// .hwp 와 같은 확장자들을 판단할 때 주로 사용한다.
		System.out.println("원본문자열이 ddd로 끝나는지 확인 : source1.endsWith(\"ddd\") : " 
				+ source1.endsWith("ddd"));
		System.out.println();
		
		// boolean startsWith(String startString)
		// 원본 문자열이 인자로 지정된 문자열로 시작되는지 판단! => true, false
		// 쇼핑몰에서 오늘 본 상품 이미지를 탭 메뉴에 보여주는 기능! = > 이때 쿠키라는 기능을 이용해서 본 이미지를 심어준다.
		// 쿠키의 이름이 today인 것을 가져와서 사용자의 페이지에 뿌려주는 것이다.
		System.out.println("원본문자열이 aaa로 시작되는지 판단 : source1.startsWith(\"aaa\") : " 
				+ source1.startsWith("aaa"));
		System.out.println();
		
		// boolean contains(String containsString)
		// 원본 문자열이 인자로 지정한 문자열을 포함하고 있는지 판단 => true, false
		System.out.println("원본문자열이 aaa를 포함하는지 판단 : source1.contains(\"aaa\") : " 
				+ source1.contains("aaa"));
		System.out.println();
		
		// int length()
		// 문자열에 포함된 문자 수를 반환.
		System.out.print("source1 문자열의 문자 개수 :source1.length() : ");
		System.out.println(source1.length());
		System.out.println();
		
		// boolean equals(String str)
		// 대소문자를 구분하여 두 문자열 비교
		System.out.print("source1 문자열과 source2 문자열을 대소문자 구분하여 비교 : source1.equals(source2) : ");
		System.out.println(source1.equals(source2));
		System.out.println();
		
		// boolean equalsIgnoreCase(String str)
		// 대소문자를 구분하지 않고 문자열 비교
		System.out.print("source1 문자열과 source2 문자열을 대소문자 구분하지 않고 비교 : source1.equalsIgnoreCase(source2) : ");
		System.out.println(source1.equalsIgnoreCase(source2));
		System.out.println();

		// String toUpperCase()
		// 원본 문자열의 모든 문자를 대문자로 만들어준다.
		System.out.println("원본문자열 모든 문자를 대문자로 변경 : source1.toUpperCase() : " 
				+ source1.toUpperCase());
		System.out.println();
		
		// String toLowerCase()
		// 원본 문자열의 모든 문자를 소문자로 만들어준다.
		System.out.println("원본문자열 모든 문자를 소문자로 변경 : source1.toLowerCase() : " 
				+ source1.toLowerCase());
		System.out.println();
		
		// String[] split(String delims)
		// 
		String source3 = "aaa,bbb,ccc";
		System.out.println("원본 문자열 : " + source3);
		
		String[] splitArray = source3.split(",");
		System.out.println("source3.split(\",\")을 실행한 결과 문자열");
//		for (int i = 0; i < splitArray.length; i++) {
//			System.out.print(splitArray[i]);
//		}
		// 개선된 for 문 사용
		for(String str : splitArray) {
			System.out.print(str);
		}
		System.out.println();
		
		// String trim()
		// 문자열의 공백을 제거!!
		String source4 = " java ";
		// 공백이 있는지 확인을 위해 어포스트로피를 쏴준다.
		System.out.println("원본문자열 : '" + source4 + "'");
		System.out.println("공백 제거후 source4 문자열 : source4.trim() : '" + source4.trim() + "'");
		
		
		
		
		
		
	}

}
