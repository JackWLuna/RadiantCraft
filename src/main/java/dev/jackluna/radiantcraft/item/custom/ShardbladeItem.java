package dev.jackluna.radiantcraft.item.custom;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class ShardbladeItem extends SwordItem {
    public static final Tier DEFAULT_TIER = Tiers.NETHERITE;
    public static final int DEFAULT_ATTACK_DAMAGE = 10;
    public static final float DEFAULT_ATTACK_SPEED = 5f;
    public ShardbladeItem(Properties properties){
        super(DEFAULT_TIER,DEFAULT_ATTACK_DAMAGE,DEFAULT_ATTACK_SPEED,properties);
    }

}
