package me.connlost.toomanymixins.mixin;

import me.connlost.toomanymixins.TooManyMixins;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class MixinAnvilScreen {

    @ModifyConstant(
            method = "drawForeground",
            constant = @Constant(intValue = 40)
    )
    private int modifyConstant_maxLvlLimit(int ori){
        return TooManyMixins.maxLvl;
    }
}
