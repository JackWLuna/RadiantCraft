package dev.jackluna.radiantcraft.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_RADIANTCRAFT = "key.category.radiantcraft.radiant";
    public static final String KEY_PRIMARY_SURGE = "key.radiantcraft.primary_surge";
    public static final String KEY_SECONDARY_SURGE = "key.radiantcraft.secondary_surge";


    public static final KeyMapping PRIMARY_SURGE = new KeyMapping(KEY_PRIMARY_SURGE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY_RADIANTCRAFT);

    public static final KeyMapping SECONDARY_SURGE = new KeyMapping(KEY_SECONDARY_SURGE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_CATEGORY_RADIANTCRAFT);
}
