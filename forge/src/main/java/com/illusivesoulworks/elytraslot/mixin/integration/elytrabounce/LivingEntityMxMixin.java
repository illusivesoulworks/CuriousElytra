package com.illusivesoulworks.elytraslot.mixin.integration.elytrabounce;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import top.theillusivec4.caelus.api.CaelusApi;

@Mixin(value = LivingEntity.class, priority = 1500)
public abstract class LivingEntityMxMixin extends Entity {

  public LivingEntityMxMixin(EntityType<?> pEntityType, Level pLevel) {
    super(pEntityType, pLevel);
  }

  @TargetHandler(
      mixin = "org.infernalstudios.elytrabounce.mixin.MixinLivingEntity",
      name = "elytraBounce$updateFallFlying")
  @ModifyVariable(
      method = "@MixinSquared:Handler",
      at = @At("HEAD")
  )
  private boolean elytraslot$updateFallFlying(boolean flag) {
    LivingEntity livingEntity = (LivingEntity) (Object) this;
    return this.getSharedFlag(7) && !livingEntity.isOnGround() && !livingEntity.isPassenger() &&
        !livingEntity.hasEffect(MobEffects.LEVITATION) &&
        CaelusApi.getInstance().canFly(livingEntity);
  }
}
