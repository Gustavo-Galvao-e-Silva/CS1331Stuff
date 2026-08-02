import java.util.ArrayList;

/**
 * Represents Olivia, a customer who manages a shopping cart for renting media
 * items.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class Olivia {
    private static double budget = 200.0;
    private static ArrayList<Media> cart = new ArrayList<Media>();
    private static boolean canUseConsole = true;

    /**
     * Attempts to add the desired media item to Olivia's cart from the given
     * Blockbuster store.
     * The item is only added if Olivia has sufficient budget and the media is
     * playable.
     *
     * @param desiredMedia Media representing the media item Olivia wants to add to
     *                     her cart
     * @param blockbuster  Blockbuster representing the store to retrieve the media
     *                     from
     * @return boolean representing whether the media was successfully added to the
     *         cart
     */
    public static boolean addToCart(Media desiredMedia, Blockbuster blockbuster) {
        if (desiredMedia.getRentalPrice() > budget || !(mediaIsPlayable(desiredMedia))) {
            return false;
        }
        Media storeMedia = blockbuster.findMedia(desiredMedia);
        if (storeMedia == null) {
            return false;
        }
        blockbuster.removeMedia(storeMedia);
        budget -= storeMedia.getRentalPrice();
        cart.add(storeMedia);
        return true;
    }

    /**
     * Returns a media item from Olivia's cart back to the given Blockbuster store.
     *
     * @param changedMedia Media representing the media item to return to the store
     * @param blockbuster  Blockbuster representing the store to return the media to
     */
    public static void changeMind(Media changedMedia, Blockbuster blockbuster) {
        for (int i = 0; i < cart.size(); i++) {
            Media media = cart.get(i);
            if (media.equals(changedMedia)) {
                cart.remove(i);
                blockbuster.addMedia(media);
                budget += media.getRentalPrice();
                break;
            }
        }
    }

    private static boolean mediaIsPlayable(Media media) {
        if (media instanceof VideoGame) {
            VideoGame game = (VideoGame) media;
            boolean needConsole = game.getNeedsConsole();
            return needConsole ? canUseConsole : true;
        }
        return true;
    }
}
