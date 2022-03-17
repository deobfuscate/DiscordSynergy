package com.github.DiscordSynergy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

public class Main extends JavaPlugin {
    private Logger logger;
    private Discord discord;
    private Boolean online = false;

    @Override
    public void onEnable() {
        logger = Bukkit.getLogger();
        discord = new Discord();
        try {
            String name = discord.connect();
            online = true;
            logger.info("Connected to Discord as " + name);
        }
        catch (LoginException exception) {
            logger.warning("Could not log in to Discord: " + exception.getMessage());
            online = false;
            this.getPluginLoader().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (online) {
            logger.info("Disconnecting from Discord");
            discord.disconnect();
        }
    }
}