package adris.altoclef.Render.shapes;

import java.awt.*;

import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.util.math.Vec3d;

public class Box extends RenderableObject
{
    boolean _outlineOnly;

    private Vec3d _pos1;

    private Vec3d _pos2;


    public Box(Color color, float lineWidth, float alpha, boolean ignoreDepth, boolean doubleSided, Vec3d pos1, Vec3d pos2)
    {
        super(color, lineWidth, alpha, ignoreDepth, doubleSided);

        _pos1 = pos1;
        _pos2 = pos2;

        setDrawMode(DrawMode.QUADS);

        CalculateVertexPositions();

    }

    public Box(Vec3d pos1, Vec3d pos2)
    {
        super(Color.BLUE, 50, 0.7f, false, true);

        _pos1 = pos1;
        _pos2 = pos2;

        setDrawMode(DrawMode.QUADS);

        CalculateVertexPositions();

    }

    private void CalculateVertexPositions(){
        double minX = Math.min(_pos1.x, _pos2.x);
        double minY = Math.min(_pos1.y, _pos2.y);
        double minZ = Math.min(_pos1.z, _pos2.z);

        double maxX = Math.max(_pos1.x, _pos2.x);
        double maxY = Math.max(_pos1.y, _pos2.y);
        double maxZ = Math.max(_pos1.z, _pos2.z);


        //bottom quad
        Vec3d vert1 = new Vec3d(minX, minY, minZ);

        Vec3d vert2 = new Vec3d(maxX, minY, minZ);

        Vec3d vert3 = new Vec3d(maxX, minY, maxZ);

        Vec3d vert4 = new Vec3d(minX, minY, maxZ);

        //top quad

        Vec3d vertUp1 = new Vec3d(minX, maxY, minZ);

        Vec3d vertUp2 = new Vec3d(maxX, maxY, minZ);

        Vec3d vertUp3 = new Vec3d(maxX, maxY, maxZ);

        Vec3d vertUp4 = new Vec3d(minX, maxY, maxZ);

        addPositions(vert1, vert2, vert3, vert4, vertUp1, vertUp2, vertUp3, vertUp4);

    }

}
