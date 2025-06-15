package lol.mayaaa.divineclear;

import lol.mayaaa.divineclear.commands.ClearCommand;
import lol.mayaaa.divineclear.gui.ClearGUI;
import org.bukkit.plugin.java.JavaPlugin;

public final class DivineClear extends JavaPlugin {

    @Override
    public void onEnable() {
        new ClearGUI(this);
        getCommand("clear").setExecutor(new ClearCommand());
    }

    @Override
    public void onDisable() {
    }
}