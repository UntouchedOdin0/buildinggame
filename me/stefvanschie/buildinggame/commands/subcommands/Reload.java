package me.stefvanschie.buildinggame.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.stefvanschie.buildinggame.commands.commandutils.CommandResult;
import me.stefvanschie.buildinggame.commands.commandutils.SubCommand;
import me.stefvanschie.buildinggame.managers.messages.MessageManager;

public class Reload extends SubCommand {

	@Override
	public CommandResult onCommand(Player player, String[] args) {
		MessageManager.getInstance().send(player, ChatColor.GOLD + "I'm sorry this command is diabled, due to unexpected behavior.");
		MessageManager.getInstance().send(player, ChatColor.GOLD + "You don't have to contact me, I already know about this bug.");
		MessageManager.getInstance().send(player, ChatColor.GOLD + "Please use /reload or /restart to reload this plugin.");
		
		return CommandResult.ERROR;
		
		/*
		long start = System.nanoTime();
		MessageManager.getInstance().send(player, ChatColor.GOLD + "Loading files");
		SettingsManager.getInstance().setup(Main.getInstance());
		
		MessageManager.getInstance().send(player, ChatColor.GOLD + "Loading arenas");
		ArenaManager.getInstance().setup();
		LobbyManager.getInstance().setup();
		MinPlayersManager.getInstance().setup();
		MaxPlayersManager.getInstance().setup();
		
		MessageManager.getInstance().send(player, ChatColor.GOLD + "Loading plots");
		PlotManager.getInstance().setup();
		LocationManager.getInstance().setup();
		BoundaryManager.getInstance().setup();
		
		MessageManager.getInstance().send(player, ChatColor.GOLD + "Loading main spawn");
		MainSpawnManager.getInstance().setup();
		
		MessageManager.getInstance().send(player, ChatColor.GOLD + "Loading soft dependencies");
		if (Bukkit.getPluginManager().isPluginEnabled("BarAPI")) {
			SDBarApi.getInstance().setup();
		}
		if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
			SDVault.getInstance().setup();
		}
		
		MessageManager.getInstance().send(player, ChatColor.GOLD + "Loading listeners");
		Bukkit.getPluginManager().registerEvents(new VoteEvent(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new JoinSignCreate(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new LeaveSignCreate(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new ClickJoinSign(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new ClickLeaveSign(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new BlockBreak(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new BlockPlace(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new Leave(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new TakeDamage(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new LoseFood(), Main.getInstance());
		
		MessageManager.getInstance().send(player, ChatColor.GOLD + "Loading commands");
		CommandManager command = new CommandManager();
		command.setup();
		
		Main.getInstance().getCommand("bg").setExecutor(command);
		Main.getInstance().getCommand("buildinggame").setExecutor(command);
		
		long end = System.nanoTime();
		double timeTaken = (end - start) / 1000000000;
		
		MessageManager.getInstance().send(player, ChatColor.GREEN + "Reloaded plugin in " + timeTaken + " seconds");
		return CommandResult.SUCCES;
		*/
	}

	@Override
	public String getName() {
		return "reload";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getInfo() {
		return "Reload the server";
	}

	@Override
	public String getPermission() {
		return "bg.reload";
	}
}
