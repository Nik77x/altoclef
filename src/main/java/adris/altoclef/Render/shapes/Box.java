package adris.altoclef.Render.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

import com.mojang.blaze3d.systems.RenderSystem;

public class Box extends RenderableObject
{
    boolean _outlineOnly;

    private Vec3d _pos1;

    private Vec3d _pos2;

    private ArrayList<Vec3d> _lineSidePositions = new ArrayList<>();

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

        setDrawMode(DrawMode.DEBUG_LINES);

     //   CalculateVertexPositions();

    }

    @Override
    public void Render(MatrixStack stack){

        Matrix4f positionMatrix = stack.peek().getPositionMatrix();

        Vec3d renderPositions = new Vec3d(renderManager.renderPosX(), renderManager.renderPosY(), renderManager.renderPosZ());

        float[] colors = _lineColor.getColorComponents(null);
        
        Vec3f newPos1 = doubleToFloat(_pos1.subtract(renderPositions));
        Vec3f newPos2 = doubleToFloat(_pos2.subtract(renderPositions));

        Vec3f maxPos = doubleToFloat(max(_pos1, _pos2).subtract(renderPositions));
        Vec3f minPos = doubleToFloat(min(_pos1, _pos2).subtract(renderPositions));
        
//        float minX = (float) Math.min(_pos1.x, _pos2.x);
//        float minY = (float) Math.min(_pos1.y, _pos2.y);
//        float minZ = (float) Math.min(_pos1.z, _pos2.z);
//        float maxX = (float) Math.max(_pos1.x, _pos2.x);
//        float maxY = (float) Math.max(_pos1.y, _pos2.y);
//        float maxZ = (float) Math.max(_pos1.z, _pos2.z);

        buffer.begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);

        buffer.vertex(positionMatrix, minPos.getX(), minPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), minPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), minPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), minPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), minPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, minPos.getX(), minPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, minPos.getX(), minPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, minPos.getX(), minPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        // top
        buffer.vertex(positionMatrix, minPos.getX(), maxPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), maxPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), maxPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), maxPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), maxPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, minPos.getX(), maxPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, minPos.getX(), maxPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, minPos.getX(), maxPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        // corners
        buffer.vertex(positionMatrix, minPos.getX(), minPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, minPos.getX(), maxPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), minPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), maxPos.getY(), minPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), minPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, maxPos.getX(), maxPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, minPos.getX(), minPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();
        buffer.vertex(positionMatrix, minPos.getX(), maxPos.getY(), maxPos.getZ()).color(colors[0], colors[1], colors[2], _alpha).next();

        tessellator.draw();

    }

    
    public Vec3d max(Vec3d vec1, Vec3d vec2){
        
        return new Vec3d(Math.max(vec1.x, vec2.x),
                Math.max(vec1.y, vec2.y),
                Math.max(vec1.z, vec2.z));
        
    }

    public Vec3d min(Vec3d vec1, Vec3d vec2){

        return new Vec3d(Math.min(vec1.x, vec2.x),
                Math.min(vec1.y, vec2.y),
                Math.min(vec1.z, vec2.z));

    }
    
    public Vec3f doubleToFloat(Vec3d vec3d){
        return new Vec3f((float) vec3d.x,
                         (float) vec3d.y,
                         (float) vec3d.z);
    }
    
    
// TODO this function is so bad OMG
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

        //right quad

        Vec3d vert1Right = new Vec3d(minX, minY, maxZ);

        Vec3d vert2Right = new Vec3d(maxX, minY, maxZ);

        Vec3d vert3Right = new Vec3d(maxX, maxY, maxZ);

        Vec3d vert4Right = new Vec3d(minX, maxY, maxZ);

        //left quad

        Vec3d vert1Left = new Vec3d(minX, minY, minZ);

        Vec3d vert2Left = new Vec3d(maxX, minY, minZ);

        Vec3d vert3Left = new Vec3d(maxX, maxY, minZ);

        Vec3d vert4Left = new Vec3d(minX, maxY, minZ);

        //front quad

        Vec3d vert1Front = new Vec3d(maxX, minY, minZ);
        Vec3d vert2Front = new Vec3d(maxX, minY, maxZ);
        Vec3d vert3Front = new Vec3d(maxX, maxY, maxZ);
        Vec3d vert4Front = new Vec3d(maxX, maxY, minZ);

        //back quad

        Vec3d vert1Back = new Vec3d(minX, minY, minZ);
        Vec3d vert2Back = new Vec3d(minX, minY, maxZ);
        Vec3d vert3Back = new Vec3d(minX, maxY, maxZ);
        Vec3d vert4Back = new Vec3d(minX, maxY, minZ);

        _lineSidePositions.addAll(List.of(vert1, vert2,
                vert2, vert3,
                vert3, vert4,
                vertUp1, vertUp2,
                vertUp2, vertUp3,
                vertUp3, vertUp4,
                vertUp4 ,vert1,
                vert1, vertUp1,
                vertUp1, vert2,
                vert2, vertUp2,
                vertUp2 ,vert3,
                vert3 ,vertUp3,
                vertUp3, vert4,
                vert4 ,vertUp4));

        addPositions(vert1, vert2, vert3, vert4,
                vertUp1, vertUp2, vertUp3, vertUp4,
                vert1Right, vert2Right, vert3Right, vert4Right,
                vert1Left, vert2Left, vert3Left, vert4Left,
                vert1Front, vert2Front, vert3Front, vert4Front,
                vert1Back, vert2Back, vert3Back, vert4Back);

    }

}
