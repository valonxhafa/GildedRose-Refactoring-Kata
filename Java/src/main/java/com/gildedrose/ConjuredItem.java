package com.gildedrose;

public class ConjuredItem extends AbstractItem {

    public ConjuredItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        // Decrease quality twice as fast as normal items
        if (item.quality > GildedRose.MIN_QUALITY) {
            item.quality -= 2;
        }

        // Decrease the sellIn value for the item
        item.sellIn--;

        // If sellIn is below 0, quality decreases by an additional 2
        if (item.sellIn < 0 && item.quality > GildedRose.MIN_QUALITY) {
            item.quality -= 2;
        }
    }
}
