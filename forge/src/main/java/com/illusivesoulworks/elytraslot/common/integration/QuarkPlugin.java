package com.illusivesoulworks.elytraslot.common.integration;

import net.minecraft.world.item.ItemStack;
import org.violetmoon.quark.content.tools.module.ColorRunesModule;

public class QuarkPlugin {

  public static void setRuneTarget(ItemStack stack) {
    ColorRunesModule.setTargetStack(stack);
  }
}
