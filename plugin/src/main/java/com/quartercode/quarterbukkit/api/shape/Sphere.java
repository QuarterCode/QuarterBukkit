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

package com.quartercode.quarterbukkit.api.shape;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * This class represents a sphere shape that has an origin {@link Vector} and a radius.
 * The sphere is immutable and cannot be modified after construction.
 *
 * @see Shape
 */
public class Sphere implements Shape {

    private final Vector origin;
    private final double radius;

    /**
     * Creates a new sphere shape with the given three origin {@link Vector} coordinates (the center of the sphere) and the given radius.
     *
     * @param originX The x-coordinate of the origin vector of the represented sphere.
     * @param originY The y-coordinate of the origin vector of the represented sphere.
     * @param originZ The z-coordinate of the origin vector of the represented sphere.
     * @param radius The radius of the represented sphere.
     */
    public Sphere(double originX, double originY, double originZ, double radius) {

        this(new Vector(originX, originY, originZ), radius);
    }

    /**
     * Creates a new sphere shape with the given origin {@link Vector} (the center of the sphere) and the given radius.
     *
     * @param origin The origin vector of the represented sphere.
     * @param radius The radius of the represented sphere.
     */
    public Sphere(Vector origin, double radius) {

        this.origin = origin.clone();
        this.radius = radius;
    }

    /**
     * Creates a new sphere shape with the given origin {@link Location} (the center of the sphere) and the given radius.
     *
     * @param origin The origin location of the represented sphere.
     * @param radius The radius of the represented sphere.
     */
    public Sphere(Location origin, double radius) {

        this(origin.toVector(), radius);
    }

    /**
     * Returns the origin {@link Vector} (the center) of the sphere.
     *
     * @return The origin of the sphere.
     */
    public Vector getOrigin() {

        return origin.clone();
    }

    /**
     * Returns a new sphere shape with the given three origin {@link Vector} coordinates (the center of the sphere) and the same radius as the current sphere.
     *
     * @param originX The x-coordinate of the new origin vector of the returned sphere copy.
     * @param originY The y-coordinate of the new origin vector of the returned sphere copy.
     * @param originZ The z-coordinate of the new origin vector of the returned sphere copy.
     * @return The new sphere copy.
     */
    public Sphere withOrigin(double originX, double originY, double originZ) {

        return new Sphere(originX, originY, originZ, radius);
    }

    /**
     * Returns a new sphere shape with the given origin {@link Vector} (the center of the sphere) and the same radius as the current sphere.
     *
     * @param origin The new origin vector of the returned sphere copy.
     * @return The new sphere copy.
     */
    public Sphere withOrigin(Vector origin) {

        return new Sphere(origin, radius);
    }

    /**
     * Returns a new sphere shape with the given origin {@link Location} (the center of the sphere) and the same radius as the current sphere.
     *
     * @param origin The new origin location of the returned sphere copy.
     * @return The new sphere copy.
     */
    public Sphere withOrigin(Location origin) {

        return new Sphere(origin, radius);
    }

    /**
     * Returns the radius of the sphere.
     *
     * @return The radius of the sphere.
     */
    public double getRadius() {

        return radius;
    }

    /**
     * Returns a new sphere shape with the given radius and the same origin vector as the current sphere.
     *
     * @param radius The new radius of the returned sphere copy.
     * @return The new sphere copy.
     */
    public Sphere withRadius(double radius) {

        return new Sphere(origin, radius);
    }

    @Override
    public Vector getCenter() {

        return origin.clone();
    }

    @Override
    public Vector getBlockCenter() {

        return new Vector(origin.getBlockX(), origin.getBlockY(), origin.getBlockZ());
    }

    @Override
    public Cuboid getAxisAlignedBoundingBox() {

        return new Cuboid(origin.getX() + radius, origin.getY() + radius, origin.getZ() + radius, origin.getX() - radius, origin.getY() - radius, origin.getZ() - radius);
    }

    @Override
    public Collection<Vector> getContent(double distance) {

        Collection<Vector> vectors = new ArrayList<Vector>();

        for (Vector bbVector : getAxisAlignedBoundingBox().getContent(distance)) {
            if (intersects(bbVector)) {
                vectors.add(bbVector);
            }
        }

        return vectors;
    }

    @Override
    public boolean intersects(double x, double y, double z) {

        return intersects(new Vector(x, y, z));
    }

    @Override
    public boolean intersects(Vector vector) {

        return vector.isInSphere(origin, radius);
    }

    @Override
    public boolean intersects(Location location) {

        return intersects(location.toVector());
    }

    @Override
    public int hashCode() {

        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {

        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
