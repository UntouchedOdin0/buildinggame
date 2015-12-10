package me.stefvanschie.buildinggame.events.player.gui.buildmenu;

import me.stefvanschie.buildinggame.managers.arenas.ArenaManager;
import me.stefvanschie.buildinggame.managers.files.SettingsManager;
import me.stefvanschie.buildinggame.utils.guis.buildmenu.BuildMenu;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OptionsMenu implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		Player player = e.getPlayer();
		
		if (ArenaManager.getInstance().getArena(player) == null) {
			return;
		}
		
		if (!player.getItemInHand().hasItemMeta()) {
			return;
		}
		if (player.getItemInHand().getType() != Material.EMERALD) {
			return;
		}
		if (!player.getItemInHand().hasItemMeta()) {
			return;
		}
		if (!player.getItemInHand().getItemMeta().getDisplayName().equals(messages.getString("gui.options-emerald")
				.replaceAll("&", "�"))) {
			return;
		}
		
		BuildMenu menu = new BuildMenu();
		menu.show(player);
		e.setCancelled(true);
	}
}