package net.sn0wix_.encounter.common.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sn0wix_.encounter.common.sounds.ModSounds;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class PhantomEntity extends JumpscaringEntity<PhantomEntity> {
    public static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.phantom.idle");
    public static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.phantom.walk");
    public static final RawAnimation SCARE_ANIM = RawAnimation.begin().thenPlayAndHold("animation.phantom.scare");

    public PhantomEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "walk_idle_controller", 5, this::walkIdlePredicate));
        controllerRegistrar.add(new AnimationController<>(this, "scare_controller", 0, this::scarePredicate).triggerableAnim("scare", getScareAnim()));
    }

    public PlayState walkIdlePredicate(AnimationState<PhantomEntity> state) {
        if (this.dataTracker.get(SCARING)) {
            return PlayState.STOP;
        }

        if (state.isMoving()) {
            return state.setAndContinue(getWalkAnim());
        }

        return state.setAndContinue(getIdleAnim());
    }

    public PlayState scarePredicate(AnimationState<PhantomEntity> state) {
        if (this.dataTracker.get(SCARING)) {
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32f);
    }

    @Override
    public void startScareAnim() {

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
        playSound(ModSounds.PHANTOM_JUMPSCARE, 10, 1);
    }

    @Override
    public int getMaxScareTicks() {
        return 24;
    }

    @Override
    public int getSoundScareTicks() {
        return 23;
    }

    @Override
    public double getScaringDistanceBetweenPlayer() {
        return 3;
    }

    @Override
    public double getScaringPosYOffset() {
        return 0;
    }

    @Override
    public BlockPos getRedstoneBlockSpawnPos() {
        return new BlockPos(0, 0, 0);
    }
}
