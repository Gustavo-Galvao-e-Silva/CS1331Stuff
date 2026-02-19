public class Driver {

    public static void main(String[] args) {
        Collection myCollection = new Collection(new Card[5]);
        Card firstCard = new Card();
        Card secondCard = new Card();
        Card thirdCard = new Card();
        myCollection.addCard(0, thirdCard);

    }
}
