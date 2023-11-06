### NetworkThreatDetector

## Components of my code: 

blacklist Map Functionality: The blacklist is a HashMap that stores IP addresses as keys and their associated dynamic threat levels as values. It keeps track of potential threats based on their threat levels.

MAX_THREAT_LEVEL and THRESHOLD_FACTOR Constants Functionality: These constants define the upper limit for the threat level (10) and the factor used to calculate the dynamic threshold for each IP address.

addToBlacklist(String ipAddress, int threatLevel) Functionality: Adds an IP address to the blacklist map with a given threat level after validating the input. The threat level is adjusted based on the dynamic threshold.

removeFromBlacklist(String ipAddress) Functionality: Removes an IP address from the blacklist map if it exists in the blacklist.

isThreat(String ipAddress) Functionality: Checks if the given IP address exists in the blacklist map, indicating that it is a potential threat.

printBlacklist() Functionality: Prints all IP addresses in the blacklist map along with their associated threat levels. The blacklist is sorted by threat level before printing, improving readability.

calculateDynamicThreshold(int threatLevel) Functionality: Calculates the dynamic threshold based on the given threat level and the THRESHOLD_FACTOR.

isValidIPAddress(String ipAddress) Functionality: Validates if the provided input is a valid IPv4 address using regular expressions.

main(String[] args) Functionality: The main method contains a user-friendly menu that allows users to interact with the Network Threat Detection system. It provides options to add IP addresses with threat levels, remove IP addresses from the blacklist, check if an IP address is a threat, print the blacklist, and exit the program.


## Unique Features:

Input Validation: The program performs input validation for IP addresses and threat levels to ensure they are in the correct format and range, respectively.

Case-Insensitive Menu: The menu options are case-insensitive, allowing users to enter choices in any case.

Persistence: The program uses a shutdown hook to save the blacklist data to a file when the program exits and loads it back when the program starts, allowing the blacklist to persist between executions.

Blacklist Size Limit: The program includes a maximum size limit for the blacklist, preventing new IP addresses from being added when the blacklist is full.


## Optimization Element:

Using a HashMap: The blacklist is implemented using a HashMap, which provides fast lookup time for IP addresses. It allows for efficient retrieval and removal of IP addresses from the blacklist.


Differentiating Factors:

Threat Severity Levels: The program defines threat severity levels based on the threat level, providing additional context to the users about the potential impact of a threat.
Graceful Exit: The program provides an option for users to exit the program gracefully, ensuring that resources are released correctly.
