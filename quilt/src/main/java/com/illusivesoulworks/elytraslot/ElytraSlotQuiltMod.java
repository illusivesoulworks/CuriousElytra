/*
 * Copyright (C) 2019-2022 Illusive Soulworks
 *
 * Elytra Slot is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * Elytra Slot is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Elytra Slot. If not, see <https://www.gnu.org/licenses/>.
 */

package com.illusivesoulworks.elytraslot;

import com.illusivesoulworks.elytraslot.platform.Services;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ElytraSlotQuiltMod implements ModInitializer {

  @Override
  public void onInitialize(ModContainer modContainer) {
    ElytraSlotCommonMod.init();
    EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> {
      if (ElytraSlotCommonMod.canFly(entity)) {

        if (tickElytra) {
          ItemStack stack = Services.ELYTRA.getEquipped(entity);

          if (stack.getItem() instanceof FabricElytraItem fabricElytraItem) {
            return fabricElytraItem.useCustomElytra(entity, stack, true);
          }
        }
        return true;
      }
      return false;
    });
    TrinketsApi.registerTrinket(Items.ELYTRA, new Trinket() {

      @Override
      public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return ElytraSlotCommonMod.canEquip(entity);
      }
    });
  }
}
