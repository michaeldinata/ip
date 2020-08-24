import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        GetCommand();
        bye();
    }

    public static void greet(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void GetCommand(){
        Vector<String> ListToDo = new Vector<String>();
        Scanner in = new Scanner(System.in);
        int currentCommandNumber = 0;

        while(true){
            String command = in.nextLine();
            if(command.compareTo("bye") == 0){
                return;
            }
            else if(command.compareTo("list") == 0){
                list(ListToDo);
            }
            else{
                ListToDo.add(command);
                System.out.println("Added: " + command);
            }
        }
    }

    public static void list(Vector ListToDo){
        for(int i = 0; i < ListToDo.size(); i++){
            System.out.println((i+1) + ". " + ListToDo.get(i));
        }
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
