package com.fuzzycraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TipsReloadedCommandExecutor implements CommandExecutor {
    protected TipsReloaded plugin;
    
    protected TipsReloadedCommandExecutor(TipsReloaded plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender.hasPermission("tips.reload")) {
            if (args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
            	Set<String> globalTips = plugin.getConfig().getConfigurationSection("global").getKeys(false);
            	sender.sendMessage(globalTips.toString());
                return true;
            } else return false;
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use that command");
            return true;
        }
    }
}
