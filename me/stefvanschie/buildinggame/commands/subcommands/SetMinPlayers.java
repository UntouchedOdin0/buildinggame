package me.stefvanschie.buildinggame.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import me.stefvanschie.buildinggame.commands.commandutils.CommandResult;
import me.stefvanschie.buildinggame.commands.commandutils.ConsoleCommand;
import me.stefvanschie.buildinggame.managers.arenas.ArenaManager;
import me.stefvanschie.buildinggame.managers.arenas.MinPlayersManager;
import me.stefvanschie.buildinggame.managers.files.SettingsManager;
import me.stefvanschie.buildinggame.managers.messages.MessageManager;
import me.stefvanschie.buildinggame.utils.arena.Arena;

public class SetMinPlayers extends ConsoleCommand {

	@Override
	public CommandResult onCommand(CommandSender sender, String[] args) {
		YamlConfiguration arenas = SettingsManager.getInstance().getArenas();
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		if (args.length <= 1) {
			MessageManager.getInstance().send(sender, ChatColor.RED + "Please specify the arena and the amount of players");
			return CommandResult.ARGUMENTEXCEPTION;
		}
		
		Arena arena = ArenaManager.getInstance().getArena(args[0]);
		
		if (arena == null) {
			MessageManager.getInstance().send(sender, ChatColor.RED + "That is not a valid arena");
			return CommandResult.ERROR;
		}
		
		try {
			Integer.parseInt(args[1]);
		} catch (NumberFormatException npe) {
			MessageManager.getInstance().send(sender, ChatColor.RED + "That's not a number");
			return CommandResult.ERROR;
		}
		
		int players = Integer.parseInt(args[1]);
		
		arenas.set(arena.getName() + ".minplayers", players);
		SettingsManager.getInstance().save();
		
		MinPlayersManager.getInstance().setup();
		
		MessageManager.getInstance().send(sender, messages.getString("setMinPlayers.succes")
				.replaceAll("&", "�"));
		
		return null;
	}

	@Override
	public String getName() {
		return "setminplayers";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getInfo() {
		return "Set the minimum amount of players";
	}

	@Override
	public String getPermission() {
		return "bg.setminplayers";
	}

}