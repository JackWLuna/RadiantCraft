package dev.jackluna.radiantcraft;

import com.mojang.logging.LogUtils;
import dev.jackluna.radiantcraft.item.ModItems;
import dev.jackluna.radiantcraft.item.custom.ShardbladeItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RadiantCraft.MOD_ID)
public class RadiantCraft
{
    public static final String MOD_ID = "radiantcraft";
    private static final Logger LOGGER = LogUtils.getLogger();




    public RadiantCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }


    }

//    @SubscribeEvent
//    public static void honorbladePowers(TickEvent.PlayerTickEvent e) {
//
//        if(e.player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ShardbladeItem sb) {
//            e.player.getAbilities().mayfly = true;
//            e.player.sendSystemMessage(Component.literal("Holding"));
//        } else {
//            e.player.getAbilities().mayfly = false;
//            e.player.sendSystemMessage(Component.literal("Not Holding"));
//
//        }
//        e.player.onUpdateAbilities();
//
//    }


}
