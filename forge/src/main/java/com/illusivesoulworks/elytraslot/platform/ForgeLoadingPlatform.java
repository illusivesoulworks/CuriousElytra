package com.illusivesoulworks.elytraslot.platform;

import com.illusivesoulworks.elytraslot.platform.services.ILoadingPlatform;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgeLoadingPlatform implements ILoadingPlatform {

  @Override
  public boolean isModLoaded(String id) {
    return FMLLoader.getLoadingModList().getModFileById(id) != null;
  }
}
