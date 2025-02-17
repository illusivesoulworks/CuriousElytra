package com.illusivesoulworks.elytraslot.common.integration.deeperdarker;

import com.kyanite.deeperdarker.client.Keybinds;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;

public class DeeperDarkerClientPlugin {

  public static void setup() {
    NeoForge.EVENT_BUS.addListener(EventPriority.LOW, DeeperDarkerClientPlugin::keyInput);
  }

  private static void keyInput(final InputEvent.Key evt) {
    Minecraft mc = Minecraft.getInstance();

    if (mc.player != null && mc.getConnection() != null &&
        evt.getKey() == Keybinds.BOOST.getKey().getValue()) {
      PacketDistributor.sendToServer(new SoulElytraBoostPayload(true));
    }
  }
}
