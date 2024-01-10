package net.sn0wix_.encounter.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.sn0wix_.encounter.Encounter;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static String KEY_CATEGORY_INCOUNTER = "key.category." + Encounter.MOD_ID + ".keys";
    public static String KEY_CRAWL = "key." + Encounter.MOD_ID + ".crawl";

    public static KeyBinding crawlKey;

    public static void registerKeyBindings() {
        crawlKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(KEY_CRAWL, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY_INCOUNTER));
    }
}
