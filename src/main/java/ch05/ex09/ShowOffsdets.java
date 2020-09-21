package ch05.ex09;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class ShowOffsdets {
public static void main(String[] args) {
        Instant now = Instant.now();
        ZoneId.getAvailableZoneIds().stream().forEach(
            zoneId -> {
                ZoneOffset offset = now.atZone(ZoneId.of(zoneId)).getOffset();
                if ((offset.getTotalSeconds() % 3600) != 0)
                    System.out.printf("zoneID: %s offset: %s%n", zoneId, offset);
            });
    }
}
