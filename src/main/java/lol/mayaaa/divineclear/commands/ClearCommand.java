package lol.mayaaa.divineclear.commands;

import lol.mayaaa.divineclear.gui.ClearGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage("§a§l[!] §fOpening Trash menu.");
        ClearGUI.opengui(player);
        return true;
    }
}