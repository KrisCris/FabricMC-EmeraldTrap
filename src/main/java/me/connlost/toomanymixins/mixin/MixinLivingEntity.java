package me.connlost.toomanymixins.mixin;

import me.connlost.toomanymixins.util.IDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);
    private boolean manipulated = false;

    public MixinLivingEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tryUseTotem", at=@At(value = "HEAD"))
    private void setOutOfWorldFalse(DamageSource source, CallbackInfoReturnable<Boolean> cir){
        if (source.isOutOfWorld()){
            ((IDamageSource)source).setOutOfWorld(false);
            this.manipulated = true;
        }
    }

    @Inject(method = "tryUseTotem", at=@At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/damage/DamageSource;isOutOfWorld()Z"))
    private void revertOutOfWorld(DamageSource source, CallbackInfoReturnable<Boolean> cir){
        if (manipulated){
            manipulated = false;
            ((IDamageSource)source).setOutOfWorld(true);
        }
    }


    @Inject(method = "tryUseTotem", at=@At(value = "NEW", target = "net/minecraft/entity/effect/StatusEffectInstance", ordinal = 0))
    private void savePlayerFromVoid(DamageSource source, CallbackInfoReturnable<Boolean> cir){
        if (source.isOutOfWorld()){
            this.setVelocity(0,0,0);
            Vec3d pos = this.getPos();
            this.refreshPositionAfterTeleport(pos.x,0,pos.z);
            addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 400, 1));
        }
    }

}
