package adris.altoclef.Render;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.math.MatrixStack;

import org.jetbrains.annotations.NotNull;

import adris.altoclef.util.shapes.IRenderableObject;
import baritone.utils.IRenderer;

public class AltoClefRenderer
{
    private List<IRenderableObject> _renderableObjects = new ArrayList<IRenderableObject>();

    private final Tessellator _tessellator = Tessellator.getInstance();
    private final BufferBuilder _buffer = _tessellator.getBuffer();

    public void Render(MatrixStack stack){

        if(_renderableObjects.isEmpty()) return;

        for (int i = 0; i < _renderableObjects.size(); i++)
        {
            _renderableObjects.get(i).Render(stack, _tessellator, _buffer);
            _renderableObjects.remove(i);
        }
    }

    public void RenderObject(IRenderableObject objectToRender){
        _renderableObjects.add(objectToRender);
    }
}
