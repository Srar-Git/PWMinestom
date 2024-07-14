package net.minestom.demo.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

public class SoundPacket {

    public static void playSound(Player player, String soundName, float v, float p) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.NAMED_SOUND_EFFECT);
        
        packet.getSoundEffects().write(0, Sound.valueOf(soundName));
        packet.getSoundCategories().write(0, EnumWrappers.SoundCategory.BLOCKS);
        packet.getIntegers().write(0,player.getEyeLocation().getBlockX() * 8);
        packet.getIntegers().write(1,player.getEyeLocation().getBlockY() * 8);
        packet.getIntegers().write(2,player.getEyeLocation().getBlockZ() * 8);
        packet.getFloat().write(0,v);
        packet.getFloat().write(1,p);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.sendServerPacket(player, packet);
    }
     public static void playSound(Player player, Sound sound, float v, float p) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.NAMED_SOUND_EFFECT);

        packet.getSoundEffects().write(0, sound);
        packet.getSoundCategories().write(0, EnumWrappers.SoundCategory.BLOCKS);
        packet.getIntegers().write(0,player.getEyeLocation().getBlockX() * 8);
        packet.getIntegers().write(1,player.getEyeLocation().getBlockY() * 8);
        packet.getIntegers().write(2,player.getEyeLocation().getBlockZ() * 8);
        packet.getFloat().write(0,v);
        packet.getFloat().write(1,p);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.sendServerPacket(player, packet);
    }


    public void sendProtocolLibPacket2(Player player, String soundName) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_SOUND);
        packet.getSoundEffects().write(0, Sound.BLOCK_STONE_BREAK);
        packet.getSoundCategories().write(0, EnumWrappers.SoundCategory.BLOCKS);
        packet.getIntegers().write(0,player.getEyeLocation().getBlockX() * 8);
        packet.getIntegers().write(1,player.getEyeLocation().getBlockY() * 8);
        packet.getIntegers().write(2,player.getEyeLocation().getBlockZ() * 8);
        packet.getFloat().write(0,1f);
        packet.getFloat().write(1,1f);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.sendServerPacket(player, packet);
    }
    public static void sendBlockEffectPacket(Location location, Player player) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.WORLD_EVENT);
        packet.getIntegers().write(0,2001);
        BlockPosition blockPosition = new BlockPosition(location.getBlockX(),location.getBlockY(),location.getBlockZ());
        packet.getBlockPositionModifier().write(0,blockPosition);
        BlockData blockData = Material.STONE.createBlockData();
        int id = Material.STONE.createBlockData().getAsString().hashCode();
        packet.getIntegers().write(1, 1);
        packet.getBooleans().write(0,false);
        manager.sendServerPacket(player, packet);
    }


//        // 创建声音包
//        ClientboundSoundPacket clientboundSoundPacket = new ClientboundSoundPacket();
//
//        PacketPlayOutNamedSoundEffect soundPacket = new PacketPlayOutNamedSoundEffect(soundEffect, category, location.getX(), location.getY(), location.getZ(), volume, pitch);
//
//        // 发送声音包给玩家
//        craftPlayer.getHandle().b.a(soundPacket);




//    public void addSoundEffectListener(Plugin plugin) {
//        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
//
//        protocolManager.addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.NAMED_SOUND_EFFECT) {
//            @Override
//            public void onPacketSending(PacketEvent event) {
//                if (event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT) {
//                    PacketContainer packet = event.getPacket();
//                    Bukkit.broadcastMessage(packet.toString());
//                    // 解析数据包内容
//                    String soundName = packet.getStrings().read(0);
//                    EnumWrappers.SoundCategory category = packet.getSoundCategories().read(0);
//                    int x = packet.getIntegers().read(0) / 8; // 转换为标准坐标
//                    int y = packet.getIntegers().read(1) / 8; // 转换为标准坐标
//                    int z = packet.getIntegers().read(2) / 8; // 转换为标准坐标
//                    float pitch = packet.getFloat().read(0);
//                    float volume = packet.getFloat().read(1);
//
//                    // 执行你的逻辑（例如打印信息）
//                    System.out.println("Sound Effect: " + soundName);
//                    System.out.println("Category: " + category);
//                    System.out.println("Location: (" + x + ", " + y + ", " + z + ")");
//                    System.out.println("Pitch: " + pitch);
//                    System.out.println("Volume: " + volume);
//                }
//            }
//        });
//    }

}
