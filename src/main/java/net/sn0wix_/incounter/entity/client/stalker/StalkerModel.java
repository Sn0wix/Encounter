package net.sn0wix_.incounter.entity.client.stalker;

import net.minecraft.util.Identifier;
import net.sn0wix_.incounter.Incounter;
import net.sn0wix_.incounter.entity.custom.StalkerEntity;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class StalkerModel extends GeoModel<StalkerEntity> {
    public static final Identifier modelResource = new Identifier(Incounter.MOD_ID, "geo/stalker.geo.json");
    public static final Identifier textureResource = new Identifier(Incounter.MOD_ID, "textures/entity/stalker/stalker.png");
    public static final Identifier animationResource = new Identifier(Incounter.MOD_ID, "animations/stalker.animation.json");


    @Override
    public Identifier getModelResource(StalkerEntity animatable) {
        return modelResource;
    }

    @Override
    public Identifier getTextureResource(StalkerEntity animatable) {
        return textureResource;
    }

    @Override
    public Identifier getAnimationResource(StalkerEntity animatable) {
        return animationResource;
    }

    @Override
    public void setCustomAnimations(StalkerEntity animatable, long instanceId, AnimationState<StalkerEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
