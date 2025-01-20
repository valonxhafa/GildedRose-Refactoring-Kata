package com.gildedrose;

class GildedRose {
    Item[] items;

    public final static int MIN_QUALITY = 0;
    public final static int MAX_QUALITY = 50;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isNormalItem(item)) {
                if (item.quality > MIN_QUALITY) {
                    decreaseQuality(item);
                }

            } else { // for isAgedBrie && isBackStagePass
                if (item.quality < MAX_QUALITY) {
                    item.quality++;

                    if (isBackStagePass(item)) {
                        if (item.sellIn < 11) {
                            increaseQuality(item);
                        }

                        if (item.sellIn < 6) {
                            increaseQuality(item);
                        }
                    }
                }
            }

            if (!isSulfuras(item)) {
                decreaseSellIn(item);
            }

            if (item.sellIn < MIN_QUALITY) {
                if (!isAgedBrie(item)) {
                    if (!isBackStagePass(item)) {
                        if (item.quality > MIN_QUALITY) {
                            if (!isSulfuras(item)) {
                                item.quality--;
                            }
                        }
                    } else {
                        item.quality = MIN_QUALITY;
                    }
                } else {
                    increaseQuality(item);
                }
            }
            ensureQualityThresholds(item);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality--;
        }
    }

    private void decreaseSellIn(Item item) {
            item.sellIn--;
    }

    private void ensureQualityThresholds(Item item) {
        if (!isSulfuras(item)) {
            if (item.quality > MAX_QUALITY) {
                item.quality = MAX_QUALITY;
            }
            if (item.quality < MIN_QUALITY) {
                item.quality = MIN_QUALITY;
            }
        }
    }

    private boolean isAgedBrie(Item item) {return "Aged Brie".equals(item.name);}
    private boolean isBackStagePass(Item item) {return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);}
    private boolean isSulfuras(Item item) {return "Sulfuras, Hand of Ragnaros".equals(item.name);}
    private boolean isNormalItem(Item item) {return !isAgedBrie(item) && !isBackStagePass(item) && !isSulfuras(item);}
}
