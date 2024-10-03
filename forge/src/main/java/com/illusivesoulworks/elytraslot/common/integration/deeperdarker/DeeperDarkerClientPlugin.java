package com.illusivesoulworks.elytraslot.common.integration.deeperdarker;

import com.kyanite.deeperdarker.client.Keybinds;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;

public class DeeperDarkerClientPlugin {

  public static void setup() {
    MinecraftForge.EVENT_BUS.addListener(EventPriority.LOW, DeeperDarkerClientPlugin::keyInput);
  }
  private static void keyInput(final InputEvent.Key evt) {
    Minecraft mc = Minecraft.getInstance();

    if (mc.player != null && mc.getConnection() != null &&
        evt.getKey() == Keybinds.BOOST.getKey().getValue()) {
      DeeperDarkerPlugin.NETWORK.sendToServer(new SoulElytraBoostPayload());
    }
  }
}
