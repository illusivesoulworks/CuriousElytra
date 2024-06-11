package com.illusivesoulworks.elytraslot.mixin.integration.aileron;

import com.bawnorton.mixinsquared.TargetHandler;
import com.illusivesoulworks.elytraslot.platform.Services;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Player.class, priority = 1500)
public class PlayerEntityMxMixin {

  @Unique
  private ItemStack elytraslot$stack = ItemStack.EMPTY;

  @TargetHandler(
      mixin = "com.lodestar.aileron.mixin.PlayerEntityMixin",
      name = "postTick")
  @Inject(
      method = "@MixinSquared:Handler",
      at = @At("HEAD")
  )
  private void elytraslot$prePostTick(CallbackInfo ci) {
    Player player = (Player) (Object) this;
    this.elytraslot$stack = player.getInventory().getArmor(2);

    if (!(this.elytraslot$stack.getItem() instanceof ElytraItem)) {
      ItemStack elytra = Services.ELYTRA.getEquipped(player);

      if (!elytra.isEmpty()) {
        player.getInventory().armor.set(2, elytra);
      }
    }
  }

  @TargetHandler(
      mixin = "com.lodestar.aileron.mixin.PlayerEntityMixin",
      name = "postTick")
  @Inject(
      method = "@MixinSquared:Handler",
      at = @At("RETURN")
  )
  private void elytraslot$postPostTick(CallbackInfo ci) {
    Player player = (Player) (Object) this;
    player.getInventory().armor.set(2, this.elytraslot$stack);
    this.elytraslot$stack = ItemStack.EMPTY;
  }
}
