package me.connlost.toomanymixins.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(VillagerEntity.class)
public abstract class MixinVillagerEntity extends AbstractTraderEntity {

	public MixinVillagerEntity(EntityType<? extends AbstractTraderEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new TemptGoal(this, 1.0D, Ingredient.ofItems(Items.EMERALD_BLOCK), false));
	}
}
