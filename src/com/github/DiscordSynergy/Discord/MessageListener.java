package com.github.DiscordSynergy.Discord;

import java.util.Collection;

import com.github.DiscordSynergy.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    private Boolean relayToMinecraft = Main.config.getBoolean("RelayToMinecraft");

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!relayToMinecraft) return;
        if (event.isFromType(ChannelType.TEXT)) {
            String message = String.format("[%s][#%s] %s: %s\n", event.getGuild().getName(),
                event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                event.getMessage().getContentDisplay());
            
            System.out.print(message);
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            for (Player player : players)
                player.sendMessage(message);
        }
    }
}