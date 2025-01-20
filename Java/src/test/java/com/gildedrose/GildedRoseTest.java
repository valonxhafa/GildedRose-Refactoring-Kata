package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    Item[] currentValues = TestData.currentValues;
    Item[] futureValues = TestData.nextDayValues; //day + 1

    @Test
    void normalItemDecreasesInQualityAndSellIn() {
        Item[] items = new Item[] { new Item("Normal Item", 5, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(9, items[0].quality);
    }

    @Test
    void compareTodayValuesWithTomorrow() {
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
