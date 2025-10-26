package com.alihaine.testpacket;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PacketCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        Bukkit.getLogger().info("Sending data..");

        //Set up a data object to write and send our data
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        //Example to write a String in the data object
        out.writeUTF("Hello client! im " + player.getName());
        //Example to write an int in the data object
        out.writeInt(123);

        //Send the message using the Minecraft API
        player.sendPluginMessage(
                TestPacket.testPacket,
                TestPacket.CHANNEL,
                out.toByteArray() //Convert to byte so the client can decompile and read it.
        );
        return true;
    }
}
