package adris.altoclef.tasks;

import adris.altoclef.AltoClef;
import adris.altoclef.tasksystem.Task;

public class DummyTask extends Task
{
    @Override protected void onStart(AltoClef mod)
    {

    }

    @Override protected Task onTick(AltoClef mod)
    {
        return null;
    }

    @Override protected void onStop(AltoClef mod, Task interruptTask)
    {

    }

    @Override protected boolean isEqual(Task obj)
    {
        return obj instanceof DummyTask;
    }

    @Override protected String toDebugString()
    {
        return "Dummy";
    }
}
