package com.todoroo.andlib;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtilities {

    /**
     * Displays a dialog box with an OK button
     *
     * @param activity
     * @param text
     * @param okListener
     */
    public static void okDialog(final Activity activity,
            final CharSequence text, final DialogInterface.OnClickListener okListener) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(activity)
                .setMessage(text)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.ok, okListener)
                .show().setOwnerActivity(activity);
            }
        });
    }

    /**
     * Displays a dialog box with an OK button
     *
     * @param activity
     * @param text
     * @param okListener
     */
    public static void okDialog(final Activity activity, final int icon,
            final CharSequence title, final CharSequence text, final DialogInterface.OnClickListener okListener) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(text)
                .setIcon(icon)
                .setPositiveButton(android.R.string.ok, okListener)
                .show().setOwnerActivity(activity);
            }
        });
    }

    /**
     * Displays a dialog box with OK and Cancel buttons and custom title
     *
     * @param activity
     * @param title
     * @param text
     * @param okListener
     * @param cancelListener
     */
    public static void okCancelDialog(final Activity activity, final CharSequence title,
            final CharSequence text, final DialogInterface.OnClickListener okListener,
            final DialogInterface.OnClickListener cancelListener) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(text)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.ok, okListener)
                .setNegativeButton(android.R.string.cancel, cancelListener)
                .show().setOwnerActivity(activity);
            }
        });
    }

    /**
     * Displays a dialog box with OK and Cancel buttons
     *
     * @param activity
     * @param text
     * @param okListener
     * @param cancelListener
     */
    public static void okCancelDialog(final Activity activity, final CharSequence text,
            final DialogInterface.OnClickListener okListener,
            final DialogInterface.OnClickListener cancelListener) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(activity)
                .setMessage(text)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.ok, okListener)
                .setNegativeButton(android.R.string.cancel, cancelListener)
                .show().setOwnerActivity(activity);
            }
        });
    }

    /**
     * Displays a progress dialog. Must be run on the UI thread
     * @param context
     * @param text
     * @return
     */
    public static ProgressDialog progressDialog(Context context, String text) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(text);
        dialog.show();
        dialog.setOwnerActivity((Activity)context);
        return dialog;
    }

    /**
     * Dismiss a dialog off the UI thread
     *
     * @param activity
     * @param dialog
     */
    public static void dismissDialog(Activity activity, final ProgressDialog dialog) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    // could have killed activity
                }
            }
        });
    }
}
