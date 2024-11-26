
class DiffieHellman {
    public static void main(String args[]) {
        int p = 23; // Publicly known prime number
        int g = 5;  // Publicly known primitive root
        int x = 4;  // Secret key known only to Alice
        int y = 3;  // Secret key known only to Bob

        // Step 1: Alice computes and sends her public value
        double aliceSends = (Math.pow(g, x)) % p;

        // Step 2: Bob computes the shared secret using Alice's public value
        double bobComputes = (Math.pow(aliceSends, y)) % p;

        // Step 3: Bob computes and sends his public value
        double bobSends = (Math.pow(g, y)) % p;

        // Step 4: Alice computes the shared secret using Bob's public value
        double aliceComputes = (Math.pow(bobSends, x)) % p;

        // Step 5: Verify the shared secret
        double sharedSecret = (Math.pow(g, (x * y))) % p;

        // Output
        System.out.println("Simulation of Diffie-Hellman Key Exchange Algorithm\n---");
        System.out.println("Alice Sends: " + aliceSends);
        System.out.println("Bob Computes: " + bobComputes);
        System.out.println("Bob Sends: " + bobSends);
        System.out.println("Alice Computes: " + aliceComputes);
        System.out.println("Shared Secret: " + sharedSecret);

        // Check if shared secrets match
        if ((aliceComputes == sharedSecret) && (aliceComputes == bobComputes)) {
            System.out.println("Success: Shared secrets match! " + sharedSecret);
        } else {
            System.out.println("Error: Shared secrets do not match.");
        }
    }
}
