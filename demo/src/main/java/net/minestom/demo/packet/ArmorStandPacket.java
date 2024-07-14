package net.minestom.demo.packet;


import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.EquipmentSlot;
import net.minestom.server.entity.Metadata;
import net.minestom.server.entity.metadata.other.ArmorStandMeta;
import net.minestom.server.instance.Instance;
import net.minestom.server.item.ItemStack;
import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.play.*;

import javax.swing.text.Position;
import java.util.*;

public class ArmorStandPacket {

    public static ServerPacket armorStandDestroyPacket(int id) {
        return new DestroyEntitiesPacket(id);
    }

    public static ServerPacket armorStandTeleportPacket(int id, Pos pos) {
        return new EntityTeleportPacket(id, pos, false);
    }

    public static ServerPacket armorStandEquipPacket(int id, ItemStack item) {
        Map<EquipmentSlot, ItemStack> items = new HashMap<EquipmentSlot, ItemStack>();
        items.put(EquipmentSlot.HELMET, item);
        return new EntityEquipmentPacket(id, items);
    }

    public static ServerPacket armorStandMetaPacket(int id) {
        Map<Integer, Metadata.Entry<?>> entries = new HashMap<>();
        entries.put(0, Metadata.Byte((byte) (0x01 | 0x20)));
        entries.put(15, Metadata.Byte((byte) (0x01 | 0x08 | 0x10)));
        return new EntityMetaDataPacket(id, entries);
    }

    public static ServerPacket armorStandMetaPacket(int id, Pos location) {
        float yaw = location.yaw();
        float pitch = location.pitch();
        double radianYaw = Math.toRadians(-yaw + 90); // +90 是为了调整北方为0度
        double radianPitch = Math.toRadians(pitch);
        Map<Integer, Metadata.Entry<?>> entries = new HashMap<>();
        entries.put(0, Metadata.Byte((byte) (0x01 | 0x20)));
        entries.put(15, Metadata.Byte((byte) (0x01 | 0x08 | 0x10)));
        entries.put(16, Metadata.Vector3(new Pos( Math.toDegrees(radianPitch) , Math.toDegrees(0), Math.toDegrees(0))));
        return new EntityMetaDataPacket(id, entries);
    }

    public static ServerPacket armorStandPacket(Pos location, int id, UUID uuid) {
        return new SpawnEntityPacket(
                id,
                uuid,
                EntityType.ARMOR_STAND.id(),
                location,
                (float) 0,
                0,
                (short) 0,
                (short) 0,
                (short) 0
        );
    }

    public static ServerPacket armorStandPacket(Pos location, int id, UUID uuid, int yaw, int pitch) {
        return new SpawnEntityPacket(
                id,
                uuid,
                EntityType.ARMOR_STAND.id(),
                location,
                (float) (yaw / 360.0) * 256,
                0,
                (short) 0,
                (short) 0,
                (short) 0
        );
    }

}
