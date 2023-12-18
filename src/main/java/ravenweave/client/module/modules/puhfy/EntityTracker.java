package ravenweave.client.module.modules.puhfy;

import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;

import java.util.HashMap;
import java.util.Map;

public class EntityTracker {

    // Map to store the last known server positions of entities
    private static final Map<Integer, ServerPosition> serverPositions = new HashMap<>();

    // Update the server position for a specific entity
    public static void updateServerPosition(Entity entity, double x, double y, double z) {
        int entityId = entity.getEntityId();
        ServerPosition position = new ServerPosition(x, y, z);
        serverPositions.put(entityId, position);
    }

    // Retrieve the last known server position for a specific entity
    public static ServerPosition getLastServerPosition(Entity entity) {
        return serverPositions.get(entity.getEntityId());
    }

    // Class to represent a server position
    public static class ServerPosition {
        public double x, y, z;

        public ServerPosition(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    // Handle teleportation based on the last known server position
    public static void handleEntityTeleport(Entity entity) {
        int entityId = entity.getEntityId();
        ServerPosition lastPosition = serverPositions.get(entityId);

        if (lastPosition != null) {
            entity.setPosition(lastPosition.x, lastPosition.y, lastPosition.z);
            entity.setPositionAndUpdate(lastPosition.x, lastPosition.y, lastPosition.z);
            entity.worldObj.getPlayerEntityByUUID(entity.getUniqueID()).setPositionAndRotation(lastPosition.x, lastPosition.y, lastPosition.z, entity.rotationYaw, entity.rotationPitch);
            entity.worldObj.getPlayerEntityByUUID(entity.getUniqueID()).setVelocity(0, 0, 0);
            entity.worldObj.getPlayerEntityByUUID(entity.getUniqueID()).rotationYaw = entity.rotationYaw;
            entity.worldObj.getPlayerEntityByUUID(entity.getUniqueID()).rotationPitch = entity.rotationPitch;

            entity.worldObj.getPlayerEntityByUUID(entity.getUniqueID()).fallDistance = entity.fallDistance;
            entity.worldObj.getPlayerEntityByUUID(entity.getUniqueID()).motionX = 0;
            entity.worldObj.getPlayerEntityByUUID(entity.getUniqueID()).motionY = 0;
            entity.worldObj.getPlayerEntityByUUID(entity.getUniqueID()).motionZ = 0;

            entity.worldObj.getPlayerEntityByUUID(entity.getUniqueID()).isDead = false;
        }
    }
}
