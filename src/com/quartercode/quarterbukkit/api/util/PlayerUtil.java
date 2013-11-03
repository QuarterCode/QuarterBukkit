
package com.quartercode.quarterbukkit.api.util;

import java.lang.reflect.Field;
import org.bukkit.entity.Player;
import com.quartercode.quarterbukkit.api.Language;

public class PlayerUtil {

    /**
     * Class for some useful Player Methods
     */

    /**
     * Return the local language from a {@link Player}.
     * 
     * @param player The {@link Player} from get the Language.
     * @return The Language.
     */
    public static Language getLanguage(final Player player) {

        try {
            Object craftPlayer = BukkitPlayerToCraftPlayer(player);
            Field f = craftPlayer.getClass().getDeclaredField("locale");
            f.setAccessible(true);
            String language = (String) f.get(craftPlayer);
            return Language.getByCode(language);
        }
        catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    /**
     * Convert {@link Player} to CraftPlayer Object.
     * 
     * @param player The {@link Player} to convert.
     * @return The CraftPlayer Object
     */

    public static Object BukkitPlayerToCraftPlayer(Player player) {

        Object entityPlayer = null;
        try {
            entityPlayer = ReflectionUtil.getMethod("getHandle", player.getClass(), 0).invoke(player);
        }
        catch (Exception e) {
            return null;
        }
        return entityPlayer;
    }
}
