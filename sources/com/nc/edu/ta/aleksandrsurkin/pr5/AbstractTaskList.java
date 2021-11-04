package com.nc.edu.ta.aleksandrsurkin.pr5;

/**
 * This abstract class "AbstractTaskList".
 * This class contains abstract methods for add task, remove task, and get task.
 * This class contains one public method for the getting size of task.
 * This class contains one integer field: counter for count of tasks.
 */
public abstract class AbstractTaskList {

    int sizeCounter = 0;

    /**
     * This method is adding the not unique tasks into the tasks list.
     * @param task - input of task.
     */
    public abstract void add(Task task);

    /**
     * This method is for removing tasks out of a tasks list.
     * @param task - input of task.
     */
    public abstract void remove(Task task);

    /**
     * This method is counting quantity of tasks;
     * @return quantity of tasks;
     */
    public int size() {
        return sizeCounter;
    }

    /**
     * This method is get the tasks from their input indexes.
     * @param index - index of task from tasks list.
     * @return tasks.
     */
    public abstract Task getTask(int index);


    public abstract Task[] incoming(int from, int to);
}
