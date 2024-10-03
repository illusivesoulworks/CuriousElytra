package com.illusivesoulworks.elytraslot.common.integration.deeperdarker;

import com.illusivesoulworks.elytraslot.ElytraSlotConstants;
import com.kyanite.deeperdarker.DeeperDarker;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class SoulElytraBoostPayload implements FabricPacket {

  public static final PacketType<SoulElytraBoostPayload> TYPE =
      PacketType.create(new ResourceLocation(ElytraSlotConstants.MOD_ID, "soul_elytra_boost"),
          SoulElytraBoostPayload::new);

  public SoulElytraBoostPayload(ByteBuf buf) {
  }

  @Override
  public void write(FriendlyByteBuf buf) {

  }

  @Override
  public PacketType<?> getType() {
    return TYPE;
  }
}
