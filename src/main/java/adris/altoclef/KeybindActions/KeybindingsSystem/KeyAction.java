package adris.altoclef.KeybindActions.KeybindingsSystem;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.lwjgl.glfw.GLFW;

import adris.altoclef.AltoClef;

public abstract class KeyAction extends KeyBinding
{
    AltoClef _mod;

    public KeyAction(String translationKey, int code, String category)
    {

        //      "key.AltoClef.GoToLookedBlock",          // The translation key of the keybinding's name
        //        InputUtil.Type.KEYSYM,                // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
        //        GLFW.GLFW_KEY_T,                      // The keycode of the key
        //       "category.AltoClef.AltoClefUtils"));   // The translation key of the keybinding's category.
        super(translationKey, code, category);
    }

    public abstract void execute(AltoClef mod);
}
