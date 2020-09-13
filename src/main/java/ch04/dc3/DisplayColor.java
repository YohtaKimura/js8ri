package ch04.dc3;

import javafx.scene.paint.Color;

public enum DisplayColor {
    /* @formatter:off */
    BLACK(Color.rgb(0, 0, 0)),
    SILVER(Color.rgb(192, 192, 192)),
    GRAY(Color.rgb(128, 128, 128)),
    WHITE(Color.rgb(255, 255, 255)),
    MAROON(Color.rgb(128, 0, 0)),
    RED(Color.rgb(255, 0, 0)),
    PURPLE(Color.rgb(128, 0, 128)),
    FUCHSIA(Color.rgb(255, 0, 255)),
    GREEN(Color.rgb(0, 128, 0)),
    LIME(Color.rgb(0, 255, 0)),
    OLIVE(Color.rgb(128, 128, 0)),
    YELLOW(Color.rgb(255, 255, 0)),
    NAVY(Color.rgb(0, 0, 128)),
    BLUE(Color.rgb(0, 0, 255)),
    TEAL(Color.rgb(0, 128, 128)),
    AQUA(Color.rgb(0, 255, 255)),
    DARKGRAY(Color.rgb(50, 50, 50));
    /* @formatter:on */

    private static final String COLOR_TIP = "\u25a0\u25a0\u25a0";

    private final Color color;

    DisplayColor(Color color) {
	this.color = color;
    }

    /** Get name of color with lower case. e.g. "red", "green", and "blue". */
    @Override
    public String toString() {
	return name().toLowerCase();
    }

    /**
     * Get color object of this color.
     *
     * @return color
     */
    public Color getValue() {
	return color;
    }

    /**
     * Find item by name.
     *
     * @param color name of color
     * @return item
     * @throws NullPointerException if color is null
     * @throws IllegalArgumentException if cannot find item
     */
    public static DisplayColor nameOf(String color) {
	for (DisplayColor c : values()) {
	    String name = c.toString();
	    if (name.equals(color) || name.toLowerCase().equals(color) || c.getHtml().equals(color))
		return c;
	}
	throw new IllegalArgumentException("Undefined color: " + color);
    }

    /**
     * Find item by color object.
     *
     * @param color color object.
     * @return item
     * @throws NullPointerException if color is null
     * @throws IllegalArgumentException if cannot find item
     */
    public static DisplayColor valueOf(Color color) {
	if (color == null)
	    throw new NullPointerException();
	for (DisplayColor c : values()) {
	    if (c.getValue().equals(color))
		return c;
	}
	throw new IllegalArgumentException("Undefined color: " + color);
    }

    public String getHtml() {
	return "<html>" + "<span style=\"color:#" + getHexValue() + "\"> " + COLOR_TIP + "</span> "
		+ toString() + "</html>";
    }

    private String getHexValue() {
        return String.format( "#%02X%02X%02X",
            (int)( getValue().getRed() * 255 ),
            (int)( getValue().getGreen() * 255 ),
            (int)( getValue().getBlue() * 255 ) );
    }
}
