package adris.altoclef.Render.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;

import com.mojang.blaze3d.systems.RenderSystem;

import baritone.utils.accessor.IEntityRenderManager;

public class RenderableObject
{

    protected Color _lineColor;

    protected float _lineWidth;

    protected float _alpha;

    protected boolean _ignoreDepth;

    protected boolean _doubleSided;

    protected ArrayList<Vec3d> _positions = new ArrayList<>();

    protected Tessellator tessellator;

    protected BufferBuilder buffer;

    IEntityRenderManager renderManager;

    private boolean _isRendered = false;

    public RenderableObject(Color color, float lineWidth, float alpha, boolean ignoreDepth, boolean doubleSided)
    {
        _lineColor = color;
        _lineWidth = lineWidth;
        _alpha = alpha;
        _ignoreDepth = ignoreDepth;
        _doubleSided = doubleSided;

        tessellator = Tessellator.getInstance();
        buffer = tessellator.getBuffer();
        renderManager = (IEntityRenderManager) MinecraftClient.getInstance().getEntityRenderDispatcher();

    }

    public RenderableObject(Color color, float lineWidth, float alpha, boolean ignoreDepth, boolean doubleSided, Vec3d... positions)
    {

        this(color, lineWidth, alpha, ignoreDepth, doubleSided);

        _positions.addAll(List.of(positions));
    }

    public void Render(MatrixStack stack)
    {

        Matrix4f positionMatrix = stack.peek().getPositionMatrix();

        Vec3d renderPositions = new Vec3d(renderManager.renderPosX(), renderManager.renderPosY(), renderManager.renderPosZ());

        float[] colors = _lineColor.getColorComponents(null);

        buffer.begin(DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);

        for (Vec3d position : _positions)
        {
            // transform the position
            Vec3d newPos = position.subtract(renderPositions);

            // add the vertexes to render
            buffer.vertex(positionMatrix, (float) newPos.x, (float) newPos.y, (float) newPos.z)
                    .color(colors[0], colors[1], colors[2], _alpha).next();

        }

        tessellator.draw();

    }

    public void StartRender(MatrixStack stack)
    {

        PreRender();

        Render(stack);

        PostRender();
    }

    private void PreRender()
    {
        // some stuff to do before rendering

        RenderSystem.enableBlend();

        // magic numbers taken from baritone (IRenderer)
        RenderSystem.blendFuncSeparate(770, 771, 1, 0);
        RenderSystem.lineWidth(_lineWidth);
        RenderSystem.disableTexture();
        RenderSystem.depthMask(false);
        if (_ignoreDepth)
            RenderSystem.disableDepthTest();
        if (_doubleSided)
            RenderSystem.disableCull();
    }

    private void PostRender()
    {
        RenderSystem.depthMask(true);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();

        _isRendered = true;
    }

    public void setColor(Color color, float alpha)
    {
        _lineColor = color;
        _alpha = alpha;
    }

    public void setIgnoreDepth(boolean value)
    {
        _ignoreDepth = value;
    }

    public void setWidth(float width)
    {
        _lineWidth = width;
    }

    public boolean isRendered()
    {
        return _isRendered;
    }

    protected void addPosition(Vec3d position)
    {
        _positions.add(position);
    }

    protected void addPositions(Vec3d... positions)
    {

        _positions.addAll(List.of(positions));

    }

    protected void setPosition(int positionIndex, Vec3d position)
    {
        _positions.set(positionIndex, position);
    }

}
