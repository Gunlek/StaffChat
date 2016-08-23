package com.simpleduino.staffchat.Messaging;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Simple-Duino on 10/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class MessageListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();

        if (subchannel.equals("HelperStaffMessage")) {
            short len = in.readShort();
            byte[] msgBytes = new byte[len];
            in.readFully(msgBytes);

            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgBytes));
            String server = null, msgSender = null, msg = null;
            try {
                server = msgin.readUTF();
                msgSender = msgin.readUTF();
                msg = msgin.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(Player p : Bukkit.getOnlinePlayers())
            {
                if(p.hasPermission("StaffChat.HelperStaff.Messenger"))
                {
                    p.sendMessage(ChatColor.RED + "[HelperChat] " + ChatColor.AQUA + "("+server+")" + msgSender+ ChatColor.RESET+": "+msg.replace("&","§"));
                }
            }
        }

        if (subchannel.equals("AdminStaffMessage")) {
            short len = in.readShort();
            byte[] msgBytes = new byte[len];
            in.readFully(msgBytes);

            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgBytes));
            String server = null, msgSender = null, msg = null;
            try {
                server = msgin.readUTF();
                msgSender = msgin.readUTF();
                msg = msgin.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(Player p : Bukkit.getOnlinePlayers())
            {
                if(p.hasPermission("StaffChat.AdminStaff.Messenger"))
                {
                    p.sendMessage(ChatColor.RED + "[AdminChat] " + ChatColor.AQUA + "("+server+")" + msgSender+ ChatColor.RESET+": "+msg.replace("&","§"));
                }
            }
        }

        if (subchannel.equals("ModoStaffMessage")) {
            short len = in.readShort();
            byte[] msgBytes = new byte[len];
            in.readFully(msgBytes);

            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgBytes));
            String server = null, msgSender = null, msg = null;
            try {
                server = msgin.readUTF();
                msgSender = msgin.readUTF();
                msg = msgin.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(Player p : Bukkit.getOnlinePlayers())
            {
                if(p.hasPermission("StaffChat.ModoStaff.Messenger"))
                {
                    p.sendMessage(ChatColor.RED + "[ModoChat] " + ChatColor.AQUA + "("+server+")" + msgSender+ ChatColor.RESET+": "+msg.replace("&","§"));
                }
            }
        }
    }

}
