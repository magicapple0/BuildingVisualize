package crystalcube.buildingvisualize.Json;

public enum TileModifiers {
    FlipX, FlipY, FlipZ, RotateX, RotateY, RotateZ, None;

    public static TileModifiers fromInteger(int x) {
        switch(x) {
            case 0:
                return FlipX;
            case 1:
                return FlipY;
            case 2:
                return FlipZ;
            case 3:
                return RotateX;
            case 4:
                return RotateY;
            case 5:
                return RotateZ;
            case 6:
                return None;
        }
        return null;
    }

}
