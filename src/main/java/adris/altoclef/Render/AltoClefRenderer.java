package adris.altoclef.Render;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.math.MatrixStack;

import adris.altoclef.Render.shapes.RenderableObject;

public class AltoClefRenderer
{
    private List<RenderableObject> _renderableObjects = new ArrayList<RenderableObject>();

    private final Tessellator _tessellator = Tessellator.getInstance();
    private final BufferBuilder _buffer = _tessellator.getBuffer();

    public void Render(MatrixStack stack){

        if(_renderableObjects.isEmpty()) return;

        for (int i = 0; i < _renderableObjects.size(); i++)
        {
            _renderableObjects.get(i).StartRender(stack);
        }
    }

    public void onTick(){

        if(_renderableObjects.isEmpty()) return;

        //this is probably really slow (temporary hack)
        // remove object when it ticks
        for (int i = 0; i < _renderableObjects.size(); i++)
        {
            if(_renderableObjects.get(i).isRendered()){
                _renderableObjects.remove(i);
            }
        }

    }

    public void RenderObject(RenderableObject objectToRender){
        _renderableObjects.add(objectToRender);
    }
}
