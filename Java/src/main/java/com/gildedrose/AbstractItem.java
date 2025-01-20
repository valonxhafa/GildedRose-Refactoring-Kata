package com.gildedrose;

 abstract class AbstractItem {
    protected Item item;

    public AbstractItem(Item item) {
        this.item = item;
    }

     protected void increaseQuality() {
         if (item.quality < GildedRose.MAX_QUALITY) {
             item.quality++;
         }
     }

     protected void decreaseQuality() {
         if (item.quality > GildedRose.MIN_QUALITY) {
             item.quality--;
         }
     }

    protected abstract void update();
}
