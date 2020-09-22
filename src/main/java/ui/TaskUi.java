package ui;

public class TaskUi extends Ui{
    private static final String SEPARATING_LINE = "  ________________________________________";
    
    public void showLeftoverTask(){
        System.out.println("Here is the list that you left off with: ");
    }

    public void showCurrentTaskCount(int currentTaskCount){
        System.out.println("    You currently have " + currentTaskCount 
                            + " items to do in your list, master...");
    }

    public void showMarkTaskAsDone(){
        System.out.println("    Nice! I've marked this task as done: ");
    }

    public void showMissingToDoDescription(){
        System.out.println();
        System.out.println("Sorry, but you need to describe what to do, master");
    }

    public void showMissingDeadlineDate(){
        System.out.println();
        System.out.println("You need to tell me when this task is due, master");
    }

    public void showMissingDeadlineDescription(){
        System.out.println();
        System.out.println("Sorry, but you need to describe what deadline this is, master");
    }

    public void showMissingEventDate(){
        System.out.println();
        System.out.println("You need to tell me when this event is at, master");
    }

    public void showMissingEventDescription(){
        System.out.println();
        System.out.println("Sorry, but you need to describe what event this is, master");
    }

    public void acknowledgeAddition(){
        System.out.println("    Understood. I've added this into the list.. ");
    }

    public void acknowledgeDeletion(){
        System.out.println("Noted. I have deleted this task:");
    }
    
}
