package ch08.ex16;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedCapture {
    static public class CityInfo {
        public final String city;
        public final String state;
        public final String zipCode;
        private CityInfo(String city, String state, String zipCode) {
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
        }
    }

    static public CityInfo extractCityInfo(String cityInfo) {
        Objects.requireNonNull(cityInfo, "cityInfo is null");
        Pattern pattern = Pattern.compile("(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2})\\s+(?<zipCode>[0-9]{5}|[0-9]{5}-[0-9]{4})");
        Matcher matcher = pattern.matcher(cityInfo);
        if (matcher.matches()) {
            return new CityInfo(matcher.group("city"), matcher.group("state"), matcher.group("zipCode"));

        }
        throw new IllegalArgumentException("No match");
    }

}
