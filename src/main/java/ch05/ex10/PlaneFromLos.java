package ch05.ex10;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PlaneFromLos {
    private static final String LOS_ANGELES = "America/Los_Angeles";
    private static final String FRANKFURT = "Europe/Berlin";

    public static void main(String[] args) {
        ZonedDateTime departure = ZonedDateTime.of(2014, 12, 30, 15, 5, 0, 0,
                ZoneId.of(LOS_ANGELES)
        );
        ZonedDateTime arrival = advance(departure, 60 * 10 + 50, FRANKFURT);
        System.out.println(departure);
        System.out.println(arrival);
    }

    private static ZonedDateTime advance(
            ZonedDateTime zdt, int minutes, String zoneId) {
        return zdt.plusMinutes(minutes).toInstant().atZone(ZoneId.of(zoneId));
    }

}
