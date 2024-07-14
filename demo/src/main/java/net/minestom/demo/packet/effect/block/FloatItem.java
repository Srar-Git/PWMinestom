package net.minestom.demo.packet.effect.block;

import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;
import net.minestom.server.sound.SoundEvent;

/**
 * @author pyf
 * @description
 */

public class FloatItem {
    private boolean sound = true;
    private boolean glowing = false;
    private SoundEvent breakSound = SoundEvent.BLOCK_STONE_BREAK;
    private float breakVolume = 1f;
    private float breakPitch = 0.9f;
    private long breakSoundSeed = -4944752074109353836L;
    private SoundEvent pickupSound = SoundEvent.ENTITY_ITEM_PICKUP;
    private float pickupVolume = 0.2f;
    private float pickupPitch = 2f;
    private Material material = Material.AIR;

    public FloatItem() {
    }

    public FloatItem(Material material) {
        this.material = material;
    }

    public FloatItem(boolean sound, Material material) {
        this.sound = sound;
        this.material = material;
    }

    public FloatItem(boolean sound, boolean glowing, Material material) {
        this.sound = sound;
        this.glowing = glowing;
        this.material = material;
    }

    public FloatItem(boolean sound, boolean glowing, SoundEvent breakSound, float breakVolume, float breakPitch, SoundEvent pickupSound, float pickupVolume, float pickupPitch, Material material) {
        this.sound = sound;
        this.glowing = glowing;
        this.breakSound = breakSound;
        this.breakVolume = breakVolume;
        this.breakPitch = breakPitch;
        this.pickupSound = pickupSound;
        this.pickupVolume = pickupVolume;
        this.pickupPitch = pickupPitch;
        this.material = material;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean isGlowing() {
        return glowing;
    }

    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
    }

    public SoundEvent getBreakSound() {
        return breakSound;
    }

    public void setBreakSound(SoundEvent breakSound) {
        this.breakSound = breakSound;
    }

    public long getBreakSoundSeed() {
        return breakSoundSeed;
    }

    public float getBreakVolume() {
        return breakVolume;
    }

    public void setBreakVolume(float breakVolume) {
        this.breakVolume = breakVolume;
    }

    public float getBreakPitch() {
        return breakPitch;
    }

    public void setBreakPitch(float breakPitch) {
        this.breakPitch = breakPitch;
    }

    public SoundEvent getPickupSound() {
        return pickupSound;
    }

    public void setPickupSound(SoundEvent pickupSound) {
        this.pickupSound = pickupSound;
    }

    public float getPickupVolume() {
        return pickupVolume;
    }

    public void setPickupVolume(float pickupVolume) {
        this.pickupVolume = pickupVolume;
    }

    public float getPickupPitch() {
        return pickupPitch;
    }

    public void setPickupPitch(float pickupPitch) {
        this.pickupPitch = pickupPitch;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
