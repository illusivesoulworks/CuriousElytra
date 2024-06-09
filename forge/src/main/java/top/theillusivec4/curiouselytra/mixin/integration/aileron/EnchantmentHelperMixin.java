package top.theillusivec4.curiouselytra.mixin.integration.aileron;

import com.lodestar.aileron.AileronEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

  @Inject(at = @At("HEAD"), method = "getEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/entity/LivingEntity;)I", cancellable = true)
  private static void curiouselytra$getEnchantmentLevel(Enchantment enchantment,
                                                        LivingEntity livingEntity,
                                                        CallbackInfoReturnable<Integer> info) {

    if (enchantment == AileronEnchantments.CLOUDSKIPPER ||
        enchantment == AileronEnchantments.SMOKESTACK) {
      CuriosApi.getCuriosHelper().getEquippedCurios(livingEntity).ifPresent(curios -> {
        int level = 0;

        for (int i = 0; i < curios.getSlots(); i++) {
          ItemStack stack = curios.getStackInSlot(i);

          if (!stack.isEmpty()) {
            level = Math.max(level, EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack));
          }
        }

        if (level > 0) {
          info.setReturnValue(level);
        }
      });
    }
  }
}
