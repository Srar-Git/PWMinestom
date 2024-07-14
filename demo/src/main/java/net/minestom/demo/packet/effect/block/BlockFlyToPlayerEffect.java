package net.minestom.demo.packet.effect.block;

import net.minestom.demo.packet.FlyEffect;
import net.minestom.demo.packet.SoundPacket;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.timer.Scheduler;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;

import java.util.concurrent.atomic.AtomicInteger;

public class BlockFlyToPlayerEffect {

    public static final String TYPE = "block";
    private final Vec BLOCK_LOC_OFFSET_UP = new Vec(0.5, 0.45, 0.5);
    private final Vec BLOCK_LOC_OFFSET_DOWN = new Vec(0.5, -1.1, 0.5);
    private final Vec BLOCK_LOC_OFFSET_EAST = new Vec(1.3, -0.35, 0.5);
    private final Vec BLOCK_LOC_OFFSET_WEST = new Vec(-0.3, -0.35, 0.5);
    private final Vec BLOCK_LOC_OFFSET_SOUTH = new Vec(0.5, -0.35, 1.3);
    private final Vec BLOCK_LOC_OFFSET_NORTH = new Vec(0.5, -0.35, -0.3);

    private String name;
    private BlockFace blockFace = BlockFace.TOP;
    private Vec blockLocOffset = BLOCK_LOC_OFFSET_UP;
    private FloatItem floatItem = new FloatItem();
    private long animationTick = 1;
    private Pos blockLoc;

    private Vec getItemOffset(){
        if (this.blockLocOffset == BLOCK_LOC_OFFSET_UP){
            return BLOCK_LOC_OFFSET_UP.sub(new Vec(0, 0.25,0));
        }else if (this.blockLocOffset == BLOCK_LOC_OFFSET_DOWN){
            return BLOCK_LOC_OFFSET_DOWN.sub(new Vec(0, 0.35,0));
        }else if (this.blockLocOffset == BLOCK_LOC_OFFSET_EAST){
            return BLOCK_LOC_OFFSET_EAST.sub(new Vec(0, 0.25,0));
        }else if (this.blockLocOffset == BLOCK_LOC_OFFSET_WEST){
            return BLOCK_LOC_OFFSET_WEST.sub(new Vec(0, 0.25,0));
        }else if (this.blockLocOffset == BLOCK_LOC_OFFSET_SOUTH){
            return BLOCK_LOC_OFFSET_SOUTH.sub(new Vec(0, 0.25,0));
        }else if (this.blockLocOffset == BLOCK_LOC_OFFSET_NORTH){
            return BLOCK_LOC_OFFSET_NORTH.sub(new Vec(0, 0.25,-0.15));
        }
        return BLOCK_LOC_OFFSET_UP;
    }

    /**
     * 最基本的特效，根据方块决定，有声音.
     */
    public BlockFlyToPlayerEffect(Material material, Pos blockLoc, BlockFace blockFace) {
        this.blockLoc = blockLoc;
        this.floatItem = new FloatItem(true, material);
        if (blockFace.equals(BlockFace.BOTTOM)){
            this.blockLocOffset = BLOCK_LOC_OFFSET_DOWN;
        } else if (blockFace.equals(BlockFace.WEST)) {
            this.blockLocOffset = BLOCK_LOC_OFFSET_WEST;
        }else if (blockFace.equals(BlockFace.EAST)) {
            this.blockLocOffset = BLOCK_LOC_OFFSET_EAST;
        }else if (blockFace.equals(BlockFace.SOUTH)) {
            this.blockLocOffset = BLOCK_LOC_OFFSET_SOUTH;
        }else if (blockFace.equals(BlockFace.NORTH)) {
            this.blockLocOffset = BLOCK_LOC_OFFSET_NORTH;
        }
        else {
            this.blockLocOffset = BLOCK_LOC_OFFSET_UP;
        }
    }

    public void start(Player player) {
        ItemStack item = ItemStack.of(this.floatItem.getMaterial());
        if (this.floatItem.isSound()) SoundPacket.playSound(player, this.floatItem.getBreakSound(), this.floatItem.getBreakVolume(), this.floatItem.getBreakPitch(),this.floatItem.getBreakSoundSeed());
        Pos beginLoc = this.blockLoc;
        if (this.floatItem.getMaterial().isBlock()) beginLoc = this.blockLoc.add(this.blockLocOffset);
        else beginLoc = this.blockLoc.add(getItemOffset());
        int id = FlyEffect.startFlowBlock(player, beginLoc, item);
        
        
        Scheduler scheduler = MinecraftServer.getSchedulerManager();

        int yaw = 0;
        AtomicInteger taskCounter = new AtomicInteger();
        double upDown = 0.05;
        AtomicInteger upTimes = new AtomicInteger();
        Pos finalBeginLoc = beginLoc;
        scheduler.submitTask(() -> {
            Pos nowLoc = finalBeginLoc;
            if (taskCounter.get() < 6) {
                if (this.blockFace.equals(BlockFace.TOP)) {
                    nowLoc = nowLoc.add(0, upDown * (1 + taskCounter.get()), 0);
                }
                else if (this.blockFace.equals(BlockFace.BOTTOM)) {
                    nowLoc = nowLoc.sub(0, upDown * (1 + taskCounter.get()), 0);
                } else if (this.blockFace.equals(BlockFace.EAST)) {
                    nowLoc = nowLoc.add(upDown * (1 + taskCounter.get()), 0, 0);
                } else if (this.blockFace.equals(BlockFace.WEST)) {
                    nowLoc = nowLoc.sub(upDown * (1 + taskCounter.get()), 0, 0);
                } else if (this.blockFace.equals(BlockFace.SOUTH)) {
                    nowLoc = nowLoc.add(0, 0, upDown * (1 + taskCounter.get()));
                } else {
                    nowLoc = nowLoc.sub(0, 0, upDown * (1 + taskCounter.get()));
                }
                upTimes.incrementAndGet();
            } else if (taskCounter.get() < 10 && taskCounter.get() >= 6) {
                if (this.blockFace.equals(BlockFace.TOP)) {
                    nowLoc = nowLoc.sub(0, upDown * (taskCounter.get()-6), 0);
                }
                else if (this.blockFace.equals(BlockFace.BOTTOM)) {
                    nowLoc = nowLoc.add(0, upDown * (taskCounter.get()-6), 0);
                } else if (this.blockFace.equals(BlockFace.EAST)) {
                    nowLoc = nowLoc.sub(upDown * (taskCounter.get()-6), 0, 0);
                } else if (this.blockFace.equals(BlockFace.WEST)) {
                    nowLoc = nowLoc.add(upDown * (taskCounter.get()-6), 0, 0);
                } else if (this.blockFace.equals(BlockFace.SOUTH)) {
                    nowLoc = nowLoc.sub(0, 0, upDown * (taskCounter.get()-6));
                } else {
                    nowLoc = nowLoc.add(0, 0, upDown * (taskCounter.get()-6));
                }
            } else {
                // 获取玩家当前位置
                Pos playerLoc = player.getPosition();
                // 计算新的实体位置
                double x = finalBeginLoc.x() + (playerLoc.x() - finalBeginLoc.x()) * (taskCounter.get() / animationTick + 1) / 15;
                double y = finalBeginLoc.y() + (playerLoc.y() - finalBeginLoc.y()) * (taskCounter.get() / animationTick + 1) / 15;
                double z = finalBeginLoc.z() + (playerLoc.z() - finalBeginLoc.z()) * (taskCounter.get() / animationTick + 1) / 15;
                nowLoc = new Pos(x, y, z);

            }
            int nowYaw = yaw + taskCounter.get() * 13;
            if (nowYaw < -180) {
                nowYaw += 360;
            }

            FlyEffect.updateFlowBlock(player, nowLoc, id, nowYaw);

            if (taskCounter.get() >= 25) {
                FlyEffect.destroyFlowItem(player, id);
                if (this.floatItem.isSound()) SoundPacket.playSound(player, this.floatItem.getPickupSound(), this.floatItem.getPickupVolume(), this.floatItem.getPickupPitch(), 0);
                return TaskSchedule.stop();
            }
            taskCounter.getAndIncrement();

            return TaskSchedule.tick((int) animationTick);
        }); // delay task by 20 ticks

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlockFace getBlockFace() {
        return blockFace;
    }

    public void setBlockFace(BlockFace blockFace) {
        this.blockFace = blockFace;
    }

    public Vec getBlockLocOffset() {
        return blockLocOffset;
    }

    public void setBlockLocOffset(Vec blockLocOffset) {
        this.blockLocOffset = blockLocOffset;
    }

    public long getAnimationTick() {
        return animationTick;
    }

    public void setAnimationTick(long animationTick) {
        this.animationTick = animationTick;
    }

    public Pos getBlockLoc() {
        return blockLoc;
    }

    public void setBlockLoc(Pos  blockLoc) {
        this.blockLoc = blockLoc;
    }
}
