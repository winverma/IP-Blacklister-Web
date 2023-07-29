import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class NetworkThreatDetection {
    private static final Map<String, Integer> blacklist = new HashMap<>();
    private static final int MAX_THREAT_LEVEL = 10;
    private static final double THRESHOLD_FACTOR = 0.8;

    /**
     * Adds an IP address to the blacklist with a given threat level.
     *
     * @param ipAddress   The IP address to add to the blacklist.
     * @param threatLevel The threat level associated with the IP address (1 to 10).
     */
    public static void addToBlacklist(String ipAddress, int threatLevel) {
        if (threatLevel <= MAX_THREAT_LEVEL && threatLevel > 0) {
            int dynamicThreshold = calculateDynamicThreshold(threatLevel);
            blacklist.put(ipAddress.toLowerCase(), dynamicThreshold);
            System.out.println("Added " + ipAddress + " to the blacklist with threat level " + threatLevel);
        } else {
            System.out.println("Invalid threat level. Threat level should be between 1 and 10.");
        }
    }

    /**
     * Removes an IP address from the blacklist.
     *
     * @param ipAddress The IP address to remove from the blacklist.
     */
    public static void removeFromBlacklist(String ipAddress) {
        String lowercaseIpAddress = ipAddress.toLowerCase();
        if (blacklist.containsKey(lowercaseIpAddress)) {
            blacklist.remove(lowercaseIpAddress);
            System.out.println(ipAddress + " removed from the blacklist.");
        } else {
            System.out.println(ipAddress + " is not in the blacklist.");
        }
    }

    /**
     * Checks if an IP address is in the blacklist (i.e., a potential threat).
     *
     * @param ipAddress The IP address to check.
     * @return True if the IP address is in the blacklist, otherwise false.
     */
    public static boolean isThreat(String ipAddress) {
        return blacklist.containsKey(ipAddress.toLowerCase());
    }

    /**
     * Prints all IP addresses in the blacklist along with their associated threat levels.
     */
    public static void printBlacklist() {
        if (blacklist.isEmpty()) {
            System.out.println("The blacklist is empty.");
        } else {
            System.out.println("IP addresses in the blacklist:");
            for (Map.Entry<String, Integer> entry : blacklist.entrySet()) {
                System.out.println(entry.getKey() + " (Threat Level: " + entry.getValue() + ")");
            }
        }
    }

    /**
     * Calculates the dynamic threshold based on the given threat level.
     *
     * @param threatLevel The threat level (1 to 10).
     * @return The dynamic threshold.
     */
    private static int calculateDynamicThreshold(int threatLevel) {
        return (int) Math.ceil(THRESHOLD_FACTOR * threatLevel);
    }

    /**
     * Validates if the provided input is a valid IP address.
     *
     * @param ipAddress The IP address to validate.
     * @return True if the IP address is valid, otherwise false.
     */
    private static boolean isValidIPAddress(String ipAddress) {
        // Regular expression to validate IPv4 addresses
        String ipPattern = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$";
        Pattern pattern = Pattern.compile(ipPattern);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add IP address to blacklist");
            System.out.println("2. Remove IP address from blacklist");
            System.out.println("3. Check if IP address is a threat");
            System.out.println("4. Print blacklist");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter IP address: ");
                    String ipAddress = scanner.nextLine();
                    if (isValidIPAddress(ipAddress)) {
                        System.out.print("Enter threat level (1 to 10): ");
                        int threatLevel = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline left by nextInt()
                        addToBlacklist(ipAddress, threatLevel);
                    } else {
                        System.out.println("Invalid IP address format. Please try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter IP address to remove: ");
                    String ipToRemove = scanner.nextLine();
                    if (isValidIPAddress(ipToRemove)) {
                        removeFromBlacklist(ipToRemove);
                    } else {
                        System.out.println("Invalid IP address format. Please try again.");
                    }
                    break;
                case 3:
                    System.out.print("Enter IP address to check: ");
                    String ipToCheck = scanner.nextLine();
                    if (isValidIPAddress(ipToCheck)) {
                        if (isThreat(ipToCheck)) {
                            System.out.println(ipToCheck + " is a potential threat with threat level " + blacklist.get(ipToCheck.toLowerCase()));
                        } else {
                            System.out.println(ipToCheck + " is not a threat.");
                        }
                    } else {
                        System.out.println("Invalid IP address format. Please try again.");
                    }
                    break;
                case 4:
                    printBlacklist();
                    break;
                case 5:
                    scanner.close();
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        }
    }
}
