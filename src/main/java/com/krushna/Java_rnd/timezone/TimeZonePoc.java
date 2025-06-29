package com.krushna.Java_rnd.timezone;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeZonePoc {
	
	public static void main(String[] args) {
		
		// User's selected time and timezone
        String userTime = "2025-05-14T10:00:00";
        String userTimeZone = "America/New_York";

        // Step 1: Parse user time with their timezone
        ZonedDateTime userDateTime = ZonedDateTime.of(
                2025, 5, 14, 10, 0, 0, 0, ZoneId.of(userTimeZone));

        System.out.println("User Time (New York): " + userDateTime);

        // Step 2: Convert to UTC for storage
        ZonedDateTime utcDateTime = userDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("Stored in UTC: " + utcDateTime);

        // Simulate retrieval and conversion to another user's timezone (Asia/Kolkata)
        ZonedDateTime indiaDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
        System.out.println("Displayed in IST (India): " + indiaDateTime);

        // Step 3: Format for display
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        System.out.println("Formatted for India User: " + indiaDateTime.format(formatter));
        
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Current time (System Default Timezone): " + now);

		
	}

}
