package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketAnimation implements Packet<INetHandlerPlay> {

    private int entityId;
    private EnumAnimation type;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketAnimation(int entityId, EnumAnimation type) {
        this.entityId = entityId;
        this.type = type;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entityId);
        buf.writeByte(type.ordinal());
    }

    ///////////////////////////////////////////////////////////////////////////

    public enum EnumAnimation {
        SWING_MAINHAND,
        TAKE_DAMAGE,
        LEAVE_BED,
        SWING_OFFHAND,
        CRITICAL_EFFECT,
        MAGIC_CRITICAL_EFFECT
    }
}
