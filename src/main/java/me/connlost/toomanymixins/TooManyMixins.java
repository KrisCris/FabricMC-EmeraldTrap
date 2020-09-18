package me.connlost.toomanymixins;

import me.connlost.toomanymixins.command.MaxAnvilLvlCommand;
import me.connlost.toomanymixins.config.ConfigManager;
import me.connlost.toomanymixins.util.IItemMaxCount;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.File;
import java.util.Map;


public class TooManyMixins implements ModInitializer {

	public static int maxLvl = 20;

	@Override
	public void onInitialize() {
		ConfigManager cm = ConfigManager.getConfigManager();
		for (Map.Entry<String, Integer> entry: cm.loadConfig(new File("config/stack-all.json")).entrySet()){
			((IItemMaxCount)Registry.ITEM.get(new Identifier(entry.getKey()))).setMaxCount(entry.getValue());
		}



	}


}
