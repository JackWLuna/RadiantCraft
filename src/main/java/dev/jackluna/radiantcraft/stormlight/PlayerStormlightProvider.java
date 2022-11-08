package dev.jackluna.radiantcraft.stormlight;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerStormlightProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerStormlight> PLAYER_STORMLIGHT =
            CapabilityManager.get(new CapabilityToken<PlayerStormlight>() { });

    private PlayerStormlight stormlight = null;
    private final LazyOptional<PlayerStormlight> optional = LazyOptional.of(this::createPlayerStormlight);

    private PlayerStormlight createPlayerStormlight() {
        if(this.stormlight == null){
            this.stormlight = new PlayerStormlight();
        }
        return this.stormlight;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_STORMLIGHT){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerStormlight().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerStormlight().loadNBTData(nbt);
    }
}
