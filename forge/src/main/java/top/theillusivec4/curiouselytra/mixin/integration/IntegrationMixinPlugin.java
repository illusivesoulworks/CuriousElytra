package top.theillusivec4.curiouselytra.mixin.integration;

import java.util.List;
import java.util.Set;
import net.minecraftforge.fml.loading.FMLLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class IntegrationMixinPlugin implements IMixinConfigPlugin {

  @Override
  public void onLoad(String s) {

  }

  @Override
  public String getRefMapperConfig() {
    return "";
  }

  @Override
  public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {

    if (mixinClassName.startsWith("top.theillusivec4.curiouselytra.mixin.integration.aileron")) {
      return FMLLoader.getLoadingModList().getModFileById("aileron") != null;
    } else if (mixinClassName.startsWith("top.theillusivec4.curiouselytra.mixin.integration.waveycapes")) {
      return FMLLoader.getLoadingModList().getModFileById("waveycapes") != null;
    }
    return true;
  }

  @Override
  public void acceptTargets(Set<String> set, Set<String> set1) {

  }

  @Override
  public List<String> getMixins() {
    return List.of();
  }

  @Override
  public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

  }

  @Override
  public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

  }
}
