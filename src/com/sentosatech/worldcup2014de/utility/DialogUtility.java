/*
 * Name: DialogUtility.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

import com.sentosatech.worldcup2014de.R;

public final class DialogUtility {
	/**
	 * Show an alert dialog box
	 * 
	 * @param context
	 * @param message
	 */

	// private BaseActivity context;

	public static void alert(Context context, String message) {
		// if (context != null) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle(context.getString(R.string.app_name));
		alertDialog.setMessage(message);
		alertDialog.setPositiveButton(R.string.button_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
		alertDialog.show();
	}

	public static void toast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	public static void toast(Context context, int message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	// }

	/**
	 * Show an alert dialog box
	 * 
	 * @param context
	 * @param messageId
	 */
	public static void alert(Context context, int messageId) {
		// if (context != null) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle(context.getString(R.string.app_name));
		alertDialog.setMessage(context.getString(messageId));
		alertDialog.setPositiveButton(R.string.button_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
		alertDialog.show();
		// }
	}

	public static void showOkDialog(Context context, int messageId,
			int OkTextId, final DialogInterface.OnClickListener onOKClick) {
		if (context != null) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
			alertDialog.setTitle(context.getString(R.string.app_name));
			alertDialog.setMessage(context.getString(messageId));

			alertDialog.setPositiveButton(OkTextId, onOKClick);
			alertDialog.show();
		}
	}

	public static void showYesNoDialog(final Context context, int messageId,
			int OkTextId, int cancelTextId,
			final DialogInterface.OnClickListener onOKClick) {
		if (context != null) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
			alertDialog.setTitle(context.getString(R.string.app_name));
			alertDialog.setMessage(context.getString(messageId));
			alertDialog.setNegativeButton(cancelTextId,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();

						}
					});
			alertDialog.setPositiveButton(OkTextId, onOKClick);
			alertDialog.show();
		}
	}

	public static void showYesNoDialog(final Context context, int messageId,
			int OkTextId, int cancelTextId,
			final DialogInterface.OnClickListener onOKClick,
			final DialogInterface.OnClickListener onCancelClick) {
		if (context != null) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
			alertDialog.setTitle(context.getString(R.string.app_name));
			alertDialog.setMessage(context.getString(messageId));
			alertDialog.setNegativeButton(cancelTextId, onCancelClick);
			alertDialog.setPositiveButton(OkTextId, onOKClick);
			alertDialog.show();
		}
	}

	private static String temp = "";

	public static void showInputDialog(Context c, int titleId, int hintId,
			int okTextId, int cancelTextId, int iconId, String returnValue) {

		AlertDialog.Builder alert = new AlertDialog.Builder(c);
		final EditText input = new EditText(c);
		alert.setView(input);

		alert.setTitle(c.getString(titleId));
		alert.setIcon(iconId);
		alert.setPositiveButton(c.getString(okTextId),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						temp = input.getText().toString().trim();
						SmartLog.log("DialogUtility", "Value entered" + temp);
						if (temp != "")
							dialog.cancel();
					}
				});

		alert.setNegativeButton(c.getString(cancelTextId),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();

					}
				});
		returnValue = temp;
		alert.show();

	}

	public static void showSimpleOptionDialog(Context mContext, int titleId,
			String[] items, String positiverButton,
			DialogInterface.OnClickListener itemOnClick,
			DialogInterface.OnClickListener positiveOnClick) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(mContext.getString(titleId));
		builder.setItems(items, itemOnClick);
		builder.setPositiveButton(positiverButton, positiveOnClick);
		AlertDialog alert = builder.create();
		alert.show();
	}

	public static void alert(Context context, String message,
			DialogInterface.OnClickListener onOkClick) {
		if (context != null) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
			alertDialog.setTitle(R.string.app_name);
			alertDialog.setMessage(message);
			alertDialog.setPositiveButton(R.string.button_ok, onOkClick);
			alertDialog.show();
		}
	}

}
