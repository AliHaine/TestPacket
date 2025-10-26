package com.alihaine.testpacket;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/*
 * Implements PluginMessageListener to catch and do something when
 * data is coming in the target channel
 */
public class PacketListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] data) {
        Bukkit.getLogger().info("Received data from client for the channel: " +  channel + " for the player: " + player.getName());

        //Convert the byte[] data to a usable object
        ByteArrayDataInput in = ByteStreams.newDataInput(data);
        //Example to read a value of type string
        String msg = in.readUTF();
        //Example to read a value of type int
        int num = in.readInt();

        Bukkit.getLogger().info("message received: " + msg + " int: " + num);
    }
}
