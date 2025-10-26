package com.alihaine.testpacket;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPacket extends JavaPlugin {
    public static TestPacket testPacket;

    /*
     * Choose a unique channel name
     * The name length can't exceed 20 chars
     * Recommended to follow the convention CUS|name (custom)
     */
    public static final String CHANNEL = "CUS|testtesttesttest";

    @Override
    public void onEnable() {
        testPacket = this;

        //Register Outgoing messages, tells to spigot 'my plugin will send data on that channel'
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, CHANNEL);
        //Register Incoming messages, tells to spigot 'my plugin wants to get all data on that channel'
        this.getServer().getMessenger().registerIncomingPluginChannel(this, CHANNEL, new PacketListener());

        this.getCommand("test").setExecutor(new PacketCmd());

        //Recommended to print the registered channels to be sure that all was loaded.
        Bukkit.getLogger().info("Registered out channels: " + Bukkit.getMessenger().getOutgoingChannels());
        Bukkit.getLogger().info("Registered in channels: " + Bukkit.getMessenger().getIncomingChannels());

        Bukkit.getLogger().info("Plugin has been enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin has been disabled");
    }
}
