package com.simpleduino.staffchat.Commands;

import com.simpleduino.staffchat.Messaging.CustomMessageSender;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Simple-Duino on 16/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class HelperChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length >= 1) {
            String msg = "";
            for(int i = 0;i<args.length;i++)
            {
                msg+=args[i]+" ";
            }
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("StaffChat.HelperStaff.Messenger")) {
                    for (Player p1 : Bukkit.getOnlinePlayers()) {
                        if (p1.hasPermission("StaffChat.HelperStaff.Messenger")) {
                            // p1.sendMessage(ChatColor.RED + "[HelperChat] " + ChatColor.AQUA + "("+Bukkit.getServer().getServerName()+")" + p.getName()+ChatColor.RESET+": "+msg.replace("&","§"));
                            p1.sendMessage(ChatColor.RED + "[HelperChat] " + ChatColor.AQUA  + p.getName()+ChatColor.RESET+": "+msg.replace("&","§"));
                        }
                    }
                    new CustomMessageSender("ALL", "HelperChatMessage", new String[]{Bukkit.getServerName(), p.getName(), msg});
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'envoyer des messages staff");
                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"Seul un joueur peut exécuter cette commande");
            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Vous devez spécifier un message: /ca <message>");
        }
        return false;
    }
}
