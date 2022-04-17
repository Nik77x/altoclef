package adris.altoclef.KeybindActions.Actions;

import adris.altoclef.KeybindActions.KeybindingsSystem.KeybindSettings;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.InputUtil.Type;

import org.lwjgl.glfw.GLFW;

import adris.altoclef.AltoClef;
import adris.altoclef.KeybindActions.KeybindingsSystem.KeyAction;

public class PrintHello extends KeyAction
{

    public PrintHello()
    {
        super(new KeybindSettings("Utils", "PrintHello", Type.KEYSYM, GLFW.GLFW_KEY_K));
    }



    @Override public void execute(AltoClef mod)
    {
        mod.getPlayer().sendChatMessage("Hello!");
    }

}
