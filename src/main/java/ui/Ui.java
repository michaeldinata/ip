package ui;

public class Ui{
    private static final String SEPARATING_LINE = "  ________________________________________";

    public Ui(){

    }

    /**
    * Tells the user that there was something wrong loading 
    * the file
    *
    */
    public void showLoadingError(){
        System.out.println("Sorry master, I am having difficulties finding" + 
                                " the tasks you left off with");
    }

    /**
    * Greets the user 
    *
    */
    public void greet(){
        System.out.println(SEPARATING_LINE);
        System.out.println("Welcome back, master");
        System.out.println("Your command is my command...");
        System.out.println(SEPARATING_LINE);
        System.out.println();
    }

    /**
    * Requests for new command from the user
    *
    */
    public void requestNewCommand(){
        System.out.println("Please give me your command");
    }

    /**
    * Tells the user that he did not give any command
    *
    */
    public void showEmptyCommandError(){
        System.out.println();
        System.out.println("You did not give me any command");
    }

    /**
    * Tells the user that the command given was invalid
    *
    */
    public void showInvalidCommandError(){
        System.out.println();
        System.out.println("Please give me a valid command, master...");
    }

    /**
    * Tells the user that the file was not saved properly
    *
    */
    public void showFileUnsavedError(){
        System.out.println("Your file was not saved properly");
    }

    /**
    * Bids the user goodbye
    *
    */
    public void bye(){
        System.out.println("Have a safe journey");
    }
}