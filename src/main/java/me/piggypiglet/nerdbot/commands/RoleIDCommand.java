package me.piggypiglet.nerdbot.commands;

import com.google.inject.Inject;
import me.piggypiglet.framework.commands.Command;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.framework.utils.annotations.files.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RoleIDCommand extends Command {
    @Inject private JDA jda;
    @Inject @Config private FileConfiguration config;

    @Inject
    public RoleIDCommand() {
        super("roleid");
        options.handlers("console").usage("<role name>");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        Guild guild = jda.getGuildById(config.getString("guild_id"));

        if (args.length > 0) {
            List<Role> roles = guild.getRolesByName(args[0], true);

            if (roles.size() > 0) {
                user.sendMessage(roles.get(0).getId());
            } else {
                user.sendMessage("No role exists with that name.");
            }

            return true;
        }

        return false;
    }
}
