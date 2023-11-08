package net.sn0wix_.incounter.entity.custom;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.sn0wix_.incounter.networking.ModPackets;
import net.sn0wix_.incounter.sounds.ModSounds;
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


    public static final TrackedData<Boolean> SLEEPING = DataTracker.registerData(StalkerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int scareTicksLeft = 0;
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public StalkerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.dataTracker.startTracking(SLEEPING, true);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1, false));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PigEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 2, this::predicate)
                .triggerableAnim("scare", SCARE).triggerableAnim("wakeup", WAKEUP));
    }

    private PlayState predicate(AnimationState<StalkerEntity> state) {
        if (state.isMoving()) {
            return state.setAndContinue(WALK);
        }

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

            if (this.scareTicksLeft >= 1) {
                scareTicksLeft--;

                if (scareTicksLeft == 35) {
                    playSound(ModSounds.JUMPSCARE, 10, 1);
                }
            }
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (target instanceof PlayerEntity && !getWorld().isClient && scareTicksLeft == 0) {
            double squaredDistance = this.squaredDistanceTo(target.getX(), target.getY(), target.getZ());
            double d = this.getSquaredMaxAttackDistance((LivingEntity) target);

            if (squaredDistance <= d) {
                this.getNavigation().stop();
                this.scareTicksLeft = 40;
                this.getWorld().getPlayers().forEach(player -> {
                    if (!player.isSpectator()) {
                        ServerPlayNetworking.send((ServerPlayerEntity) player, ModPackets.LOCK_PLAYER, PacketByteBufs.create());
                    }
                });
                this.triggerAnim("controller","scare");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return super.damage(source, Float.MAX_VALUE);
        }
        return false;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        this.dataTracker.set(SLEEPING, true);
    }

    private double getSquaredMaxAttackDistance(LivingEntity entity) {
        return this.getWidth() * 2.0F * this.getWidth() * 2.0F + entity.getWidth();
    }
}
