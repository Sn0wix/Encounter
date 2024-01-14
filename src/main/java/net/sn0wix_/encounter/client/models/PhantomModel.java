package net.sn0wix_.encounter.client.models;

import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.common.Encounter;
import net.sn0wix_.encounter.common.entity.custom.PhantomEntity;

public class PhantomModel extends EntityModel<PhantomEntity>{
    public PhantomModel()  {
        super(MODEL_RESOURCE, TEXTURE_RESOURCE, ANIMATION_RESOURCE);
    }

    public static final Identifier MODEL_RESOURCE = new Identifier(Encounter.MOD_ID, "geo/phantom.geo.json");
    public static final Identifier TEXTURE_RESOURCE = new Identifier(Encounter.MOD_ID, "textures/entity/phantom/phantom.png");
    public static final Identifier ANIMATION_RESOURCE = new Identifier(Encounter.MOD_ID, "animations/phantom.animation.json");
}
