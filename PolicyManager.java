package InsurancePolicyManagementSystemUsingMapInterface;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PolicyManager {
    private Map<String, Policy> policyMap = new HashMap<>();
    private LinkedHashMap<String, Policy> linkedPolicyMap = new LinkedHashMap<>();
    private TreeMap<Date, Policy> treePolicyMap = new TreeMap<>();

    // Method to add policies to all maps
    public void addPolicy(Policy policy) {
        policyMap.put(policy.getPolicyNumber(), policy);
        linkedPolicyMap.put(policy.getPolicyNumber(), policy);
        treePolicyMap.put(policy.getExpiryDate(), policy);
    }

    // Method to retrieve a policy by its number
    public Policy getPolicyByNumber(String policyNumber) {
        return policyMap.get(policyNumber); 
    }

    // Method to list all policies expiring within the next 30 days
    public List<Policy> getPoliciesExpiringSoon() {
        List<Policy> expiringSoon = new ArrayList<>();
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_YEAR, 30);
        Date next30Days = cal.getTime();

        // Get policies expiring in the next 30 days
        for (Date expiryDate : treePolicyMap.headMap(next30Days, true).keySet()) {
            expiringSoon.add(treePolicyMap.get(expiryDate));
        }

        return expiringSoon;
    }

    // Method to list all policies for a specific policyholder
    public List<Policy> getPoliciesByPolicyholder(String policyholderName) {
        List<Policy> policies = new ArrayList<>();
        for (Policy policy : linkedPolicyMap.values()) {
            if (policy.getPolicyholderName().equalsIgnoreCase(policyholderName)) {
                policies.add(policy);
            }
        }
        return policies;
    }

    // Method to remove expired policies
    public void removeExpiredPolicies() {
        Date today = new Date();
        // Remove from HashMap and LinkedHashMap
        policyMap.values().removeIf(policy -> policy.getExpiryDate().before(today));
        linkedPolicyMap.values().removeIf(policy -> policy.getExpiryDate().before(today));
        // Remove from TreeMap
        treePolicyMap.headMap(today).clear();
    }

    // Method to compare performance (for demonstration, we'll measure time for insertion)
    public void comparePerformance() {
        long startTime, endTime;

        // HashMap performance
        startTime = System.nanoTime();
        policyMap.put("P101", new Policy("P101", "Test", new Date(), "Auto", 5000));
        endTime = System.nanoTime();
        System.out.println("HashMap insertion time: " + (endTime - startTime) + " ns");

        // LinkedHashMap performance
        startTime = System.nanoTime();
        linkedPolicyMap.put("P101", new Policy("P101", "Test", new Date(), "Auto", 5000));
        endTime = System.nanoTime();
        System.out.println("LinkedHashMap insertion time: " + (endTime - startTime) + " ns");

        // TreeMap performance
        startTime = System.nanoTime();
        treePolicyMap.put(new Date(), new Policy("P101", "Test", new Date(), "Auto", 5000));
        endTime = System.nanoTime();
        System.out.println("TreeMap insertion time: " + (endTime - startTime) + " ns");
    }
}

