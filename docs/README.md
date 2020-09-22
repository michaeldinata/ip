# User Guide

## Features 

### Start the application
The application starts and shows the lists of tasks that the user left off with, and then continues to run the application

### Add "To Do" task
Adds a "To Do" task into the list of tasks

### Add "Deadline" task
Adds a "Deadline" task into the list of tasks

### Add "Event" task
Adds a "Event" task into the list of tasks

### List out all tasks
Lists out all current existing tasks

### Mark task as done
Marks a specified task as done

### Delete task
Deletes a specified task from the list

### Find tasks
Finds and lists out tasks based on a certain description specified by the user

### Exit the application
Quits the application and saves the current task to be used for the next time the user reopens the application

## Usage

### `todo (description)` 

This command takes in a description for a "To Do" task and adds it into the current existing task list
If "description" is not in the command, then the program will tell the user to input a valid description

Example of usage: 

`todo CS2113 Project`

Expected outcome:

`Understood. I've added this into the list..`

`[T][✘] CS2113 Project`

______________________________________________________________________________________________________________________________________

### `deadline (action) /by (date/time)` 

This command takes in a description and date/time for a "Deadline" task and adds it into the current existing task list
If "description" is not in the command, then the program will tell the user to input a valid description
If "date/time" is not in the command, then the program will tell the user to input a valid time

Example of usage: 

`deadline CS2113 Project /by end of this month`

Expected outcome:

`Understood. I've added this into the list..`

`[D][✘] CS2113 Project (by: end of this month)`

______________________________________________________________________________________________________________________________________

### `event (action) /at (date/time)` 

This command takes in a description and date/time for a "Deadline" task and adds it into the current existing task list
If "description" is not in the command, then the program will tell the user to input a valid description
If "date/time" is not in the command, then the program will tell the user to input a valid time

Example of usage: 

`event wedding /at tomorrow 2359`

Expected outcome:

`Understood. I've added this into the list..` 

`[E][✘] wedding (at: tomorrow 2359)`

______________________________________________________________________________________________________________________________________

### `list`

This command will list out all the existing tasks in the list, in the order that they were added into the list

Example of usage:

`list`

Expected outcome:

`You currently have 4 items to do in your list, master...`

`1.     [T][✘] CS2113 Project`

`2.     [E][✘] wedding (at: tomorrow 2359)`

______________________________________________________________________________________________________________________________________

### `done (taskIndex)`

Command takes in the index of a task as shown in 'list' command, and marks that task as done

Example of usage:

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`

`[E][✓] wedding (at: tomorrow 2359)`

______________________________________________________________________________________________________________________________________

### `delete (taskIndex)`

Command takes in the index of a task as shown in 'list' command, and deletes that task from the list

Example of usage:

`delete 2`

Expected outcome:

`Noted. I have deleted this task:`

`[E][✓] wedding (at: tomorrow 2359)`

______________________________________________________________________________________________________________________________________

### `find (description)`

Command takes in a description and searches through the whole list of tasks, and returns the tasks that matches with the description given by the user

Example of usage:

`find Project`

Expected outcome:

`Here are the tasks that match what you are looking for, master`

`1.     [T][✘] CS2113 Project`

______________________________________________________________________________________________________________________________________

 ### `bye`

 This command will save the current task into a file and exits the application

 Example of usage:

 `bye`

 Expected outcome:

`Have a safe journey`