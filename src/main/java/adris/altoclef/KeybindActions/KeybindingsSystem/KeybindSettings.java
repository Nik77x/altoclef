package adris.altoclef.KeybindActions.KeybindingsSystem;

import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeybindSettings {

    public  KeybindSettings(String Category, String KeybindName, InputUtil.Type InputType, int KeyCode) {

        _category = Category;
        _keybindName = KeybindName;
        _InputType = InputType;
        _KeyCode = KeyCode;

    }

    public KeybindSettings(){}


    public String _category = "Misc";

    public String _keybindName = "NONE";

    public InputUtil.Type _InputType = InputUtil.Type.KEYSYM;

    public int _KeyCode = GLFW.GLFW_KEY_E;


}
