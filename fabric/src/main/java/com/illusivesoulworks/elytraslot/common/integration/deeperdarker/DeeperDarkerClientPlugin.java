package com.illusivesoulworks.elytraslot.common.integration.deeperdarker;

import com.illusivesoulworks.elytraslot.platform.Services;
import com.kyanite.deeperdarker.client.Keybinds;
import com.kyanite.deeperdarker.content.DDItems;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class DeeperDarkerClientPlugin {

  public static void setup() {
    ClientTickEvents.START_WORLD_TICK.register(world -> {
      Minecraft client = Minecraft.getInstance();

      if (client.player == null) {
        return;
      }
      ItemStack itemStack = Services.ELYTRA.getEquipped(client.player);

      if (itemStack.is(DDItems.SOUL_ELYTRA) && client.player.getCooldowns()
          .getCooldownPercent(DDItems.SOUL_ELYTRA, Minecraft.getInstance().getFrameTime()) == 0 &&
          client.player.isFallFlying() && Keybinds.BOOST.isDown()) {
        ClientPlayNetworking.send(new SoulElytraBoostPayload(PacketByteBufs.empty()));
      }
    });
    HudRenderCallback.EVENT.register(DeeperDarkerClientModule::registerHudCallback);
  }
}
