package com.illusivesoulworks.elytraslot.common.integration.deeperdarker;

import com.illusivesoulworks.elytraslot.ElytraSlotConstants;
import com.kyanite.deeperdarker.DeeperDarker;
import com.kyanite.deeperdarker.DeeperDarkerConfig;
import com.kyanite.deeperdarker.content.DDItems;
import com.kyanite.deeperdarker.network.Messages;
import com.kyanite.deeperdarker.network.SoulElytraBoostPacket;
import com.kyanite.deeperdarker.network.SoulElytraClientPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import top.theillusivec4.curios.api.event.CurioChangeEvent;

public class DeeperDarkerPlugin {

  public static SimpleChannel NETWORK;
  private static int ID;

  public static void setup() {
    MinecraftForge.EVENT_BUS.addListener(DeeperDarkerPlugin::onCurioChange);
    NETWORK =
        NetworkRegistry.newSimpleChannel(new ResourceLocation(ElytraSlotConstants.MOD_ID, "main"),
            () -> "1", (s) -> s.equals("1"), (s) -> s.equals("1"));
    NETWORK.registerMessage(ID++, SoulElytraBoostPayload.class, SoulElytraBoostPayload::toBytes,
        SoulElytraBoostPayload::new, SoulElytraBoostPayload::handle);
  }

  private static void onCurioChange(final CurioChangeEvent evt) {

    if (!evt.getTo().is(DDItems.SOUL_ELYTRA.get()) || evt.getFrom().is(DDItems.SOUL_ELYTRA.get())) {
      return;
    }
    if (evt.getEntity() instanceof ServerPlayer player) {
      Messages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),
          new SoulElytraClientPacket());
    }
  }

  public static void tick(Level level, Entity entity) {

    if (level.isClientSide() && entity instanceof Player player) {

      if (player.getCooldowns().isOnCooldown(DDItems.SOUL_ELYTRA.get())) {
        float percent = player.getCooldowns().getCooldownPercent(DDItems.SOUL_ELYTRA.get(), 0);
        player.displayClientMessage(
            Component.translatable("item." + DeeperDarker.MOD_ID + ".soul_elytra.cooldown",
                (int) Math.ceil(percent * DeeperDarkerConfig.soulElytraCooldown / 20)), true);
      }
    }
  }
}
