package adris.altoclef.KeybindActions.Actions;

import net.minecraft.client.util.InputUtil.Type;

import org.lwjgl.glfw.GLFW;

import adris.altoclef.AltoClef;
import adris.altoclef.KeybindActions.KeybindingsSystem.KeyAction;

public class StopAll extends KeyAction
{
    public StopAll()
    {
        super("StopAll", Type.KEYSYM, GLFW.GLFW_KEY_J, "Utils");
    }

    @Override public void execute(AltoClef mod)
    {
        mod.getUserTaskChain().cancel(mod);
    }
}
