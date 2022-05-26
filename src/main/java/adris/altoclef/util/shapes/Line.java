package adris.altoclef.util.shapes;

import java.awt.*;

import net.minecraft.util.math.Vec3d;

import org.lwjgl.system.CallbackI.V;

public class Line
{

    private Color _lineColor;
    private float _alpha;
    private Vec3d _pos1;
    private Vec3d _pos2;


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

    public Line Color(Color color, float alpha){
        _lineColor = color;
        _alpha = alpha;
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
}
