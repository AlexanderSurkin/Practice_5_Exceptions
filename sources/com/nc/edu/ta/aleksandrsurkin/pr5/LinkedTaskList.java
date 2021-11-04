package com.nc.edu.ta.aleksandrsurkin.pr5;

/**
 * This class "LinkedTaskList" extends class "AbstractTaskList" and
 * realise methods of abstract class "AbstractTaskList".
 */
public class LinkedTaskList extends AbstractTaskList {

    private Node head;
    final String THEME_OF_TITLE_LINKED_LIST = "[EDUCTR][TA]";
    static private int numberOfLinkedTasksListsCounter;

    /**
     * This method is set values for new linked tasks list.
     */
    public LinkedTaskList() {
        head = null;
        numberOfLinkedTasksListsCounter++;
    }

    /**
     * This class is create fields for create linked tasks list.
     */
    public class Node {
        public Task task;
        public Node next;

        /**
         * This method is set input value into field "task" and set "null" into next node.
         * @param task set value into field "task"
         */
        public Node(Task task) {
            this.task = task;
            next = null;
        }
    }

    /**
     * This method is realise actions of method "abstract void add(Task task)" from class "LinkedTaskList",
     * adding theme to tasks title and counts the number of incoming tasks.
     * @param task input of task.
     */
    @Override
    public void add(Task task) {

        if (task == null) {
            throw new NullPointerException("The <task> can not be Null");
        }

        Node newNode = new Node(task);
        Node currentNode = head;

        String taskTitle = task.getTitle();
        task.setTitle(THEME_OF_TITLE_LINKED_LIST + taskTitle);

        if (head == null) {
            head = newNode;

        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        sizeCounter++;
    }

    /**
     * This method is realise actions of method "abstract void remove(Task task)" from class "LinkedTaskList".
     * @param task - input of task.
     */
    @Override
    public void remove(Task task) {

        if (task == null) {
            throw new NullPointerException("The <task> can not be Null");
        }


        Node currentNode = head;
        Node previousNode = null;

        while (currentNode.next != null) {

            if (currentNode.task == task) {
                if (currentNode == head) {
                    head = currentNode.next;
                } else {
                    previousNode.next = currentNode.next;
                }
            }

            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        sizeCounter--;
    }

    /**
     * This method is realise actions of method "abstract Task getTask(int index)" from
     * class "LinkedTaskList".
     * @param index index of task from tasks list.
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


        if (index < size()) {
            Node currentNode = head;
            for (int i = 0; i < size(); i++) {
                if (i == index) {
                    break;
                }
                currentNode = currentNode.next;
            }
            return currentNode.task;
        } else {
            return null;
        }
    }

    /**
     * This method returns a list with tasks which time is into "from" and "to".
     * @param from start time of the time range.
     * @param to end time of the time range.
     * @return list with found tasks.
     */
    @Override
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

        Task[] incTasks = new Task [sizeCounter];
        Task[] emptyMassive = new Task [0];
        int incTaskCounter = 0;
        Node currentNode = head;
        for (int i = 0; i < sizeCounter; i++) {
            if (currentNode.task.nextTimeAfter(from) > from && currentNode.task.nextTimeAfter(from) <= to) {
                 if (incTaskCounter == 0) {
                     incTasks[0] = currentNode.task;
                     incTaskCounter = 1;
                     currentNode = currentNode.next;
                 } else {
                     incTasks[incTaskCounter++] = currentNode.task;
                     currentNode = currentNode.next;
                 }
            }
            else {
                currentNode = currentNode.next;
            }
        }
        if (incTaskCounter == 0) {
            incTasks = emptyMassive;
            return incTasks;
        } else {
            Task[] countedMassive = new Task [incTaskCounter];
            for (int i = 0; i < countedMassive.length; i++) {
                if (incTasks[i] != null) {
                    countedMassive[i] = incTasks[i];
                }
            }
            return countedMassive;
        }
    }
}
