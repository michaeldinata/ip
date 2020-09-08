package tasks;

public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }

    public void markAsDone(){
        this.isDone = true;
        System.out.println("    Nice! I've marked this task as done: ");
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}