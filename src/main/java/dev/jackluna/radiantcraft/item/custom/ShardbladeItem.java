package dev.jackluna.radiantcraft.item.custom;

import dev.jackluna.radiantcraft.RadiantCraft;
import dev.jackluna.radiantcraft.networking.ModMessages;
import dev.jackluna.radiantcraft.networking.packets.DivisionPressedC2S;
import dev.jackluna.radiantcraft.stormlight.PlayerStormlightProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RadiantCraft.MOD_ID)
public class ShardbladeItem extends SwordItem {
    public static final Tier DEFAULT_TIER = Tiers.NETHERITE;
    public static final int DEFAULT_ATTACK_DAMAGE = 10;
    public static final float DEFAULT_ATTACK_SPEED = 5f;

    public static final float SPS = 5f;
    public ShardbladeItem(Properties properties){
        super(DEFAULT_TIER,DEFAULT_ATTACK_DAMAGE,DEFAULT_ATTACK_SPEED,properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide()) {
            player.getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(stormlight -> {
                stormlight.resetStormlight();
                stormlight.addStormlight(100); //Placeholder
                player.sendSystemMessage(Component.literal("Reset stormlight"));
            });
            ModMessages.sendToServer(new DivisionPressedC2S(3));
        }
        return super.use(level, player, hand);
    }
}
