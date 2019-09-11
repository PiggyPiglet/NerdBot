package me.piggypiglet.nerdbot.commands;

import com.google.inject.Inject;
import me.piggypiglet.framework.commands.Command;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.framework.utils.annotations.files.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class GenerateCommand extends Command {
    private final TextChannel channel;

    @Inject
    public GenerateCommand(JDA jda, @Config FileConfiguration config) {
        super("generate");
        options.handlers("console").usage("");
        this.channel = jda.getTextChannelById(config.getString("roles.channel_id"));
    }

    @Override
    protected boolean execute(User user, String[] strings) {
        channel.sendMessage("test").queue();
        return true;
    }
}
