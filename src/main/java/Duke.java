import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

import exceptions.DukeException;
import exceptions.EmptyCommandException;
import exceptions.EmptyDescriptionException;
import exceptions.InvalidCommandException;
import exceptions.NoDateGivenException;

import storage.Storage;
import tasklist.TaskList;

public class Duke {

    private static final String SEPARATING_LINE = "  ________________________________________";
    private static final String TASK_FILE = "data/tasks.txt";
    private static final String TASK_FOLDER = "data";
    private static TaskList tasks;
    private static Storage storage;

    public Duke(){
        storage = new Storage(TASK_FOLDER, TASK_FILE);
        try{
            tasks = new TaskList(storage.loadFile());
        } catch(IOException e){
            System.out.println("Sorry master, I am having difficulties finding" + 
                                " the tasks you left off with");
        }
    }

    public static void run(){
        greet();
        getCommand();
        bye();
    }

    public static void main(String[] args){
        new Duke();
        run();
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
    private static void getCommand(){
        Scanner input = new Scanner(System.in);

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
                    tasks.listOutTasks();
                } else if(command.contains("done")){
                    tasks.markAsDone(command);
                } else if(command.contains("todo")){
                    tasks.addNewToDo(command);
                } else if(command.contains("deadline")){
                    tasks.addDeadline(command);
                } else if(command.contains("event")){
                    tasks.addEvent(command);
                } else if (command.contains("delete")){
                    tasks.deleteTask(command);
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
    * Prints goodbye
    */
    private static void bye(){
        try{
            storage.saveFile();
        } catch(IOException e){
            System.out.println("Your file was not saved properly");
        } catch(NullPointerException e){
            System.out.println("Your file was not saved properly");
        }
        System.out.println("Have a safe journey");
    }
}