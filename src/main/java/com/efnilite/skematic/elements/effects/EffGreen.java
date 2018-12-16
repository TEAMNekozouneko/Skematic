package com.efnilite.skematic.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import com.efnilite.skematic.lang.SkematicEffect;
import com.efnilite.skematic.lang.annotations.Patterns;
import com.efnilite.skematic.utils.FaweTools;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import org.bukkit.Location;
import org.bukkit.event.Event;

@Name("Greenify")
@Description("Greenify an area - Turns it into grass.")
@Examples("greenify 2, 3, 4 in \"world\" within a radius of 20")
@Patterns("(green|grass)[ify] %location% (in|within) [a] radius [of] %number% (1¦[(with|and)] only [normal] dirt)")
public class EffGreen extends SkematicEffect {

    static {
        Skript.registerEffect(EffGreen.class, "(green|grass)[ify] %location% (in|within) [a] radius [of] %number% (1¦[(with|and)] only [normal] dirt)");
    }

    @Override
    protected void execute(Event e) {
        Location location = (Location) expressions[0].getSingle(e);
        Number radius = (Number) expressions[1].getSingle(e);
        boolean dirt = false;

        if (radius == null || location == null) {
            return;
        }
        if (mark == 1) {
            dirt = true;
        }

        EditSession session = FaweTools.getEditSession(location.getWorld());
        session.green(new Vector(location.getBlockX(), location.getBlockY(), location.getBlockZ()), (double) radius, dirt);
        session.flushQueue();
    }
}
