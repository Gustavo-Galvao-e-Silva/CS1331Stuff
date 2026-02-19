public class BattleSimulator {
    public static void main(String[] args) {
        Character hero = new Character("Link", 30.0, 20.0, 2.0, 0.9);  
        Character villain = new Character("Ganon", 60.0, 10.0, 2.0, 0.7);  

        while (hero.getHealth() > 0 && villain.getHealth() > 0) {
            hero.attackOpponent(villain);
            villain.attackOpponent(hero);
        }

        String winner = hero.getHealth() > villain.getHealth() ? hero.getName() : villain.getName();
        System.out.printf("%s has won the battle.", winner);
    }
}


class Character {
    private String name;
    private double health;
    private double attackDamage;
    private double randomDamageMultiplier;
    private double accuracy;

    public Character(String n, double h, double dmg, double dmgMltpr, double acc) throws IllegalArgumentException {
        this.name = n;
        this.health = h;
        this.attackDamage = dmg;
        this.randomDamageMultiplier = dmgMltpr;

        if (acc <= 0 || acc > 1) {
            throw new IllegalArgumentException("Accuracy can only be a value in the interval (0, 1].");
        }
        this.accuracy= acc;
    }

    public void attackOpponent(Character opponent) {
        if (this.health <= 0) {
            System.out.printf("%s has died, and cannot attack!%n", this.name, opponent.getName());
            return;
        }
        // Checks for attack miss with chance being equal to 1 - this.accuracy
        boolean hasMissed = Math.random() > this.accuracy;
        if (hasMissed) {
            System.out.printf("%s has missed their attack!%n", this.name);
            return;
        }
        // Damage calculation
        double damage = (Math.random() * this.randomDamageMultiplier) + this.attackDamage;
        opponent.takeDamage(damage);

        System.out.printf("%s has landed their attack, and dealt %.2f damage to %s.%n", this.name, damage, opponent.name);
    }

    public void takeDamage(double damage) throws IllegalArgumentException {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative.");
        }
        this.health -= damage;
    }

    public double getHealth() {
        return this.health;
    }

    public String getName() {
        return this.name;
    }
}
