package com.illusivesoulworks.elytraslot.mixin;

import com.illusivesoulworks.elytraslot.platform.Services;
import java.util.List;
import java.util.Set;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class IntegrationMixinPlugin implements IMixinConfigPlugin {

  @Override
  public void onLoad(String mixinPackage) {

  }

  @Override
  public String getRefMapperConfig() {
    return "";
  }

  @Override
  public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {

    if (mixinClassName.startsWith("com.illusivesoulworks.elytraslot.mixin.integration.aileron")) {
      return Services.LOADING.isModLoaded("aileron");
    } else if (mixinClassName.startsWith("com.illusivesoulworks.elytraslot.mixin.integration.waveycapes")) {
      return Services.LOADING.isModLoaded("waveycapes");
    }
    return true;
  }

  @Override
  public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

  }

  @Override
  public List<String> getMixins() {
    return List.of();
  }

  @Override
  public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName,
                        IMixinInfo mixinInfo) {

  }

  @Override
  public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName,
                       IMixinInfo mixinInfo) {

  }
}
