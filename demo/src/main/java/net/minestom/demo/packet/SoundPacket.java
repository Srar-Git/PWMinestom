package net.minestom.demo.packet;


import net.kyori.adventure.sound.Sound;
import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.server.play.SoundEffectPacket;
import net.minestom.server.sound.SoundEvent;

public class SoundPacket {

     public static void playSound(Player player, SoundEvent sound, float v, float p, long seed) {
        SoundEffectPacket packet = new SoundEffectPacket(sound, Sound.Source.BLOCK, player.getPosition(), v, p, seed);
        player.sendPacket(packet);
    }

}
