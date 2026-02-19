public class Driver {

    public static void main(String[] args) {
        Food[] meal1 = TechDining.createMeal(6);
        Food[] meal2 = TechDining.createMeal(7);

        int costMeal1 = TechDining.mealCost(meal1);
        int costMeal2 = TechDining.mealCost(meal2);

        Food[][] order1 = TechDining.createOrder(6);
        Food[][] order2 = TechDining.createOrder(7);

        int costOrder1 = TechDining.orderCost(order1);
        int costOrder2 = TechDining.orderCost(order2);

        System.out.printf("Meal costs: 1 -> %d; 2 -> %d%n", costMeal1, costMeal2);
        System.out.printf("Order costs: 1 -> %d; 2 -> %d%n", costOrder1, costOrder2);
    }
}
