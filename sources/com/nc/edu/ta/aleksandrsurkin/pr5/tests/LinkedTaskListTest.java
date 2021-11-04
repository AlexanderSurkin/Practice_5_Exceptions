package com.nc.edu.ta.aleksandrsurkin.pr5.tests;

import org.junit.Before;
import com.nc.edu.ta.aleksandrsurkin.pr5.*;

public class LinkedTaskListTest extends TaskListTest {
    @Before 
    public void createTaskList() {
        tasks = new LinkedTaskList();
    }
}

