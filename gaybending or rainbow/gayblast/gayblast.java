package mac.mac;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.util.DamageHandler;
import com.projectkorra.projectkorra.util.ParticleEffect;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.permissions.Permission;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class gayblast extends WaterAbility implements AddonAbility {



    private static final double DAMAGE = 4;
    private static final double RANGE = 60;
    private static final long COOLDOWN = 3000;

    private double ANGLE = 12 * Math.PI / Math.PI/10;






    private int currPoint;

    private GayblastListener listener;

    private Permission perm;

private Vector mac;
    private  Location location;
    private Vector direction;
    private double disanceTravelled;

    	private int layer;




    private Set<Entity> hurt;


    public gayblast(Player player) {
        super(player);

        location = player.getEyeLocation();
        direction = player.getLocation().getDirection();
        direction.multiply(1.6);
        bPlayer.addCooldown(this);
        disanceTravelled = 0;
        hurt = new HashSet<>();








        start();
    }







    @Override
    public void progress() {



        if (!bPlayer.canBendIgnoreBindsCooldowns(this)) {
            remove();
            return;
        }
        if (location.getBlock().getType().isSolid()) {




            remove();
            return;
        }
        if (location.distanceSquared(player.getLocation()) > RANGE)
            if(disanceTravelled > RANGE){
                remove();
                return;
            }


playParticleRing(60, 1F, 100);

GeneralMethods.getOrthogonalVector(direction, ANGLE, RANGE).add(direction);


            affectTarrgets();






        location.add(direction);
        disanceTravelled += direction.length();




        Location loc = getLocation();

        String[] colors = {"ff0000", "ff8000", "ffff00", "80ff00", "00ff80", "0000ff"};
        for (int i = 0; i < 6; i++)
            GeneralMethods.displayColoredParticle(colors[i], loc);




        this.direction = player.getLocation().getDirection();



        GeneralMethods.getOrthogonalVector(direction, ANGLE, RANGE);



        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 0.2F, 0.7F);





    }


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
        return "GayBlast";
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void load() {
        listener = new GayblastListener();
        ProjectKorra.plugin.getServer().getPluginManager().registerEvents(listener, ProjectKorra.plugin);



    }

    @Override
    public void stop() {
        HandlerList.unregisterAll(listener);


    }

    @Override
    public String getAuthor() {
        return "Macbarm";
    }

    @Override
    public String getVersion() {
        return "69420 amogus 69";
    }

    @Override
    public  String getDescription(){
        return "Shoot with the power of GAY HAHAHAHAHA and hearts ;))";
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





                            



            }

            if (target.getUniqueId() == target.getUniqueId());






                remove();


            direction.multiply(1);
            Location mobloc = target.getLocation();
            ParticleEffect.HEART.display(mobloc,40, 1 ,2, 1, 1);
            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 4f, 1.6F);
            player.sendMessage(  "§x§f§f§0§0§0§0§lY§x§f§f§1§1§0§0§lO§x§f§f§2§1§0§0§lU §x§f§f§3§2§0§0§lM§x§f§f§4§2§0§0§lA§x§f§f§5§3§0§0§lD§x§f§f§6§3§0§0§lE §x§f§f§7§4§0§0§lE§x§f§f§8§5§0§0§lM §x§f§f§9§5§0§0§lG§x§f§f§a§6§0§0§lA§x§f§f§b§7§0§0§lY §x§f§f§c§7§0§0§l(§x§f§f§d§8§0§0§ln§x§f§f§e§9§0§0§lo§x§f§f§f§9§0§0§lt§x§e§9§f§f§0§0§lh§x§c§8§f§f§0§0§li§x§a§6§f§f§0§0§ln§x§8§5§f§f§0§0§lg §x§6§4§f§f§0§0§lw§x§4§3§f§f§0§0§lr§x§2§1§f§f§0§0§lo§x§0§0§f§f§0§0§ln§x§0§0§d§e§2§1§lg §x§0§0§b§c§4§3§lw§x§0§0§9§b§6§4§li§x§0§0§7§a§8§5§lt§x§0§0§5§9§a§6§lh §x§0§0§3§7§c§8§lg§x§0§0§1§6§e§9§la§x§0§3§0§0§f§a§ly §x§0§d§0§0§e§9§lb§x§1§7§0§0§d§9§lu§x§2§1§0§0§c§9§lt §x§2§a§0§0§b§8§li§x§3§4§0§0§a§8§lt§x§3§e§0§0§9§8§ls §x§4§8§0§0§8§7§lg§x§5§1§0§0§8§9§la§x§5§b§0§0§9§4§ly§x§6§4§0§0§9§e§lb§x§6§e§0§0§a§9§ll§x§7§7§0§0§b§3§la§x§8§1§0§0§b§e§ls§x§8§a§0§0§c§8§lt§x§9§4§0§0§d§3§l\n"  );



        }}
    private void playParticleRing(int points, float size, int speed) {

        Location temploc = location.clone();
        currPoint += 360 / points;

        double angle = currPoint * Math.PI/0.6;
        Vector vec = GeneralMethods.getOrthogonalVector(temploc.getDirection(), angle, RANGE);
        vec.normalize().multiply(size);

        Location loc = temploc.add(vec);

        ParticleEffect.HEART.display(temploc, 8, 0.0, 0., 0, 6);


    }










}



