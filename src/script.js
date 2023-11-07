//Functions to add, remove, check, and print IP addresses in the blacklist
const addBtn = document.getElementById("addBtn");
const removeBtn = document.getElementById("removeBtn");
const checkBtn = document.getElementById("checkBtn");
const printBtn = document.getElementById("printBtn");
const resultDiv = document.getElementById("result");

const blacklist = new Map(); //Map to store IP addresses and their threat levels

addBtn.addEventListener("click", () => 
{// Lambda Function to add an IP address to the blacklist
    const ipAddress = prompt("Enter the IP address:");
    if (ipAddress !== null) 
    {// Check if the user cancels the prompt
        if (isValidIPAddress(ipAddress)) 
        {// Check if the IP address is valid
            const CustomThresholdFactor = parseFloat(prompt("Enter a custom threshold factor:"));
            if (!isNaN(CustomThresholdFactor) && CustomThresholdFactor > 0)
            {// Check if the custom threshold factor is valid
                const threatLevel = parseInt(prompt("Enter the threat level (1 to 10):"));
                if (!isNaN(threatLevel) && threatLevel >= 1 && threatLevel <= 10) 
                {// Check if the threat level is valid
                    addToBlacklist(ipAddress, threatLevel, CustomThresholdFactor);
                }
                else
                {
                    alert("Invalid threat level😑. Threat level must be between 1 and 10.");
                }
            }
            else 
            {
                alert("Invalid custom threshold factor😒. Please try again.");
            }
        } 
        else 
        {
            alert("Invalid IP address format😒. Please try again.");
        }
    }
});

removeBtn.addEventListener("click", () => {
    const ipAddress = prompt("Enter the IP address to remove:");
    if (ipAddress !== null) {
        if (isValidIPAddress(ipAddress)) {
            removeFromBlacklist(ipAddress);
        } else {
            alert("Invalid IP address format😒. Please try again.");
        }
    }
});

checkBtn.addEventListener("click", () => {
    const ipAddress = prompt("Enter the IP address to check:");
    if (ipAddress !== null) {
        if (isValidIPAddress(ipAddress)) {
            checkIPThreat(ipAddress);
        } else {
            alert("Invalid IP address format😒. Please try again.");
        }
    }
});

printBtn.addEventListener("click", () => {
    printBlacklist();
});

clearBtn.addEventListener("click", () => {
    clearBlacklist();
});

function addToBlacklist(ipAddress, threatLevel, CustomThresholdFactor) {
    const dynamicThreshold = calculateDynamicThreshold(threatLevel, CustomThresholdFactor);
    blacklist.set(ipAddress.toLowerCase(), dynamicThreshold);
    updateResult(`${ipAddress} added to the blacklist with threat 💀 level ${threatLevel}`);
}

function removeFromBlacklist(ipAddress) {
    const lowercaseIpAddress = ipAddress.toLowerCase();
    if (blacklist.has(lowercaseIpAddress)) {
        blacklist.delete(lowercaseIpAddress);
        updateResult(`${ipAddress} removed from the blacklist👍.`);
    } else {
        updateResult(`${ipAddress} is not in the blacklist😒.`);
    }
}

function checkIPThreat(ipAddress) {
    const lowercaseIpAddress = ipAddress.toLowerCase();
    if (blacklist.has(lowercaseIpAddress)) {
        const threatLevel = blacklist.get(lowercaseIpAddress);
        updateResult(`${ipAddress} is a potential threat 💀 with a cumulative threat score of ${threatLevel}`);
    } else {
        updateResult(`${ipAddress} is not a threat😇.`);
    }
}

function printBlacklist() {
    if (blacklist.size === 0) {
        updateResult("The blacklist is empty :(");
    } else {
        let result = "IP addresses in the blacklist:<br>";
        blacklist.forEach((threatLevel, ipAddress) => {
            result += `${ipAddress} (Threat Level: ${threatLevel})<br>`;
        });
        updateResult(result);
    }
}

function clearBlacklist() {
    blacklist.clear();
    updateResult("Blacklist has been cleared!👍");
}

function calculateDynamicThreshold(threatLevel, CustomThresholdFactor) {
    return Math.ceil(CustomThresholdFactor * threatLevel);
}

function isValidIPAddress(ipAddress) {
    const ipPattern = /^(?:\d{1,3}\.){3}\d{1,3}$/;
    return ipPattern.test(ipAddress);
}

function updateResult(message) {
    resultDiv.innerHTML = message;
}
