package com.pipe.world;

public enum EnumGameType {

    NOT_SET(-1),
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3);

    ///////////////////////////////////////////////////////////////////////////

    private int id;

    ///////////////////////////////////////////////////////////////////////////

    EnumGameType(int id) {
        this.id = id;
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getId() {
        return id;
    }
}
