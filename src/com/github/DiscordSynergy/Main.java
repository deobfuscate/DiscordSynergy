package com.github.DiscordSynergy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

public class Main extends JavaPlugin {
    private Logger logger;
    private Boolean online = false;
    private Discord discord;

    @Override
    public void onEnable() {
        logger = Bukkit.getLogger();
        discord = new Discord();
        try {
            discord.connect();
            online = true;
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
            discord.disconnect();
        }
    }
}