package net.sn0wix_.encounter.client.models;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.sn0wix_.encounter.common.Encounter;
import net.sn0wix_.encounter.common.entity.custom.CrawlerEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;

public class CrawlerModel extends EntityModel<CrawlerEntity> {
    public CrawlerModel() {
        super(MODEL_RESOURCE, TEXTURE_RESOURCE, ANIMATION_RESOURCE);
    }

    public static final Identifier MODEL_RESOURCE = new Identifier(Encounter.MOD_ID, "geo/crawler.geo.json");
    public static final Identifier TEXTURE_RESOURCE = new Identifier(Encounter.MOD_ID, "textures/entity/crawler/crawler.png");
    public static final Identifier ANIMATION_RESOURCE = new Identifier(Encounter.MOD_ID, "animations/crawler.animation.json");

    @Override
    public void setCustomAnimations(CrawlerEntity animatable, long instanceId, AnimationState<CrawlerEntity> animationState) {
        if (animatable.scareTicksLeft != 0) {
            CoreGeoBone head = getAnimationProcessor().getBone("head");

            if (head != null) {
                EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

                head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
                head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
            }
        }
    }
}
