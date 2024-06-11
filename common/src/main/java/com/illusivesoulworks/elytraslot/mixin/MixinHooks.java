package com.illusivesoulworks.elytraslot.mixin;

import com.illusivesoulworks.elytraslot.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class MixinHooks {

  public static int getEnchantmentLevel(LivingEntity livingEntity, String key) {
    Enchantment enchantment = Registry.ENCHANTMENT.get(new ResourceLocation(key));
    int level = 0;

    if (enchantment != null) {
      level = EnchantmentHelper.getItemEnchantmentLevel(enchantment,
          Services.ELYTRA.getEquipped(livingEntity));
    }
    return level;
  }
}
