package dev.jackluna.radiantcraft.surges;

import dev.jackluna.radiantcraft.networking.ModMessages;
import dev.jackluna.radiantcraft.networking.packets.DivisionPressedC2S;

public class NullSurge extends Surge{

    public NullSurge(int level){
        super(-1);
    }

    /**
     * Does nothing.
     */
    @Override
    public void onPressed(){

    }

    @Override
    public void onReleased(){

    }

}
