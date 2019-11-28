package fr.volax.valorionrtp.utils;

import fr.volax.valorionrtp.RandomTPMain;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

public class TeleportHandler {
    RandomTPMain plugin;
    Player player = null;
    World world = null;
    int xCoord = -1;
    int zCoord = -1;
    int xF = 0;
    int yF = 0;
    int zF = 0;
    String uuid;

    public TeleportHandler(Player player, World world, int xCoord, int zCoord) {
        this.player = player;
        this.world = world;
        this.uuid = player.getUniqueId().toString();
        this.xCoord = xCoord;
        this.zCoord = zCoord;
    }

    public void teleport(){
        Location location = getLocation();
        this.player.teleport(location);
    }

    public int getX() {
        return this.xF;
    }

    public int getY() {
        return this.yF;
    }

    public int getZ() {
        return this.zF;
    }

    private void set(double x,double y,double z){
        this.xF = ((int)x);
        this.yF = ((int)y);
        this.zF = ((int)z);
    }

    public String getMessage(){
        String msg1 = "§aTéléportation à la location:";
        String msg2 = "§aX: " + getX();
        String msg3 = "§aY: " + getY();
        String msg4 = "§aZ: " + getZ();
        String espace = "\n";
        return RandomTPMain.getMain().prefix + msg1 + espace + RandomTPMain.getMain().prefix + msg2 + espace + RandomTPMain.getMain().prefix + msg3 + espace + RandomTPMain.getMain().prefix + msg4 + espace;
    }

    public Location getLocation() {
        Random random = new Random();
        int x = random.nextInt(this.xCoord);
        int z = random.nextInt(this.zCoord);

        x = rdm(x);
        z = rdm(z);
        int y = 63;

        Location loc = safeY(new Location(this.world, x, y, z));
        set(loc.getX(), loc.getY(), loc.getZ());

        return loc;
    }

    public Location safeY(Location location) {
        location.setY(location.getWorld().getHighestBlockYAt(location));
        while (location.getBlock().getType() != Material.AIR) {
            location.setX(location.getX() + 1.0D);
            location.setZ(location.getZ() + 1.0D);
            location.setY(location.getY() + 1.0D);
        }
        while (this.player.getEyeLocation().getBlock().getType() != Material.AIR) {
            location.setX(location.getX() + 1.0D);
            location.setY(location.getY() + 1.0D);
            location.setZ(location.getZ() + 1.0D);
        }
        location.setY(location.getWorld().getHighestBlockYAt(location));
        this.player.getEyeLocation().getBlock().setType(Material.AIR);
        return location;
    }

    public int rdm(int i){
        Random random = new Random();
        int j = random.nextInt(2);
        switch (j) {
            case 0:
                return i *= -1;
            case 1:
                return i;
        }
        return i *= -1;
    }
}

