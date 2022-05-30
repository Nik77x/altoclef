package adris.altoclef.Test;

import java.awt.*;

import net.minecraft.util.math.Vec3d;

import adris.altoclef.AltoClef;
import adris.altoclef.tasksystem.Task;
import adris.altoclef.Render.shapes.Line;
import adris.altoclef.Render.shapes.Box;

public class RenderTest extends Task
{
    @Override protected void onStart(AltoClef mod)
    {
        mod.log("Started Rendering!");
    }

    @Override protected Task onTick(AltoClef mod)
    {
        Box box = new Box(Vec3d.ZERO, new Vec3d(50, 50, 50));
        box.setColor(Color.BLUE, 0.7f);

        mod.getRenderer().RenderObject(box);

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
