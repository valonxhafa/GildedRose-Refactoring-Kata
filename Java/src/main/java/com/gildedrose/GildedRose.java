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
            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > MIN_QUALITY) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.quality--;
                    }
                }
            } else {
                if (item.quality < MAX_QUALITY) {
                    item.quality++;

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
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

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > MIN_QUALITY) {
                            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                                item.quality--;
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < MAX_QUALITY) {
                        item.quality++;
                    }
                }
            }
        }
    }
}
