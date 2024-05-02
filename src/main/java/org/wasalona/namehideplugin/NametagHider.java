package org.wasalona.namehideplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class NametagHider {

    public void togglePlayerName(Player player){
        boolean isNameHidden = isInNoNameTagTeam(player);

        if(isNameHidden) {
            executeCommand("team join NoNameTag " + player.getDisplayName());
            player.sendMessage("Your name is hidden now!");
        } else {
            executeCommand("team leave NoNameTag " + player.getDisplayName());
            player.sendMessage("Your name is shown now!");
        }
    }

    private boolean isInNoNameTagTeam(Player player) {
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        Team noNameTagTeam = scoreboard.getTeam("NoNameTag");

        if (noNameTagTeam != null) {
            return noNameTagTeam.hasEntry(player.getName());
        } else {
            createNametagTeam();
            return false;
        }
    }


    private void executeCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    private void createNametagTeam(){
        executeCommand("team add NoNameTag");
        executeCommand("team modify NoNameTag nametagVisibility never");
    }
}
