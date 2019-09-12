package me.piggypiglet.nerdbot.commands;

import com.google.inject.Inject;
import me.piggypiglet.framework.commands.Command;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.framework.utils.annotations.files.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class GenerateCommand extends Command {
    @Inject @Config private FileConfiguration config;
    @Inject private JDA jda;

    public GenerateCommand() {
        super("generate");
        options.handlers("console").usage("");
    }

    @Override
    protected boolean execute(User user, String[] strings) {
        final EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Pick your roles")
                .setDescription("React with the relevant reactions to toggle which channels you'll have access to.")
                .setFooter("React below to change your roles.");

        List<FileConfiguration> sections = config.getConfigList("roles.emotes");
        List<Emote> emotes = new ArrayList<>();
        TextChannel channel = jda.getTextChannelById(config.getString("roles.channel_id"));

        for (int i = 0, sectionsSize = sections.size(); i < sectionsSize; i++) {
            FileConfiguration section = sections.get(i);

            emotes.add(jda.getEmotesByName(section.getString("reaction"), true).get(0));
            embed.addField(
                    emotes.get(i).getAsMention() + " - " + section.getString("name"),
                    "• Get the " + section.getString("name") + " role.",
                    true);
        }

        channel.sendMessage(embed.build()).queue(s -> {
                    for (Emote emote : emotes) {
                        s.addReaction(emote).queue();
                    }
                });
        return true;
    }
}
