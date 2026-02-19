import java.util.Random;
import java.util.Scanner;

public class PokemonBattle {
    public static void main(String[] args) {
        Random rand = new Random(); 
        Scanner scan = new Scanner(System.in);

        String myPokemonNickname;
        String rivalPokemonNickname;

        double rivalPokemonHealth = rand.nextInt(20) + 40;

        System.out.print("Enter your Pokemon's nickname: ");        
        myPokemonNickname = scan.nextLine().trim();

        System.out.print("Enter your rival's Pokemon's nickname: ");        
        rivalPokemonNickname = scan.nextLine().trim();
        
        System.out.printf("Your rival has chosen %s to fight, which has %.2f health.%n", rivalPokemonNickname, rivalPokemonHealth);
        
        int numBattleTurns = 0;

        do {
            AttackType attack = AttackType.values()[rand.nextInt(3)];
            double totalAttackDamage = 0;

            switch (attack) {
                case SCRATCH:
                    int numScratches = rand.nextInt(3) + 1;
                    double scratchDamage = (rand.nextDouble() * 5) + 1;
                    totalAttackDamage = numScratches * scratchDamage;
                    break;
                case SURF: 
                    double surfDamage = (rand.nextDouble() * 9) + 2;
                    totalAttackDamage = surfDamage;
                    break;
                case TACKLE:
                    double tackleDamage = (rand.nextDouble() * 2) + 7;
                    totalAttackDamage = tackleDamage;
                    break;
            }

            rivalPokemonHealth -= totalAttackDamage;
            rivalPokemonHealth = rivalPokemonHealth < 0 ? 0  : rivalPokemonHealth; 

            System.out.printf("%s used %s and did %.2f damage. Your rival’s %s has %.2f health remaining.%n", myPokemonNickname, attack, totalAttackDamage, rivalPokemonNickname, rivalPokemonHealth);

            numBattleTurns++;
        } while (rivalPokemonHealth > 0);
        
        System.out.printf("%s fainted after %d turns!%n", rivalPokemonNickname, numBattleTurns);

        double prizeMoney = (rand.nextDouble() * -1200) + 2400;
        System.out.printf("You have earned $%.2f!", prizeMoney);

        scan.close();
    }
}
