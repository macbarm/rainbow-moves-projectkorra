package sheepshot.mac;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.airbending.AirSpout;
import com.projectkorra.projectkorra.attribute.Attribute;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Random;

import static com.projectkorra.projectkorra.ability.FireAbility.playFirebendingSound;

public final class rainbowflight extends WaterAbility implements AddonAbility {


    private long time;
    private  int COOLDOWN = 10000;
    private Boolean showGliding;
    int duration = 120;

    private Location location;

    int color = 0;
    private Boolean previousGlidingState;

    private Listener listener;

    private Permission perm;

long runningTime =  System.currentTimeMillis() - getStartTime();
    private Random random;

    private double speed = 2;


    public rainbowflight(Player player) {
        super(player);
        if (bPlayer.isOnCooldown(this))
            return;

        bPlayer.addCooldown(this);




            this.flightHandler.createInstance(player, this.getName());
            player.setAllowFlight(true);
            this.time = System.currentTimeMillis();


                start();
        }



    @Override
    public void progress() {
         runningTime = getRunningTicks();




        if (!bPlayer.canBendIgnoreCooldowns(this)) {
            remove();
            return;
        }
       double timefactor = 1;
        final Vector velocity = this.player.getEyeLocation().getDirection().clone().normalize().multiply(this.speed * timefactor);
        GeneralMethods.setVelocity(this, this.player, velocity);
        this.player.setFallDistance(0);







        Location loc = player.getLocation().subtract(0, 1, 0);
        int sizeX = 5;
        int sizeZ = 3;

        for (int x = -sizeX; x <= sizeX; x++) {
            for (int z = -sizeZ; z <= sizeZ; z++) {


                String particleColor = "ff0000";


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
                GeneralMethods.displayColoredParticle(particleColor, loc.clone().add(x, 0, z));

                color++;
                if (color >= 7) {
                    color = 0;
                }


                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_STEP, 0.4F, 2f);


                if (duration < runningTime)

                    remove();


            }}
    }



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
        return "RainbowFlight";
    }



    @Override
    public Location getLocation() {
        return player.getLocation();
    }

    @Override
    public void load() {
        listener = new RainbowfligtListner();
        ProjectKorra.plugin.getServer().getPluginManager().registerEvents(listener, ProjectKorra.plugin);
        perm = new Permission("bending.ability.Rainbowflight");
        ProjectKorra.plugin.getServer().getPluginManager().addPermission(perm);

    }

    @Override
    public void stop() {
        HandlerList.unregisterAll(listener);
        ProjectKorra.plugin.getServer().getPluginManager().removePermission(perm);

    }
@Override
public String getDescription() {
        return "Click left click to fly with the power of the rainbow!";

}

    @Override
    public String getAuthor() {
        return "Macbarm";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }


        }

