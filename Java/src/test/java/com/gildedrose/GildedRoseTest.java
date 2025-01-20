package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    Item[] currentValues = TestData.currentValues;
    Item[] futureValues = TestData.nextDayValues; //day + 1

    @Test
    void normalItemsDecreasesInQualityAndSellIn_By_One() {
        Item[] items = new Item[] { new Item("Normal Item", 5, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(9, items[0].quality);
    }

    @Test
    void AgedBrieIncreasesInQualityOverTime_By_One() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(11, items[0].quality);
    }

    @Test
    void AgedBrieIncreasesDoubleInQualityWhenSellInBelowZero() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-2, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    void ItemQualityCantGoBelowZero() {
        Item[] items = new Item[] { new Item("Normal Item", 0, 0) };
        GildedRose app = new GildedRose(items);

        for (int i = 0; i < 3; i++) {
            app.updateQuality();
        }

        assertEquals(0, items[0].quality);
    }

    @Test
    void compareAllTodayValuesWithTomorrow() {
        GildedRose app = new GildedRose(currentValues);

        app.updateQuality();

        for (int i = 0; i < currentValues.length; i++) {
            Item currentItem = currentValues[i];
            Item futureItem = futureValues[i];

            // Compare the current item and the future item
            assertEquals(futureItem.name, currentItem.name);
            assertEquals(futureItem.quality, currentItem.quality);
            assertEquals(futureItem.sellIn, currentItem.sellIn);

            itemPrinter(currentItem);
            itemPrinter(futureItem);
        }
    }

    static void itemPrinter(Item item) {
        System.out.println(item.name + ", sellIn: " + item.sellIn + ", quality: " + item.quality);
    }

}
