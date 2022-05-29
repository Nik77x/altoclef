package adris.altoclef.util.shapes;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.math.MatrixStack;

public interface IRenderableObject
{
    //TODO make this an abstract class and add the methods to change the color and all other common stuff for shapes


    void Render(MatrixStack stack, Tessellator tessellator, BufferBuilder buffer);
}
