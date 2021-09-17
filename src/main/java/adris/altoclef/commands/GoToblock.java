package adris.altoclef.commands;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import adris.altoclef.AltoClef;
import adris.altoclef.commandsystem.ArgBase;
import adris.altoclef.commandsystem.ArgParser;
import adris.altoclef.commandsystem.Command;
import adris.altoclef.commandsystem.CommandException;

public class GoToblock extends Command
{
    float _maxRaycastDistance;

    public GoToblock(String name, String description, ArgBase... args)
    {
        super("Test", "raycast to point and path", args);
    }

    @Override protected void Call(AltoClef mod, ArgParser parser) throws CommandException
    {
//        Vec3d playerEyepos = mod.getPlayer().getEyePos();
//        Vec3d rayEndPos = mod.getPlayer().getEyePos();
//
//        RaycastContext raycastContext = new RaycastContext(playerEyepos, playerEyepos * _maxRaycastDistance);
//
//        mod.getWorld().raycast(RaycastContext())
    }
}
