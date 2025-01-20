package com.gildedrose;

public class NormalItem extends AbstractItem{

    public NormalItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        decreaseQuality();
        item.sellIn--;

        // Once the sell by date has passed, quality degrades twice as fast for normal items
        if (item.sellIn < 0) {
            decreaseQuality();
        }

    }
}
