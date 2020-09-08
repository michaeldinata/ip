import java.util.Scanner;

public class Duke {

    public static final String SEPARATING_LINE = "  ________________________________________";
    public static final int MAX_TASKS = 100;
    public static final int TODO_LEN = 5;
    public static final int EVENT_LEN = 6;
    public static final int DEADLINE_LEN = 9;
    public static int currentTaskCount = 0;
    static Task[] tasksToDo = new Task[MAX_TASKS];

    public static void main(String[] args) {
        greet();
        GetCommand();
        bye();
    }
    
    /**
    * Greets the user 
    */
    public static void greet(){
        System.out.println(SEPARATING_LINE);
        System.out.println("Welcome back, master");
        System.out.println("Your command is my command...");
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    /**
    * Continuously gets command from the user
    * until the user types out "bye"
    */
    public static void GetCommand(){
        Scanner input = new Scanner(System.in);

        while(true){
            System.out.println("Please give me your command");

            String command;

            try{
                command = input.nextLine();

                if(command == null || command.isEmpty()){
                    throw new EmptyCommandException();
                }

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
            } catch(EmptyCommandException e){
                System.out.println("You did not give me any command");
            }
        }
    }

    /**
    * Lists out the current tasks that the 
    * user has typed in the bot so far
    */
    public static void listOutTasks(){
        System.out.println(SEPARATING_LINE);
        System.out.println("    You currently have " + currentTaskCount 
                            + " items to do in your list, master...");
        for(int i = 0; i < currentTaskCount; i++){
            Task currentTask = tasksToDo[i];
            int currentNum = i+1;
            System.out.print("  " + currentNum + ". ");
            printTaskStatus(currentTask);
        }
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    /**
    * Marks the task that the user indicated to be done
    * by changing the cross to a tick and then
    * prints out the task that was done
    *
    * @param command the current command passed from user input
    */
    public static void markAsDone(String command){
        int indexTaskDone = Integer.parseInt(command.substring(5, command.length()));
        Task currentTask = tasksToDo[indexTaskDone-1];
        System.out.println(SEPARATING_LINE);
        currentTask.markAsDone();
        printTaskStatus(currentTask);
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    /**
    * Adds a "To Do" task in the bot
    *
    * @param command the current command passed from user input
    */
    public static void addNewToDo(String command){
        String extractedCommand = command.substring(TODO_LEN, command.length());
        ToDo newToDo = new ToDo(extractedCommand);
        tasksToDo[currentTaskCount++] = newToDo;
        acknowledgeTaskAddition(newToDo);
    }

    /**
    * Adds a "Deadline" task in the bot
    *
    * @param command the current command passed from user input
    */
    public static void addDeadline(String command){
        int positionOfBy = command.indexOf("/by");
        String extractedCommand = command.substring(DEADLINE_LEN, positionOfBy);
        String by = command.substring(positionOfBy + 4, command.length());
        Deadline newDeadline = new Deadline(extractedCommand, by);
        tasksToDo[currentTaskCount++] = newDeadline;
        acknowledgeTaskAddition(newDeadline);
    }

    /**
    * Adds a "Event" task in the bot
    *
    * @param command the current command passed from user input
    */
    public static void addEvent(String command){
        int positionOfAt = command.indexOf("/at");
        String extractedCommand = command.substring(EVENT_LEN, positionOfAt);
        String by = command.substring(positionOfAt + 4, command.length());
        Event newEvent = new Event(extractedCommand, by);
        tasksToDo[currentTaskCount++] = newEvent;
        acknowledgeTaskAddition(newEvent);
    }

    /**
    * Acknowledges the addition of task by printing
    * that the bot understood the task
    *
    * @param task the current task (To Do, Deadline or Event)
    */
    public static void acknowledgeTaskAddition(Task task){
        System.out.println(SEPARATING_LINE);
        System.out.println("    Understood. I've added this into the list.. ");
        printTaskStatus(task);
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    /**
    * Prints the task status at hand
    *
    * @param task the current task (To Do, Deadline or Event)
    */
    public static void printTaskStatus(Task task){
        System.out.println(task.toString());
    }

    /**
    * Prints goodbye
    */
    public static void bye(){
        System.out.println("Have a safe journey");
    }
}