package me.stefvanschie.buildinggame.events.player;

import me.stefvanschie.buildinggame.managers.arenas.ArenaManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class TakeDamage implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) e.getEntity();
		
		if (ArenaManager.getInstance().getArena(player) == null) {
			return;
		}
		
		e.setCancelled(true);
	}
}
