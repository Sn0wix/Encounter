package net.sn0wix_.incounter.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.sn0wix_.incounter.Incounter;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class StalkerEntity extends HostileEntity implements GeoEntity {
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.stalker.idle");
    public static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.stalker.run");
    public static final RawAnimation SCARE = RawAnimation.begin().thenPlay("animation.stalker.scare");
    public static final RawAnimation WAKEUP = RawAnimation.begin().thenPlay("animation.stalker.wakeup");


    private int screamTicksLeft = 0;
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public StalkerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1, false));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PigEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,"controler", 2, this::predicate)
                .triggerableAnim("scare", SCARE).triggerableAnim("wakeup", WAKEUP));
    }

    private PlayState predicate(AnimationState<StalkerEntity> state) {
        if (state.isMoving()) {
            Incounter.LOGGER.info("walk");
            return state.setAndContinue(WALK);
        }

        Incounter.LOGGER.info("idle");
        return state.setAndContinue(IDLE);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            if (this.screamTicksLeft >= 1) {
                screamTicksLeft--;

                if (screamTicksLeft <= 0) {
                    stopScreaming();
                }
            }
        }
    }

    private void stopScreaming() {

    }

    @Override
    public boolean tryAttack(Entity target) {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.isOf(DamageTypes.OUT_OF_WORLD)) {
            return super.damage(source, amount);
        }
        return false;
    }
}
