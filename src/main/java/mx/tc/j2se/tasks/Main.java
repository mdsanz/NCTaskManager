package mx.tc.j2se.tasks;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello Training Center!");
		TaskImpl task1 = new TaskImpl();
		task1.setTime(0, 100, 25);
		System.out.println(task1.isRepeated());
	}
}

