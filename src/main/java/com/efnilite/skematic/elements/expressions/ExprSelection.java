package com.efnilite.skematic.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.efnilite.skematic.utils.WeUtils;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.entity.Player;

@Name("Selection")
@Description("Gets the selection of a player (region)")
@Since("1.0")
public class ExprSelection extends SimplePropertyExpression<Player, CuboidRegion> {

    static {
        register(ExprSelection.class, CuboidRegion.class, "[(skematic|fawe)] selection[s]", "players");
    }

    @Override
    public CuboidRegion convert(Player p) {
        LocalSession session = WeUtils.getLocalSession(p);
        Region selection = session.getSelection(session.getSelectionWorld());
        return new CuboidRegion(selection.getWorld(), selection.getMaximumPoint(), selection.getMaximumPoint());
    }

    @Override
    public Class<? extends CuboidRegion> getReturnType() {
        return CuboidRegion.class;
    }

    @Override
    protected String getPropertyName() {
        return "selection";
    }
}
