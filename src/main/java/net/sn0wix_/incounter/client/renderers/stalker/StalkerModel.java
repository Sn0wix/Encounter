package net.sn0wix_.incounter.client.renderers.stalker;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.sn0wix_.incounter.Incounter;
import net.sn0wix_.incounter.entity.custom.StalkerEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class StalkerModel extends GeoModel<StalkerEntity> {
    public static final Identifier MODEL_RESOURCE = new Identifier(Incounter.MOD_ID, "geo/stalker.geo.json");
    public static final Identifier TEXTURE_RESOURCE = new Identifier(Incounter.MOD_ID, "textures/entity/stalker/stalker.png");
    public static final Identifier ANIMATION_RESOURCE = new Identifier(Incounter.MOD_ID, "animations/stalker.animation.json");


    @Override
    public Identifier getModelResource(StalkerEntity animatable) {
        return MODEL_RESOURCE;
    }

    @Override
    public Identifier getTextureResource(StalkerEntity animatable) {
        return TEXTURE_RESOURCE;
    }

    @Override
    public Identifier getAnimationResource(StalkerEntity animatable) {
        return ANIMATION_RESOURCE;
    }

    @Override
    public void setCustomAnimations(StalkerEntity animatable, long instanceId, AnimationState<StalkerEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("h_head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
