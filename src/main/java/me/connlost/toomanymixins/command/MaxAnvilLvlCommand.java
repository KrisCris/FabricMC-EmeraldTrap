package me.connlost.toomanymixins.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.connlost.toomanymixins.TooManyMixins;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class MaxAnvilLvlCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder = literal("maxlvl").requires(source -> source.hasPermissionLevel(4))

                .then(literal("set")
                        .then(argument("lvl", IntegerArgumentType.integer(1))
                                .executes(ctx -> updateMaxLvl(
                                        ctx.getSource(),
                                        IntegerArgumentType.getInteger(ctx, "lvl"))))
                )
                .then(literal("show")
                        .executes(ctx -> showMaxLvl(ctx.getSource()))
                );

        dispatcher.register(literalArgumentBuilder);

    }

    private static int updateMaxLvl(ServerCommandSource source, int lvl) {
        TooManyMixins.maxLvl = lvl;
        source.sendFeedback(new LiteralText("set to "+lvl), true);
        return 1;
    }

    private static int showMaxLvl(ServerCommandSource source){
        source.sendFeedback(new LiteralText(TooManyMixins.maxLvl+""), false);
        return 1;
    }

}
