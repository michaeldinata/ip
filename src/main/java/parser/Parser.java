package parser;

public class Parser {
    private static final int TODO_LEN = 5;
    private static final int EVENT_LEN = 6;
    private static final int DEADLINE_LEN = 9;
    private static final int DONE_LEN = 5;
    private static final int DELETE_LEN = 7;

    public Parser(){
        
    }

    public int findIndexOfTaskDone(String command){
        return Integer.parseInt(command.substring(DONE_LEN, command.length()));
    }

    public String extractToDo(String command){
        return command.substring(TODO_LEN, command.length());
    }

    public String extractDeadline(String command, int positionOfBy){
        return command.substring(DEADLINE_LEN, positionOfBy-1);
    }

    public String extractDate(String command, int positionOfDate){
        return command.substring(positionOfDate + 4, command.length());
    }

    public String extractEvent(String command, int positionOfAt){
        return command.substring(EVENT_LEN, positionOfAt-1);
    }

    public int findIndexToDelete(String command){
        return Integer.parseInt(command.substring(DELETE_LEN, command.length()));
    }
}
