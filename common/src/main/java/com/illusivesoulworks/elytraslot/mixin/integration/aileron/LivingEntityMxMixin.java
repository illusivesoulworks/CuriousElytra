package com.illusivesoulworks.elytraslot.mixin.integration.aileron;

import com.bawnorton.mixinsquared.TargetHandler;
import com.illusivesoulworks.elytraslot.mixin.MixinHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = LivingEntity.class, priority = 1500)
public class LivingEntityMxMixin {

  @TargetHandler(
      mixin = "com.lodestar.aileron.mixin.LivingEntityMixin",
      name = "modifyVelocity")
  @ModifyVariable(
      method = "@MixinSquared:Handler",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/world/entity/LivingEntity;position()Lnet/minecraft/world/phys/Vec3;"
      ),
      ordinal = 0
  )
  private int elytraslot$modifyVelocity(int cloudSkipper) {
    LivingEntity livingEntity = (LivingEntity) (Object) this;

    if (livingEntity instanceof Player player) {
      return Math.max(cloudSkipper,
          MixinHooks.getEnchantmentLevel(livingEntity, "aileron:cloudskipper"));
    }
    return cloudSkipper;
  }
}
