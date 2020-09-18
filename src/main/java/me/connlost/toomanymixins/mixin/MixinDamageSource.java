package me.connlost.toomanymixins.mixin;

import me.connlost.toomanymixins.util.IDamageSource;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DamageSource.class)
public class MixinDamageSource implements IDamageSource {

    @Shadow
    private boolean outOfWorld;

    @Override
    public void setOutOfWorld(boolean b) {
        this.outOfWorld = b;
    }
}
