package com.todoroo.astrid.data;

import android.content.Context;

import com.todoroo.andlib.ContentResolverDao;
import com.todoroo.andlib.Criterion;

public class StoreObjectDao extends ContentResolverDao<StoreObject> {

    public StoreObjectDao(Context context) {
        super(StoreObject.class, context, StoreObject.CONTENT_URI);
    }

    // --- SQL clause generators

    /**
     * Generates SQL clauses
     */
    public static class StoreObjectCriteria {

        /** Returns all store objects with given type */
        public static Criterion byType(String type) {
            return StoreObject.TYPE.eq(type);
        }

        /** Returns store object with type and key */
        public static Criterion byTypeAndItem(String type, String item) {
            return Criterion.and(byType(type), StoreObject.ITEM.eq(item));
        }

    }

}
