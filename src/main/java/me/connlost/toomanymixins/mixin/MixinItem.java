package me.connlost.toomanymixins.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class MixinItem {

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    void tryMergeTotem(CallbackInfoReturnable<Integer> cir){
        if (this.equals(Items.TOTEM_OF_UNDYING) ){
            cir.setReturnValue(4);
        }
    }

}
