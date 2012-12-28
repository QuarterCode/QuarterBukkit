
package com.quartercode.quarterbukkit.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.quartercode.quarterbukkit.api.thread.ThreadUtil;

/**
 * Class for modifying NBT-Tags and mutating Packtes easily.
 * This is e.g. for modifying the name of items etc.
 * 
 * @deprecated This class is deprecated. Use {@link MetaUtil} instead.
 */
@Deprecated
public class TagUtil {

    /**
     * Returns the name of an {@link ItemStack}.
     * 
     * @param itemStack The {@link ItemStack}.
     * @return The name of the {@link ItemStack}.
     */
    public static String getName(final ItemStack itemStack) {

        ThreadUtil.check();

        return itemStack.getItemMeta().getDisplayName();
    }

    /**
     * Sets the name of an {@link ItemStack}.
     * You can use every chat code.
     * 
     * @param itemStack The {@link ItemStack} to modify.
     * @param name The name to set.
     */
    public static void setName(final ItemStack itemStack, final String name) {

        ThreadUtil.check();

        if (name == null || name.isEmpty()) {
            itemStack.getItemMeta().setDisplayName(null);
        } else {
            itemStack.getItemMeta().setDisplayName(name);
        }
    }

    /**
     * Returns the description lines of an {@link ItemStack}.
     * They equals to the "Lore".
     * 
     * @param itemStack The {@link ItemStack}.
     * @return The description lines of the {@link ItemStack} as an {@link String}-{@link List}.
     */
    public static List<String> getDescriptions(final ItemStack itemStack) {

        ThreadUtil.check();

        return itemStack.getItemMeta().getLore();
    }

    /**
     * Sets the description lines of an {@link ItemStack}.
     * They equals to the "Lore". You can use every chat code.
     * 
     * @param itemStack The {@link ItemStack} to modify.
     * @param descriptions The description lines to set as an {@link String}-{@link List}.
     */
    public static void setDescriptions(final ItemStack itemStack, final List<String> descriptions) {

        ThreadUtil.check();

        if (descriptions == null || descriptions.isEmpty()) {
            itemStack.getItemMeta().setLore(null);
        } else {
            itemStack.getItemMeta().setLore(descriptions);
        }
    }

    /**
     * Returns the saved player show names as unmodifiable map.
     * You can add players with setShowName().
     * 
     * @return The saved player show names (beacuse the deprecation an empty map).
     * 
     * @deprecated This method is deprecated and does nothing (it only returns an empty map).
     */
    @Deprecated
    public static Map<Player, String> getShowPlayerNames() {

        return new HashMap<Player, String>();
    }

    /**
     * Sets the name above the {@link Player}'s head
     * 
     * @param player The {@link Player} to modify.
     * @param name The show name above the {@link Player}'s head to set.
     * 
     * @deprecated This method is deprecated and does nothing.
     */
    @Deprecated
    public static void setShowName(final Player player, final String name) {

        ThreadUtil.check();
    }

    private TagUtil() {

    }

}
