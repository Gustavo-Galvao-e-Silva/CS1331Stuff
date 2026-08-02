public class Card {
    private int hitpoints;
    private String pokemonName;
    private PokemonType pokemonType;
    private String attack;
    private int condition;

    public Card(int hp, String name, PokemonType type, String attack, int condition) {
        this.hitpoints = hp;
        this.pokemonName = name;
        this.pokemonType = type;
        this.attack = attack;
        this.condition = isValidCondition(condition) ? condition : 80;
        int conditionFlag = this.condition / 10;
        switch (conditionFlag) {
        case 4:
            System.out.println("Card condition: Damaged");
            break;
        case 5:
            System.out.println("Card condition: Fine");
            break;
        case 6:
            System.out.println("Card condition: Good");
            break;
        case 7:
            System.out.println("Card condition: Very Good");
            break;
        case 8:
            System.out.println("Card condition: Excellent");
            break;
        default:
            System.out.println("Card condition: Mint");
            break;
        }
    }

    public Card(int hp, String name, PokemonType type) {
        this(hp, name, type, "Hyperbeam", 80);
    }

    public Card() {
        this(120, "Ditto", PokemonType.NORMAL, "Imposter", 89);
    }

    public int getCondition() {
        return this.condition;
    }

    public void setCondition(int newCondition) {
        this.condition = isValidCondition(newCondition) ? newCondition : this.condition;
    }

    public boolean isRestorable() {
        return (this.condition >= 50 && this.condition <= 89) ? true : false;
    }

    public String toString() {
        return String.format("<%d,%s,%s,%s,%d,%b>", this.hitpoints, this.pokemonName, this.pokemonType,
                this.attack, this.condition, this.isRestorable());
    }

    private static boolean isValidCondition(int condition) {
        return (condition >= 40 && condition <= 100);
    }

}
