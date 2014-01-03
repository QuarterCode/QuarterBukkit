/*
 * This file is part of QuarterBukkit-Plugin.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * QuarterBukkit-Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QuarterBukkit-Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with QuarterBukkit-Plugin. If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * This file is part of QuarterBukkit.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * QuarterBukkit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QuarterBukkit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with QuarterBukkit. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.quarterbukkit.api.select;

/**
 * This enum defines four different click types.
 * Currently, there're four values: LEFT, RIGHT, LEFT_SHIFT, RIGHT_SHIFT.
 * 
 * Every type has three methods which can output the concrete boolean values:
 * 
 * public boolean isLeft()
 * public boolean isRight()
 * public boolean isShift()
 */
public enum ClickType {

    /**
     * A default left click.
     */
    LEFT (true, false, false),
    /**
     * A default right click.
     */
    RIGHT (false, true, false),
    /**
     * A left click while holding shift.
     */
    LEFT_SHIFT (true, false, true),
    /**
     * A right click while holding shift.
     */
    RIGHT_SHIFT (false, true, true);

    public static ClickType getClickType(final boolean left, final boolean right, final boolean shift) {

        for (final ClickType clickType : values()) {
            if (clickType.isLeft() == left && clickType.isRight() == right && clickType.isShift() == shift) {
                return clickType;
            }
        }

        return null;
    }

    private boolean left;
    private boolean right;
    private boolean shift;

    private ClickType(final boolean left, final boolean right, final boolean shift) {

        this.left = left;
        this.right = right;
        this.shift = shift;
    }

    /**
     * If the left mouse button was clicked.
     * 
     * @return If the left mouse button was clicked.
     */
    public boolean isLeft() {

        return left;
    }

    /**
     * If the right mouse button was clicked.
     * 
     * @return If the right mouse button was clicked.
     */
    public boolean isRight() {

        return right;
    }

    /**
     * If shift was holded while clicking.
     * 
     * @return If shift was holded while clicking.
     */
    public boolean isShift() {

        return shift;
    }
}