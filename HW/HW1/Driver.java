public class Driver {

    public static void main(String[] args) {
        Collection myCollection = new Collection(new Card[5]);
        Card firstCard = new Card(50, "Bulbasaur", PokemonType.GRASS, "Razor Leaf", 90);
        Card secondCard = new Card(65, "Munchlax", PokemonType.NORMAL);
        Card thirdCard = new Card();
        myCollection.addCard(0, firstCard);
        myCollection.addCard(2, secondCard);
        myCollection.addCard(3, thirdCard);
        System.out.println(myCollection);
        myCollection.sellCard(3);
        myCollection.showCertainCards(85);
        myCollection.restoreAllCards();
        System.out.println(myCollection);
        myCollection.battle(0);
        System.out.println(myCollection);
    }
}
