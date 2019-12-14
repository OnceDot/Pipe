package com.pipe.entity;

import com.mojang.authlib.GameProfile;
import com.pipe.netty.handler.PlayerConnection;
import com.pipe.netty.packet.SPacketDisconnect;
import com.pipe.netty.packet.play.SPacketChat;
import com.pipe.netty.packet.play.SPacketHeldItemChange;
import com.pipe.netty.packet.play.SPacketPlayerPosLook;
import com.pipe.util.text.ChatType;
import com.pipe.util.text.ITextComponent;
import com.pipe.util.text.TextComponentString;
import com.pipe.util.world.Location;

import java.util.EnumSet;
import java.util.UUID;

public class EntityPlayer extends Player {

    public GameProfile profile;
    public PlayerConnection connection;
    public boolean allowFlying;
    public boolean isFlying;

    public EntityPlayer(GameProfile profile) {
        this.profile = profile;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  MESSAGE
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void sendMessage(String message) {
        connection.sendPacket(new SPacketChat(new TextComponentString(message)));
    }

    @Override
    public void sendMessage(String message, ChatType type) {
        connection.sendPacket(new SPacketChat(new TextComponentString(message), type));
    }

    @Override
    public void sendMessage(ITextComponent message) {
        connection.sendPacket(new SPacketChat(message));
    }

    @Override
    public void sendMessage(ITextComponent message, ChatType type) {
        connection.sendPacket(new SPacketChat(message, type));
    }


    ///////////////////////////////////////////////////////////////////////////
    //  TELEPORTATION
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void teleport(Entity entity) {
        teleport(entity.getLocation());
    }

    @Override
    public void teleport(Location location) {
        teleport(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    @Override
    public void teleport(double posX, double posY, double posZ) {
        teleport(posX, posY, posZ, yaw, pitch);
    }

    @Override
    public void teleport(double posX, double posY, double posZ, float yaw, float pitch) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.yaw = yaw;
        this.pitch = pitch;

        connection.sendPacket(new SPacketPlayerPosLook(posX, posY, posZ, yaw, pitch, EnumSet.noneOf(SPacketPlayerPosLook.EnumFlags.class), 0));
    }


    ///////////////////////////////////////////////////////////////////////////
    //  INVENTORY
    ///////////////////////////////////////////////////////////////////////////

    public void setHeldItemSlot(int slot) {
        connection.sendPacket(new SPacketHeldItemChange(slot));
    }


    ///////////////////////////////////////////////////////////////////////////
    //  KICK
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void kick(String reason) {
        kick(new TextComponentString(reason));
    }

    @Override
    public void kick(ITextComponent reason) {
        connection.sendPacket(new SPacketDisconnect(reason));
        connection.closeConnection();
    }


    ///////////////////////////////////////////////////////////////////////////
    //  GETTERS
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public GameProfile getProfile() {
        return profile;
    }

    @Override
    public UUID getUUID() {
        return profile.getId();
    }

    @Override
    public String getName() {
        return profile.getName();
    }
}
