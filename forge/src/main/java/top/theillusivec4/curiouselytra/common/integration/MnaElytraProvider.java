package top.theillusivec4.curiouselytra.common.integration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curiouselytra.common.IElytraProvider;

public class MnaElytraProvider implements IElytraProvider {

  @Override
  public ResourceLocation getTexture(ItemStack stack) {
    return new ResourceLocation("mna:textures/entity/elytra.png");
  }

  @Override
  public boolean attachCapability(ItemStack stack) {
    return matches(stack);
  }

  @Override
  public boolean matches(ItemStack stack) {
    ResourceLocation rl = ForgeRegistries.ITEMS.getKey(stack.getItem());
    return rl != null && rl.equals(new ResourceLocation("mna:spectral_elytra"));
  }

  @Override
  public boolean isEnchanted(ItemStack stack) {
    return true;
  }
}
