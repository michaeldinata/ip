import java.util.Scanner;
import java.io.IOException;

import exceptions.DukeException;
import exceptions.EmptyCommandException;
import exceptions.InvalidCommandException;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Duke {

    private static final String TASK_FILE = "data/tasks.txt";
    private static final String TASK_FOLDER = "data";
    private static TaskList tasks;
    private static Storage storage;
    private static Ui ui;

    public Duke(){
        storage = new Storage(TASK_FOLDER, TASK_FILE);
        try{
            tasks = new TaskList(storage.loadFile());
        } catch(IOException e){
            ui.showLoadingError();
        }
    }

    public static void run(){
        ui = new Ui();
        ui.greet();
        new Duke();
        getCommand();
        bye();
    }

    public static void main(String[] args){
        run();
    }

    /**
    * Continuously gets command from the user
    * until the user types out "bye".
    * Also prompts the user to put in valid commands
    * by resolving exceptions
    *
    */
    private static void getCommand(){
        Scanner input = new Scanner(System.in);

        while(true){
            ui.requestNewCommand();

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
                ui.showEmptyCommandError();
            } catch(InvalidCommandException e){
                ui.showInvalidCommandError();
            }
        }
    }

    /**
    * Prints goodbye
    *
    */
    private static void bye(){
        try{
            storage.saveFile();
        } catch(IOException e){
            ui.showFileUnsavedError();
        } catch(NullPointerException e){
            ui.showFileUnsavedError();
        }
        ui.bye();
    }
}