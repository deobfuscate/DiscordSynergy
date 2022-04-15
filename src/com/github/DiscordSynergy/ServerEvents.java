package com.github.DiscordSynergy;

import com.github.DiscordSynergy.Discord.Connection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import net.dv8tion.jda.api.JDA;

public class ServerEvents implements Listener {
    private Boolean relayToDiscord = Plugin.config.getBoolean("RelayToDiscord");
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (relayToDiscord)
            System.out.println(player.getName() + " said " + event.getMessage());
        Connection.jda.getGuildById().getTextChannelById().sendMessage(player.getName() + ": " + event.getMessage());
    }
}
