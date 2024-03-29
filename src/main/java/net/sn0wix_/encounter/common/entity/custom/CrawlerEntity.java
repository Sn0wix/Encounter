package net.sn0wix_.encounter.common.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sn0wix_.encounter.common.sounds.ModSounds;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class CrawlerEntity extends JumpscaringEntity<CrawlerEntity> {
    public static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.crawler.idle");
    public static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.crawler.walk");
    public static final RawAnimation SCARE_ANIM = RawAnimation.begin().thenPlay("animation.crawler.scare");
    public static final RawAnimation PEEK_ANIM = RawAnimation.begin().thenPlay("animation.crawler.peek");

    public CrawlerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 2, this::predicate)
                .triggerableAnim("scare", getScareAnim()).triggerableAnim("peek", PEEK_ANIM));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 169f);
    }

    @Override
    protected Vec3d assignLookVec() {
        return new Vec3d(this.getLookControl().getLookX(), this.getEyeY() + 0.5, this.getLookControl().getLookZ());
    }

    public void startPeekAnim() {
        this.triggerAnim("controller", "peek");
    }

    @Override
    public RawAnimation getIdleAnim() {
        return IDLE_ANIM;
    }

    @Override
    public RawAnimation getWalkAnim() {
        return WALK_ANIM;
    }

    @Override
    public RawAnimation getScareAnim() {
        return SCARE_ANIM;
    }

    @Override
    public void playScareSound() {
        playSound(ModSounds.CRAWLER_JUMPSCARE, 10, 1);
    }

    @Override
    public int getMaxScareTicks() {
        return 29;
    }

    @Override
    public int getSoundScareTicks() {
        return 28;
    }

    @Override
    public double getScaringDistanceBetweenPlayer() {
        return 2.7;
    }

    @Override
    public double getScaringPosYOffset() {
        return -0.2;
    }

    @Override
    public BlockPos getRedstoneBlockSpawnPos() {
        return new BlockPos(76, 57, -116);
    }
}
