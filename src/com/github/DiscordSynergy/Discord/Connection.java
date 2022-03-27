package com.github.DiscordSynergy.Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA.Status;

import javax.security.auth.login.LoginException;

public class Connection {
    private JDA jda;
    private String token;
    
    public Connection(String token) {
        this.token = token;
    }

    public String connect() throws LoginException {
        jda = JDABuilder.createDefault(token).build();
        jda.addEventListener(new MessageListener());
        return jda.getSelfUser().getName();
    }

    public Boolean isOnline() {
        if (jda != null && jda.getStatus() == Status.CONNECTED)
            return true;
        else
            return false;
    }

    public void disconnect() {
        jda.shutdownNow();
    }
}