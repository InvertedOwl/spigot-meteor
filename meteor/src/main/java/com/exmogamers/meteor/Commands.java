package com.exmogamers.meteor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Configuration config = Main.getInstance().getConfig();
        if (s.equalsIgnoreCase("togglemeteors")) {
            if (commandSender.hasPermission("meteor.togglemeteors")) {

                config.set("meteors-on", (!config.getBoolean("meteors-on")));
                commandSender.sendMessage("Meteors Now: " + ChatColor.GREEN + config.getBoolean("meteors-on"));
            }
        }
        if (s.equalsIgnoreCase("frequency")) {
            if (commandSender.hasPermission("meteor.frequency")) {
                config.set("frequency", Integer.parseInt(strings[0]));
                commandSender.sendMessage("Frequency Now: " + ChatColor.GREEN + config.getInt("frequency"));
            }
        }

        if (s.equalsIgnoreCase("radius")) {
            if (commandSender.hasPermission("meteor.radius")) {
                config.set("radius", Integer.parseInt(strings[0]));
                commandSender.sendMessage("Radius Now: " + ChatColor.GREEN + config.getInt("radius"));
            }
        }

        if (s.equalsIgnoreCase("impact")) {
            if (commandSender.hasPermission("meteor.impact")) {
                config.set("impact", Integer.parseInt(strings[0]));
                commandSender.sendMessage("Impact Now: " + ChatColor.GREEN + config.getInt("impact"));
            }
        }

        return false;
    }
}
