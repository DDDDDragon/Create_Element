package org.mantodea.create_element.Mixins;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IForgeItem.class)
public abstract class MixinBacktankItem implements IForgeItem {

    @Inject(method = "hasCraftingRemainingItem", at = @At("RETURN"), cancellable = true, remap = false)
    public void hasCraftingRemainingItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean ret = cir.getReturnValue();
        if (stack.getTag() != null) {
            if(stack.getTag().contains("Air"))
                ret = true;
        }
        cir.setReturnValue(ret);
    }

    @Inject(method = "getCraftingRemainingItem", at = @At("RETURN"), cancellable = true, remap = false)
    public void getCraftingRemainingItem(ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack ret = cir.getReturnValue();
        if (stack.getTag() != null) {
            if(stack.getTag().contains("Air")) {
                CompoundTag orCreateTag = ret.getOrCreateTag();
                int air = orCreateTag.getInt("Air");
                air -= 900;
                if(air < 0)
                    air = 0;
                orCreateTag.putInt("Air", air);
                ret.setTag(orCreateTag);
            }
        }
        cir.setReturnValue(ret);
    }
}
