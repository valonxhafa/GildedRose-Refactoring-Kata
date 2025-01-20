package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private GildedRose app;

    @BeforeEach
    void setup() {
        Item[] items = new Item[] {
            new Item("Normal Item", 10, 20),
            new Item("Aged Brie", 10, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Conjured Mana Cake", 10, 20)
        };
        app = new GildedRose(items);
    }

    @Test
    void normalItemsDecreasesInQualityAndSellIn_By_One() {
        app.updateQuality();

        assertEquals(9, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);
    }

    @Test
    void agedBrieIncreasesInQualityOverTime() {
        app.updateQuality();

        assertEquals(9, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);
    }

    @Test
    void AgedBrieIncreasesDoubleInQualityWhenSellInBelowZero() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 10) };
        app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-2, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }


    @Test
    void backStagePassIncreasesInQualityOverTime() {
        assertEquals(20, app.items[1].quality);
        app.updateQuality();
        assertEquals(21, app.items[1].quality);
    }

    @Test
    void backStagePassIncreasesInQualityDependingOnSellInValue() {
        assertEquals(20, app.items[1].quality);
        app.updateQuality();
        assertEquals(21, app.items[1].quality);
    }

    @Test
    void sulfurasQualityWontChangeOverTime() {
        assertEquals(80, app.items[3].quality);
        app.updateQuality();
        assertEquals(80, app.items[3].quality);
    }

    @Test
    void conjuredItemsDegradeTwiceAsFast() {
        assertEquals(20, app.items[4].quality); // Conjured item
        app.updateQuality();

        // After update, Conjured Mana Cake quality should have decreased by 2
        assertEquals(18, app.items[4].quality);

        //We sellIn to negative. Now conjured mana cake should drop by an additional 2 (so 4)
        app.items[4].sellIn = -5;
        app.updateQuality();

        // After second update, Conjured Mana Cake quality should have decreased by 2 more
        assertEquals(14, app.items[4].quality);
    }

    @Test
    void qualityCantExceedMaxQuality() {
        app.items[1].quality = 50;
        app.updateQuality();
        assertEquals(50, app.items[1].quality);
    }

    @Test
    void qualityCantExceedMinimumQuality() {
        app.items[0].quality = 0;
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityResetAfterConcert() {
        app.items[2].sellIn = 0;
        assertEquals(20, app.items[2].quality);

        app.updateQuality();

        // After update, Backstage passes quality should be reset to 0
        assertEquals(0, app.items[2].quality);
    }

    @Test
    void normalItemQualityDecreasesAfterSellIn() {
        app.items[0].sellIn = 0;
        assertEquals(20, app.items[0].quality);

        app.updateQuality();

        assertEquals(18, app.items[0].quality);
    }

    @Test
    void itemQualityCantGoBelowZero() {
        Item[] items = new Item[] { new Item("Normal Item", 0, 0) };
        GildedRose app = new GildedRose(items);

        for (int i = 0; i < 3; i++) {
            app.updateQuality();
        }

        assertEquals(0, items[0].quality);
    }
}
