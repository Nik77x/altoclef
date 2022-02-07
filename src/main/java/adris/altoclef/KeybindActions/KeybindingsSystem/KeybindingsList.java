package adris.altoclef.KeybindActions.KeybindingsSystem;

import adris.altoclef.AltoClef;

public class KeybindingsList
{

    public KeybindingsList(AltoClef mod, KeyAction... keyActions)
    {
        for (KeyAction k : keyActions)
        {
            mod.RegisterKeybindingAction(k);
        }

    }
}


