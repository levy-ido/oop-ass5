package game.listener;

/**
 * Objects implementing this interface notify listeners when hit.
 */
public interface HitNotifier {
    /**
     * Adds the given hit listener to this hit notifiers' notifying list.
     * @param hl A HitListener to add to this hit notifiers' notifying list
     */
    void addHitListener(HitListener hl);

    /**
     * Removes the given hit listener from this hit notifiers' notifying list.
     * @param hl A HitListener to remove from this hit notifiers' notifying list
     */
    void removeHitListener(HitListener hl);
}
