package com.gildedrose.items;

import com.gildedrose.Item;

public class AgedBrieItem extends AbstractItem  {

    public AgedBrieItem(Item item) {
        super(item);
    }

    @Override
    public void update() {

        //AgedBrie increases in quality over time!
        increaseQuality();
        item.sellIn--;

        // Once the sell by date has passed, quality will also increase twice as fast
        if (item.sellIn < 0) {
            increaseQuality();
        }
    }
}
