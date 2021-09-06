package adris.altoclef.KeybindActions.KeybindingsSystem;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.InputUtil.Type;

import org.lwjgl.glfw.GLFW;

import adris.altoclef.AltoClef;

public abstract class KeyAction
{
    private String _name;
    private KeyBinding keyBinding;

    public KeyAction(String keyName, InputUtil.Type mouseOrKeyboard ,int keyCode, String category)
    {

        //      "key.AltoClef.GoToLookedBlock",         // The translation key of the keybinding's name
        //        InputUtil.Type.KEYSYM,                // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
        //        GLFW.GLFW_KEY_T,                      // The keycode of the key
        //       "category.AltoClef.AltoClefUtils"));   // The translation key of the keybinding's category

        keyBinding = new KeyBinding("key.AltoClef." + keyName, mouseOrKeyboard , keyCode, "category.AltoClef."+ category );
        _name = keyName;
    }

    public abstract void execute(AltoClef mod);

    public String GetName()
    {
        return _name;
    }

    public KeyBinding GetKeybinding(){
        return keyBinding;
    }
}
