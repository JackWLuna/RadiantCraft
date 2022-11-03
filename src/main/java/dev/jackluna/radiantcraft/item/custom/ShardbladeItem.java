package dev.jackluna.radiantcraft.item.custom;

import dev.jackluna.radiantcraft.RadiantCraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RadiantCraft.MOD_ID)
public class ShardbladeItem extends SwordItem {
    public static final Tier DEFAULT_TIER = Tiers.NETHERITE;
    public static final int DEFAULT_ATTACK_DAMAGE = 10;
    public static final float DEFAULT_ATTACK_SPEED = 5f;
    public ShardbladeItem(Properties properties){
        super(DEFAULT_TIER,DEFAULT_ATTACK_DAMAGE,DEFAULT_ATTACK_SPEED,properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        player.getAbilities().mayfly = !player.getAbilities().mayfly;

        player.onUpdateAbilities();


        return super.use(level, player, hand);


    }

}
