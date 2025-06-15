package lol.mayaaa.divineclear.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class ClearGUI implements Listener {

    private static final int[] ACCEPT_PANE = {45, 46, 47, 48};
    private static final int INFO_BOOK = 49;
    private static final int[] DENY_PANE = {50, 51, 52, 53};

    public ClearGUI(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static void opengui(Player player) {
        Inventory trashgui = Bukkit.createInventory(null, 54, "Trash");

        // Create control items
        ItemStack acceptpane = createitem(Material.STAINED_GLASS_PANE, "§a§lACCEPT", (short)5);
        ItemStack infobook = createitem(Material.BOOK, "§c§lINFORMATION", "§cline 1", "§aline 2");
        ItemStack denypane = createitem(Material.STAINED_GLASS_PANE, "§c§lDENY", (short)14);

        // Add items to inventory
        for (int slot : ACCEPT_PANE) trashgui.setItem(slot, acceptpane);
        trashgui.setItem(INFO_BOOK, infobook);
        for (int slot : DENY_PANE) trashgui.setItem(slot, denypane);

        player.openInventory(trashgui);
    }

    @EventHandler
    public void invClick(InventoryClickEvent event) {
        if (!event.getInventory().getName().equals("Trash")) return;
        if (!(event.getWhoClicked() instanceof Player)) return;

        event.setCancelled(true);
        Player clicker = (Player) event.getWhoClicked();
        int clickedslot = event.getRawSlot();

        // Only handle bottom row clicks
        if (clickedslot < 45 || clickedslot > 53) return;

        if (inslots(clickedslot, ACCEPT_PANE)) {
            clicker.getInventory().clear();
            clicker.closeInventory();
            clicker.sendMessage("§a§l[!] §fYour inventory has been cleared!");
        }
        else if (inslots(clickedslot, DENY_PANE)) {
            clicker.closeInventory();
            clicker.sendMessage("§c§l[!] §fTrash menu closed.");
        }
    }

    private boolean inslots(int slot, int[] slots) {
        for (int s : slots) {
            if (slot == s) return true;
        }
        return false;
    }

    private static ItemStack createitem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (lore.length > 0) meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack createitem(Material material, String name, short durability) {
        ItemStack item = new ItemStack(material, 1, durability);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}