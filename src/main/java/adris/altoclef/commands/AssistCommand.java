package adris.altoclef.commands;

import java.util.Locale;

import adris.altoclef.AltoClef;
import adris.altoclef.Debug;
import adris.altoclef.commandsystem.Arg;
import adris.altoclef.commandsystem.ArgParser;
import adris.altoclef.commandsystem.Command;
import adris.altoclef.commandsystem.CommandException;
import adris.altoclef.tasks.AssistTask;

public class AssistCommand extends Command
{
    public AssistCommand() throws CommandException
    {
        super("AssistMode", "Starts assistance mode, basically MLG buckets for you and other useful stuff",
                new Arg(String.class, "shouldDodgeArrows", "", 0)
        );
    }

    @Override protected void call(AltoClef mod, ArgParser parser) throws CommandException
    {
        String _shouldDodgeArrows = parser.get(String.class);
        boolean _dodge = true;

        switch (_shouldDodgeArrows.toLowerCase(Locale.ROOT)){
        case "":
            break;

        case "nododge":
            Debug.logMessage("Ok, no dodging.");
            _dodge = false;
            break;


        default:
            Debug.logWarning("Invalid argument");
            return;
        }

        mod.getMobDefenseChain().ShouldForceField(false);
        mod.getMobDefenseChain().ShouldAvoidMobs(false);
        mod.getMobDefenseChain().ShouldDodgeArrows(_dodge);

        mod.runUserTask(new AssistTask());


    }


}
