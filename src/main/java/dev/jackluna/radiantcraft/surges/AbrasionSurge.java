package dev.jackluna.radiantcraft.surges;

import dev.jackluna.radiantcraft.networking.ModMessages;
import dev.jackluna.radiantcraft.networking.packets.AbrasionPressedC2S;
import dev.jackluna.radiantcraft.networking.packets.AbrasionReleasedC2S;
import dev.jackluna.radiantcraft.networking.packets.DivisionPressedC2S;

public class AbrasionSurge extends Surge{

    public AbrasionSurge(int level){
        super(Surge.ABRASION);
        setStormlightCost(10);
    }

    /**
     * We want this to send a packet enables a single boolean in the capability we call this from because god hates programmers.
     */
    @Override
    public void onPressed(){

        ModMessages.sendToServer(new AbrasionPressedC2S(3));
    }

    @Override
    public void onReleased(){

        ModMessages.sendToServer(new AbrasionReleasedC2S(3));
    }

}
