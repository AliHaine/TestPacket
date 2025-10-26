# TestPacket

_A Minecraft plugin that demonstrates sending and receiving custom data through packets between a server plugin and a (modded) client._

**Features:**
- Send a simple string + integer payload to a player.
- Listen for messages from clients on a custom channel.
- Logs all sent and received messages to the server console.

**Channel Naming Convention:**
- Custom channels must **not exceed 20 characters**.
- Recommended format: `CUS|yourchannelname`.

## Code Overview

### **Plugin Main Class: TestPacket**

```java
//Choose a unique channel name, the name length can't exceed 20 chars.
//Recommended to follow the convention CUS|name (custom)
public static final String CHANNEL = "CUS|testtesttesttest";

//Register Outgoing messages, tells to spigot 'my plugin will send data on that channel'
this.getServer().getMessenger().registerOutgoingPluginChannel(this, CHANNEL);

//Register Incoming messages, tells to spigot 'my plugin wants to get all data on that channel'
this.getServer().getMessenger().registerIncomingPluginChannel(this, CHANNEL, new PacketListener());

//Recommended to print the registered channels to be sure that all was loaded.
Bukkit.getLogger().info("Registered out channels: " + Bukkit.getMessenger().getOutgoingChannels());
Bukkit.getLogger().info("Registered in channels: " + Bukkit.getMessenger().getIncomingChannels());
```

### **PacketListener**
```java
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
```

### **PacketCmd**
```java
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
```

⚠️ **Important notes**
- Keep channel names ≤ 20 characters (or will not work at all).
- Data reading/writing order must match exactly.
- Incoming messages are handled on the main server thread – safe to interact with Bukkit API.
- The data can't exceed 32KB
- Avoid sending large amounts of packets frequently (e.g., every tick); only use this system for specific actions.