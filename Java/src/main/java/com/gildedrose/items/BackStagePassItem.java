package com.gildedrose.items;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;

public class BackStagePassItem extends AbstractItem {

    public BackStagePassItem(Item item) {
        super(item);
    }

    @Override
    public void update() {

        if (item.quality < GildedRose.MAX_QUALITY) {
            increaseQuality();
            if (item.sellIn < 11) {
                increaseQuality();
            }
            if (item.sellIn < 6) {
                increaseQuality();
            }
        }

        item.sellIn--;

        //Quality drops to 0 after the concert
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
