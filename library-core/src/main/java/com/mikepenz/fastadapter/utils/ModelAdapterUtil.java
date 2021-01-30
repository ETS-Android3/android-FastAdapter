package com.mikepenz.fastadapter.utils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ModelAdapter;

import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;

public abstract class ModelAdapterUtil {

    public static <Item extends IItem> void compareAdapter(@Nullable ModelAdapter<Item, Item> adapter, @Nullable Comparator<Item> comparator) {
        if (adapter == null || adapter.getItemList() == null || !(adapter.getItemList() instanceof ComparableItemListImpl)) {
            return;
        }

        ///注意：withComparator(getComparator(), true, false)的第三个参数！为了不重复进行FastAdapter().notifyAdapterDataSetChanged()
        if (adapter.getItemFilter() == null || adapter.getItemFilter().getOriginalItems() == null) {
            ((ComparableItemListImpl<Item>) adapter.getItemList()).withComparator(comparator, true, true);
        } else {
            ((ComparableItemListImpl<Item>) adapter.getItemList()).withComparator(comparator, false, false);
            adapter.getItemFilter().sortOriginalItems();
            adapter.getItemFilter().publishResults();
        }
    }

    public static <Item extends IItem> List<Item> getAdapterOriginalItems(@Nullable ModelAdapter<Item, Item> adapter) {
        if (adapter == null) {
            return null;
        }

        if (adapter.getItemFilter() == null || adapter.getItemFilter().getOriginalItems() == null) {
            return adapter.getAdapterItems();
        } else {
            return adapter.getItemFilter().getOriginalItems();
        }
    }

    public static <Item extends IItem> void add(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, Item... items) {
        if (adapter == null || items.length == 0) {
            return;
        }

        add(isPublishResults, adapter, asList(items));
    }
    public static <Item extends IItem> void add(@Nullable ModelAdapter<Item, Item> adapter, Item... items) {
        if (adapter == null || items.length == 0) {
            return;
        }

        add(true, adapter, asList(items));
    }

    public static <Item extends IItem> void add(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, @Nullable List<Item> items) {
        if (adapter == null || items == null || items.size() == 0) {
            return;
        }

        ///[FastAdapter#Filter]用mItemAdapter.getItemFilter()的add/remove方法替代mItemAdapter的方法
        if (adapter.getItemFilter() == null) {
            adapter.add(items);
        } else {
            ///注意：FastAdapter必须升级为v0.10.7#[FIX#ItemFilter#Sort]以上！否则mOriginalItems无法排序
            adapter.getItemFilter().add(isPublishResults, items);
        }
    }
    public static <Item extends IItem> void add(@Nullable ModelAdapter<Item, Item> adapter, @Nullable List<Item> items) {
        add(true, adapter, items);
    }

    public static <Item extends IItem> void add(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int position, Item... items) {
        if (adapter == null || position == RecyclerView.NO_POSITION || items.length == 0) {
            return;
        }

        add(isPublishResults, adapter, position, asList(items));
    }
    public static <Item extends IItem> void add(@Nullable ModelAdapter<Item, Item> adapter, int position, Item... items) {
        if (adapter == null || position == RecyclerView.NO_POSITION || items.length == 0) {
            return;
        }

        add(true, adapter, position, asList(items));
    }

    public static <Item extends IItem> void add(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int position, @Nullable List<Item> items) {
        if (adapter == null || position == RecyclerView.NO_POSITION || items == null || items.size() == 0) {
            return;
        }

        ///[FastAdapter#Filter]用mItemAdapter.getItemFilter()的add/remove方法替代mItemAdapter的方法
        if (adapter.getItemFilter() == null) {
            adapter.add(position, items);
        } else {
            ///注意：FastAdapter必须升级为v0.10.7#[FIX#ItemFilter#Sort]以上！否则mOriginalItems无法排序
            adapter.getItemFilter().add(isPublishResults, position, items);
        }
    }
    public static <Item extends IItem> void add(@Nullable ModelAdapter<Item, Item> adapter, int position, @Nullable List<Item> items) {
        add(true, adapter, position, items);
    }

    @SafeVarargs
    public static <Item extends IItem> void addInAdapter(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int index, Item... items) {
        if (adapter == null || items.length == 0) {
            return;
        }

        addInAdapter(isPublishResults, adapter, index, asList(items));
    }
    @SafeVarargs
    public static <Item extends IItem> void addInAdapter(@Nullable ModelAdapter<Item, Item> adapter, int index, Item... items) {
        if (adapter == null || items.length == 0) {
            return;
        }

        addInAdapter(true, adapter, index, asList(items));
    }

    public static <Item extends IItem> void addInAdapter(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int index, @Nullable List<Item> items) {
        if (adapter == null || items == null || items.size() == 0) {
            return;
        }

        ///[FastAdapter#Filter]用mItemAdapter.getItemFilter()的add/remove方法替代mItemAdapter的方法
        if (adapter.getItemFilter() == null) {
            adapter.addInAdapter(index, items);
        } else {
            ///注意：FastAdapter必须升级为v0.10.7#[FIX#ItemFilter#Sort]以上！否则mOriginalItems无法排序
            adapter.getItemFilter().addInAdapter(isPublishResults, index, items);
        }
    }
    public static <Item extends IItem> void addInAdapter(@Nullable ModelAdapter<Item, Item> adapter, int index, @Nullable List<Item> items) {
        addInAdapter(true, adapter, index, items);
    }

    public static <Item extends IItem> void set(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int position, @Nullable Item item) {
        if (adapter == null || position == RecyclerView.NO_POSITION) {
            return;
        }

        if (item == null) {
            remove(adapter, position);
            return;
        }

        ///[FastAdapter#Filter]用mItemAdapter.getItemFilter()的add/remove方法替代mItemAdapter的方法
        if (adapter.getItemFilter() == null) {
            adapter.set(position, item);
        } else {
            ///注意：FastAdapter必须升级为v0.10.7#[FIX#ItemFilter#Sort]以上！否则mOriginalItems无法排序
            adapter.getItemFilter().set(isPublishResults, position, item);
        }
    }
    public static <Item extends IItem> void set(@Nullable ModelAdapter<Item, Item> adapter, int position, @Nullable Item item) {
        set(true, adapter, position, item);
    }

    public static <Item extends IItem> void setInAdapter(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int index, @Nullable Item item) {
        if (adapter == null) {
            return;
        }

        if (item == null) {
            remove(isPublishResults, adapter, index);
            return;
        }

        ///[FastAdapter#Filter]用mItemAdapter.getItemFilter()的add/remove方法替代mItemAdapter的方法
        if (adapter.getItemFilter() == null) {
            adapter.setInAdapter(index, item);
        } else {
            ///注意：FastAdapter必须升级为v0.10.7#[FIX#ItemFilter#Sort]以上！否则mOriginalItems无法排序
            adapter.getItemFilter().setInAdapter(isPublishResults, index, item);
        }
    }
    public static <Item extends IItem> void setInAdapter(@Nullable ModelAdapter<Item, Item> adapter, int index, @Nullable Item item) {
        setInAdapter(true, adapter, index, item);
    }

    public static <Item extends IItem> void move(@Nullable ModelAdapter<Item, Item> adapter, int fromPosition, int toPosition) {
        if (adapter == null
                || fromPosition == RecyclerView.NO_POSITION
                || toPosition == RecyclerView.NO_POSITION
                || fromPosition == toPosition) {
            return;
        }

        ///[FastAdapter#Filter]用mItemAdapter.getItemFilter()的add/remove方法替代mItemAdapter的方法
        if (adapter.getItemFilter() == null) {
            adapter.move(fromPosition, toPosition);
        } else {
            ///注意：FastAdapter必须升级为v0.10.7#[FIX#ItemFilter#Sort]以上！否则mOriginalItems无法排序
            adapter.getItemFilter().move(fromPosition, toPosition);
        }
    }

    public static <Item extends IItem> void clear(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter) {
        if (adapter == null) {
            return;
        }

        if (adapter.getItemFilter() == null) {
            adapter.clear();
        } else {
            adapter.getItemFilter().clear(isPublishResults);
        }
    }
    public static <Item extends IItem> void clear(@Nullable ModelAdapter<Item, Item> adapter) {
        clear(true, adapter);
    }

    public static <Item extends IItem> void remove(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int position) {
        if (adapter == null || position == RecyclerView.NO_POSITION) {
            return;
        }

        if (adapter.getItemFilter() == null) {
            adapter.remove(position);
        } else {
            adapter.getItemFilter().remove(isPublishResults, position);
        }
    }
    public static <Item extends IItem> void remove(@Nullable ModelAdapter<Item, Item> adapter, int position) {
        remove(true, adapter, position);
    }

    public static <Item extends IItem> void removeInAdapter(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int index) {
        if (adapter == null) {
            return;
        }

        if (adapter.getItemFilter() == null) {
            adapter.removeInAdapter(index);
        } else {
            adapter.getItemFilter().removeInAdapter(isPublishResults, index);
        }
    }
    public static <Item extends IItem> void removeInAdapter(@Nullable ModelAdapter<Item, Item> adapter, int index) {
        removeInAdapter(true, adapter, index);
    }

    public static <Item extends IItem> void remove(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, Item item) {
        if (adapter == null) {
            return;
        }

        if (adapter.getItemFilter() == null) {
            adapter.remove(item);
        } else {
            adapter.getItemFilter().remove(isPublishResults, item);
        }
    }
    public static <Item extends IItem> void remove(@Nullable ModelAdapter<Item, Item> adapter, Item item) {
        remove(true, adapter, item);
    }

    public static <Item extends IItem> void removeByIdentifier(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, final long identifier) {
        if (adapter == null || identifier == -1L) {
            return;
        }

        if (adapter.getItemFilter() == null) {
            adapter.removeByIdentifier(identifier);
        } else {
            adapter.getItemFilter().removeByIdentifier(isPublishResults, identifier);
        }
    }
    public static <Item extends IItem> void removeByIdentifier(@Nullable ModelAdapter<Item, Item> adapter, final long identifier) {
        removeByIdentifier(true, adapter, identifier);
    }

    public static <Item extends IItem> void removeRange(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int position, int itemCount) {
        if (adapter == null || position == RecyclerView.NO_POSITION || itemCount <= 0) {
            return;
        }

        if (adapter.getItemFilter() == null) {
            adapter.removeRange(position, itemCount);
        } else {
            adapter.getItemFilter().removeRange(isPublishResults, position, itemCount);
        }
    }
    public static <Item extends IItem> void removeRange(@Nullable ModelAdapter<Item, Item> adapter, int position, int itemCount) {
        removeRange(true, adapter, position, itemCount);
    }

    public static <Item extends IItem> void removeRangeInAdapter(boolean isPublishResults, @Nullable ModelAdapter<Item, Item> adapter, int index, int itemCount) {
        if (adapter == null || itemCount <= 0) {
            return;
        }

        if (adapter.getItemFilter() == null) {
            adapter.removeRangeInAdapter(index, itemCount);
        } else {
            adapter.getItemFilter().removeRangeInAdapter(isPublishResults, index, itemCount);
        }
    }
    public static <Item extends IItem> void removeRangeInAdapter(@Nullable ModelAdapter<Item, Item> adapter, int index, int itemCount) {
        removeRangeInAdapter(true, adapter,index, itemCount);
    }

}
