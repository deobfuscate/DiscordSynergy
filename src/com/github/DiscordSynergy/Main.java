package com.github.DiscordSynergy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    private Logger logger;
    
    @Override
    public void onEnable() {
        logger = Bukkit.getLogger();
        logger.info("Plugin Enabled");
    }

    @Override
    public void onDisable() {
        logger.info("Plugin Disabled");
    }
}