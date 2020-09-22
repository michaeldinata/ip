package parser;

public class Parser {
    private static final int TODO_LEN = 5;
    private static final int EVENT_LEN = 6;
    private static final int DEADLINE_LEN = 9;
    private static final int DONE_LEN = 5;
    private static final int DELETE_LEN = 7;
    private static final int FIND_LEN = 5;

    public Parser(){

    }

    /**
    * extracts the index of the task completed
    *
    * @param command the current command passed from user input
    * @return index of task completed
    */
    public int findIndexOfTaskDone(String command){
        return Integer.parseInt(command.substring(DONE_LEN, command.length()));
    }

    /**
    * extracts the description of a To Do task
    *
    * @param command the current command passed from user input
    * @return description of To Do task
    */
    public String extractToDo(String command){
        return command.substring(TODO_LEN, command.length());
    }

    /**
    * extracts the description of a Deadline task
    *
    * @param command the current command passed from user input
    * @param positionOfBy position of "by" in the command
    * @return description of Deadline task
    */
    public String extractDeadline(String command, int positionOfBy){
        return command.substring(DEADLINE_LEN, positionOfBy-1);
    }

    /**
    * extracts the date of a Deadline or Event task
    *
    * @param command the current command passed from user input
    * @param positionOfDate position of "at" or "by" in the command
    * @return the date/time of the deadline or event
    */
    public String extractDate(String command, int positionOfDate){
        return command.substring(positionOfDate + 4, command.length());
    }

    /**
    * extracts the description of a Event task
    *
    * @param command the current command passed from user input
    * @param positionOfAt position of "at" in the command
    * @return description of Event task
    */
    public String extractEvent(String command, int positionOfAt){
        return command.substring(EVENT_LEN, positionOfAt-1);
    }

    /**
    * extracts the index of the task to be deleted
    *
    * @param command the current command passed from user input
    * @return index of task to be deleted
    */
    public int findIndexToDelete(String command){
        return Integer.parseInt(command.substring(DELETE_LEN, command.length()));
    }

    public String extractKeyword(String command){
        return command.substring(FIND_LEN, command.length());
    }
}
