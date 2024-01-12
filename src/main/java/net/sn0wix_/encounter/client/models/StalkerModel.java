package net.sn0wix_.encounter.client.models;

import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.common.Encounter;
import net.sn0wix_.encounter.common.entity.custom.StalkerEntity;
public class StalkerModel extends EntityModel<StalkerEntity> {
    public StalkerModel() {
        super(MODEL_RESOURCE, TEXTURE_RESOURCE, ANIMATION_RESOURCE);
    }

    public static final Identifier MODEL_RESOURCE = new Identifier(Encounter.MOD_ID, "geo/stalker.geo.json");
    public static final Identifier TEXTURE_RESOURCE = new Identifier(Encounter.MOD_ID, "textures/entity/stalker/stalker.png");
    public static final Identifier ANIMATION_RESOURCE = new Identifier(Encounter.MOD_ID, "animations/stalker.animation.json");
}
