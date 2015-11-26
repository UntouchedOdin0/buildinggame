package me.stefvanschie.buildinggame.timers;

import me.confuser.barapi.BarAPI;
import me.stefvanschie.buildinggame.Main;
import me.stefvanschie.buildinggame.managers.files.SettingsManager;
import me.stefvanschie.buildinggame.managers.messages.MessageManager;
import me.stefvanschie.buildinggame.managers.softdependencies.SDBarApi;
import me.stefvanschie.buildinggame.timers.utils.Timer;
import me.stefvanschie.buildinggame.utils.Arena;
import me.stefvanschie.buildinggame.utils.GameState;
import me.stefvanschie.buildinggame.utils.plot.Plot;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class BuildTimer extends Timer {

	private boolean running = false;
	private int seconds;
	private Arena arena;
	
	public BuildTimer(int seconds, Arena arena) {
		this.seconds = seconds;
		this.arena = arena;
	}
	
	@Override
	public void run() {
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		running = true;
		if (seconds <= 0) {
			//voten
			for (Plot plot : arena.getUsedPlots()) {
				Player player = plot.getGamePlayer().getPlayer();
				
				player.setGameMode(GameMode.CREATIVE);
				player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
				
				//messages
				MessageManager.getInstance().send(player, messages.getString("buildingCountdown.time-up.message"));
				
				plot.getGamePlayer().sendTitle(messages.getString("buildingCountdown.time-up.title"));
				plot.getGamePlayer().sendSubtitle(messages.getString("buildingCountdown.time-up.subtitle"));
				
				if (SDBarApi.getInstance().isEnabled()) {
					if (BarAPI.hasBar(player)) {
						BarAPI.removeBar(player);
					}
				}
			}
			arena.setState(GameState.VOTING);
			arena.getVoteTimer().runTaskTimer(Main.getInstance(), 20L, 20L);
			running = false;
			this.cancel();
			return;
		} else if (seconds % 60 == 0 || seconds == 30 || seconds == 15 || (seconds <= 10 && seconds >= 1)) {
			for (Plot plot : arena.getUsedPlots()) {
				Player player = plot.getGamePlayer().getPlayer();
				MessageManager.getInstance().send(player, messages.getString("buildingCountdown.message")
						.replace("%seconds%", getSeconds() + "")
						.replace("%minutes", getMinutes() + "")
						.replace("%time%", getMinutes() + ":" + getSecondsFromMinute())
						.replace("%seconds_from_minute%", getSecondsFromMinute() + ""));
				
				plot.getGamePlayer().sendTitle(messages.getString("buildingCountdown.title")
						.replace("%seconds%", getSeconds() + "")
						.replace("%minutes", getMinutes() + "")
						.replace("%time%", getMinutes() + ":" + getSecondsFromMinute())
						.replace("%seconds_from_minute%", getSecondsFromMinute() + ""));
				plot.getGamePlayer().sendSubtitle(messages.getString("buildingCountdown.subtitle")
						.replace("%seconds%", getSeconds() + "")
						.replace("%minutes", getMinutes() + "")
						.replace("%time%", getMinutes() + ":" + getSecondsFromMinute())
						.replace("%seconds_from_minute%", getSecondsFromMinute() + ""));
			}
		}
		for (Plot plot : arena.getUsedPlots()) {
			Player player = plot.getGamePlayer().getPlayer();
			
			player.setLevel(getSeconds());
		}
		seconds--;
	}
	
	@Override
	public int getSeconds() {
		return seconds;
	}
	
	@Override
	public boolean isActive() {
		return running;
	}

	@Override
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
}
