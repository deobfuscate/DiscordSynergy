package com.github.DiscordSynergy;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class Discord {
    private JDA jda;

    public void connect() throws LoginException {
        jda = JDABuilder.createDefault("").build();
    }

    public void disconnect() {
        jda.shutdownNow();
    }
}