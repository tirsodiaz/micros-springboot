import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;


public class TimezoneDisplayApp {

	public enum OffsetBase {
	    GMT, UTC
	}
	
    public static void main(String... args) {
    	TimezoneDisplayApp display = new TimezoneDisplayApp();

        System.out.println("Time zones in UTC:");
        List<String> utc = display.getTimeZoneList(
        		TimezoneDisplayApp.OffsetBase.UTC);
        utc.forEach(System.out::println);

        System.out.println("Time zones in GMT:");
        List<String> gmt = display.getTimeZoneList(
        		TimezoneDisplayApp.OffsetBase.GMT);
        gmt.forEach(System.out::println);
        
        //Resumiendo OffsetDateTime es para modelos de persistencia y ZonedDateTime para realizar operaciones con las fechas, 
        //y como ultimo comentario mencionar que podemos obtener un ZonedDateTime mediante un OffsetDateTime y viceversa.
        ZoneId zoneId = ZoneId.of("Europe/Madrid");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId); //= ZonedDateTime.of(localDateTime, zoneId)
        ZonedDateTime destZonedDateTimeCanary = zonedDateTime.withZoneSameInstant(ZoneId.of("Atlantic/Canary"));
        ZonedDateTime destZonedDateTimeGMT = zonedDateTime.withZoneSameInstant(ZoneId.of("GMT"));

        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        
        ZoneOffset zoneOffSet= ZoneOffset.of("+02:00");
        OffsetDateTime offsetDateTime = OffsetDateTime.now(zoneOffSet);     
        offsetDateTime.withOffsetSameInstant(ZoneOffset.of("+03:00"));
        System.out.println("\n\tHoras del Mundo");
        System.out.println("*******************************");
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("Hora en GMT: " + getHoraFormato(calendar));
        Instant instant = calendar.toInstant();
        LocalDateTime ldtMadrid = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Madrid"));
        LocalDateTime ldtGMT = LocalDateTime.ofInstant(instant, ZoneId.of("GMT"));
        
        
        calendar.setTimeZone(TimeZone.getTimeZone("Atlantic/Canary"));
        System.out.println("Hora en Canary: " + getHoraFormato(calendar));
         
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Lisbon"));
        System.out.println("Hora en Lisboa: " + getHoraFormato(calendar));
         
        calendar.setTimeZone(TimeZone.getTimeZone("Africa/Casablanca"));
        System.out.println("Hora en Casablanca: " + getHoraFormato(calendar));
        
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        System.out.println("Hora en Madrid: " + getHoraFormato(calendar));
         
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        System.out.println("Hora en Roma: " + getHoraFormato(calendar));
         
        calendar.setTimeZone(TimeZone.getTimeZone("Africa/Cairo"));
        System.out.println("Hora en El Cairo: " + getHoraFormato(calendar));
         
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        System.out.println("Hora en Moscu: " + getHoraFormato(calendar));
                  
        //Mostrar TimeZone  
        /*for (String timeZone : TimeZone.getAvailableIDs()) {
            System.out.println(timeZone);
        }*/
    }
     
    static String getHoraFormato(Calendar cal){
        String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" +
                    cal.get(Calendar.MINUTE) + ":" + 
                    cal.get(Calendar.SECOND);
        return hora;
    }
    
    
    public List<String> getTimeZoneList(OffsetBase base) {
    	 
        LocalDateTime now = LocalDateTime.now();
        return ZoneId.getAvailableZoneIds().stream()
          .map(ZoneId::of)
          .sorted(new ZoneComparator())
          .map(id -> String.format(
            "(%s%s) %s", 
            base, getOffset(now, id), id.getId()))
          .collect(Collectors.toList());
    }
    
    private String getOffset(LocalDateTime dateTime, ZoneId id) {
        return dateTime
          .atZone(id)
          .getOffset()
          .getId()
          .replace("Z", "+00:00");
    }
}
