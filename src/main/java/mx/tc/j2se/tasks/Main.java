package mx.tc.j2se.tasks;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello Training Center!");
		TaskImpl task1 = new TaskImpl("task1", 8);
		TaskImpl task2 = new TaskImpl("task2", 2, 10, 2);
		TaskImpl task3 = new TaskImpl("task3", 1, 5, 1);
		TaskImpl task4 = new TaskImpl("task4", 4);
		task1.setActive(true);
		task2.setActive(true);
		task3.setActive(true);
		task4.setActive(true);
		LinkedTaskListImpl taskList1 = new LinkedTaskListImpl();
		taskList1.add(task1);
		taskList1.add(task2);
		taskList1.add(task3);
		taskList1.add(task4);
		for (int i = 0; i <taskList1.size(); i++) {
			System.out.println(taskList1.getTask(i).getTitle() + " " + taskList1.getTask(i).isRepeated());
		}
		System.out.println("Removing task =======");
		// taskList1.remove(task1);
		for (int i = 0; i < taskList1.size(); i++) {
			System.out.println(taskList1.getTask(i).getTitle() + " " + taskList1.getTask(i).isRepeated());
		}
		System.out.println("====== IncomingTaskList ======");
		LinkedTaskList incomingTask1 = taskList1.incoming(-1, 5);
		for (int i = 0; i < incomingTask1.size(); i++) {
			System.out.println(incomingTask1.getTask(i).getTitle() + " " + incomingTask1.getTask(i).isRepeated());
		}
		System.out.println(taskList1.getTask(2).getTitle());
	}
}

