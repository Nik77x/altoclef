package adris.altoclef.util.shapes;

import java.awt.*;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;

import org.lwjgl.system.CallbackI.V;

import baritone.api.utils.Helper;
import baritone.utils.IRenderer;
import baritone.utils.accessor.IEntityRenderManager;

public class Line implements IRenderableObject
{
//TODO set default values and create a constructor to set
// the values and an empty one to keep the defaults or change them later
    private Color _lineColor;

    private float _lineWidth;
    private float _alpha;

    private boolean _ignoreDepth;
    private Vec3d _pos1;
    private Vec3d _pos2;


    // normal setters

    public void SetColor(Color color, float alpha){
        _lineColor = color;
        _alpha = alpha;
    }

    public void setPosition(Vec3d pos1, Vec3d pos2)
    {
        _pos1 = pos1;
        _pos2 = pos2;
    }

    public void setPosition(float pos1X, float pos1Y, float pos1Z, float pos2X, float pos2Y, float pos2Z){
        _pos1 = new Vec3d(pos1X, pos1Y, pos1Z);
        _pos2 = new Vec3d(pos2X, pos2Y, pos2Z);
    }

    public void setIgnoreDepth(boolean value){
        _ignoreDepth = value;
    }

    public void setWidth(float width){
        _lineWidth = width;
    }

    // Builder methods

    public Line Color(Color color, float alpha){
        _lineColor = color;
        _alpha = alpha;
        return this;
    }

    public Line Width(float width){
        _lineWidth = width;
        return this;
    }

    public Line ignoreDepth(boolean value){
        _ignoreDepth = value;
        return this;
    }

    public Line Position(Vec3d pos1, Vec3d pos2){
        _pos1 = pos1;
        _pos2 = pos2;
        return this;
    }

    public Line Position(float pos1X, float pos1Y, float pos1Z, float pos2X, float pos2Y, float pos2Z){
        _pos1 = new Vec3d(pos1X, pos1Y, pos1Z);
        _pos2 = new Vec3d(pos2X, pos2Y, pos2Z);
        return this;
    }

    IEntityRenderManager renderManager = (IEntityRenderManager) MinecraftClient.getInstance().getEntityRenderDispatcher();

    @Override public void Render(MatrixStack stack, Tessellator tessellator, BufferBuilder buffer)
    {
        //TODO make startlines and endlines be called always
        IRenderer.startLines(_lineColor, _alpha, _lineWidth, _ignoreDepth);

        Matrix4f positionMatrix = stack.peek().getPositionMatrix();
        _pos1 = _pos1.subtract(renderManager.renderPosX(), renderManager.renderPosY(), renderManager.renderPosZ());
        _pos2 = _pos2.subtract(renderManager.renderPosX(), renderManager.renderPosY(), renderManager.renderPosZ());

        buffer.begin(DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION);
        buffer.vertex(positionMatrix, (float)_pos1.x , (float)_pos1.y, (float)_pos1.z).color(1,1,1,1).next();
        buffer.vertex(positionMatrix, (float)_pos2.x, (float)_pos2.y, (float)_pos2.z).color(1,1,1, 1).next();
        tessellator.draw();

        IRenderer.endLines(_ignoreDepth);
    }
}
