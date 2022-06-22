package mx.tc.j2se.tasks;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello Training Center!");
		TaskImpl task1 = new TaskImpl("Hello", 8);
		ArrayTaskListImpl taskList1 = new ArrayTaskListImpl();
		taskList1.add(task1);
		System.out.println(taskList1.size());
		taskList1.add(task1);
		System.out.println(taskList1.size());
		System.out.println(taskList1.getTask(1).getTitle());
		ArrayTaskListImpl taskList2 = new ArrayTaskListImpl();
		System.out.println(taskList2.size());
		System.out.println("Removing tasks");
		System.out.println(taskList1.remove(task1));
	}
}

