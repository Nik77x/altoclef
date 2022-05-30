package adris.altoclef.Test;

import java.awt.*;

import adris.altoclef.AltoClef;
import adris.altoclef.tasksystem.Task;
import adris.altoclef.Render.shapes.Line;

public class RenderTest extends Task
{
    @Override protected void onStart(AltoClef mod)
    {
        mod.log("Started Rendering!");
    }

    @Override protected Task onTick(AltoClef mod)
    {
        Line line = new Line();
        line.setColor(Color.BLUE, 0.7f);
        line.setWidth(200);

        mod.getRenderer().RenderObject(line);

        return null;
    }

    @Override protected void onStop(AltoClef mod, Task interruptTask)
    {

    }

    @Override protected boolean isEqual(Task other)
    {
        return other instanceof RenderTest;
    }

    @Override protected String toDebugString()
    {
        return "Render test";
    }
}
