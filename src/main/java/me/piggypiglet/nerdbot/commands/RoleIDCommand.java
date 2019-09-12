package me.piggypiglet.nerdbot.commands;

import me.piggypiglet.framework.commands.Command;
import me.piggypiglet.framework.jda.user.JDAUser;
import me.piggypiglet.framework.user.User;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RoleIDCommand extends Command {
    public RoleIDCommand() {
        super("roleid");
        options.handlers("console").usage("<role name>");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        Guild guild = ((JDAUser) user).getChannel().getGuild();

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
