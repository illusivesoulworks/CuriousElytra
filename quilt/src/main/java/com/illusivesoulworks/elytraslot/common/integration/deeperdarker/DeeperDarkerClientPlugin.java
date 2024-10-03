package com.illusivesoulworks.elytraslot.common.integration.deeperdarker;

import com.illusivesoulworks.elytraslot.ElytraSlotConstants;
import com.illusivesoulworks.elytraslot.platform.Services;
import com.kyanite.deeperdarker.client.Keybinds;
import com.kyanite.deeperdarker.content.DDItems;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class DeeperDarkerClientPlugin {

  public static void setup() {
    ClientTickEvents.START.register(world -> {
      Minecraft client = Minecraft.getInstance();

      if (client.player == null) {
        return;
      }
      ItemStack itemStack = Services.ELYTRA.getEquipped(client.player);

      if (itemStack.is(DDItems.SOUL_ELYTRA) && client.player.getCooldowns()
          .getCooldownPercent(DDItems.SOUL_ELYTRA, Minecraft.getInstance().getFrameTime()) == 0 &&
          client.player.isFallFlying() && Keybinds.BOOST.isDown()) {
        ClientPlayNetworking.send(
            new ResourceLocation(ElytraSlotConstants.MOD_ID, "soul_elytra_boost"),
            PacketByteBufs.empty());
      }
    });
    HudRenderCallback.EVENT.register(DeeperDarkerClientModule::registerHudCallback);
  }
}
