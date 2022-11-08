package dev.jackluna.radiantcraft.surges;

import dev.jackluna.radiantcraft.networking.ModMessages;
import dev.jackluna.radiantcraft.networking.packets.DivisionPressedC2S;

public class DivisionSurge extends Surge{

    public DivisionSurge(int level){
        super(2);
        setStormlightCost(10);
    }

    /**
     * We want this to send a packet which sets fire to a block.
     */
    @Override
    public void onPressed(){
        ModMessages.sendToServer(new DivisionPressedC2S(3));
    }

    @Override
    public void onReleased(){

    }

}
