package com.github.DiscordSynergy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;

import com.github.DiscordSynergy.Discord.Connection;

public class Main extends JavaPlugin {
    private Logger logger;
    private Connection discord;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        config = getConfig();
        this.saveDefaultConfig();        
        logger = Bukkit.getLogger();
        String token = config.getString("Token");
        if (token == null || token.isEmpty()) {
            logger.warning("Discord token is not set!");
            return;
        }

        discord = new Connection(token);

        try {
            String name = discord.connect();
            logger.info("Connected to Discord as " + name);
        }
        catch (LoginException exception) {
            logger.warning("Could not log in to Discord: " + exception.getMessage());
            this.getPluginLoader().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (discord == null || !discord.isOnline()) return;
        logger.info("Disconnecting from Discord");
        discord.disconnect();
    }
}