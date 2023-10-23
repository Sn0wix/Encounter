package net.sn0wix_.incounter.entity.client.stalker;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sn0wix_.incounter.entity.custom.StalkerEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StalkerRenderer extends GeoEntityRenderer<StalkerEntity> {
    public StalkerRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new StalkerModel());
    }

    @Override
    public Identifier getTextureLocation(StalkerEntity animatable) {
        return StalkerModel.textureResource;
    }
}
