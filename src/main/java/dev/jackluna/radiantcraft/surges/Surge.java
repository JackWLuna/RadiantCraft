package dev.jackluna.radiantcraft.surges;


import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Base class for the radiant powers, or surges. Not to be confused with Resonances, which are passive abilities.
 */
public class Surge {
    public static final int ADHESION = 0;
    public static final int GRAVITATION = 1;
    public static final int DIVISION = 2;
    public static final int ABRASION = 3;
    public static final int PROGRESSION = 4;
    public static final int ILLUMINATION = 5;
    public static final int TRANSFORMATION = 6;
    public static final int TRANSPORTATION = 7;
    public static final int COHESION = 8;
    public static final int TENSION = 9;


    //Stores the level of the ability. For some abilities this is 1, for others this is dependent on ideal.
    private int level;


    private final int SURGE_ID;

    //Contains the stormlight cost. For whileheld abilities, this is per second.
    private float stormlightCost;


    public Surge(int SURGE_ID){
        level = 1;
        this.SURGE_ID = SURGE_ID;
    }
    /**
     * Returns the level of the surge.
     * @return The level of the surge.
     */
    public int getLevel(){
        return level;
    }

    /**
     * Sets the level of the ability
     * @param level The new level of the ability
     */
    public void setLevel(int level){
        this.level = level;
    }

    /**
     * Must send packets or otherwise execute the onPressed logic for the surge. Override this!
     */
    public void onPressed(){

    }

    /**
     * Must send packets or otherwise execute the onReleased logic for the surge. Override this!
     */
    public void onReleased(){

    }

    public static BlockPos getTargetBlock(Player player, Level level, boolean placing){
        BlockHitResult res = getBlockLooking(player,level);
        if(placing){
            return res.getBlockPos().relative(res.getDirection());
        }
        return res.getBlockPos();
    }

    public static BlockPos getTargetBlock(Player player, Level level){
        return getTargetBlock(player,level,false);
    }

    public static BlockHitResult getBlockLooking(Player player, Level level){
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3 = player.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        //double d0 = player.getReachDistance();
        double d0 = player.getReachDistance()*2;
        Vec3 vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
    }


    public float getStormlightCost() {
        return stormlightCost;
    }

    public void setStormlightCost(float stormlightCost) {
        this.stormlightCost = stormlightCost;
    }

    public int getSURGE_ID() {
        return SURGE_ID;
    }
}
