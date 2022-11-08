package dev.jackluna.radiantcraft.networking;

import dev.jackluna.radiantcraft.RadiantCraft;
import dev.jackluna.radiantcraft.networking.packets.AbrasionPressedC2S;
import dev.jackluna.radiantcraft.networking.packets.AbrasionReleasedC2S;
import dev.jackluna.radiantcraft.networking.packets.DivisionPressedC2S;
import dev.jackluna.radiantcraft.networking.packets.TransportationPressedC2S;
import dev.jackluna.radiantcraft.surges.TransportationSurge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetID = 0;

    private static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(RadiantCraft.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(DivisionPressedC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DivisionPressedC2S::new)
                .encoder(DivisionPressedC2S::toBytes)
                .consumerMainThread(DivisionPressedC2S::handle)
                .add();

        net.messageBuilder(AbrasionPressedC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(AbrasionPressedC2S::new)
                .encoder(AbrasionPressedC2S::toBytes)
                .consumerMainThread(AbrasionPressedC2S::handle)
                .add();

        net.messageBuilder(AbrasionReleasedC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(AbrasionReleasedC2S::new)
                .encoder(AbrasionReleasedC2S::toBytes)
                .consumerMainThread(AbrasionReleasedC2S::handle)
                .add();

        net.messageBuilder(TransportationPressedC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(TransportationPressedC2S::new)
                .encoder(TransportationPressedC2S::toBytes)
                .consumerMainThread(TransportationPressedC2S::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

}
