package adris.altoclef.KeybindActions.Actions;

import adris.altoclef.KeybindActions.KeybindingsSystem.KeybindSettings;
import net.minecraft.client.util.InputUtil.Type;

import org.lwjgl.glfw.GLFW;

import adris.altoclef.AltoClef;
import adris.altoclef.KeybindActions.KeybindingsSystem.KeyAction;

public class StopAll extends KeyAction
{

    public StopAll()
    {
        super(new KeybindSettings("Utils", "StopAll", Type.KEYSYM, GLFW.GLFW_KEY_J));
    }

    @Override public void execute(AltoClef mod)
    {
        mod.getUserTaskChain().cancel(mod);
    }
}
