package dev.jackluna.radiantcraft.surges;

import dev.jackluna.radiantcraft.networking.ModMessages;
import dev.jackluna.radiantcraft.networking.packets.AbrasionPressedC2S;
import dev.jackluna.radiantcraft.networking.packets.AbrasionReleasedC2S;
import dev.jackluna.radiantcraft.networking.packets.TransportationPressedC2S;

public class TransportationSurge extends Surge{

    public TransportationSurge(int level){
        super(7);
        setStormlightCost(10);
    }

    /**
     * We want this to send a packet which transports the user.
     */
    @Override
    public void onPressed(){
        ModMessages.sendToServer(new TransportationPressedC2S(this.getLevel()));
    }

    @Override
    public void onReleased(){  }

}
