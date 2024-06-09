package top.theillusivec4.curiouselytra.common.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import me.imjoshh.elytraphysicsforge.ElytraPhysicsForge;
import net.minecraft.world.entity.LivingEntity;

public class ElytraPhysicsModule {

  public static void applyTransformation(PoseStack poseStack, LivingEntity livingEntity,
                                         float partialTicks) {
    ElytraPhysicsForge.applyMovementTransformation(poseStack, livingEntity, partialTicks);
  }
}
