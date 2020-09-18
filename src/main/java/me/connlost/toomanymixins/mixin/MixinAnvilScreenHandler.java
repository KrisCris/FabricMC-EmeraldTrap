package me.connlost.toomanymixins.mixin;

import me.connlost.toomanymixins.TooManyMixins;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreenHandler.class)
public class MixinAnvilScreenHandler {
    @ModifyConstant(
            method = "updateResult",
            constant = @Constant(intValue =40, ordinal = 2)
    )
    private int modifyConstant_maxLvlLimit(int ori){
        return TooManyMixins.maxLvl;
    }


}
