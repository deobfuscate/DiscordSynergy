package com.github.DiscordSynergy;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class Discord {
    private JDA jda;
    private String token;
    
    public Discord(String token) {
        this.token = token;
    }

    public String connect() throws LoginException {
        jda = JDABuilder.createDefault(token).build();
        return jda.getSelfUser().getName();
    }

    public void disconnect() {
        jda.shutdownNow();
    }
}