package tasklist;

import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.NoDateGivenException;

import java.util.ArrayList;
import ui.TaskUi;
import parser.Parser;

public class TaskList{
    private static final String SEPARATING_LINE = "  ________________________________________";
    private static ArrayList<Task> tasksToDo = new ArrayList<>();
    private static TaskUi ui = new TaskUi();
    private static Parser parser = new Parser();

    public TaskList(ArrayList<Task> loadedTasks){
        this.tasksToDo = loadedTasks;
        if(tasksToDo.size() > 0){
            ui.showLeftoverTask();
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
        int currentNum = 1;
        ui.showCurrentTaskCount(currentTaskCount);

        for(Task t: tasksToDo){
            System.out.print("  " + currentNum + ". ");
            currentNum++;
            printTaskStatus(t);
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
        try{
            int indexTaskDone = parser.findIndexOfTaskDone(command);
            Task currentTask = tasksToDo.get(indexTaskDone-1);
            System.out.println(SEPARATING_LINE);
            currentTask.markAsDone();
            ui.showMarkTaskAsDone();
            printTaskStatus(currentTask);
            System.out.println(SEPARATING_LINE);
            System.out.println();
        } catch(StringIndexOutOfBoundsException e){
            ui.showMissingCompletedIndex();
        } catch(NumberFormatException e){
            ui.showMissingCompletedIndex();
        } catch(IndexOutOfBoundsException e){
            ui.showMarkDoneOutOfBounds();
        }
    }

    /**
    * Adds a "To Do" task in the bot
    *
    * @param command the current command passed from user input
    */
    public void addNewToDo(String command){
        String extractedCommand;
        try{
            extractedCommand = parser.extractToDo(command);
            //String[] commands = extractedCommand.split(" ");
            if(extractedCommand == null || extractedCommand.trim().length() == 0){
                throw new EmptyDescriptionException();
            }
            ToDo newToDo = new ToDo(extractedCommand);
            tasksToDo.add(newToDo);
            acknowledgeTaskAddition(newToDo);
        } catch(StringIndexOutOfBoundsException e){
            ui.showMissingToDoDescription();
        } catch(EmptyDescriptionException e){
            ui.showMissingToDoDescription();
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
            String extractedCommand = parser.extractDeadline(command, positionOfBy);
            if(extractedCommand == null || extractedCommand.trim().length() == 0){
                throw new EmptyDescriptionException();
            }

            String by = parser.extractDate(command, positionOfBy);
            if(by == null || by.trim().length() == 0){
                throw new NoDateGivenException();
            }

            Deadline newDeadline = new Deadline(extractedCommand, by);
            tasksToDo.add(newDeadline);
            acknowledgeTaskAddition(newDeadline);
        } catch(NoDateGivenException e){
            ui.showMissingDeadlineDate();
        } catch(EmptyDescriptionException e){
            ui.showMissingDeadlineDescription();
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

            String extractedCommand = parser.extractEvent(command, positionOfAt);
            if(extractedCommand == null || extractedCommand.trim().length() == 0){
                throw new EmptyDescriptionException();
            }

            String at = parser.extractDate(command, positionOfAt);
            if(at == null || at.trim().length() == 0){
                throw new NoDateGivenException();
            }

            Event newEvent = new Event(extractedCommand, at);
            tasksToDo.add(newEvent);
            acknowledgeTaskAddition(newEvent);
        } catch(NoDateGivenException e){
            ui.showMissingEventDate();
        } catch(EmptyDescriptionException e){
            ui.showMissingEventDescription();
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
        ui.acknowledgeAddition();
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
        try{
            int indexTaskDelete = parser.findIndexToDelete(command);
            Task currentTask = tasksToDo.get(indexTaskDelete-1);
            System.out.println(SEPARATING_LINE);
            ui.acknowledgeDeletion();
            printTaskStatus(currentTask);
            tasksToDo.remove(indexTaskDelete-1);
            System.out.println(SEPARATING_LINE);
            System.out.println();
        } catch(StringIndexOutOfBoundsException e){
            ui.showMissingDeletionIndex();
        } catch(NumberFormatException e){
            ui.showMissingDeletionIndex();
        } catch(IndexOutOfBoundsException e){
            ui.showDeletionOutOfBounds();
        }
        
    }

    /**
    * Finds tasks with the keyword that the user specified
    * and prints them as a list
    *
    * @param command the current command passed from user input
    */
    public void findTask(String command){
        try{
            String keyword = parser.extractKeyword(command);
            ArrayList<Task> tasksWithKeyword = new ArrayList<>();

            if(keyword == null || keyword.trim().length() == 0){
                throw new EmptyDescriptionException();
            }

            System.out.println(SEPARATING_LINE);

            for(Task t: tasksToDo){
                String taskDescription = t.getDescription();
                if(taskDescription.contains(keyword)){
                    tasksWithKeyword.add(t);
                }
            }

            if(tasksWithKeyword.size() == 0){
                ui.showNoMatchingTasks();
            } else{
                ui.showMatchingTasks();
                int currentNum = 1;

                for(Task t: tasksWithKeyword){
                    System.out.print("  " + currentNum + ". ");
                    currentNum++;
                    printTaskStatus(t);
                }
            }
        
            System.out.println(SEPARATING_LINE);
            System.out.println();
        } catch(EmptyDescriptionException e){
            ui.showMissingKeyword();
        } catch (StringIndexOutOfBoundsException e){
            ui.showMissingKeyword();
        }
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