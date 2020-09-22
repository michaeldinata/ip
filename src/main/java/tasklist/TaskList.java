package tasklist;

import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.NoDateGivenException;

import java.util.ArrayList;

public class TaskList{
    private static final String SEPARATING_LINE = "  ________________________________________";
    private static final int TODO_LEN = 5;
    private static final int EVENT_LEN = 6;
    private static final int DEADLINE_LEN = 9;
    private static final int DONE_LEN = 5;
    private static final int DELETE_LEN = 7;
    private static ArrayList<Task> tasksToDo = new ArrayList<>();

    public TaskList(ArrayList<Task> loadedTasks){
        this.tasksToDo = loadedTasks;
        if(tasksToDo.size() > 0){
            System.out.println("Here is the list that you left off with: ");
            listOutTasks();
        }
    }

    /**
    * Lists out the current tasks that the 
    * user has typed in the bot so far
    */
    public void listOutTasks(){
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
    public void markAsDone(String command){
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
    public void addNewToDo(String command){
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
    public void addDeadline(String command){
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
    public void addEvent(String command){
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
    public void acknowledgeTaskAddition(Task task){
        System.out.println(SEPARATING_LINE);
        System.out.println("    Understood. I've added this into the list.. ");
        printTaskStatus(task);
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    /**
    * Deletes the task that the user specifies
    *
    * @param command the current command passed from user input
    */
    public void deleteTask(String command){
        int indexTaskDelete = Integer.parseInt(command.substring(DELETE_LEN, command.length()));
        Task currentTask = tasksToDo.get(indexTaskDelete-1);
        System.out.println(SEPARATING_LINE);
        System.out.println("Noted. I have deleted this task:");
        printTaskStatus(currentTask);
        tasksToDo.remove(indexTaskDelete-1);
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    /**
    * Prints the task status at hand
    *
    * @param task the current task (To Do, Deadline or Event)
    */
    public void printTaskStatus(Task task){
        System.out.println(task.toString());
    }
}