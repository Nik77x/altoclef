package adris.altoclef.Render.shapes;

import java.awt.*;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

import baritone.utils.accessor.IEntityRenderManager;

public class Line extends RenderableObject
{

    IEntityRenderManager renderManager = (IEntityRenderManager) MinecraftClient.getInstance().getEntityRenderDispatcher();

    public Line(Color color, float lineWidth, float alpha, boolean ignoreDepth, Vec3d pos1, Vec3d pos2)
    {
        super(color, lineWidth, alpha, ignoreDepth,false,  pos1, pos2);
    }

    public Line()
    {
        // default constructor
        super(Color.BLUE, 10, 1, false, false,  new Vec3d(0,0,0), new Vec3d(0,100,0));
    }

    public Line Position(Vec3d pos1, Vec3d pos2){
        setPosition(0, pos1);
        setPosition(1, pos2);
        return this;
    }

    public Line Position(float pos1X, float pos1Y, float pos1Z, float pos2X, float pos2Y, float pos2Z){
        setPosition(0, new Vec3d(pos1X, pos1Y, pos1Z));
        setPosition(1, new Vec3d(pos2X, pos2Y, pos2Z));
        return this;
    }


}
