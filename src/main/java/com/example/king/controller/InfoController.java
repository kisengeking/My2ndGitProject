import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

@RestController
public class InfoController {

    private final Instant startTime;

    public InfoController() {
        // Store the time when the app starts
        this.startTime = Instant.now();
    }


    @GetMapping({"/", "/home", "/info"})
    public ResponseEntity<Object> getServerAndDatabaseInfo() {
        String serverIp = getServerIp();
        String hostName = getHostName();

        // Extract database name from JDBC URL
        String dbName = "not exist";


        // Calculate runtime
        Duration runtime = Duration.between(startTime, Instant.now());
        String runtimeFormatted = formatDuration(runtime);

        HashMap map = new HashMap();
        map.put("Application", "Job Order Automation");
        map.put("hostname", hostName);
        map.put("serverIp", serverIp);
        map.put("runtime", runtimeFormatted);
        map.put("current time", LocalDateTime.now());

//        return String.format(
//                "Server IP: %s, Database: %s, Runtime: %s, At: %s",
//                serverIp, dbName, runtimeFormatted, LocalDateTime.now()
//        );

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "unknown";
        }
    }

    private String getServerIp() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface ni : Collections.list(interfaces)) {
                if (!ni.isLoopback() && ni.isUp()) {
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    for (InetAddress addr : Collections.list(addresses)) {
                        if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()) {
                            return addr.getHostAddress();
                        }
                    }
                }
            }
            return InetAddress.getLocalHost().getHostAddress(); // fallback
        } catch (Exception e) {
            e.printStackTrace();
            return "unknown";
        }
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        StringBuilder sb = new StringBuilder();
        if (hours > 0) sb.append(hours).append(" hour").append(hours > 1 ? "s " : " ");
        if (minutes > 0) sb.append(minutes).append(" minute").append(minutes > 1 ? "s " : " ");
        sb.append(seconds).append(" second").append(seconds != 1 ? "s" : "");

        return sb.toString();
    }
}
