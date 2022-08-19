package com.github.DiscordSynergy.Discord;

import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import com.github.DiscordSynergy.Plugin;

public class MessageListener extends ListenerAdapter {
    private Boolean relayToMinecraft = Plugin.config.getBoolean("RelayToMinecraft");
    private Boolean relayToConsole = Plugin.config.getBoolean("RelayToConsole");
    private String discordChannelId = Plugin.config.getString("DiscordChannelId");

    // When a Discord message is received
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.isFromType(ChannelType.TEXT) && event.getTextChannel().getId().equals(discordChannelId)) {
            String message = String.format("[%s][#%s] %s: %s\n",
                event.getGuild().getName(),
                event.getTextChannel().getName(),
                event.getMember().getEffectiveName(),
                event.getMessage().getContentDisplay());
                
            if (relayToConsole) {
                System.out.print("[Discord] " + message);
            }
            if (relayToMinecraft) {
                Collection<? extends Player> players = Bukkit.getOnlinePlayers();
                for (Player player : players) {
                    player.sendMessage(message);
                }
            }
        }
    }
}