package confettiburst.confet;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Scanner;

import static com.projectkorra.projectkorra.ProjectKorra.plugin;

public final class confettiBurst extends WaterAbility implements AddonAbility {

    private final double distancetraveled = 0;
    private ConfettiBurstListener listener;

    private Permission perm;

    private Vector direction;


    private int currPoint;
private Location location;



    private static final long COOLDOWN = 10000;


    int duration = 60; // Duration in ticks (3 seconds)

    int color = 0;





    long runningTime = System.currentTimeMillis() - getStartTime();



    public confettiBurst(Player player) {
        super(player);
        if (bPlayer.isOnCooldown(this))
            return;


        location = player.getEyeLocation();
        direction = player.getLocation().getDirection();
        direction.multiply(1.6);

        start();
    }


    @Override
    public void progress() {

        runningTime = getRunningTicks();
bPlayer.addCooldown(this);
        if (!bPlayer.canBendIgnoreBindsCooldowns(this)) {
            remove();
            return;


        }

Addeffects();





        Location loc = location;
        double radius = 4;
        player.getWorld().playSound(location, Sound.BLOCK_AMETHYST_BLOCK_STEP, 0.4F, 0.1f);

        for (double theta = 0; theta <= Math.PI; theta += Math.PI / 10) {
            double sinTheta = Math.sin(theta);
            double cosTheta = Math.cos(theta);

            for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / 10) {
                double sinPhi = Math.sin(phi);
                double cosPhi = Math.cos(phi);

                double x = radius * sinTheta * cosPhi;
                double y = radius * sinTheta * sinPhi;
                double z = radius * cosTheta;


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
                Location particleLoc = player.getLocation().add(x, y, z);

                GeneralMethods.displayColoredParticle(particleColor, particleLoc);

                color++;
                if (color >= 7) {
                    color = 0;
                }

                }

                if (duration < runningTime)
                    remove();


            }


            Location playerLoc = player.getLocation();
            for (Player nearbyPlayer : playerLoc.getWorld().getPlayers()) {

                if (nearbyPlayer.getLocation().distance(playerLoc) <= radius) {

                    nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 1));

                    player.removePotionEffect(PotionEffectType.BLINDNESS);


                }}

        }


            @Override
            public boolean isSneakAbility () {
                return false;
            }

            @Override
            public boolean isHarmlessAbility () {
                return false;
            }

            @Override
            public long getCooldown () {
                return COOLDOWN;
            }

            @Override
            public String getName () {
                return "conffetiAura";
            }

            @Override
            public Location getLocation () {
                return null;
            }

            @Override
            public void load () {
                listener = new ConfettiBurstListener();
                plugin.getServer().getPluginManager().registerEvents(listener, plugin);
                perm = new Permission("bending.ability.blast");
                perm.setDefault(PermissionDefault.OP);
                plugin.getServer().getPluginManager().addPermission(perm);

            }

            @Override
            public void stop () {
                HandlerList.unregisterAll(listener);

                plugin.getServer().getPluginManager().removePermission(perm);

            }

            @Override
            public String getAuthor () {
                return "Macbarm";
            }

            @Override
            public String getVersion () {
                return "1.0.0";
            }

    private void Addeffects() {
        int radius;
                radius = 1;

        Location playerLoc = location;
        for (Player nearbyPlayer : playerLoc.getWorld().getPlayers()) {

            if (nearbyPlayer.getLocation().distance(playerLoc) <= radius) {

                nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 1));
            }


        }}}