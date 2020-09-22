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

    public void showMarkDoneOutOfBounds(){
        System.out.println("You have to specify a valid task index to mark as done, master");
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

    public void showDeletionOutOfBounds(){
        System.out.println("You have to specify a valid task index to delete, master");
    }

    public void showMissingKeyword(){
        System.out.println("Sorry, but you need to describe what task you are looking for, master");
    }

    public void showMatchingTasks(){
        System.out.println("Here are the tasks that match what you are looking for, master");
    }

    public void showNoMatchingTasks(){
        System.out.println("There are no matching tasks in your list, master...");
    }

    public void showMissingDeletionIndex(){
        System.out.println("You need to specify the index number to delete, master");
    }

    public void showMissingCompletedIndex(){
        System.out.println("You need to specify the index number of the task completed, master");
    }
    
}
