package ch04.dc4;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class ConfigurationPreferences {
    public static final String KEY_LOCATION_X = "frame.location.x";
    public static final String KEY_LOCATION_Y = "frame.location.y";
    public static final String KEY_WIDTH = "frame.width";
    public static final String KEY_HEIGHT = "frame.height";
    public static final String KEY_FONT_NAME = "panel.font.name";
    public static final String KEY_FONT_SIZE = "panel.font.size";
    public static final String KEY_COLOR_FOREGROUND_R = "panel.color.fg.r";
    public static final String KEY_COLOR_FOREGROUND_G = "panel.color.fg.g";
    public static final String KEY_COLOR_FOREGROUND_B = "panel.color.fg.b";
    public static final String KEY_COLOR_BACKGROUND_R = "panel.color.bg.r";
    public static final String KEY_COLOR_BACKGROUND_G = "panel.color.bg.g";
    public static final String KEY_COLOR_BACKGROUND_B = "panel.color.bg.b";
    public static final int DEFAULT_WIDTH = 370;
    public static final int DEFAULT_HEIGHT = 80;
    public static final int DEFAULT_FONT_SIZE = 50;
    public static final String DEFAULT_FONT_NAME = Font.getDefault().getName();
    public static final DisplayColor DEFAULT_COLOR_FOREGROUND = DisplayColor.BLACK;
    public static final DisplayColor DEFAULT_COLOR_BACKGROUND = DisplayColor.WHITE;

    private Preferences prefs;

    public ConfigurationPreferences() {
        prefs = Preferences.userNodeForPackage(this.getClass());
    }


    public void update(Point2D location, int width, int height) {
        prefs.putInt(KEY_LOCATION_X, (int)location.getX());
        prefs.putInt(KEY_LOCATION_Y, (int)location.getY());
        prefs.putInt(KEY_WIDTH, width);
        prefs.putInt(KEY_HEIGHT, height);
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public void update(Point2D location, int width, int height, Font font, DisplayColor foreground,
                       DisplayColor background) {
        prefs.putInt(KEY_LOCATION_X, (int)location.getX());
        prefs.putInt(KEY_LOCATION_Y, (int)location.getY());
        prefs.putInt(KEY_WIDTH, width);
        prefs.putInt(KEY_HEIGHT, height);
        prefs.put(KEY_FONT_NAME, font.getName());
        prefs.putInt(KEY_FONT_SIZE, (int)font.getSize());
        prefs.putDouble(KEY_COLOR_FOREGROUND_R, foreground.getValue().getRed());
        prefs.putDouble(KEY_COLOR_FOREGROUND_G, foreground.getValue().getGreen());
        prefs.putDouble(KEY_COLOR_FOREGROUND_B, foreground.getValue().getBlue());
        prefs.putDouble(KEY_COLOR_BACKGROUND_R, background.getValue().getRed());
        prefs.putDouble(KEY_COLOR_BACKGROUND_G, background.getValue().getGreen());
        prefs.putDouble(KEY_COLOR_BACKGROUND_B, background.getValue().getBlue());
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public Point2D getLocation() {
        int x = prefs.getInt(KEY_LOCATION_X, -1);
        int y = prefs.getInt(KEY_LOCATION_Y, -1);
        if (x == -1 && y == -1)
            return null;
        return new Point2D(x, y);
    }

    public int getWidth() {
        return prefs.getInt(KEY_WIDTH, DEFAULT_WIDTH);
    }

    public int getHeight() {
        return prefs.getInt(KEY_HEIGHT, DEFAULT_HEIGHT);
    }

    public String getFontName() {
        return prefs.get(KEY_FONT_NAME, DEFAULT_FONT_NAME);
    }

    public int getFontSize() {
        return prefs.getInt(KEY_FONT_SIZE, DEFAULT_FONT_SIZE);
    }

    public DisplayColor getForeground() {
        double r = prefs.getDouble(KEY_COLOR_FOREGROUND_R, -1);
        double g = prefs.getDouble(KEY_COLOR_FOREGROUND_G, -1);
        double b = prefs.getDouble(KEY_COLOR_FOREGROUND_B, -1);
        try {
            return DisplayColor.valueOf(Color.rgb((int)r, (int)g, (int)b));
        } catch (IllegalArgumentException e) {
            return DEFAULT_COLOR_FOREGROUND;
        }
    }

    public DisplayColor getBackground() {
        double r = prefs.getDouble(KEY_COLOR_BACKGROUND_R, -1);
        double g = prefs.getDouble(KEY_COLOR_BACKGROUND_G, -1);
        double b = prefs.getDouble(KEY_COLOR_BACKGROUND_B, -1);
        try {
            return DisplayColor.valueOf(Color.rgb((int)r, (int)g, (int)b));
        } catch (IllegalArgumentException e) {
            return DEFAULT_COLOR_BACKGROUND;
        }
    }
}
