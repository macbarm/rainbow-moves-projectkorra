package scream.e;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.util.DamageHandler;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class rainbowguard extends WaterAbility implements AddonAbility {


    private Set<Entity> hurt;

    public rainbowguard(Player player) {
        super(player);
        if (bPlayer.isOnCooldown(this))
            return;


        location = player.getEyeLocation();
        direction = player.getLocation().getDirection();
        direction.multiply(1.6);

        disanceTravelled = 0;
        hurt = new HashSet<>();

        start();

    }

        private int COOLDOWN = 10000;
    long runningTime = System.currentTimeMillis() - getStartTime();

    private static final double RANGE = 60;

    private Location location;
        private Listener listener;
        private Permission perm;
        private int currPoint;

        private  Vector direction;

    long duration = 200;
    Location loc = player.getLocation().add(0,1.2 , 0);
    double radius = 0;
    double height = 1.2;
    double angle = 0;
    double angleIncrease = 10;
    double heightIncrease = 0.05;

    private static final double DAMAGE = 100;
    double radiusIncrease = 0.05;

    private double disanceTravelled;

    int color = 0;

    @Override
    public void progress() {


        runningTime = getStartTick();

        affectTarrgets();

        angle += angleIncrease;
        if (angle >= 360) {
            angle = 0;
        }

        radius += radiusIncrease;

        double x = radius * Math.cos(Math.toRadians(angle));
        double z = radius * Math.sin(Math.toRadians(angle));

        String particleColor = "ff0000"; // default red color
        switch (color) {
            case 0:
                particleColor = "ff0000"; // red
                break;
            case 1:
                particleColor = "ff8000"; // orange
                break;
            case 2:
                particleColor = "ffff00"; // yellow
                break;
            case 3:
                particleColor = "80ff00"; // light green
                break;
            case 4:
                particleColor = "00ff80"; // turquoise
                break;
            case 5:
                particleColor = "0000ff"; // blue
                break;
            case 6:
                particleColor = "ff00ff"; // purple
                break;

        }
        Location particleLoc = player.getLocation().add(x, height, z);

        GeneralMethods.displayColoredParticle(particleColor, particleLoc);

        player.getWorld().playSound(particleLoc, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1f , 0.5f);

        color++;
        if (color >= 7) {
            color = 0;
        }

        if (radius > 3) {
            radiusIncrease = 0;

        }
       // if (duration != runningTime) {
           // bPlayer.addCooldown(this);
          //  remove();




        }
  //  }





    @Override
    public boolean isSneakAbility() {
        return true;
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
        return "RainbowGuard";
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public void load() {
        listener = new RainbowguardListener();
        ProjectKorra.plugin.getServer().getPluginManager().registerEvents(listener, ProjectKorra.plugin);
        perm = new Permission("bending.ability.RainbowScream");
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
    public String getVersion() {
        return "1.0.0";
    }





    private void affectTarrgets() {
        double x = radius * Math.cos(Math.toRadians(angle));
        double z = radius * Math.sin(Math.toRadians(angle));
        Location particleLoc = player.getLocation().add(x, height, z);


        List<Entity> targets = GeneralMethods.getEntitiesAroundPoint(particleLoc, 0.4);
        for (Entity target : targets) {
            if (target.getUniqueId() == player.getUniqueId()) {
                continue;
            }
            if (!hurt.contains(target)) {
                DamageHandler.damageEntity(target, DAMAGE, this);
                hurt.add(target);

                if (target.getUniqueId() == target.getUniqueId())
                    bPlayer.addCooldown(this);
                    remove();
            }

        }}}