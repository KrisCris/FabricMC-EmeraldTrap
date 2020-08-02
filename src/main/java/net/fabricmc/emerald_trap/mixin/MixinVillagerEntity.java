package net.fabricmc.emerald_trap.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(VillagerEntity.class)
public final class MixinVillagerEntity extends PathAwareEntity {


	protected MixinVillagerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new TemptGoal(this, 1.1D, Ingredient.ofItems(Items.EMERALD_BLOCK), false));
		this.goalSelector.add(2, new TemptGoal(this, 1.0D, Ingredient.ofItems(Items.EMERALD), false));
		this.goalSelector.add(3, new TemptGoal(this, 0.9D, Ingredient.ofItems(Items.EMERALD_ORE), false));
	}
}