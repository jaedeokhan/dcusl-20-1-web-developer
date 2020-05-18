### 2020 0512 TUE 
### 당일 학습내용
1. Java Collection Framework
   * set
      * HashSet : Set 형태로, 정렬 x
         * LottoHashSet.java
	    * size() 메소드 == 컬렉션의 요소의 개수를 반환하는 메소드
	    * Math.random() 
      * TreeSet : HashSet + 정렬 기능 o
         * LottoTreeSet.java
   * List => LinkedList() , LinkedList(argument)
      * Stack : Last in - Frist Out 형태(후입선출)
      * Vector 
      * ArrayList
      * Queue 
      
   * Map
      * Hashptable : 스레드 동기화를 가진다.
      * HashMap : 스레드 동기화를 지원하지 않는다. 최근에는 이것을 많이 사용한다.
   * Iterator : 선방향으로 순차적인 것!
   * ListIterator : Iterator에서 향상된 버전, 후방향을 접근도 가능
      * hasNext() => true, fasle
      * hasPrevious() 
      * next() => 다음으로 넘어가는 것.
      * previous() => 끝에서 처음으로 가는 것.
   * Collections 객체
      * sort() 
	

#### 1. Java Collection Framework
Collection과 array의 차이는?
배열은 값을 넣은 후에 크기를 변경할 수 없어서 불편했다.
그렇지만, Collection은 데이터가 들어가는 만큼 자동으로 크기가 증가가 된다.
그래서, 배열보다 데이터를 다루기가 Collection이 더 편하다.

#### 1.1 set 
집합과 같다고 생각하면 된다.
Set을 기본적으로 구현한 것이 <b>HashSet<b/>이다.
HashSet + 정렬 기능을 추가한 것이 <b>TreeSet<b/>이다.

> HashSetTest.java
```java
package HashSet;
// 배열은 기본 타입의 데이터나 객체 타입의 데이터 모두 저장할 수 있었지만
// 컬렉션은 객체 타입만 저장할 수 있다.
// 배열은 크기가 한 번 정해지면 변경할 수 없지만,
// 컬렉션은 크기보다 더 많은 데이터를 저장하면 크기가 자동으로 늘어난다.


import java.util.HashSet;
import static java.lang.System.out;

public class HashSetTest {

	public static void main(String[] args) {
		
		String[] carArray = {"제네시스" ,"소나타", "K7", "벤츠", "아우디", "K7"};
		
		// carArray 가 HashSet에 들어가면?
		HashSet<String> hs1 = new HashSet<String>();
		HashSet<String> hs2 = new HashSet<String>();
		
		for (String car : carArray) {
			// 요소를 담는것을 실패하면 if문으로 들어간다. 즉 중복된 값이 존재할때 if문 안으로 들어간다.
			if(!hs1.add(car)) {
				// add : Collection 인터페이스에서 제공하는 메소드, 요소를 추가하는 메소드이다.
				// 요소가 정상적으로 담기면 true 반환
				// 중복되는 값은 hs2에 add한다.
				hs2.add(car);
			}
		}
		out.println("hs1 : " + hs1);
		out.println("hs2 : " + hs2);
		
		out.println("");
		out.println("제네시스 삭제");
		hs1.remove("제네시스");
		out.println("hs1 : " + hs1);
		
		out.println("");
		out.println("hs2에 있는 K7을 제거한다.");
		hs1.removeAll(hs2);
		out.println("hs1 : " + hs1);
		
	}

}
```

#### 1.2.1 HashSet 객체를 이용해서 로또 번호를 자동으로 추첨하기.
무작위로 6개의 숫자를 던지고, 정렬해서 출력하기.

> LottoHashSet.java
```java
package HashSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LottoHashSet {

	public static void main(String[] args) {
		
		HashSet<Integer> hashSet = new HashSet<Integer>();
		
		for (int i = 0; hashSet.size() < 6; i++) {
			// size() : Collection 인터페이스에서 제공하는 메소드 
			// 컬렉션에 추가되어 있는 요소의 개수를 반환하는 메소드이다.
			// Math.random() * 45 == 0 - 44 , 여기서 + 1을 하면 1 - 45
			int num = (int)(Math.random() * 45) + 1 ;
			hashSet.add(num);
		}
		
		// Set 타입의 컬렉션은 정렬이 되지 않는다.
		// 정렬을 List 타입으로 생성한다.
//		List listA = new ArrayList();
//		ArrayList<Integer> listB = new ArrayList<Integer>();
//		
//		
////		listA.add(hashSet);
//		listB.addAll(hashSet);
//		Collections.sort(listB);
//		
//		System.out.println(listB);
//		
		
		// List는 interface이다. List interface를 구현한 객체 중에 하나가 LinkedList이다.
		List<Integer> list1 = new LinkedList<Integer>();
		list1.addAll(hashSet);
		Collections.sort(list1);
		System.out.println(list1);
		
		// 위의 LinkedList에 인자를 안받고 해도 되고 받고 사용을 해도 상관은 없다.
		List<Integer> list2 = new LinkedList<Integer>(hashSet);
		Collections.sort(list2);
		System.out.println();
		System.out.println(list2);
	}

}
```

#### 1.2.2 TreeSet 객체를 이용해서 로또 번호를 자동으로 추첨하기.
TreeSet은 정렬 기능이 있어서 HashSet과는 달리 List로 받지 않고, Collections 객체의 sort를 사용하지 않아도 되서 더욱 편리하다.
{
> LottoTreeSet.java

```java
// TreeSet
// HashSet 에 정렬기능이 추가된 클래스
package HashSet;

import java.util.TreeSet;

public class LottoTreeSet {

	public static void main(String[] args) {
		
		TreeSet<Integer> lotto = new TreeSet<Integer>();
		
		for (int i = 0; lotto.size() < 6; i++) {
			int num = (int)(Math.random() * 45) + 1;
			lotto.add(num);
		}
		
		System.out.println(lotto);
	}

}
```

#### 2. List 

#### 2.1 Stack 구조

> StackTest.java

```java
// LIFO (Last In Frist Out)
package HashSet;

import java.util.Iterator;
import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {

		String[] countryArray = {"대한민국", "영국", "미국", "사우디", "중국", "러시아", "필리핀"};

		Stack<String> countryStack = new Stack<String>();

		// 요소 추가
		System.out.println("들어가는 순서 ");
		for (String country : countryArray) {
			// add를 사용해도 되지만, stack에서 사용하는 push가 더 유용함.
			countryStack.push(country);
			// 들어가는 순서
			System.out.println(country);
		}

		// 요소 조회
		System.out.println("나오는 순서");
		while (!countryStack.isEmpty()) {
			// isEmpty() : Collection 인터페이스에서 제공되는 메소드
			// 컬렉션이 요소가 하나도 없으면 true를 반환하는 메소드
			System.out.println(countryStack.pop());

		}
	}

}
```

#### 2.2 Vector 구조
Vector는 배열과 유사하지만, 크기가 자동으로 증가, 감소한다.
배열보다는 Collection의 Vector를 사용하는 것이 더 효율적이다.
Vector와 array의 차이점은 Vector는 구버전이다.
Vector는 동기화를 자동으로 하게 된다. => 스레드!!
스레드란 프로그램안에서 명령을 처리하는 단위!! 
그래서, Vector는 나중에 배우는 스레드 동기화가 지원되는 List 구조다.
<b>동기화를 걸어주는게 좋을 거 같지만, 성능의 저하가 발생할 수 있다!!!</b>


#### 3. ArrayList
Vector 보다 나중에 나온 신버전으로 현재 Vector보다 많이 사용한다!!
프로그래밍에서 가장 중요한 CRUD(Create, Read, Update, Delete)를 ArrayList로 구현하기.
* Create == add, push, addAll
* Read == get(index)
* Update == set(index, "변경 데이터")
* 
> ArrayListTest.java

```java
package HashSet;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>(); 
		
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
		list.add("555");
		list.add("111");
			
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 다른 방법으로 add하는 방법은? => 요 방법은 C(Create)
		list.add(0, "aaa");
		System.out.println();
		System.out.println("0번 인덱스 출력 : ");
		System.out.print(list.get(0));
	
		System.out.println("\n\n다시 모두 출력!");
		// 요 놈은 R(Read)
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		ArrayList<String> addListA = new ArrayList<String>();
		addListA.add("add1");
		addListA.add("add2");
		addListA.add("add3");
		
		// list에 위에서 새로 만든 arrayList를 한 번에 모두 넣기!
		list.addAll(addListA);
		System.out.println("\nlist에서 addAll을 사용해서 addListA를 한 번에 넣어서 출력하기!!!");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 다시 0번 리스트부터 addListA에 있는 모든 데이터를 차례대로 넣기
		list.addAll(0, addListA);
		System.out.println("\n 다시 addListA에 있는 데이터를 addAll을 사용해서 0번 인덱스부터 넣기");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	
		ArrayList<String> addListB = new ArrayList<String>(list);
		System.out.println("\n arrListB에서 객체 생성과 동시에 list를 한 번에 받아서 출력 !!");
		System.out.println(addListB);
		
		// U (Update) 
		System.out.println("\n0번 인덱스 영역에 있는 요소를 수정하기!!!");
		System.out.println(list.get(0));

		// 수정
		list.set(0, "0번 인덱스 데이터 변경");
		System.out.println(list.get(0));
		
		// D (Delete)
		list.remove(0);
		System.out.println("\n0번 인덱스인 0번 인덱스 데이터 변경을 제거하면 나머지는 위로 당겨진다.\n"
				+ "==========================================");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 인덱스 번호를 사용해서 삭제하는 방법말고 특정 데이터의 값을 선택해서 삭제를 하기.
		list.remove("add2");
		System.out.println("\n바로 위에서 맨 위로 당겨진 add2를 직접적으로 선택해서 삭제해본다."
				+ "삭제를 하면! 당연하게도 add3가 위로 당겨진다!!"
				+ "=======================================");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 위에서 ad
		
	}
	
}

```

#### 4. Queue 인터페이스
Frist in - Frist out , 선입선출 구조!
API 문서를 참조해 보면 Queue를 구현하는 클래스는 여러 가지가 있는 것을 알 수 있으며 여기서는 그 중에서 LinkedList에 대해서 알아봄!

> QueueTest.java
```java
package HashSet;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {


		String[] flowerArray = {"개나리", "국화", "카네이션", "장미", "무궁화", "개나리", "안개꽃"};

		// 큐는 LinkedList를 사용한 것!
		LinkedList<String> queue = new LinkedList<String>();

		System.out.println("Queue 데이터 삽입하는 순서!!");
		for (String flower : flowerArray) {
			queue.offer(flower);
			System.out.println(flower);
		}
		System.out.println("\nqueue의 크기  :" + queue.size());

		String data = "";

		System.out.println("\nQueue 데이터를 출력!");
		while((data = queue.poll()) != null) {
			// queue에 요소가 있으면...
			System.out.println(data);
		}

		System.out.println("\nqueue.poll 로 모든 데이터를 data에 담아주고나면 queue의 size()는?");
		System.out.println("queue의 크기 : " + queue.size());
	}

}
```


#### 5. Map 인터페이스
Key와 Value를 매핑하는 객체이다. 여기에 사용하는 Key는 절대 중복될 수 없으며 각Key는 1개의 Vlaue만 매핑이 가능하다. 같은 Key의 값을 가지는 것을 다시 정의하면 덮어쓰게 된다.
정렬의 기준이 없다.


> HaspMapTest.java
```java
package HashSet;

import java.util.HashMap;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		
		String[] houseArray = {"아파트", "원룸", "빌라", "고시원", "오피스텔"};
		
		
		// <generic type>
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		
		for (int i = 0; i < houseArray.length; i++) {
			hashMap.put(i, houseArray[i]);
		}
		
		System.out.println(hashMap);

		// 각 키에 대한 값들을 출력
		// Map에 저장된 요소들의 Key 값드만 얻어오는 메소드가 있다.
		Set<Integer> hashKey = hashMap.keySet();
		
		System.out.println("\nhashMap의 Key값만 저장된 hashKey를 출력");
		
		for (Integer key : hashKey) {
			// 각각의 키를 출력
			System.out.print(key + " ");
			
			// 각 키에 해당하는 요소들을 출력
			System.out.println(hashMap.get(key));
		}
	}

}
```

#### 6. Iterator 와 Listlterator
컬렉션의 요소를 순차적으로 접근할 수 있게 지원해주는 인터페이스


> Iterator.java
```java
package HashSet;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorTest {

	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		System.out.println(list);
		
		// 해당 컬렉션 객테를 다루는 iterator()가 반환이 되는 메소드 == iterator()
		// <String>을 주지 않으면 Object를 반환한다.
		Iterator<String> iter = list.iterator();
		
		System.out.println("\niterator 출력 시작!");
		// hasNext() : 작업할 다음 요소가 있으면 true를 반환, 없으면 false를 반환한다.
		while (iter.hasNext()) {
			String string = iter.next();  // next() : 요소를 반환하고 다음 작업할 요소 위치로 이동
			System.out.println(string);
			
		}
	}

}
```

#### 6.1 Listiterator
Iterator에서 향상된 버전, Iterator는 선방향에서 순차적인 접근만 가능하지만,
요 놈은 후방향에서도 순차적인 접근이 가능하다.

> ListIteratorTest.java

```java
package HashSet;

import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorTest {

	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		// listIterator의 객체를 반환해주는 메소드
		ListIterator<String> it = list.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			
		}
		System.out.println("\n\n previous를 사용해서 이전 출력!!");
		
		// 이전으로 되돌아가서
		while(it.hasPrevious()) {
			System.out.println(it.previous());
		}
	}

}
```

