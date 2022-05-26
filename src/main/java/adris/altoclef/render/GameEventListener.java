package adris.altoclef.render;

import net.minecraft.util.math.Box;

import adris.altoclef.AltoClef;
import baritone.api.event.events.BlockInteractEvent;
import baritone.api.event.events.ChatEvent;
import baritone.api.event.events.ChunkEvent;
import baritone.api.event.events.PacketEvent;
import baritone.api.event.events.PathEvent;
import baritone.api.event.events.PlayerUpdateEvent;
import baritone.api.event.events.RenderEvent;
import baritone.api.event.events.RotationMoveEvent;
import baritone.api.event.events.SprintStateEvent;
import baritone.api.event.events.TabCompleteEvent;
import baritone.api.event.events.TickEvent;
import baritone.api.event.events.WorldEvent;
import baritone.api.event.listener.IGameEventListener;
import baritone.utils.IRenderer;

public class GameEventListener implements IGameEventListener
{

    private AltoClef _mod;



    public GameEventListener(AltoClef mod){
        _mod = mod;
    }

    @Override public void onTick(TickEvent tickEvent)
    {

    }

    @Override public void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent)
    {

    }

    @Override public void onSendChatMessage(ChatEvent chatEvent)
    {

    }

    @Override public void onPreTabComplete(TabCompleteEvent tabCompleteEvent)
    {

    }

    @Override public void onChunkEvent(ChunkEvent chunkEvent)
    {

    }
    int counter;
    @Override public void onRenderPass(RenderEvent renderEvent)
    {
        if(counter >= 10){
            _mod.log("rndr");
        }else{
            counter++;
        }

        IRenderer.drawAABB(renderEvent.getModelViewStack(), new Box(0,0,0, 10,10,10));
    }

    @Override public void onWorldEvent(WorldEvent worldEvent)
    {

    }

    @Override public void onSendPacket(PacketEvent packetEvent)
    {

    }

    @Override public void onReceivePacket(PacketEvent packetEvent)
    {

    }

    @Override public void onPlayerRotationMove(RotationMoveEvent rotationMoveEvent)
    {

    }

    @Override public void onPlayerSprintState(SprintStateEvent sprintStateEvent)
    {

    }

    @Override public void onBlockInteract(BlockInteractEvent blockInteractEvent)
    {

    }

    @Override public void onPlayerDeath()
    {

    }

    @Override public void onPathEvent(PathEvent pathEvent)
    {

    }
}
