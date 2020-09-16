import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.lang.StringBuilder;

import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

import exceptions.DukeException;
import exceptions.EmptyCommandException;
import exceptions.EmptyDescriptionException;
import exceptions.InvalidCommandException;
import exceptions.NoDateGivenException;

public class Duke {

    public static final String SEPARATING_LINE = "  ________________________________________";
    public static final String LINE_DOWN = "|";
    public static final int TODO_LEN = 5;
    public static final int EVENT_LEN = 6;
    public static final int DEADLINE_LEN = 9;
    public static final int DONE_LEN = 5;
    public static final String TASK_FILE = "data/tasks.txt";
    public static final String TASK_FOLDER = "data";
    static ArrayList<Task> tasksToDo = new ArrayList<>();

    public static void main(String[] args){
        greet();
        GetCommand();
        bye();
    }
    
    /**
    * Greets the user 
    */
    private static void greet(){
        System.out.println(SEPARATING_LINE);
        System.out.println("Welcome back, master");
        System.out.println("Your command is my command...");
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    /**
    * Continuously gets command from the user
    * until the user types out "bye".
    * Also prompts the user to put in valid commands
    * by resolving exceptions
    */
    private static void GetCommand(){
        Scanner input = new Scanner(System.in);

        try{
            readTaskFile();
            // If there are existing tasks then he is an 
            // existing user
            if(tasksToDo.size() > 0){
                System.out.println("Here is the list that you left off with: ");
                listOutTasks();
            } 
        } catch(IOException e){
            System.out.println("Sorry master, I am having difficulties finding" + 
                                " the tasks you left off with");
        }

        while(true){
            System.out.println("Please give me your command");

            String command;

            try{
                command = input.nextLine();

                // If user did not put any command, then 
                // throw empty command exception
                if(command == null || command.isEmpty()){
                    throw new EmptyCommandException();
                }

                if(command.equals("bye")) {
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
                } else{
                    throw new InvalidCommandException();
                }
            } catch(EmptyCommandException e){
                System.out.println();
                System.out.println("You did not give me any command");
            } catch(InvalidCommandException e){
                System.out.println();
                System.out.println("Please give me a valid command, master...");
            }
        }
    }

    /**
    * Lists out the current tasks that the 
    * user has typed in the bot so far
    */
    private static void listOutTasks(){
        System.out.println(SEPARATING_LINE);
        int currentTaskCount = tasksToDo.size();
        System.out.println("    You currently have " + currentTaskCount 
                            + " items to do in your list, master...");
        for(int i = 0; i < currentTaskCount; i++){
            Task currentTask = tasksToDo.get(i);
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
    private static void markAsDone(String command){
        int indexTaskDone = Integer.parseInt(command.substring(DONE_LEN, command.length()));
        Task currentTask = tasksToDo.get(indexTaskDone-1);
        System.out.println(SEPARATING_LINE);
        currentTask.markAsDone();
        System.out.println("    Nice! I've marked this task as done: ");
        printTaskStatus(currentTask);
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    /**
    * Adds a "To Do" task in the bot
    *
    * @param command the current command passed from user input
    */
    private static void addNewToDo(String command){
        String extractedCommand;
        try{
            extractedCommand = command.substring(TODO_LEN, command.length());
            //String[] commands = extractedCommand.split(" ");
            if(extractedCommand == null || extractedCommand.trim().length() == 0){
                throw new EmptyDescriptionException();
            }
            ToDo newToDo = new ToDo(extractedCommand);
            tasksToDo.add(newToDo);
            acknowledgeTaskAddition(newToDo);
        } catch(StringIndexOutOfBoundsException e){
            System.out.println();
            System.out.println("Sorry, but you need to describe what to do, master");
        } catch(EmptyDescriptionException e){
            System.out.println();
            System.out.println("Sorry, but you need to describe what to do, master");
        }
    }

    /**
    * Adds a "Deadline" task in the bot
    *
    * @param command the current command passed from user input
    */
    private static void addDeadline(String command){
        try{
            int positionOfBy = command.indexOf("/by");
            if(positionOfBy == -1){
                throw new NoDateGivenException();
            }
            String extractedCommand = command.substring(DEADLINE_LEN, positionOfBy-1);
            if(extractedCommand == null || extractedCommand.trim().length() == 0){
                throw new EmptyDescriptionException();
            }

            String by = command.substring(positionOfBy + 4, command.length());
            if(by == null || by.trim().length() == 0){
                throw new NoDateGivenException();
            }

            Deadline newDeadline = new Deadline(extractedCommand, by);
            tasksToDo.add(newDeadline);
            acknowledgeTaskAddition(newDeadline);
        } catch(NoDateGivenException e){
            System.out.println();
            System.out.println("You need to tell me when this task is due, master");
        } catch(EmptyDescriptionException e){
            System.out.println();
            System.out.println("Sorry, but you need to describe what deadline this is, master");
        }
    }

    /**
    * Adds a "Event" task in the bot
    *
    * @param command the current command passed from user input
    */
    private static void addEvent(String command){
        try{
            int positionOfAt = command.indexOf("/at");
            if(positionOfAt == -1){
                throw new NoDateGivenException();
            }

            String extractedCommand = command.substring(EVENT_LEN, positionOfAt-1);
            if(extractedCommand == null || extractedCommand.trim().length() == 0){
                throw new EmptyDescriptionException();
            }

            String at = command.substring(positionOfAt + 4, command.length());
            if(at == null || at.trim().length() == 0){
                throw new NoDateGivenException();
            }

            Event newEvent = new Event(extractedCommand, at);
            tasksToDo.add(newEvent);
            acknowledgeTaskAddition(newEvent);
        } catch(NoDateGivenException e){
            System.out.println();
            System.out.println("You need to tell me when this event is at, master");
        } catch(EmptyDescriptionException e){
            System.out.println();
            System.out.println("Sorry, but you need to describe what event this is, master");
        }
    }

    /**
    * Acknowledges the addition of task by printing
    * that the bot understood the task
    *
    * @param task the current task (To Do, Deadline or Event)
    */
    private static void acknowledgeTaskAddition(Task task){
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
    * Reads the saved task file and creates a
    * file in the directory if it does not exist
    */
    private static void readTaskFile() throws IOException{
        try{
            Files.createDirectories(Paths.get(TASK_FOLDER));
            Files.createFile(Paths.get(TASK_FILE));
        } catch(FileAlreadyExistsException e){
            // We do not need to add new file
        }
        
        File f = new File(TASK_FILE);

        Scanner fileInput = new Scanner(f);
        while (fileInput.hasNext()) {
            String line = fileInput.nextLine();

            char taskType = line.charAt(0);
            char taskStatus = line.charAt(2);
            String taskDescription = line.substring(4);

            if (taskType == 'T'){
                Task newToDo = new ToDo(taskDescription);
                addTaskFromFile(newToDo, taskStatus);
            } else if(taskType == 'D'){
                int separator = taskDescription.indexOf(LINE_DOWN);
                String deadlineDescription = taskDescription.substring(0, separator);
                String by = taskDescription.substring(separator+1, taskDescription.length());
                Task newDeadline = new Deadline(deadlineDescription, by);
                addTaskFromFile(newDeadline, taskStatus);
            } else if(taskType == 'E'){
                int separator = taskDescription.indexOf(LINE_DOWN);
                String eventDescription = taskDescription.substring(0, separator);
                String at = taskDescription.substring(separator+1, taskDescription.length());
                Task newDeadline = new Event(eventDescription, at);
                addTaskFromFile(newDeadline, taskStatus);
            }
        }
    }

    /**
    * Adds existing tasks from our file into our
    * array list
    *
    * @param task the current task (To Do, Deadline or Event)
    * @param taskStatus the status of the current task
    */
    private static void addTaskFromFile(Task task, char taskStatus){
        if(taskStatus == '1'){
            task.markAsDone();
        }
        tasksToDo.add(task);
    }

    /**
    * Writes the converted list into our task file
    */
    private static void writeTaskFile() throws IOException{
        FileWriter fw = new FileWriter(TASK_FILE);
        String finalTasks = parseTasksToFile();
        fw.write(finalTasks);
        fw.close();
    }

    /**
    * Converts the list into our file formatting
    *
    * Returns a string to list the existing tasks
    */
    private static String parseTasksToFile(){
        String finalTasks = "";
        int currentTaskCount = tasksToDo.size();
        
        for(int i = 0; i < currentTaskCount; i++){
            String taskType = "";
            String status = "";
            String taskDescription = "";
            String by = "";
            String at = "";

            // Get the task type and by/at for deadline/event
            Task task = tasksToDo.get(i);
            if(task instanceof ToDo){
                taskType = "T";
            } else if(task instanceof Deadline){
                taskType = "D";
                by = task.getBy();
            } else if(task instanceof Event){
                taskType = "E";
                at = task.getAt();
            }

            // Get the task description
            taskDescription = task.getDescription();

            // Get the task status
            if(task.getIsDone()){
                status = "1";
            } else{
                status = "0";
            }

            // Build the whole string
            String currentTask = taskType + LINE_DOWN +
                                    status + LINE_DOWN +
                                    taskDescription;
            
            // add by or at if the task is a deadline or event
            if(task instanceof Deadline){
                currentTask = currentTask + LINE_DOWN + by;
            } else if(task instanceof Event){
                currentTask = currentTask + LINE_DOWN + at;
            }

            // add current task to the list of tasks 
            // and add a line separator
            finalTasks = finalTasks + currentTask + System.lineSeparator();
        }

        return finalTasks;
    }

    /**
    * Prints goodbye
    */
    private static void bye(){
        try{
            writeTaskFile();
        } catch(IOException e){
            System.out.println("Your file was not saved properly");
        }
        System.out.println("Have a safe journey");
    }
}