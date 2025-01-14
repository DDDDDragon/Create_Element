package org.mantodea.create_element.Mixins;

import com.simibubi.create.foundation.recipe.RecipeApplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import org.mantodea.create_element.CreateElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(RecipeApplier.class)
public abstract class MixinRecipeApplier {

    @Inject(method = "applyRecipeOn(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/crafting/Recipe;)Ljava/util/List;",
    at = @At("RETURN"), cancellable = true, remap = false)
    private static void applyRecipeOn(Level level, ItemStack stackIn, Recipe<?> recipe, CallbackInfoReturnable<List<ItemStack>> cir) {
        CreateElement.LOGGER.info("Mixin Start.");
        List<ItemStack> items = cir.getReturnValue();
        if(stackIn.hasCraftingRemainingItem())
            items.add(stackIn.getCraftingRemainingItem());
        CreateElement.LOGGER.info("Modify return value.");
        cir.setReturnValue(items);
    }
}
