import java.util.*;

public class Duke {

    public static final String SEPARATING_LINE = "  ________________________________________";
    public static final int MAX_TASKS = 100;
    public static final int TODO_LEN = 5;
    public static final int EVENT_LEN = 6;
    public static final int DEADLINE_LEN = 9;
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
        System.out.println(SEPARATING_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(SEPARATING_LINE);
        System.out.println();
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
            } else if(command.contains("todo")){
                addNewToDo(command);
            } else if(command.contains("deadline")){
                addDeadline(command);
            } else if(command.contains("event")){
                addEvent(command);
            }
        }
    }

    public static void listOutTasks(){
        System.out.println(SEPARATING_LINE);
        for(int i = 0; i < currentTaskCount; i++){
            Task currentTask = tasksToDo[i];
            int currentNum = i+1;
            System.out.print("  " + currentNum + ". ");
            printTaskStatus(currentTask);
        }
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    public static void markAsDone(String command){
        int indexTaskDone = Integer.parseInt(command.substring(5, command.length()));
        Task currentTask = tasksToDo[indexTaskDone-1];
        System.out.println(SEPARATING_LINE);
        currentTask.markAsDone();
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    public static void addNewToDo(String command){
        String extractedCommand = command.substring(TODO_LEN, command.length());
        ToDo newToDo = new ToDo(extractedCommand);
        tasksToDo[currentTaskCount++] = newToDo;
        acknowledgeTaskAddition(newToDo);
    }

    public static void addDeadline(String command){
        int positionOfBy = command.indexOf("/by");
        String extractedCommand = command.substring(DEADLINE_LEN, positionOfBy);
        String by = command.substring(positionOfBy + 4, command.length());
        Deadline newDeadline = new Deadline(extractedCommand, by);
        tasksToDo[currentTaskCount++] = newDeadline;
        acknowledgeTaskAddition(newDeadline);
    }

    public static void addEvent(String command){
        int positionOfAt = command.indexOf("/at");
        String extractedCommand = command.substring(EVENT_LEN, positionOfAt);
        String by = command.substring(positionOfAt + 4, command.length());
        Event newEvent = new Event(extractedCommand, by);
        tasksToDo[currentTaskCount++] = newEvent;
        acknowledgeTaskAddition(newEvent);
    }

    public static void acknowledgeTaskAddition(Task task){
        System.out.println(SEPARATING_LINE);
        System.out.println("    Got it. I've added this task: ");
        printTaskStatus(task);
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    public static void printTaskStatus(Task task){
        System.out.println(task.toString());
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
