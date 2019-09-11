package me.piggypiglet.nerdbot.events;

import com.google.inject.Inject;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.logging.Logger;
import me.piggypiglet.framework.logging.LoggerFactory;
import me.piggypiglet.framework.utils.annotations.files.Config;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ReactionListener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger("ReactionListener");

    @Inject @Config private FileConfiguration config;

    @Override
    public void onGenericGuildMessageReaction(@Nonnull GenericGuildMessageReactionEvent e) {
        if (!config.getString("roles.channel_id").equals(e.getChannel().getId())) return;

        boolean take = true;

        if (e instanceof GuildMessageReactionAddEvent) {
            take = false;
        }

        final List<FileConfiguration> sections = config.getConfigList("messages");
        final Guild guild = e.getGuild();

        for (FileConfiguration s : sections) {
            if (s.getString("reaction").equalsIgnoreCase(e.getReactionEmote().getName())) {
                final Role role = guild.getRoleById(s.getString("role"));

                if (role == null) {
                    LOGGER.error("%s is not a valid role.", s.getString("role"));
                    return;
                }

                if (take) {
                    guild.addRoleToMember(e.getMember(), role).queue();
                } else {
                    guild.removeRoleFromMember(e.getMember(), role).queue();
                }

                return;
            }
        }
    }
}
