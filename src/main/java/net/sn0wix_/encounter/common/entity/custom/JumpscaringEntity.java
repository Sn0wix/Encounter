package net.sn0wix_.encounter.common.entity.custom;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.sn0wix_.encounter.common.networking.packets.s2c.PlayerLockS2CPacket;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public abstract class JumpscaringEntity<T extends GeoAnimatable> extends HostileEntity implements GeoEntity {
    private Vec3d scareLookVec;
    private Vec3d scarePosPlayerVec;
    private Vec3d scarePosVec;
    private int scareTicksLeft = 0;
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public JumpscaringEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public abstract RawAnimation getIdleAnim();
    public abstract RawAnimation getWalkAnim();
    public abstract RawAnimation getScareAnim();
    public abstract void playScareSound();
    public abstract int getMaxScareTicks();
    public abstract int getSoundScareTicks();
    public abstract double getScaringDistanceBetweenPlayer();
    public abstract double getScaringPosYOffset();

    @Override
    protected void initGoals() {
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1, false));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32f);
    }

    public PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()) {
            return state.setAndContinue(getWalkAnim());
        }

        return state.setAndContinue(getIdleAnim());
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            if (scareLookVec != null) {
                this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, scareLookVec);
            }

            if (this.scareTicksLeft >= 1) {
                scareTicksLeft--;
                this.getNavigation().stop();
                this.setMovementSpeed(0);
                this.setVelocity(0, 0, 0);
                scareLookVec = assignLookVec();

                if (scarePosVec != null) {
                    this.setPosition(scarePosVec);
                    //this.requestTeleport(scarePosVec.x, scarePosVec.y, scarePosVec.z);
                }

                scareAll();
                if (scareTicksLeft == getSoundScareTicks()) {
                    playScareSound();
                }

                if (scareTicksLeft == 0) {
                    this.scareLookVec = null;
                    this.scarePosPlayerVec = null;
                    this.scarePosVec = null;
                    this.setNoGravity(false);
                    this.getWorld().getPlayers().forEach(player -> PlayerLockS2CPacket.sendUnlockAndWriteToHashMap((ServerPlayerEntity) player));
                }
            }
        }
    }

    private Vec3d handleScareOffSet() {
        double deltaX = this.getPos().x - scareLookVec.x;
        double deltaZ = this.getPos().z - scareLookVec.z;

        double c = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        double ratio = getScaringDistanceBetweenPlayer() / c;

        double finalDeltaX = ratio * deltaX;
        double finalDeltaZ = ratio * deltaZ;

        return new Vec3d(this.getPos().x - finalDeltaX, 0, this.getPos().z - finalDeltaZ);
    }

    private void scareOne(ServerPlayerEntity player) {
        Vec3d finalDest = handleScareOffSet();

        player.requestTeleport(finalDest.x, this.getEyeY() - player.getEyeHeight(EntityPose.STANDING) - getScaringPosYOffset(), finalDest.z);
        scarePosPlayerVec = new Vec3d(finalDest.x, this.getEyeY() - player.getEyeHeight(EntityPose.STANDING) - getScaringPosYOffset(), finalDest.z);
        player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, this.getEyePos());
        player.setVelocity(0, 0, 0);
        player.velocityDirty = true;
        player.changeGameMode(GameMode.SPECTATOR);
    }

    private void scareAll() {
        this.getWorld().getPlayers().forEach(player -> {
            if (player instanceof ServerPlayerEntity serverPlayer && !serverPlayer.isSpectator()) {
                scareOne(serverPlayer);
            }
        });
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (target instanceof PlayerEntity && !getWorld().isClient && scareTicksLeft == 0) {
            double squaredDistance = this.squaredDistanceTo(target.getX(), target.getY(), target.getZ());
            double d = this.getSquaredMaxAttackDistance((LivingEntity) target);

            if (squaredDistance <= d) {
                this.getNavigation().stop();
                this.scareTicksLeft = getMaxScareTicks();
                scareLookVec = assignLookVec();
                scarePosVec = assignPosVec();
                this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, scareLookVec);
                this.setNoGravity(true);
                scareAll();
                this.getWorld().getPlayers().forEach(player -> PlayerLockS2CPacket.sendLockAndWriteToHashMap((ServerPlayerEntity) player, scarePosPlayerVec));
                this.triggerAnim("controller", "scare");
                return true;
            }
        }
        return false;
    }

    private Vec3d assignPosVec() {
        return new Vec3d(this.getPos().x, this.getPos().y, this.getPos().z);
    }

    private Vec3d assignLookVec() {
        return new Vec3d(this.getLookControl().getLookX(), this.getEyeY(), this.getLookControl().getLookZ());
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return super.damage(source, Float.MAX_VALUE);
        }

        if (source.getSource() instanceof PlayerEntity player && player.isCreative()) {
            return super.damage(source, Float.MAX_VALUE);
        }

        return false;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    private double getSquaredMaxAttackDistance(LivingEntity entity) {
        return this.getWidth() * 2.0F * this.getWidth() * 2.0F + entity.getWidth();
    }
}
