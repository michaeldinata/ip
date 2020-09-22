package ui;

public class TaskUi extends Ui{
    
    /**
    * Prints the leftover task from previous use
    *
    */
    public void showLeftoverTask(){
        System.out.println("Here is the list that you left off with: ");
    }

    /**
    * Prints the current task count
    *
    * @param currentTaskCount current number of tasks in the task list
    */
    public void showCurrentTaskCount(int currentTaskCount){
        System.out.println("    You currently have " + currentTaskCount 
                            + " items to do in your list, master...");
    }

    /**
    * Prints out a line to tell the user that the task is done
    *
    */
    public void showMarkTaskAsDone(){
        System.out.println("    Nice! I've marked this task as done: ");
    }

    /**
    * Prints out a line to tell the user that the 
    * To Do description is missing 
    *
    */
    public void showMissingToDoDescription(){
        System.out.println();
        System.out.println("Sorry, but you need to describe what to do, master");
    }

    /**
    * Prints out a line to tell the user that the 
    * date of Deadline task is missing 
    *
    */
    public void showMissingDeadlineDate(){
        System.out.println();
        System.out.println("You need to tell me when this task is due, master");
    }

    /**
    * Prints out a line to tell the user that the 
    * description of Deadline task is missing 
    *
    */
    public void showMissingDeadlineDescription(){
        System.out.println();
        System.out.println("Sorry, but you need to describe what deadline this is, master");
    }

    /**
    * Prints out a line to tell the user that the 
    * date of Event task is missing 
    *
    */
    public void showMissingEventDate(){
        System.out.println();
        System.out.println("You need to tell me when this event is at, master");
    }

    /**
    * Prints out a line to tell the user that the 
    * description of Event task is missing 
    *
    */
    public void showMissingEventDescription(){
        System.out.println();
        System.out.println("Sorry, but you need to describe what event this is, master");
    }

    /**
    * Prints out a line to tell the user that the 
    * new task has been added into the list
    *
    */
    public void acknowledgeAddition(){
        System.out.println("    Understood. I've added this into the list.. ");
    }

    /**
    * Prints out a line to tell the user that the 
    * task has been deleted from the list
    *
    */
    public void acknowledgeDeletion(){
        System.out.println("Noted. I have deleted this task:");
    }
    
}
