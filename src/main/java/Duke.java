import java.util.*;

public class Duke {

    public static final String separatingLine = "  ______________________________";
    private static final int MAX_TASKS = 100;
    public static int currentTaskCount = 0;
    static Task[] tasksToDo = new Task[MAX_TASKS];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        GetTask();
        bye();
    }

    public static void greet(){
        System.out.println(separatingLine);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(separatingLine);
    }

    public static void GetTask(){
        Scanner in = new Scanner(System.in);

        while(true){
            String command = in.nextLine();

            if(command.equals("bye")){
                return;
            } else if(command.equals("list")){
                listOutTasks();
            } else if(command.contains("done")){
                markAsDone(command);
            } else{
                addNewTask(command);
            }
        }
    }

    public static void listOutTasks(){
        System.out.println(separatingLine);
        for(int i = 0; i < currentTaskCount; i++){
            Task currentTask = tasksToDo[i];
            System.out.println("    " + (i+1) + ".[" + currentTask.getStatusIcon() + "] " 
            + currentTask.getDescription());
        }
        System.out.println(separatingLine);
        System.out.println();
    }

    public static void markAsDone(String command){
        int indexTaskDone = Integer.parseInt(command.substring(5, command.length()));
        Task currentTask = tasksToDo[indexTaskDone-1];
        System.out.println(separatingLine);
        currentTask.markAsDone();
        System.out.println(separatingLine);
        System.out.println();
    }

    public static void addNewTask(String command){
        Task newTask = new Task(command);
        tasksToDo[currentTaskCount++] = newTask;
        System.out.println();
        System.out.println("    Added: " + command);
        System.out.println(separatingLine);
        System.out.println();
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
