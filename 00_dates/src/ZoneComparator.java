import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Comparator;

class ZoneComparator implements Comparator<ZoneId> {

    @Override
    public int compare(ZoneId zoneId1, ZoneId zoneId2) {
        LocalDateTime now = LocalDateTime.now();
        ZoneOffset offset1 = now.atZone(zoneId1).getOffset();
        ZoneOffset offset2 = now.atZone(zoneId2).getOffset();

        return offset1.compareTo(offset2);
    }
}
