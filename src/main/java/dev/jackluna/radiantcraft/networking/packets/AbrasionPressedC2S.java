package dev.jackluna.radiantcraft.networking.packets;

import com.mojang.authlib.properties.Property;
import dev.jackluna.radiantcraft.stormlight.PlayerStormlightProvider;
import dev.jackluna.radiantcraft.surges.Surge;
import dev.jackluna.radiantcraft.util.StormlightUseResult;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

public class AbrasionPressedC2S {
    public AbrasionPressedC2S(int magnitude) {

    }

    public AbrasionPressedC2S(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {


    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //ON SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            //StormlightUseResult result = new StormlightUseResult();

            player.getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(stormlight -> {
                stormlight.setWhileHeld(Surge.ABRASION, true);
            });


        });
        return true;

    }


}
