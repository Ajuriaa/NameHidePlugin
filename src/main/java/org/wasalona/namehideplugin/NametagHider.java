package org.wasalona.namehideplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class NametagHider {

    public void togglePlayerName(Player player){
        boolean isNameHidden = isNametagHidden(player);

        if(!isNameHidden) {
            executeCommand("team join NoNameTag " + player.getDisplayName());
            player.sendMessage("Your name is hidden now!");
        } else {
            executeCommand("team leave " + player.getDisplayName());
            player.sendMessage("Your name is shown now!");
        }
    }

    private boolean isNametagHidden(Player player) {
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        Team noNameTagTeam = scoreboard.getTeam("NoNameTag");

        if(noNameTagTeam == null){
            createNametagTeam();
        }

        Team playerTeam = scoreboard.getEntryTeam(player.getName());

        if (playerTeam != null) {
            return playerTeam.getOption(Team.Option.NAME_TAG_VISIBILITY) == Team.OptionStatus.NEVER;
        } else {
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
