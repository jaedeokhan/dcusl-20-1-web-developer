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
