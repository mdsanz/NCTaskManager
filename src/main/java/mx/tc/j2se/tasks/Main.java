package mx.tc.j2se.tasks;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello Training Center!");
		TaskImpl task1 = new TaskImpl("x", 8, 8, 15);
		task1.setActive(true);
		System.out.println(task1.nextTimeAfter(7));
		task1.setTime(15);
		System.out.println(task1.nextTimeAfter(16));
	}
}

