package InsurancePolicyManagementSystemUsingMapInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        PolicyManager manager = new PolicyManager();
        
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        // Adding some policies
        manager.addPolicy(new Policy("P001", "Alice", sdf.parse("2024-10-15"), "Health", 10000));
        manager.addPolicy(new Policy("P002", "Bob", sdf.parse("2024-11-01"), "Auto", 5000));
        manager.addPolicy(new Policy("P003", "Charlie", sdf.parse("2024-09-30"), "Home", 8000));

        // Retrieve a policy by number
        System.out.println("Policy P001: " + manager.getPolicyByNumber("P001"));

        // List policies expiring in the next 30 days
        System.out.println("\nPolicies expiring soon:");
        for (Policy policy : manager.getPoliciesExpiringSoon()) {
            System.out.println(policy);
        }

        // List policies for a specific policyholder
        System.out.println("\nPolicies for Bob:");
        for (Policy policy : manager.getPoliciesByPolicyholder("Bob")) {
            System.out.println(policy);
        }

        // Remove expired policies
        System.out.println("\nRemoving expired policies...");
        manager.removeExpiredPolicies();

        // Compare performance of different maps
        System.out.println("\nPerformance comparison:");
        manager.comparePerformance();
    }
}

