package net.sn0wix_.encounter.common.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import net.sn0wix_.encounter.common.sounds.ModSounds;
import software.bernie.geckolib.core.animation.*;

public class StalkerEntity extends JumpscaringEntity {
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.stalker.idle");
    public static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.stalker.run");
    public static final RawAnimation SCARE = RawAnimation.begin().thenPlay("animation.stalker.scare");
    public static final RawAnimation WAKEUP = RawAnimation.begin().thenPlay("animation.stalker.wakeup");

    public static final TrackedData<Boolean> SLEEPING = DataTracker.registerData(StalkerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public StalkerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.dataTracker.startTracking(SLEEPING, true);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 2, this::predicate)
                .triggerableAnim("scare", getScareAnim()));
    }

    @Override
    public RawAnimation getIdleAnim() {
        return IDLE;
    }

    @Override
    public RawAnimation getWalkAnim() {
        return WALK;
    }

    @Override
    public RawAnimation getScareAnim() {
        return SCARE;
    }

    @Override
    public int getSoundScareTicks() {
        return 35;
    }

    @Override
    public int getScaringDistanceBetweenPlayer() {
        return 2;
    }

    @Override
    public double getScaringPosYOffset() {
        return 0.2;
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
