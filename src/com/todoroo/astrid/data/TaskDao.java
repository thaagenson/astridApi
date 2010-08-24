package com.todoroo.astrid.data;

import android.content.Context;

import com.todoroo.andlib.ContentResolverDao;
import com.todoroo.andlib.Criterion;
import com.todoroo.andlib.Functions;

public class TaskDao extends ContentResolverDao<Task> {

    public TaskDao(Context context) {
        super(Task.class, context, Task.CONTENT_URI);
    }

    /**
     * Generates SQL clauses
     */
    public static class TaskCriteria {

        /** @returns tasks by id */
        public static Criterion byId(long id) {
            return Task.ID.eq(id);
        }

        /** @return tasks that were deleted */
        public static Criterion isDeleted() {
            return Task.DELETION_DATE.neq(0);
        }

        /** @return tasks that were not deleted */
        public static Criterion notDeleted() {
            return Task.DELETION_DATE.eq(0);
        }

        /** @return tasks that have not yet been completed or deleted */
        public static Criterion activeAndVisible() {
            return Criterion.and(Task.COMPLETION_DATE.eq(0),
                    Task.DELETION_DATE.eq(0),
                    Task.HIDE_UNTIL.lt(Functions.now()));
        }

        /** @return tasks that have not yet been completed or deleted */
        public static Criterion isActive() {
            return Criterion.and(Task.COMPLETION_DATE.eq(0),
                    Task.DELETION_DATE.eq(0));
        }

        /** @return tasks that are not hidden at current time */
        public static Criterion isVisible() {
            return Task.HIDE_UNTIL.lt(Functions.now());
        }

        /** @return tasks that have a due date */
        public static Criterion hasDeadlines() {
            return Task.DUE_DATE.neq(0);
        }

        /** @return tasks that are due before a certain unixtime */
        public static Criterion dueBeforeNow() {
            return Criterion.and(Task.DUE_DATE.gt(0), Task.DUE_DATE.lt(Functions.now()));
        }

        /** @return tasks that are due after a certain unixtime */
        public static Criterion dueAfterNow() {
            return Task.DUE_DATE.gt(Functions.now());
        }

        /** @return tasks completed before a given unixtime */
        public static Criterion completed() {
            return Criterion.and(Task.COMPLETION_DATE.gt(0), Task.COMPLETION_DATE.lt(Functions.now()));
        }

        /** @return tasks that have a blank or null title */
        @SuppressWarnings("nls")
        public static Criterion hasNoTitle() {
            return Criterion.or(Task.TITLE.isNull(), Task.TITLE.eq(""));
        }

    }

}
