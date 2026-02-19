import java.util.Random;

public class TechDining {

    public static Food[] createMeal(int mealLength) {
        Random rand = new Random();
        Food[] options = Food.values();
        Food[] meal = new Food[mealLength];

        for (int i = 0; i < mealLength; i++) {
            int randomOptionIndex = rand.nextInt(options.length);
            meal[i] = options[randomOptionIndex];
        }

        return meal;
    }

    public static Food[][] createOrder(int numberOfMeals) {
        Food[][] order = new Food[numberOfMeals][];

        for (int i = 0; i < numberOfMeals; i++) {
            order[i] = createMeal(numberOfMeals - i);
        }

        return order;
    }

    public static int mealCost(Food[] meal) {
        int cost = 0;
        int mealLength = meal.length;

        for (int i = 0; i < mealLength; i++) {
            Food item = meal[i];
            cost += i * item.ordinal();
        }

        return cost;
    }

    public static int orderCost(Food[][] order) {
        int cost = 0;
        int numberOfMeals = order.length;

        for (int i = 0; i < numberOfMeals; i++) {
            Food[] meal = order[i];
            cost += mealCost(meal);
        }

        return cost;
    }
}
