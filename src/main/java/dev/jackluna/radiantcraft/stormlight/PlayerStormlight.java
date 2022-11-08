package dev.jackluna.radiantcraft.stormlight;

import dev.jackluna.radiantcraft.surges.*;
import dev.jackluna.radiantcraft.util.StormlightUseResult;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.List;

public class PlayerStormlight {



    private Surge primarySurge = new AbrasionSurge(1);
    private Surge secondarySurge = new NullSurge(1);
    private Surge hbSurgePrimary = new NullSurge(1);
    private Surge hbSurgeSecondary = new NullSurge(1);

    private boolean[] activeWhileHelds =
            {false,false,false,false,false,false,false,false,false,false};
    private float stormlight;

    private int idealLevel = 0;
    private final float MIN_STORMLIGHT = 0;
    private final float MAX_STORMLIGHT = 100;

    private float decayRate = 0; //TODO: Balance this

    public void setWhileHeld(int index, boolean value){
        activeWhileHelds[index] = value;
    }

    public boolean isActive(int index){
        return activeWhileHelds[index];
    }
    public float getStormlight() {
        return stormlight;
    }

    public void addStormlight(float f) {
        this.stormlight = Math.min(MAX_STORMLIGHT, this.stormlight+f);
    }

    public void subStormlight(float f) {
        this.stormlight = Math.max(MIN_STORMLIGHT, this.stormlight-f);

    }

    public void resetStormlight() {
        this.stormlight = MAX_STORMLIGHT;
    } //TODO: set to min

    /**
     * Attempts to drain the user's stormlight by an amount f. Returns true if successful, false otherwise.
     * @param f the amount of stormlight to attempt to drain
     * @return success or failure
     */
    public void attemptStormlightUse(float f, StormlightUseResult success) {

        if(stormlight < f) {
            success.result = false;
            return;
        }
        subStormlight(f);
        success.result = true;

    }


    public void copyFrom(PlayerStormlight source) {
        this.stormlight = source.stormlight;
    }



    public void saveNBTData(CompoundTag nbt) {

        int surge1 = primarySurge.getSURGE_ID();
        int lvl1 = primarySurge.getLevel();

        int surge2 = secondarySurge.getSURGE_ID();
        int lvl2 = secondarySurge.getLevel();

        int surge3 = hbSurgePrimary.getSURGE_ID();
        int lvl3 = hbSurgePrimary.getLevel();

        int surge4 = hbSurgeSecondary.getSURGE_ID();
        int lvl4 = hbSurgeSecondary.getLevel();

        nbt.putFloat("stormlight", stormlight);
        nbt.putInt("Surge1",surge1);
        nbt.putInt("Surge2",surge2);
        nbt.putInt("Surge3",surge3);
        nbt.putInt("Surge4",surge4);
        nbt.putInt("lvl1", lvl1);
        nbt.putInt("lvl2", lvl2);
        nbt.putInt("lvl3", lvl3);
        nbt.putInt("lvl4", lvl4);
        nbt.putInt("ideal", idealLevel);



    }

    public void loadNBTData(CompoundTag nbt) {
        stormlight = nbt.getFloat("stormlight");
        idealLevel = nbt.getInt("ideal");

        switch (nbt.getInt("surge1")) {
            case -1: this.primarySurge = new NullSurge(nbt.getInt("lvl1"));
                    break;
            case 0: this.primarySurge = new NullSurge(nbt.getInt("lvl1"));
                    break;
            case 1: this.primarySurge = new NullSurge(nbt.getInt("lvl1"));
                    break;
            case 2: this.primarySurge = new DivisionSurge(nbt.getInt("lvl1"));
                    break;
            case 3: this.primarySurge = new NullSurge(nbt.getInt("lvl1"));
                    break;
            case 4: this.primarySurge = new NullSurge(nbt.getInt("lvl1"));
                    break;
            case 5: this.primarySurge = new NullSurge(nbt.getInt("lvl1"));
                    break;
            case 6: this.primarySurge = new TransportationSurge(nbt.getInt("lvl1"));
                    break;
            case 7: this.primarySurge = new NullSurge(nbt.getInt("lvl1"));
                    break;
            case 8: this.primarySurge = new NullSurge(nbt.getInt("lvl1"));
                    break;
            case 9: this.primarySurge = new NullSurge(nbt.getInt("lvl1"));
                    break;

        }


    }


    public Surge getPrimarySurge() {
        return primarySurge;
    }

    public void setPrimarySurge(Surge primarySurge) {
        this.primarySurge = primarySurge;
    }

    public Surge getSecondarySurge() {
        return secondarySurge;
    }

    public void setSecondarySurge(Surge secondarySurge) {
        this.secondarySurge = secondarySurge;
    }

    public Surge getHbSurgePrimary() {
        return hbSurgePrimary;
    }

    public void setHbSurgePrimary(Surge hbSurgePrimary) {
        this.hbSurgePrimary = hbSurgePrimary;
    }

    public Surge getHbSurgeSecondary() {
        return hbSurgeSecondary;
    }

    public void setHbSurgeSecondary(Surge hbSurgeSecondary) {
        this.hbSurgeSecondary = hbSurgeSecondary;
    }

    public float getDecayRate() {
        return decayRate;
    }

    public void setDecayRate(float decayRate) {
        this.decayRate = decayRate;
    }

    public int getIdealLevel() {
        return idealLevel;
    }

    public void setIdealLevel(int idealLevel) {
        this.idealLevel = idealLevel;
    }
}
