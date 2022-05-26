package adris.altoclef.Test;

import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.math.Direction;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.systems.RenderSystem;

import adris.altoclef.AltoClef;
import adris.altoclef.tasksystem.Task;

public class RenderTest extends Task
{
    @Override protected void onStart(AltoClef mod)
    {
        mod.log("Started Rendering!");
    }

    @Override protected Task onTick(AltoClef mod)
    {



        RenderSystem.disableTexture();
        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(515);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(1f,0f,-3f).color(255,0,0,255).next();
        bufferBuilder.vertex(1f,3f,-3f).color(255,0,0,255).next();
        tessellator.draw();

        RenderSystem.enableCull();
        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableTexture();


        return null;
    }

    @Override protected void onStop(AltoClef mod, Task interruptTask)
    {

    }

    @Override protected boolean isEqual(Task other)
    {
        return false;
    }

    @Override protected String toDebugString()
    {
        return null;
    }
}
