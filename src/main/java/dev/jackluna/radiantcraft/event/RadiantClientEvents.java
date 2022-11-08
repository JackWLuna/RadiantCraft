package dev.jackluna.radiantcraft.event;

import dev.jackluna.radiantcraft.RadiantCraft;
import dev.jackluna.radiantcraft.stormlight.PlayerStormlightProvider;
import dev.jackluna.radiantcraft.surges.DivisionSurge;
import dev.jackluna.radiantcraft.surges.TransportationSurge;
import dev.jackluna.radiantcraft.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class RadiantClientEvents {
    static boolean primaryHeld = false;
    boolean secondaryHeld = false;
    @Mod.EventBusSubscriber(modid = RadiantCraft.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {




        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.PRIMARY_SURGE);
            event.register(KeyBinding.SECONDARY_SURGE);
        }

        /**
         *
         * @param event
         */
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.PRIMARY_SURGE.consumeClick()){
                primaryHeld = true;
                Minecraft.getInstance().player.getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(stormlight -> {
                    stormlight.getPrimarySurge().onPressed();
                });
            }
            //Executes if the primaryHeld key is released.
            if(primaryHeld && !KeyBinding.PRIMARY_SURGE.isDown()){
                primaryHeld = false;
                Minecraft.getInstance().player.getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(stormlight -> {
                    stormlight.getPrimarySurge().onReleased();
                });
            }



        }

    }
}
