package com.github.DiscordSynergy;

import com.github.DiscordSynergy.Discord.Connection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerEvents implements Listener {
    private String discordChannelId = Plugin.config.getString("DiscordChannelId");
    private Boolean relayToDiscord = Plugin.config.getBoolean("RelayToDiscord");
    private Boolean announceJoinsToDiscord = Plugin.config.getBoolean("AnnounceJoinsToDiscord");
    private Boolean announceQuitsToDiscord = Plugin.config.getBoolean("AnnounceQuitsToDiscord");

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!announceJoinsToDiscord || e.getPlayer().getDisplayName() == null || e.getPlayer().getDisplayName() == "") {
            return;
        }
        Connection.jda.getTextChannelById(discordChannelId).sendMessage(e.getPlayer().getDisplayName() + " joined the Minecraft server").queue();
    }
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (discordChannelId != null && !discordChannelId.equals("") && relayToDiscord) {
            Connection.jda.getTextChannelById(discordChannelId).sendMessage(player.getName() + ": " + event.getMessage()).queue();
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if (!announceQuitsToDiscord || e.getPlayer().getDisplayName() == null || e.getPlayer().getDisplayName() == "") {
            return;
        }
        Connection.jda.getTextChannelById(discordChannelId).sendMessage(e.getPlayer().getDisplayName() + " left the Minecraft server").queue();
    }
}