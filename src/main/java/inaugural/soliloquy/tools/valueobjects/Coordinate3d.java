package inaugural.soliloquy.tools.valueobjects;

public class Coordinate3d {
    public static soliloquy.specs.common.valueobjects.Coordinate3d addCoordinates3d(
            soliloquy.specs.common.valueobjects.Coordinate3d c1,
            soliloquy.specs.common.valueobjects.Coordinate3d c2) {
        return soliloquy.specs.common.valueobjects.
                Coordinate3d.of(c1.X + c2.X, c1.Y + c2.Y, c1.Z + c2.Z);
    }

    public static soliloquy.specs.common.valueobjects.Coordinate3d addOffsets3d(
            soliloquy.specs.common.valueobjects.Coordinate3d c, int offsetX, int offsetY, int offsetZ) {
        return soliloquy.specs.common.valueobjects.
                Coordinate3d.of(c.X + offsetX, c.Y + offsetY, c.Z + offsetZ);
    }
}
