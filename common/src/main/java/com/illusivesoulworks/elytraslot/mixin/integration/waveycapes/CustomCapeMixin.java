package com.illusivesoulworks.elytraslot.mixin.integration.waveycapes;

import com.illusivesoulworks.elytraslot.ElytraSlotCommonMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "dev.tr7zw.waveycapes.renderlayers.CustomCapeRenderLayer", remap = false)
public class CustomCapeMixin {

  @Inject(at = @At("HEAD"), method = "render", cancellable = true)
  private void elytraslot$render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i,
                                 AbstractClientPlayer abstractClientPlayer, float f, float g,
                                 float delta, float j, float k, float l, CallbackInfo info) {

    if (ElytraSlotCommonMod.isEquipped(abstractClientPlayer)) {
      info.cancel();
    }
  }
}