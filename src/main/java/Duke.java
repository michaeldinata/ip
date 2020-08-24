import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        echo();;
        bye();
    }

    public static void greet(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void echo(){
        Scanner in = new Scanner(System.in);

        while(true){
            String command = in.nextLine();
            if(command.compareTo("bye") == 0){
                return;
            }
            System.out.println(command);
        }
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
