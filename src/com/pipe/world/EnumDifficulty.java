package com.pipe.world;

public enum EnumDifficulty {

    PEACEFUL(0),
    EASY(1),
    NORMAL(2),
    HARD(3);

    ///////////////////////////////////////////////////////////////////////////

    private int id;

    ///////////////////////////////////////////////////////////////////////////

    EnumDifficulty(int id) {
        this.id = id;
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getId() {
        return id;
    }
}
