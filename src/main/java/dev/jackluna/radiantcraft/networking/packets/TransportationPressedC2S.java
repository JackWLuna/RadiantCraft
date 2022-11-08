package dev.jackluna.radiantcraft.networking.packets;

import dev.jackluna.radiantcraft.stormlight.PlayerStormlightProvider;
import dev.jackluna.radiantcraft.surges.Surge;
import dev.jackluna.radiantcraft.surges.TransportationSurge;
import dev.jackluna.radiantcraft.util.StormlightUseResult;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TransportationPressedC2S {
    List<ResourceKey<Level>> dimensions = new ArrayList<ResourceKey<Level>>();

    public TransportationPressedC2S(int magnitude) {
        dimensions.add(Level.NETHER);
        dimensions.add(Level.OVERWORLD);
        dimensions.add(Level.END);

    }

    public TransportationPressedC2S(FriendlyByteBuf buf) {

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

            MinecraftServer minecraftserver = level.getServer();
            ServerLevel targetLevel = minecraftserver.getLevel(Level.NETHER); //Default target

            if(result.result) {

                float xyScale = 1;

                if(player.isCrouching()) {
                    if(level.dimension() == Level.END) {
                        targetLevel = minecraftserver.getLevel(Level.OVERWORLD);
                    } else if(level.dimension() == Level.OVERWORLD){
                        xyScale = .125f;
                        targetLevel = minecraftserver.getLevel(Level.NETHER);
                    }
                } else {
                    if(level.dimension() == Level.OVERWORLD){
                        targetLevel = minecraftserver.getLevel(Level.END);
                    } else if(level.dimension() == Level.NETHER){
                        xyScale = 8.0f;
                        targetLevel = minecraftserver.getLevel(Level.OVERWORLD);
                    }

                }

                double destx = (int)(player.getX()*xyScale)+.5;
                double destz = (int)(player.getZ()*xyScale)+.5;



                player.teleportTo(targetLevel, destx, player.getY(), destz,
                        0, 0);

                BlockPos overheadBlock = new BlockPos(destx,player.getY()+2,destz);
                BlockPos headBlock = new BlockPos(destx,player.getY()+1,destz);
                BlockPos footBlock = new BlockPos(destx,player.getY(),destz);

                //targetLevel.setBlock(new BlockPos(headBlock), Blocks.AIR.defaultBlockState(), 1);
                //targetLevel.setBlock(new BlockPos(footBlock), Blocks.AIR.defaultBlockState(), 1);

                targetLevel.destroyBlock(headBlock, true);
                targetLevel.destroyBlock(footBlock, true);
                targetLevel.destroyBlock(overheadBlock, true);

            }

        });
        return true;

    }


}
