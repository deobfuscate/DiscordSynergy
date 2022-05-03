package com.github.DiscordSynergy;

import com.github.DiscordSynergy.Discord.Connection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ServerEvents implements Listener {
    private Boolean relayToDiscord = Plugin.config.getBoolean("RelayToDiscord");
    private String discordChannelId = Plugin.config.getString("DiscordChannelId");
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (relayToDiscord) {
            System.out.println(player.getName() + " said " + event.getMessage());
        }
        Connection.jda.getTextChannelById(discordChannelId).sendMessage(player.getName() + ": " + event.getMessage()).queue();
    }
}