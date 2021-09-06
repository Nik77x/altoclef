package adris.altoclef;

import java.util.Collections;
import java.util.List;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;

import adris.altoclef.KeybindActions.KeybindingsSystem.KeyAction;

public class AltoClefClient implements ClientModInitializer
{
    //all keybindActions
    private List<KeyAction> keyActions;

    @Override public void onInitializeClient()
    {
        initializeKeybindings();

        // keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        //       "key.AltoClef.GoToLookedBlock", // The translation key of the keybinding's name
        //     InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
        //   GLFW.GLFW_KEY_T, // The keycode of the key
        // "category.AltoClef.AltoClefUtils")); // The translation key of the keybinding's category.
        //
        //        ClientTickEvents.END_CLIENT_TICK.register(client -> {
        //            while (keyBinding.wasPressed()) {
        //                client.player.sendMessage(new LiteralText("Key 1 was pressed!"), false);
        //            }
        //        });
    }

    private void initializeKeybindings(){
        for (KeyAction keyAction : keyActions)
        {
            KeyBinding key = KeyBindingHelper.registerKeyBinding(keyAction.GetKeybinding());

            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                while (key.wasPressed()){
                    //keyAction.execute();

                }
            });
        }

    }

    public void RegisterKeybindingAction(KeyAction...keyActions){
        Collections.addAll(this.keyActions, keyActions);
    }
}
