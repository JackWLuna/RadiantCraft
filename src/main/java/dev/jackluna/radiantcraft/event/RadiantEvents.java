package dev.jackluna.radiantcraft.event;

import com.google.common.eventbus.Subscribe;
import dev.jackluna.radiantcraft.RadiantCraft;
import dev.jackluna.radiantcraft.item.ModItems;
import dev.jackluna.radiantcraft.item.custom.ShardbladeItem;
import dev.jackluna.radiantcraft.stormlight.PlayerStormlight;
import dev.jackluna.radiantcraft.stormlight.PlayerStormlightProvider;
import dev.jackluna.radiantcraft.surges.Surge;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RadiantCraft.MOD_ID)
public class RadiantEvents {
    private static Vec3 momentum = new Vec3(1,1,1);
    private static boolean isTryingMove = false;


    @SubscribeEvent
    public static void equipmentChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player && (event.getSlot() == EquipmentSlot.MAINHAND || event.getSlot() == EquipmentSlot.OFFHAND)) {
            if(event.getTo().is(ModItems.SHARDBLADE_DEFAULT.get())) {

                player.getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(stormlight -> {
                    //stormlight.addSPS(10);
                });

                player.getAbilities().mayfly = true; //TODO: test with other flight mods to make sure I'm not breaking things
            } else {
                if(!player.isCreative()) {
                    player.getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(stormlight -> {
                        //stormlight.resetSPS();
                    });
                    player.getAbilities().mayfly = false;
                    player.getAbilities().flying = false;
                }

            }
            player.onUpdateAbilities();
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).isPresent()){
                event.addCapability(new ResourceLocation(RadiantCraft.MOD_ID, "properties"), new PlayerStormlightProvider());
            }
        }


    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(PlayerStormlight.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.side == LogicalSide.SERVER) {

            //Process Serverside surgebinding per-tick effects.
            event.player.getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(stormlight -> {

                //Natural stormlight decay over time
                if(stormlight.getStormlight() > 0 && event.player.getRandom().nextFloat() < 0.05f) {
                    stormlight.subStormlight(stormlight.getDecayRate());
                }

                //Abrasion is active
                if(stormlight.isActive(Surge.ABRASION)){

                    event.player.sendSystemMessage(Component.literal("Abrasing!"));

                }

            });

        } else {

//            if(Minecraft.getInstance().options.keyUp.isDown() || Minecraft.getInstance().options.keyDown.isDown()
//                    || Minecraft.getInstance().options.keyLeft.isDown() || Minecraft.getInstance().options.keyRight.isDown()){
//                isTryingMove = true;
//            } else {
//                isTryingMove = false;
//            }
//
//            event.player.sendSystemMessage(Component.literal("" + event.player.getDeltaMovement()));
//            if(!isTryingMove){
//                momentum = Math.max(1, momentum - .001);
//            } else if(event.player.isOnGround()) {
//                momentum = Math.min(2, momentum + .001);
//            }
//            if(event.player.isOnGround()) {
//                event.player.setDeltaMovement(Math.min(event.player.getDeltaMovement().x * momentum, .3),
//                        event.player.getDeltaMovement().y,
//                        Math.min(event.player.getDeltaMovement().z * momentum, .3)); //Causes hyperspeed. Gonna have to check inputs
//            }
        }

    }


    @SubscribeEvent
    public static void cancelActiveAbilities(OutOfStormlightEvent event){
        Player player = event.getEntity();

        //Todo: find a more systematic way to disable active abilities.
        if(!player.isCreative()) {
            player.getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(stormlight -> {
                //stormlight.addSPS(-10);
            });
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
        }

    }








}
