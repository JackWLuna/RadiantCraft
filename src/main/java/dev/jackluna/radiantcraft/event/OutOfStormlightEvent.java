package dev.jackluna.radiantcraft.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;

public class OutOfStormlightEvent extends PlayerEvent {

    //This event should be Posted when a player runs out of stormlight. Used to cancel active abilities.
    public OutOfStormlightEvent(Player player) {
        super(player);
    }
}
