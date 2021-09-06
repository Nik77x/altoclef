package adris.altoclef.KeybindActions.Actions;

import net.minecraft.client.util.InputUtil.Type;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import org.lwjgl.glfw.GLFW;

import adris.altoclef.AltoClef;
import adris.altoclef.Debug;
import adris.altoclef.KeybindActions.KeybindingsSystem.KeyAction;
import adris.altoclef.tasks.GetToBlockTask;
import adris.altoclef.util.LookUtil;

public class GotoLookedPoint extends KeyAction
{

    public GotoLookedPoint()
    {
        super("GotoPoint", Type.KEYSYM, GLFW.GLFW_KEY_B, "Utils");
    }

    @Override public void execute(AltoClef mod)
    {


        Vec3d traceEnd = mod.getPlayer().getRotationVector().multiply(1000).add(mod.getPlayer().getPos());
        BlockHitResult hitResult = LookUtil.raycast(mod.getPlayer(), traceEnd, 1000);
        if(hitResult.getType() == HitResult.Type.BLOCK){
            Debug.logMessage("Going to block!");
            BlockPos finalPos = hitResult.getBlockPos().up();
            mod.runUserTask(new GetToBlockTask(finalPos), something ->{
                mod.getMobDefenseChain();
            });
        }else if(hitResult.getType() == HitResult.Type.MISS){
            Debug.logMessage("You're not looking at anything or block is too far");
        }



    }
}
