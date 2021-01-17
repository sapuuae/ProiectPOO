package observer;

import base.WorkingDistributors;

import java.util.ArrayList;

public abstract class Observer {
    protected Subject subject;
    public abstract void update(Integer energy,
                                ArrayList<WorkingDistributors> distributorsArrayList,
                                Integer month);
}
