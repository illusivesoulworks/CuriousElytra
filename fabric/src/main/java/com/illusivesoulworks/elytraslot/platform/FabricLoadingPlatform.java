package com.illusivesoulworks.elytraslot.platform;

import com.illusivesoulworks.elytraslot.platform.services.ILoadingPlatform;
import net.fabricmc.loader.api.FabricLoader;

public class FabricLoadingPlatform implements ILoadingPlatform {

  @Override
  public boolean isModLoaded(String id) {
    return FabricLoader.getInstance().isModLoaded(id);
  }
}
