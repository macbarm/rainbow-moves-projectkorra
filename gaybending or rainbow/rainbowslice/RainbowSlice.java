package mac.realsheepshot;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.util.DamageHandler;
import com.projectkorra.projectkorra.util.TempBlock;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class RainbowSlice extends WaterAbility implements AddonAbility {

    private static final double RANGE = 200;

private int COOLDOWN = 6000;

private double disanceTravelled ;

private Listener listener;


private Vector direction;

    private Set<Entity> hurt;

private Permission perm;
private Location location;
    private Location location1;
    private Location location2;
    private Location location3;
    private Location location4;
    private Location location5;
    private Location location6;
    private Location location7;
    private Location location8;
    private Location location9;
    private Location location10;


    private static final double DAMAGE = 4;
    public RainbowSlice(Player player) {
        super(player);

        location = player.getEyeLocation();
        location1 = player.getEyeLocation();
        location2 = player.getEyeLocation();
        location3 = player.getEyeLocation();
        location4 = player.getEyeLocation();
        location5 = player.getEyeLocation();
        location6 = player.getEyeLocation();
        location7 = player.getEyeLocation();
        location8 = player.getEyeLocation();
        location9 = player.getEyeLocation();
        location10 = player.getEyeLocation();
        direction = player.getLocation().getDirection();
        direction.multiply(1.7);
        bPlayer.addCooldown(this);
        disanceTravelled = 0;
        hurt = new HashSet<>();

        start();
    }

    @Override
    public void progress() {



        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SMALL_AMETHYST_BUD_PLACE, 0.5f, 3f);
// ROOD
        Location loc  = location;
        Location dop = location6;


        GeneralMethods.displayColoredParticle("ff0000",loc.add(0, 0.8, 0));
        GeneralMethods.displayColoredParticle("ff0000",dop.add(0, 0.7, 0));


// ORANJE

        Location doc  = location1;
        Location pip = location7;
        GeneralMethods.displayColoredParticle("ff8000",doc.add(0, 0.6, 0));
        GeneralMethods.displayColoredParticle("ff8000",pip.add(0, 0.5, 0));

        // GEEL

        Location foc  = location2;
        Location fos = location8;

        GeneralMethods.displayColoredParticle("ffff00",foc.add(0, 0.4, 0));
        GeneralMethods.displayColoredParticle("ffff00",fos.add(0, 0.3, 0));

        // GROEN

        Location soc  = location3;
        Location poepsex = location9;

        GeneralMethods.displayColoredParticle("00ff00",soc.add(0, 0.2, 0));
        GeneralMethods.displayColoredParticle("00ff00",poepsex.add(0, 0.1, 0));
// BLAUW
        Location kys = location4;
        Location ikwordtzomoehiervan = location10;
        GeneralMethods.displayColoredParticle("0000ff",kys  .add(0, 0, 0));
        GeneralMethods.displayColoredParticle("0000ff",ikwordtzomoehiervan  .add(0, -0.1, 0));
// PAARS
Location geen = location5;
        GeneralMethods.displayColoredParticle("7F00FF",geen.add(0, -0.2, 0));

affectTarrgets();

affectTarrgets2();

affectTarrgets3();

affectTarrgets4();

affectTarrgets5();

affectTarrgets6();


        location.add(direction);
        disanceTravelled += direction.length();

        location1.add(direction);
        disanceTravelled += direction.length();

        location2.add(direction);
        disanceTravelled += direction.length();


        location3.add(direction);
        disanceTravelled += direction.length();

        location4.add(direction);
        disanceTravelled += direction.length();

        location5.add(direction);
        disanceTravelled += direction.length();


        location6.add(direction);
        disanceTravelled += direction.length();

        location7.add(direction);
        disanceTravelled += direction.length();

        location8.add(direction);
        disanceTravelled += direction.length();

         location9.add(direction);
        disanceTravelled += direction.length();

        location10.add(direction);
        disanceTravelled += direction.length();




        if (location.distanceSquared(player.getLocation()) > RANGE)
            if(disanceTravelled > RANGE){
                remove();

            }else {
                if (location1.distanceSquared(player.getLocation()) > RANGE)
                    if(disanceTravelled > RANGE){
                        remove();
                    }else {
                        if (location2.distanceSquared(player.getLocation()) > RANGE)
                            if(disanceTravelled > RANGE){
                                remove();
                            }else {
                                if (location3.distanceSquared(player.getLocation()) > RANGE)
                                    if(disanceTravelled > RANGE) {
                                        remove();
                                    }else {
                                        if (location4.distanceSquared(player.getLocation()) > RANGE)
                                            if(disanceTravelled > RANGE){
                                                remove();
                                            }else {

                                                if (location5.distanceSquared(player.getLocation()) > RANGE)
                                                    if(disanceTravelled > RANGE){
                                                        remove();
                                            }


        }}}}}}








    @Override
    public boolean isSneakAbility() {
        return false;
    }

    @Override
    public boolean isHarmlessAbility() {
        return false;
    }

    @Override
    public long getCooldown() {
        return COOLDOWN;
    }

    @Override
    public String getName() {
        return "RainbowSlice";
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public void load() {
        listener = new RainbowSliceListener();
        ProjectKorra.plugin.getServer().getPluginManager().registerEvents(listener, ProjectKorra.plugin);
        perm = new Permission("bending.ability.RainbowSlice");
        perm.setDefault(PermissionDefault.OP);
        ProjectKorra.plugin.getServer().getPluginManager().addPermission(perm);


    }

    @Override
    public void stop() {
        HandlerList.unregisterAll(listener);
        ProjectKorra.plugin.getServer().getPluginManager().removePermission(perm);

    }

    @Override
    public String getAuthor() {
        return "Macbarm";

    }

    @Override
    public String getDescription() {
        return "Slice your opponent in half with this sharp rainbow! (left click) ";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }


    private void affectTarrgets() {
        List<Entity> targets = GeneralMethods.getEntitiesAroundPoint(location, 0.6);
        for (Entity target : targets) {
            if (target.getUniqueId() == player.getUniqueId()) {
                continue;
            }
            if (!hurt.contains(target)) {
                DamageHandler.damageEntity(target, DAMAGE, this);
                hurt.add(target);
            }}}
    private void affectTarrgets2() {
        List<Entity> targets = GeneralMethods.getEntitiesAroundPoint(location1, 0.6);
        for (Entity target : targets) {
            if (target.getUniqueId() == player.getUniqueId()) {
                continue;
            }
            if (!hurt.contains(target)) {
                DamageHandler.damageEntity(target, DAMAGE, this);
                hurt.add(target);

}}}


        private void affectTarrgets3() {
            List<Entity> targets = GeneralMethods.getEntitiesAroundPoint(location2, 0.6);
            for (Entity target : targets) {
                if (target.getUniqueId() == player.getUniqueId()) {
                    continue;
                }
                if (!hurt.contains(target)) {
                    DamageHandler.damageEntity(target, DAMAGE, this);
                    hurt.add(target);

                }}}

                private void affectTarrgets4() {
                    List<Entity> targets = GeneralMethods.getEntitiesAroundPoint(location3, 0.6);
                    for (Entity target : targets) {
                        if (target.getUniqueId() == player.getUniqueId()) {
                            continue;
                        }
                        if (!hurt.contains(target)) {
                            DamageHandler.damageEntity(target, DAMAGE, this);
                            hurt.add(target);

                        }

            }}

    private void affectTarrgets5() {
        List<Entity> targets = GeneralMethods.getEntitiesAroundPoint(location4, 0.6);
        for (Entity target : targets) {
            if (target.getUniqueId() == player.getUniqueId()) {
                continue;
            }
            if (!hurt.contains(target)) {
                DamageHandler.damageEntity(target, DAMAGE, this);
                hurt.add(target);

            }}}

        private void affectTarrgets6() {
            List<Entity> targets = GeneralMethods.getEntitiesAroundPoint(location5, 0.6);
            for (Entity target : targets) {
                if (target.getUniqueId() == player.getUniqueId()) {
                    continue;
                }
                if (!hurt.contains(target)) {
                    DamageHandler.damageEntity(target, DAMAGE, this);
                    hurt.add(target);

                }


            }}}