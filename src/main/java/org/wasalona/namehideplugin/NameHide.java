package org.wasalona.namehideplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NameHide extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("NameHidePlugin has been enabled!");

        // Register command executor for /bounty command
        Objects.requireNonNull(getCommand("namehide")).setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        getLogger().info("BountiesPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("namehide")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be a player to use this command!");
                return false;
            }

            Player player = (Player) sender;

            // Check permissions
            if (!player.hasPermission("namehide.toggle")) {
                player.sendMessage("You don't have permission to use this command.");
                return false;
            }

            NametagHider nametagHider = new NametagHider();

            nametagHider.togglePlayerName(player);
        }
        return false;
    }
}
