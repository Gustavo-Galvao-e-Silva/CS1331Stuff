import java.util.Random;

public class Collection {
    Card[] binder;
    int cardsOwned;

    public Collection(Card[] binder) {
        this.binder = binder.clone();
        this.cardsOwned = 0;
        for (Card card : binder) {
            if (card != null) {
                cardsOwned += 1;
            }
        }
    }

    public Collection() {
        this.binder = new Card[4];
        this.cardsOwned = 0;
    }

    public Card addCard(int index, Card newCard) {
        if (!isValidIndex(index)) {
            System.out.println("Cannot add a card to this spot.");
            return null;
        }

        this.cardsOwned++;
        Card oldCard = this.binder[index];
        if (oldCard == null) {
            System.out.printf("Inserted: %s%n", newCard);
            return null;
        }

        System.out.printf("Replaced: %s%n", oldCard);
        return oldCard;
    }

    public Card sellCard(int index) {
        if (!isValidIndex(index)) {
            System.out.println("There was no card to sell!");
            return null;
        }

        this.cardsOwned--;
        Card soldCard = this.binder[index];
        this.binder[index] = null;
        System.out.printf("Sold: %s", soldCard);
        return soldCard;
    }

    public void showCertainCards(int minCondition) {
        for (Card card : this.binder) {
            if (card != null && card.getCondition() > minCondition) {
                System.out.println(card);
            }
        }
    }

    public void restoreAllCards() {
        int cardsRestored = 0;
        Random rand = new Random();
        for (Card card : this.binder) {
            if (card != null && card.isRestorable()) {
                int newCondition = card.getCondition() + rand.nextInt(10) + 1;
                System.out.printf("Restored to %d: %s%n", newCondition, card);
                card.setCondition(newCondition);
                cardsRestored++;
            }
        }

        if (cardsRestored == 0) {
            System.out.println("There were no cards to restore.");
        }
    }

    public void battle(int index) {
        if (!isValidIndex(index)) {
            System.out.println("Cannot battle with a card at this spot.");
            return;
        }
        Random rand = new Random();
        Card batlleCard = this.binder[index];
        int newCondition = batlleCard.getCondition() + rand.nextInt(10) + 1;
        newCondition = newCondition < 40 ? 40 : newCondition;
        batlleCard.setCondition(newCondition);
        System.out.printf("Used: %s%n", batlleCard);
    }

    public String toString() {
        if (this.cardsOwned == 0) {
            return "I own no cards!";
        }

        String collectionString = String.format("I own %d cards.", this.cardsOwned);
        for (Card card : this.binder) {
            if (card != null) {
                collectionString += String.format("%s%n", card);
            }
        }

        return collectionString.trim();
    }

    private boolean isValidIndex(int index) {
        boolean isValidIndexPosition = index >= 0 && index < this.binder.length;
        return isValidIndexPosition && this.binder[index] != null;
    }
}
