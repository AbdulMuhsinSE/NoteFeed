package me.abdulmuhsinse.notefeed.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AbdulMuhsin on 3/29/2015.
 */
public class DummyContent2 {
/**
 * An array of sample (dummy) items.
 */
public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

/**
 * A map of sample (dummy) items, by ID.
 */
public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Test 1 SP2014 - Sample : PR432",6));
        addItem(new DummyItem("2", "Homework 11 : BM250 - Dr. Puches",80));
        addItem(new DummyItem("3", "Syllabus : SE311 - Dr. Wang",-12));
        }

private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        }

/**
 * A dummy item representing a piece of content.
 */
public static class DummyItem {
    public String id;
    public String content;
    public int rating;

    public DummyItem(String id, String content,int x) {
        this.id = id;
        this.content = content;
        this.rating = x;
    }

    public void upvote() {
        this.rating++;
    }

    public void downvote() {
        this.rating--;
    }

    @Override
    public String toString() {
        return content;
    }
}
}
