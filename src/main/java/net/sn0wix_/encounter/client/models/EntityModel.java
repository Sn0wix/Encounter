package net.sn0wix_.encounter.client.models;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.sn0wix_.encounter.client.util.ClientVariables;
import net.sn0wix_.encounter.common.Encounter;
import net.sn0wix_.encounter.common.entity.custom.JumpscaringEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public abstract class EntityModel<T extends JumpscaringEntity<T>> extends GeoModel<T> {
    public final Identifier MODEL_RESOURCE;
    public final Identifier TEXTURE_RESOURCE;
    public final Identifier ANIMATION_RESOURCE;

    public EntityModel(Identifier model_resource, Identifier texture_resource, Identifier animatio_resource) {
        this.MODEL_RESOURCE = model_resource;
        this.TEXTURE_RESOURCE = texture_resource;
        this.ANIMATION_RESOURCE = animatio_resource;
    }

    @Override
    public Identifier getModelResource(T animatable) {
        return MODEL_RESOURCE;
    }

    @Override
    public Identifier getTextureResource(T animatable) {
        return TEXTURE_RESOURCE;
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        return ANIMATION_RESOURCE;
    }

    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        //CoreGeoBone base = getAnimationProcessor().getBone("base");

        //base.setRotY(0);

        if (animatable.getDataTracker().get(T.SCARING)) {
            //Encounter.LOGGER.info("base rot: " + base.getRotY());
            if (ClientVariables.getScarePos() != null) {
                //Encounter.LOGGER.info("player pos: " + ClientVariables.getScarePos().y);
            }
            return;
        }

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
