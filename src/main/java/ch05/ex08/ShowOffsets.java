package ch05.ex08;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class ShowOffsets {
    public static void main(String[] args) {
        Instant now = Instant.now();

        ZoneId.getAvailableZoneIds().stream().forEach(
            zoneId -> {
                ZoneOffset offset = now.atZone(ZoneId.of(zoneId)).getOffset();
                System.out.printf("zoneID: %s offset: %s%n", zoneId, offset);
            });
    }
}
