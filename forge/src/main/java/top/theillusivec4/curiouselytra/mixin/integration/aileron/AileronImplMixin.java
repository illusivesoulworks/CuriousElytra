package top.theillusivec4.curiouselytra.mixin.integration.aileron;

import com.lodestar.aileron.forge.AileronImpl;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(AileronImpl.class)
public class AileronImplMixin {

  @Inject(at = @At("HEAD"), method = "wearingElytra", cancellable = true, remap = false)
  private static void curiouselytra$wearingElytra(Player player,
                                                  CallbackInfoReturnable<Boolean> cir) {

    if (player != null &&
        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.canElytraFly(player), player)
            .isPresent()) {
      cir.setReturnValue(true);
    }
  }
}
