package com.nc.edu.ta.aleksandrsurkin.pr5;

/**
 * This class "ArrayTaskList" extends class "AbstractTaskList" and
 * realise methods of abstract class "AbstractTaskList".
 */
public class ArrayTaskList extends AbstractTaskList {

    Task [] tasks = new Task[10];
    private int arrayCount;
    final int ARRAY_BUFFER_INC = 10;
    static private int numberOfTasksListsCounter;
    final String THEME_OF_TITLE = "[EDUCTR][TA]";

    /**
     * This method is set values for new tasks list.
     */
    public ArrayTaskList() {
        this.arrayCount = 0;
        numberOfTasksListsCounter++;
    }

    /**
     * This method is realise actions of method "abstract void add(Task task)" from class "AbstractTaskList",
     * adding theme to tasks title and counts the number of incoming tasks.
     * @param task - input of task.
     */
    @Override
    public void add(Task task) {

        if (task == null) {
            throw new NullPointerException("The <task> can not be Null");
        }

        if (arrayCount >= tasks.length) {
            Task[] middleMassive = new Task[tasks.length + ARRAY_BUFFER_INC];
            for (int i = 0; i < tasks.length; i++) {
                middleMassive[i] = tasks[i];
            }
            tasks = middleMassive;
        }

        String taskTitle = task.getTitle();
        task.setTitle(THEME_OF_TITLE + taskTitle);

        tasks[arrayCount++] = task;

        sizeCounter++;
    }

    /**
     * This method is realise actions of method "abstract void remove(Task task)" from class "AbstractTaskList".
     * @param task - input of task.
     */
    @Override
    public void remove(Task task) {

        if (task == null) {
            throw new NullPointerException("The <task> can not be Null");
        }

        int localRemove = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == task) {
                localRemove = i;
                break;
            }
        }
        for (int i = localRemove; i < tasks.length - 1; ++i) {
            tasks[i] = tasks[i + 1];
        }
        sizeCounter--;
    }

    /**
     * This method is realise actions of method "abstract Task getTask(int index)" from
     * class "AbstractTaskList".
     * @param index - index of task from tasks list.
     * @return task or null.
     */
    @Override
    public Task getTask(int index) {

        if (index < 0) {
            throw new IllegalArgumentException ("The <time> can not be a negative, try to add a positive value");
        }
        if (size() < 0) {
            throw new IllegalArgumentException ("The <size()> can not be a negative, try to add a positive value");
        }
        if (index >= size()) {
            throw new IllegalArgumentException("The <index> can not be more or equal than <size()>");
        }

        if (index < 0 && index > size()) {
            return null;
        } else {
            return tasks[index];
        }
    }

    /**
     * This method return a list with tasks which time is into "from" and "to".
     * @param from - start time of the time range.
     * @param to - end time of the time range.
     * @return list with found tasks.
     */
    public Task[] incoming(int from, int to) {

        if (from < 0) {
            throw new IllegalArgumentException ("The <from> can not be a negative, try to add a positive value");
        }
        if (to < 0) {
            throw new IllegalArgumentException ("The <to> can not be a negative, try to add a positive value");
        }
        if (to < from) {
            throw new IllegalArgumentException ("The <to> can not be less than <from>");
        }

        Task [] incTasks = new Task [arrayCount];
        Task [] emptyMassive = new Task [0];
        int incTaskCounter = 0;
        for (Task task : tasks) {
            if (task != null) {
                if (task.nextTimeAfter(from) > from && task.nextTimeAfter(from) <= to) {
                    if (task.isActive()) {
                        incTasks[incTaskCounter++] = task;
                    }
                }
            }
        }
        if (incTaskCounter == 0) {
            incTasks = emptyMassive;
            return incTasks;
        } else {
            Task [] countedMassive = new Task [incTaskCounter];
            for (int i = 0; i < countedMassive.length; i++) {
                if (incTasks[i] != null) {
                    countedMassive[i] = incTasks[i];
                }
            }
            return countedMassive;
        }
    }
}
