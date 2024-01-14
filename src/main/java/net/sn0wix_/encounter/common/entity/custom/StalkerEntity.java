package net.sn0wix_.encounter.common.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sn0wix_.encounter.common.sounds.ModSounds;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class StalkerEntity extends JumpscaringEntity<StalkerEntity> {
    public static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.stalker.idle");
    public static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.stalker.run");
    public static final RawAnimation SCARE_ANIM = RawAnimation.begin().thenPlay("animation.stalker.scare");
    public static final RawAnimation SLEEP_ANIM = RawAnimation.begin().thenPlayAndHold("animation.stalker.sleep");
    public static final RawAnimation WAKEUP_ANIM = RawAnimation.begin().thenPlay("animation.stalker.wakeup");

    public static final TrackedData<Boolean> SLEEPING = DataTracker.registerData(StalkerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public StalkerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 2, this::predicate)
                .triggerableAnim("scare", getScareAnim()));
    }

    @Override
    public PlayState predicate(AnimationState<StalkerEntity> state) {
        if (this.dataTracker.get(SLEEPING)) {
            return state.setAndContinue(SLEEP_ANIM);
        }

        if (state.isMoving()) {
            return state.setAndContinue(getWalkAnim());
        }

        return state.setAndContinue(getIdleAnim());
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SLEEPING, false);
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
    public int getSoundScareTicks() {
        return 35;
    }

    @Override
    public double getScaringDistanceBetweenPlayer() {
        return 2;
    }

    @Override
    public double getScaringPosYOffset() {
        return 0.2;
    }

    @Override
    public BlockPos getRedstoneBlockSpawnPos() {
        return new BlockPos(0,0,0);
    }

    @Override
    public int getMaxScareTicks() {
        return 40;
    }

    @Override
    public void playScareSound() {
        playSound(ModSounds.STALKER_JUMPSCARE, 10, 1);
    }
}
