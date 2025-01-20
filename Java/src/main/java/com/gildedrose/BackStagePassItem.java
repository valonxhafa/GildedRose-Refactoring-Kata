package com.gildedrose;

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
    }
}
