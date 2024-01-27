package net.sn0wix_.encounter.common.entity.custom;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sn0wix_.encounter.common.networking.packets.s2c.PlayerLockS2CPacket;
import net.sn0wix_.encounter.common.sounds.ModSounds;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class PhantomEntity extends JumpscaringEntity<PhantomEntity> {
    public static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.phantom.idle");
    public static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.phantom.walk");
    public static final RawAnimation SCARE_ANIM = RawAnimation.begin().thenPlay("animation.phantom.scare");
    public static final RawAnimation SCARE_ANIM_LOOP = RawAnimation.begin().thenLoop("animation.phantom.scare");

    public PhantomEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "walk_idle_controller", 5, this::walkIdlePredicate).triggerableAnim("scare", getScareAnim()));
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
            return state.setAndContinue(SCARE_ANIM_LOOP);
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
    public void startScaring() {
        this.getNavigation().stop();
        this.scareTicksLeft = getMaxScareTicks();
        scareLookVec = assignLookVec();
        scarePosVec = assignPosVec();
        this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, scareLookVec);
        this.setNoGravity(true);
        this.dataTracker.set(SCARING, true);
        scareAll();
        this.getWorld().getPlayers().forEach(player -> PlayerLockS2CPacket.sendLockAndWriteToHashMap((ServerPlayerEntity) player, scarePosPlayerVec));
    }

    @Override
    public void startScareAnim() {
        this.triggerAnim("walk_idle_controller", "scare");
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
        return 2;
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
