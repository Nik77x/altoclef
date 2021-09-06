package adris.altoclef.tasks;

import adris.altoclef.AltoClef;
import adris.altoclef.tasks.misc.DodgeProjectilesTask;
import adris.altoclef.tasksystem.Task;

public class AssistTask extends Task
{

    // only the default task chains are running

    @Override protected void onStart(AltoClef mod)
    {

    }

    @Override protected Task onTick(AltoClef mod)
    {

        return null;
    }

    @Override protected void onStop(AltoClef mod, Task interruptTask)
    {
        if(interruptTask == null|| !interruptTask.equals(DodgeProjectilesTask.class)){
            mod.getMobDefenseChain().ShouldForceField(true);
            mod.getMobDefenseChain().ShouldAvoidMobs(true);
            mod.getMobDefenseChain().ShouldDodgeArrows(true);
        }

    }

    @Override protected boolean isEqual(Task obj)
    {
        return obj instanceof AssistTask;
    }

    @Override protected String toDebugString()
    {
        return "Assistance mode";
    }


}
