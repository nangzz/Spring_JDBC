package lambda;

public class UsingLambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1. 고전적인 Thread 생성 방법
		Thread t1 = new Thread();
		t1.setName("둘리"); // 스레드 이름
		t1.start();
		
		// 2. Thread 생성 - Annoymous Inner Class 익명클래스 형태로
		// run() 클래스를 따로 구현하지 않음
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName());
			}
			
		});
		
		t2.setName("길동");
		t2.start();
		
		// 3. Thread 생성 - Lambda 식 형태로
		Thread t3 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
		t3.setName("자바");
		t3.start();
	}

}

class MyRunnable implements Runnable {

	@Override
	public void run() { // 여기에 작업할 것 구현
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName());
	}
	
}
