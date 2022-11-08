package dev.jackluna.radiantcraft.networking.packets;

import dev.jackluna.radiantcraft.stormlight.PlayerStormlightProvider;
import dev.jackluna.radiantcraft.surges.Surge;
import dev.jackluna.radiantcraft.util.StormlightUseResult;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.network.NetworkEvent;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.function.Supplier;

public class DivisionPressedC2S {
    public DivisionPressedC2S(int magnitude) {

    }

    public DivisionPressedC2S(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {


    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //ON SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            StormlightUseResult result = new StormlightUseResult();

            player.getCapability(PlayerStormlightProvider.PLAYER_STORMLIGHT).ifPresent(stormlight -> {

            });

            if(result.result) {
                BlockPos target = Surge.getTargetBlock(player, level, true);

                ArrayList<BlockPos> coverBlocks = new ArrayList<BlockPos>();
                BlockState blockstate1 = BaseFireBlock.getState(level, target);
                level.setBlock(target, blockstate1, 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, target);
            }

        });
        return true;

    }


}
