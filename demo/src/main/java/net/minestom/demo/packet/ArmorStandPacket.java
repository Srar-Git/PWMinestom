package net.minestom.demo.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.comphenix.protocol.wrappers.Vector3F;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.EquipmentSlot;
import net.minestom.server.entity.Metadata;
import net.minestom.server.item.ItemStack;
import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.play.EntityEquipmentPacket;
import net.minestom.server.network.packet.server.play.EntityMetaDataPacket;
import net.minestom.server.network.packet.server.play.EntityTeleportPacket;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.*;

public class ArmorStandPacket {

    public static ServerPacket armorStandDestroyPacket(int id) {
        EnTITYd packet = EntityTeleportPacket
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        packet.getModifier().write(0, new IntArrayList(new int[]{id}));
        return packet;
    }

    public static ServerPacket armorStandTeleportPacket(int id, Pos pos) {
        EntityTeleportPacket packet = new EntityTeleportPacket(id, pos, false);
//        packet.getIntegers().write(0, id);
//        packet.getDoubles().write(0, loc.getX());
//        packet.getDoubles().write(1, loc.getY());
//        packet.getDoubles().write(2, loc.getZ());
//        packet.getBytes().write(0, (byte) (yaw * 256.0F / 360.0F));
//        packet.getBytes().write(1, (byte) (pitch * 256.0F / 360.0F));
//        packet.getBooleans().write(0, false);
        return packet;
    }

    public static ServerPacket armorStandEquipPacket(int id, ItemStack item) {
        Map<EquipmentSlot, ItemStack> items = new HashMap<EquipmentSlot, ItemStack>();
        items.put(EquipmentSlot.MAIN_HAND, item);
        EntityEquipmentPacket packet = new EntityEquipmentPacket(id, items);
//        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_EQUIPMENT);
//        packet.getIntegers().write(0, id);
//        List<Pair<EnumWrappers.ItemSlot, ItemStack>> pairs = new ArrayList<>();
//        pairs.add(new Pair<>(EnumWrappers.ItemSlot.HEAD, item));
//        packet.getSlotStackPairLists().writeSafely(0, pairs);
        return packet;
    }

    public static ServerPacket armorStandMetaPacket(int id) {
        Map<Integer, Metadata.Entry<?>> entries = new HashMap<>();
        entries.put(0, new Metadata.Entry<Byte>() {

        })
        EntityMetaDataPacket packet = new EntityMetaDataPacket(id, )
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, id);
//        WrappedChatComponent wrappedChatComponent = WrappedChatComponent.fromText(ChatColor.RED + "TEST");
//        Optional<WrappedChatComponent> opt = Optional.of(wrappedChatComponent);


        WrappedDataWatcher metadata = new WrappedDataWatcher();
        //0
        //Bit mask	Meaning
        //0x01	   Is on fire
        //0x02	   Is crouching
        //0x04	   Unused (previously riding)
        //0x08	   Is sprinting
        //0x10	   Is swimming
        //0x20     Is invisible
        //0x40	   has glowing effect
        //0x80	   Is flying with an elytra
//        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) ( 0x01 ));
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01 | 0x20));
//        Bit mask	Meaning
//        0x01	   Is Small
//        0x04	   Has Arms
//        0x08	   Has no BasePlate
//        0x10	   Is Marker
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01 | 0x08 | 0x10));
        // set head rotation
//        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(16, WrappedDataWatcher.Registry.get(Vector3F.getMinecraftClass())), new Vector3F((float) Math.toDegrees(35), (float) Math.toDegrees(35), (float) Math.toDegrees(35)));
//        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(16, WrappedDataWatcher.Registry.getVectorSerializer()));
        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        return packet;
    }

    public static PacketContainer armorStandMetaPacket(int id, Location location) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, id);
//        WrappedChatComponent wrappedChatComponent = WrappedChatComponent.fromText(ChatColor.RED + "TEST");
//        Optional<WrappedChatComponent> opt = Optional.of(wrappedChatComponent);


        WrappedDataWatcher metadata = new WrappedDataWatcher();
        //0
        //Bit mask	Meaning
        //0x01	   Is on fire
        //0x02	   Is crouching
        //0x04	   Unused (previously riding)
        //0x08	   Is sprinting
        //0x10	   Is swimming
        //0x20     Is invisible
        //0x40	   has glowing effect
        //0x80	   Is flying with an elytra
//        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) ( 0x01 ));
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01 | 0x20));
//        Bit mask	Meaning
//        0x01	   Is Small
//        0x04	   Has Arms
//        0x08	   Has no BasePlate
//        0x10	   Is Marker
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01 | 0x08 | 0x10));
        // set head rotation
        // 获取玩家视角的偏航和俯仰角度
        float yaw = location.getYaw();
        float pitch = location.getPitch();

        // Bukkit的yaw角度是从负180到正180度，而EulerAngle需要的是弧度
        // 将角度转换为弧度，并调整yaw角度使其与ArmorStand的旋转方式一致
        double radianYaw = Math.toRadians(-yaw + 90); // +90 是为了调整北方为0度
        double radianPitch = Math.toRadians(pitch);
        EulerAngle eulerAngle = new EulerAngle(radianPitch, 0, 0);
//        EulerAngle eulerAngle = convertVectorToEulerAngle(direction);
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(16, WrappedDataWatcher.Registry.get(Vector3F.getMinecraftClass())), new Vector3F((float) Math.toDegrees(eulerAngle.getX()), (float) Math.toDegrees(eulerAngle.getY()), (float) Math.toDegrees(eulerAngle.getZ())));
//        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(16, WrappedDataWatcher.Registry.getVectorSerializer()));
        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        return packet;
    }

    private static EulerAngle convertVectorToEulerAngle(Vector vec) {

        double x = vec.getX();
        double y = vec.getY();
        double z = vec.getZ();

        double xz = Math.sqrt(x * x + z * z);

        double eulX;
        if (x < 0) {
            if (y == 0) {
                eulX = Math.PI * 0.5;
            } else {
                eulX = Math.atan(xz / y) + Math.PI;
            }
        } else {
            eulX = Math.atan(y / xz) + Math.PI * 0.5;
        }

        double eulY;
        if (x == 0) {
            if (z > 0) {
                eulY = Math.PI;
            } else {
                eulY = 0;
            }
        } else {
            eulY = Math.atan(z / x) + Math.PI * 0.5;
        }

        return new EulerAngle(eulX, eulY, 0);

    }

    public static PacketContainer armorStandPacket(World world, Location location, int id, UUID uuid, int yaw) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
        packet.getIntegers().write(0, id);
        packet.getUUIDs().write(0, uuid);
        // Set optional velocity (/8000)
        packet.getIntegers().write(1, 0);
        packet.getIntegers().write(2, 0);
        packet.getIntegers().write(3, 0);
        // Set yaw pitch
        packet.getIntegers().write(4, 0);
        packet.getIntegers().write(5, (int) (yaw * 256.0F / 360.0F));
        // Set object data
        packet.getIntegers().write(6, 0);
        // Set location
        packet.getDoubles().write(0, location.getX());
        packet.getDoubles().write(1, location.getY());
        packet.getDoubles().write(2, location.getZ());
        return packet;
    }

    public static PacketContainer armorStandPacket(World world, Location location, int id, UUID uuid, int yaw, int pitch) {
        byte bPitch = (byte) ((pitch / 360.0) * 256);
        byte bYaw = (byte) ((yaw / 360.0) * 256);

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
        packet.getIntegers().write(0, id);
        packet.getUUIDs().write(0, uuid);
        // Set optional velocity (/8000)
        packet.getIntegers().write(1, 0);
        packet.getIntegers().write(2, 0);
        packet.getIntegers().write(3, 0);
        // Set yaw pitch

        packet.getIntegers().write(4, (int) (pitch * 256.0F / 360.0F));
        packet.getIntegers().write(5, (int) (yaw * 256.0F / 360.0F));
//        packet.getBytes().write(0, bPitch);
//        packet.getBytes().write(1, bYaw);
        // Set object data
        packet.getIntegers().write(6, 0);
        // Set location
        packet.getDoubles().write(0, location.getX());
        packet.getDoubles().write(1, location.getY());
        packet.getDoubles().write(2, location.getZ());
        return packet;
    }

}
