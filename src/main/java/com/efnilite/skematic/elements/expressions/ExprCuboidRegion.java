package com.efnilite.skematic.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.ExpressionType;
import com.efnilite.skematic.lang.SkematicExpression;
import com.efnilite.skematic.lang.annotations.Patterns;
import com.efnilite.skematic.lang.annotations.Return;
import com.efnilite.skematic.lang.annotations.Single;
import com.efnilite.skematic.utils.FaweTools;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.regions.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

@Name("Create region")
@Description("Create a virtual region (for saving schematics, etc.)")
@Examples({"create a new cuboidregion from {_location} to {_location-2}",
            "set {_region} to the last created region"})
@Patterns("[a] [new] (cuboid|we|wordedit)[ ]region from %location% to %location%")
@Return(CuboidRegion.class)
@Single
public class ExprCuboidRegion extends SkematicExpression<CuboidRegion> {

    static {
        Skript.registerExpression(ExprCuboidRegion.class, CuboidRegion.class, ExpressionType.SIMPLE,
                "[a] [new] (cuboid|we|wordedit)[ ]region from %location% to %location%");
    }

    private static CuboidRegion[] last;

    protected CuboidRegion[] get(Event e) {
        Location location1 = (Location) expressions[0].getSingle(e);
        Location location2 = (Location) expressions[0].getSingle(e);
        if (location1 == null || location2 == null) {
            return null;
        }
        World w = location1.getWorld();
        if (location1.getWorld() == null) {
            w = location2.getWorld();
        }
        CuboidRegion cuboid =  new CuboidRegion(FaweTools.getWorld(w.getName()),
                new Vector(location1.getBlockX(), location1.getBlockY(), location1.getBlockZ()),
                new Vector(location2.getBlockX(), location2.getBlockY(), location2.getBlockZ()));
        last = new CuboidRegion[] { cuboid };
        return last;
    }

    public static CuboidRegion[] getLastCuboidRegion() {
        return last;
    }

}
