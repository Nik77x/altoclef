package adris.altoclef;

import adris.altoclef.KeybindActions.Actions.GotoBlockUnderCursor;
import adris.altoclef.KeybindActions.Actions.PrintHello;
import adris.altoclef.KeybindActions.KeybindingsSystem.KeyAction;
import adris.altoclef.KeybindActions.KeybindingsSystem.KeybindingsList;

public class AltoClefKeybindings extends KeybindingsList
{
    public AltoClefKeybindings(AltoClef mod, KeyAction... keybindingActions)
    {
        //all keyActions go here
        super(mod,
                new PrintHello(),
                new GotoBlockUnderCursor()

        );
    }
}
