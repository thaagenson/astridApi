package com.todoroo.andlib.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.todoroo.andlib.sql.Criterion;
import com.todoroo.andlib.sql.Query;


/**
 * DAO for reading and writing values from an Android ContentResolver
 *
 * @author Tim Su <tim@todoroo.com>
 *
 * @param <TYPE> model type
 */
public class ContentResolverDao<TYPE extends AbstractModel> {

    /** class of model */
    @SuppressWarnings("unused")
    private final Class<TYPE> modelClass;

    /** base content uri */
    private final Uri baseUri;

    /** content resolver */
    private final ContentResolver cr;

    public ContentResolverDao(Class<TYPE> modelClass, Context context, Uri baseUri) {
        this.modelClass = modelClass;
        this.baseUri = baseUri;

        cr = context.getContentResolver();
    }

    /**
     * Returns a URI for a single id
     * @param id
     * @return
     */
    private Uri uriWithId(long id) {
        return Uri.withAppendedPath(baseUri, Long.toString(id));
    }

    /**
     * Delete specific item from the given table
     * @param id
     * @return number of rows affected
     */
    public int delete(long id) {
        return cr.delete(uriWithId(id), null, null);
    }

    /**
     * Delete by criteria
     * @param where
     * @return number of rows affected
     */
    public int deleteWhere(Criterion where) {
        return cr.delete(baseUri, where.toString(), null);
    }

    /**
     * Query content provider
     * @param query
     * @return
     */
    public TodorooCursor<TYPE> query(Query query) {
        Cursor cursor = query.queryContentResolver(cr, baseUri);
        return new TodorooCursor<TYPE>(cursor, query.getFields());
    }

    /**
     * Create new or save existing model
     * @param model
     * @return true if data was written to the db, false otherwise
     */
    public boolean save(TYPE model) {
        if(model.isSaved()) {
            if(model.getSetValues() == null)
                return false;
            if(cr.update(uriWithId(model.getId()), model.getSetValues(), null, null) != 0)
                return true;
        }
        Uri uri = cr.insert(baseUri, model.getMergedValues());
        long id = Long.parseLong(uri.getLastPathSegment());
        model.setId(id);
        model.markSaved();
        return true;
    }

}
