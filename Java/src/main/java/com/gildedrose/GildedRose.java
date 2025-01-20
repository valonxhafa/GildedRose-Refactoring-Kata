package com.gildedrose;

class GildedRose {
    Item[] items;

    private final static int MIN_QUALITY = 0;
    private final static int MAX_QUALITY = 50;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!isAgedBrie(item) && !isBackStagePass(item)) {
                if (item.quality > MIN_QUALITY) {
                    if (!isSulfuras(item)) {
                        item.quality--;
                    }
                }
            } else {
                if (item.quality < MAX_QUALITY) {
                    item.quality++;

                    if (isBackStagePass(item)) {
                        if (item.sellIn < 11) {
                            if (item.quality < MAX_QUALITY) {
                                item.quality++;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < MAX_QUALITY) {
                                item.quality++;
                            }
                        }
                    }
                }
            }

            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!isAgedBrie(item)) {
                    if (!isBackStagePass(item)) {
                        if (item.quality > MIN_QUALITY) {
                            if (!isSulfuras(item)) {
                                item.quality--;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < MAX_QUALITY) {
                        item.quality++;
                    }
                }
            }
        }
    }

    private boolean isAgedBrie(Item item) {return "Aged Brie".equals(item.name);}
    private boolean isSulfuras(Item item) {return "Sulfuras, Hand of Ragnaros".equals(item.name);}
    private boolean isBackStagePass(Item item) {return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);}
}
