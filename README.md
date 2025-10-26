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
- Registers outgoing and incoming channels.
- Sets the command executor for `/test`.

### **PacketCmd**
- Handles `/test`.
- Creates a `ByteArrayDataOutput` object.
- Writes a `String` and an `int`.
- Sends the data from a player via `player.sendPluginMessage` to the client.

### **PacketListener**
- Implements `PluginMessageListener`.
- Handles incoming plugin messages.
- Reads the same `String` and `int` sent from the client.
- Logs the received data.

> **Important notes**
> - Keep channel names ≤ 20 characters (or will not work at all).
> - Data reading/writing order must match exactly.
> - Incoming messages are handled on the main server thread – safe to interact with Bukkit API.
> - The data can't exceed 32KB
> - You should never ever use that system to send a huge amount of packet (for example every ticks) but only for a specific action