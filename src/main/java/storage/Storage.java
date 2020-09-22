package storage;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

public class Storage{
    private String filePath;
    private String fileFolder;
    private static ArrayList<Task> tasksToDo = new ArrayList<>();
    private static final String LINE_DOWN = "|";

    public Storage(String TASK_FOLDER, String TASK_FILE){
        this.fileFolder = TASK_FOLDER;
        this.filePath = TASK_FILE;
    }

    /**
    * Reads the saved task file and creates a
    * file in the directory if it does not exist
    */
    public ArrayList<Task> loadFile() throws IOException{
        try{
            Files.createDirectories(Paths.get(fileFolder));
            Files.createFile(Paths.get(filePath));
        } catch(FileAlreadyExistsException e){
            // We do not need to add new file
        }
        
        File f = new File(filePath);

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
        fileInput.close();
        return tasksToDo;
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
    * Converts the list into our file formatting
    *
    * @return a string to list the existing tasks
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
    * Writes the converted list into our task file
    */
    public void saveFile() throws IOException{
        FileWriter fw = new FileWriter(filePath);
        String finalTasks = parseTasksToFile();
        fw.write(finalTasks);
        fw.close();
    }
}