package com.illusivesoulworks.elytraslot.common.integration.deeperdarker;

import com.illusivesoulworks.elytraslot.platform.Services;
import com.kyanite.deeperdarker.DeeperDarker;
import com.kyanite.deeperdarker.DeeperDarkerConfig;
import com.kyanite.deeperdarker.content.DDItems;
import io.netty.buffer.ByteBuf;
import java.util.function.Supplier;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

public class SoulElytraBoostPayload {

  public SoulElytraBoostPayload() {
  }

  public SoulElytraBoostPayload(ByteBuf buf) {
  }

  public void toBytes(ByteBuf buf) {
  }

  public void handle(Supplier<NetworkEvent.Context> context) {
    context.get().enqueueWork(() -> {
      ServerPlayer player = context.get().getSender();

      if (player == null) {
        return;
      }
      Level level = player.level();

      if (DeeperDarkerConfig.soulElytraCooldown == -1) {
        player.displayClientMessage(
            Component.translatable("item." + DeeperDarker.MOD_ID + ".soul_elytra.no_cooldown"),
            true);
        return;
      }
      Item item = DDItems.SOUL_ELYTRA.get();

      if (player.isFallFlying() && Services.ELYTRA.getEquipped(player).is(item) &&
          !player.getCooldowns().isOnCooldown(item)) {
        FireworkRocketEntity rocket =
            new FireworkRocketEntity(level, new ItemStack(Items.FIREWORK_ROCKET), player);
        level.addFreshEntity(rocket);
        player.getCooldowns().addCooldown(item, DeeperDarkerConfig.soulElytraCooldown);
      }
    });
    context.get().setPacketHandled(true);
  }
}
