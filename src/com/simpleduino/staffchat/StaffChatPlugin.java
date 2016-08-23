package com.simpleduino.staffchat;

import com.simpleduino.staffchat.Commands.AdminChatCommand;
import com.simpleduino.staffchat.Commands.HelperChatCommand;
import com.simpleduino.staffchat.Commands.ModoChatCommand;
import com.simpleduino.staffchat.Messaging.MessageListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Simple-Duino on 16/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class StaffChatPlugin extends JavaPlugin {

    public void onEnable()
    {
        this.getServer().getPluginCommand("chatadmin").setExecutor(new AdminChatCommand());
        this.getServer().getPluginCommand("chathelper").setExecutor(new HelperChatCommand());
        this.getServer().getPluginCommand("chatmodo").setExecutor(new ModoChatCommand());

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageListener());
    }

    public void onDisable()
    {

    }
}
