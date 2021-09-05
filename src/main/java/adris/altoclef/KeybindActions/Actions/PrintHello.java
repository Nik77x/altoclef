package adris.altoclef.KeybindActions.Actions;

import org.lwjgl.glfw.GLFW;

import adris.altoclef.AltoClef;
import adris.altoclef.KeybindActions.KeybindingsSystem.KeyAction;

public class PrintHello extends KeyAction
{

    public PrintHello()
    {
        super("key.AltoClef.PrintHello", GLFW.GLFW_KEY_K, "category.AltoClef.Utils");
    }



    @Override public void execute(AltoClef mod)
    {
        mod.getPlayer().sendChatMessage("WORKS!!!");
    }

}
