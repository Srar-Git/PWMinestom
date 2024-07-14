package net.minestom.demo.packet;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.network.packet.server.ServerPacket;
import java.util.Random;
import java.util.UUID;
import static net.minestom.demo.packet.ArmorStandPacket.*;

public class FlyEffect {

    public static void destroyFlowItem(Player player, int id) {
        ServerPacket armorStandDestroyPacket = armorStandDestroyPacket(id);
        player.sendPacket(armorStandDestroyPacket);
    }

    public static int startFlowBlock(Player player, Pos location, ItemStack item) {
        Random random = new Random();
        int id = random.nextInt(999999999);
        UUID uuid = UUID.randomUUID();
        ServerPacket armorStandPacket = armorStandPacket(location, id, uuid);
        ServerPacket armorStandMetaPacket = armorStandMetaPacket(id);
        ServerPacket armorStandEquipPacket = armorStandEquipPacket(id, item);
        player.sendPacket(armorStandPacket);
        player.sendPacket(armorStandMetaPacket);
        player.sendPacket(armorStandEquipPacket);
        return id;
    }

    public static void updateFlowBlock(Player player, Pos location, int id, int yaw) {
        Pos l = new Pos(location.x(), location.y(), location.z(), yaw, location.pitch());
        ServerPacket armorStandTeleportPacket = armorStandTeleportPacket(id, l);
        player.sendPacket(armorStandTeleportPacket);
    }
}
