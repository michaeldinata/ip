import java.util.*;

public class Duke {
    public static class Task{
        protected String description;
        protected boolean isDone;

        public Task(String description){
            this.description = description;
            this.isDone = false;
        }
    
        public String getStatusIcon(){
            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        }
    
        public String getDescription(){
            return description;
        }
        
        public void setDescription(String description){
            this.description = description;
        }
    
        public void markAsDone(){
            this.isDone = true;
        }
    }

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
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void GetTask(){
        Task[] ListToDo = new Task[100];
        Scanner in = new Scanner(System.in);
        int currentTaskCount = 0;

        while(true){
            String command = in.nextLine();
            if(command.compareTo("bye") == 0){
                return;
            }
            else if(command.compareTo("list") == 0){
                listOutTasks(ListToDo, currentTaskCount);
            }
            else if(command.contains("done")){
                int indexTaskDone = Integer.parseInt(command.substring(5, command.length()));
                Task currentTask = ListToDo[indexTaskDone-1];
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                System.out.println();
            }

            else{
                Task newTask = new Task(command);
                ListToDo[currentTaskCount++] = newTask;
                System.out.println("Added: " + command);
                System.out.println();
            }
        }
    }

    public static void listOutTasks(Task[] ListToDo, int currentTaskCount){
        for(int i = 0; i < currentTaskCount; i++){
            Task currentTask = ListToDo[i];
            System.out.println((i+1) + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
        }
        System.out.println();
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
