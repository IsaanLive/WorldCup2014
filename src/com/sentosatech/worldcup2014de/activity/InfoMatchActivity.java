package com.sentosatech.worldcup2014de.activity;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.sentosatech.worldcup2014de.AlarmReceiver;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.activity.config.MySharedPreferences;
import com.sentosatech.worldcup2014de.activity.config.WebServiceConfig;
import com.sentosatech.worldcup2014de.adapter.EventAdapter;
import com.sentosatech.worldcup2014de.database.DatabaseUtility;
import com.sentosatech.worldcup2014de.modelmanager.ModelManager;
import com.sentosatech.worldcup2014de.modelmanager.ModelManagerListener;
import com.sentosatech.worldcup2014de.modelmanager.ParserUitility;
import com.sentosatech.worldcup2014de.object.EventInfo;
import com.sentosatech.worldcup2014de.object.Match;
import com.sentosatech.worldcup2014de.object.Stadium;
import com.sentosatech.worldcup2014de.utility.DialogUtility;
import com.sentosatech.worldcup2014de.utility.NoScrollListView;
import com.sentosatech.worldcup2014de.utility.StringUtility;

public class InfoMatchActivity extends BaseActivity {

	private TextView lblDate, lblTime, lblMatch, lblName1, lblName2,
			lblStadium, lblsvdMatch, lblGoal1, lblGoal2, txtReminder15,
			txtReminder30, txtReminderOntime, lblCapacity;

	private ImageView imgCountry1, imgCountry2, imgAlarm, imgStadium;
	private Button btnBack, btnCancelAlarm, btnRefresh;
	private Long serverUptimeSeconds;
	private MediaPlayer media;
	private LinearLayout layoutWeb;
	private boolean checkMedia = true;
	public static Match currentMath;
	private LinearLayout layoutStadium;
	private Stadium stadium;
	private MySharedPreferences checkRimender = new MySharedPreferences(
			InfoMatchActivity.this);

	private int idMatch;
	private TextView lblHeaderTitle;
	private NoScrollListView lsvEvent;
	private ArrayList<EventInfo> listEvent;
	private EventAdapter eventAdapter;
	public static Activity self;
	private Handler handler;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_info_match);
		self = this;
		handler = new Handler();
		initUi();
		initControl();
		idMatch = currentMath.getMatchId();
		this.setProgressBarVisibility(true);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		self = null;

	}

	private void initControl() {
		btnBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onBackPressed();
			}
		});
		btnCancelAlarm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				DialogUtility.showYesNoDialog(InfoMatchActivity.this,
						R.string.message_cancel_alarm, R.string.button_oui,
						R.string.button_no,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								cancelAllAlarm(currentMath);
								bindReminderStatus();
							}
						});

			}
		});

		btnRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				getListEvent();
			}
		});
		imgCountry1.setImageResource(currentMath.getImageId1());
		imgCountry2.setImageResource(currentMath.getImageId2());
		lblGoal1.setText(currentMath.getScore1() + "");
		lblGoal2.setText(currentMath.getScore2() + "");
		lblName1.setText(currentMath.getFullNameTeam1());
		lblName2.setText(currentMath.getFullNameTeam2());
		lblName1.setSelected(true);
		lblMatch.setText(currentMath.getMatchId() + "");
		lblName2.setSelected(true);

		int stadiumId = 1;
		if (!currentMath.getStadiumId().equalsIgnoreCase(""))
			stadiumId = Integer.parseInt(currentMath.getStadiumId());

		stadium = DatabaseUtility.getStadium(InfoMatchActivity.this, stadiumId);

		imgStadium.setImageResource(GlobalValue.listImageStadium.get(stadium
				.getIdStadium() - 1));
		lblStadium.setText(currentMath.getStadiumName());
		lblsvdMatch.setText(currentMath.getStadiumName());
		lblCapacity.setText(getString(R.string.capacity) + " : "
				+ stadium.getCapacity());
		lblDate.setText(StringUtility.formatDate("yyyy-MM-dd HH:mm",
				currentMath.getMatchDate(), "EEE, MMM dd"));
		lblTime.setText(StringUtility.formatDate("yyyy-MM-dd HH:mm",
				currentMath.getMatchDate(), "HH:mm"));

		bindReminderStatus();

		if (!StringUtility.isDateSoonerNow("yyyy-MM-dd HH:mm",
				currentMath.getMatchDate())) {
			layoutStadium.setVisibility(View.GONE);
			imgAlarm.setVisibility(View.GONE);
			lblHeaderTitle.setText(R.string.matchresult);

			findViewById(R.id.lblTitleAlarm).setVisibility(View.GONE);

			layoutWeb.setVisibility(View.VISIBLE);
			btnCancelAlarm.setVisibility(View.GONE);
			txtReminder15.setVisibility(View.GONE);
			txtReminder30.setVisibility(View.GONE);
			txtReminderOntime.setVisibility(View.GONE);
			btnRefresh.setVisibility(View.VISIBLE);

			// auto refresh
			handler.post(refreshContent);

		} else {
			btnRefresh.setVisibility(View.GONE);
			imgAlarm.setVisibility(View.VISIBLE);
			layoutStadium.setVisibility(View.VISIBLE);
			lblGoal1.setText("? ");
			lblGoal2.setText(" ?");
			layoutWeb.setVisibility(View.GONE);
			imgAlarm.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					showDialogReminder(StringUtility.formatDate(
							"yyyy-MM-dd HH:mm", currentMath.getMatchDate(),
							"HH:mm,MMM/dd/yyyy"), "");
				}
			});
		}

	}

	private Runnable refreshContent = new Runnable() {
		public void run() {
			if (self != null) {
				getListEvent();
				Log.d("Match", "Refresh data");
				handler.postDelayed(refreshContent,
						GlobalValue.TIME_CHECK_UPDATE_INTERVAL * 1000 * 60);
			} else {
				handler.removeCallbacks(this);
			}

		}
	};

	private void cancelAllAlarm(Match match) {

		if (checkRimender.getIntValue("check15" + match.getMatchId()) == match
				.getMatchId()) {
			checkRimender.putIntValue("check15" + match.getMatchId(), 0);
		}
		if (checkRimender.getIntValue("check30" + match.getMatchId()) == match
				.getMatchId()) {
			checkRimender.putIntValue("check30" + match.getMatchId(), 0);
		}
		if (checkRimender.getIntValue("checkOntime" + match.getMatchId()) == match
				.getMatchId()) {
			checkRimender.putIntValue("checkOntime" + match.getMatchId(), 0);
		}

	}

	private boolean bindReminderStatus() {

		boolean isHasReminder = false;
		if (checkRimender.getIntValue("check15" + currentMath.getMatchId()) == currentMath
				.getMatchId()) {
			txtReminder15.setVisibility(View.VISIBLE);
			isHasReminder = true;
		} else
			txtReminder15.setVisibility(View.GONE);

		if (checkRimender.getIntValue("check30" + currentMath.getMatchId()) == currentMath
				.getMatchId()) {
			txtReminder30.setVisibility(View.VISIBLE);
			isHasReminder = true;
		} else
			txtReminder30.setVisibility(View.GONE);

		if (checkRimender.getIntValue("checkOntime" + currentMath.getMatchId()) == currentMath
				.getMatchId()) {
			txtReminderOntime.setVisibility(View.VISIBLE);
			isHasReminder = true;
		} else
			txtReminderOntime.setVisibility(View.GONE);
		btnCancelAlarm.setVisibility(isHasReminder ? View.VISIBLE : View.GONE);
		return isHasReminder;
	}

	private void getListEvent() {

		ModelManager.getData(
				InfoMatchActivity.this,
				true,
				WebServiceConfig.URL_GET_MATCH_DETAIL
						+ currentMath.getMatchId(), new ModelManagerListener() {

					@Override
					public void onWSError() {

					}

					@Override
					public void OnSuccess(String json) {
						try {
							JSONObject obj = new JSONObject(json)
									.getJSONObject("data");
							lblGoal1.setText(ParserUitility.getStringValue(obj,
									"match_host_score"));
							lblGoal2.setText(ParserUitility.getStringValue(obj,
									"match_guest_score"));
							listEvent = ParserUitility
									.parserListEvent(new JSONObject(json));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (listEvent == null || listEvent.size() == 0)
							Toast.makeText(InfoMatchActivity.this,
									R.string.msg_cannot_get_list_event,
									Toast.LENGTH_LONG).show();
						eventAdapter = new EventAdapter(InfoMatchActivity.this,
								listEvent, currentMath);
						lsvEvent.setAdapter(eventAdapter);
					}
				});

	}

	private void initUi() {
		layoutStadium = (LinearLayout) findViewById(R.id.layoutStadium);
		imgStadium = (ImageView) findViewById(R.id.imgStadium);
		lblCapacity = (TextView) findViewById(R.id.lblCapacity);
		txtReminder15 = (TextView) findViewById(R.id.txtReminder15);
		txtReminder30 = (TextView) findViewById(R.id.txtReminder30);
		txtReminderOntime = (TextView) findViewById(R.id.txtReminderOntime);
		lblDate = (TextView) findViewById(R.id.lblDate);
		lblTime = (TextView) findViewById(R.id.lbltimeMatch);
		lblName1 = (TextView) findViewById(R.id.lblname1Match);
		lblName2 = (TextView) findViewById(R.id.lblname2Match);
		lblStadium = (TextView) findViewById(R.id.lblStadiumFull);
		lblsvdMatch = (TextView) findViewById(R.id.lblsvdMatch);
		lblGoal1 = (TextView) findViewById(R.id.lblgoal1Match);
		lblGoal2 = (TextView) findViewById(R.id.lblgoal2Match);
		lblMatch = (TextView) findViewById(R.id.lblmatch);
		layoutWeb = (LinearLayout) findViewById(R.id.layoutEvent);
		imgCountry1 = (ImageView) findViewById(R.id.imgcountry1Match);
		imgCountry2 = (ImageView) findViewById(R.id.imgcountry2Match);
		btnBack = (Button) findViewById(R.id.btnbackAbout);
		imgAlarm = (ImageView) findViewById(R.id.imgAlarm);
		imgAlarm.setVisibility(View.GONE);
		txtReminder15.setVisibility(View.GONE);
		txtReminder30.setVisibility(View.GONE);
		txtReminderOntime.setVisibility(View.GONE);
		lblHeaderTitle = (TextView) findViewById(R.id.lblHeaderTitle);
		lsvEvent = (NoScrollListView) findViewById(R.id.lsvEvent);
		btnCancelAlarm = (Button) findViewById(R.id.btnCancelAlarm);
		btnRefresh = (Button) findViewById(R.id.btnRefresh);

	}

	// ---------------- Show Dialog Rimender -----------
	@SuppressLint({ "SimpleDateFormat", "DefaultLocale" })
	private void showDialogReminder(final String times,
			final String titleReminder) {
		final Dialog dialog = new Dialog(this);
		final Vibrator vibrator;
		vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(1000 * GlobalValue.vibrateOnRimider);
		media = new MediaPlayer();
		if (!media.isPlaying()) {
			try {
				AssetFileDescriptor descriptor = getAssets()
						.openFd("alarm.mp3");
				media.setDataSource(descriptor.getFileDescriptor(),
						descriptor.getStartOffset(), descriptor.getLength());
				descriptor.close();
				media.setAudioStreamType(AudioManager.STREAM_ALARM);
				media.setLooping(true);
				media.prepare();
				media.setVolume(0, 0);
				media.start();
				checkMedia = true;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.page_dialog);
		TextView lbltimes = (TextView) dialog
				.findViewById(R.id.lblTimeReminder);
		lbltimes.setText(times);
		RadioGroup rgReminder = (RadioGroup) dialog
				.findViewById(R.id.rgReminder);
		final RadioButton rgReminder15 = (RadioButton) dialog
				.findViewById(R.id.rgReminder15);
		final RadioButton rgReminder30 = (RadioButton) dialog
				.findViewById(R.id.rgReminder30);
		final RadioButton rgReminderOnTime = (RadioButton) dialog
				.findViewById(R.id.rgReminderOnTime);
		rgReminder15.setText(R.string.radio_button_befor15mins);
		rgReminder30.setText(R.string.radio_button_befor30mins);
		rgReminderOnTime.setText(R.string.radio_button_onTime);

		if (StringUtility.isDateSoonerNowWithTime("yyyy-MM-dd HH:mm",
				currentMath.getMatchDate(), 30)) {
			rgReminder30.setVisibility(View.VISIBLE);
			rgReminder15.setVisibility(View.VISIBLE);
			rgReminderOnTime.setVisibility(View.VISIBLE);
		} else if (StringUtility.isDateSoonerNowWithTime("yyyy-MM-dd HH:mm",
				currentMath.getMatchDate(), 15)) {
			rgReminder30.setVisibility(View.GONE);
			rgReminder15.setVisibility(View.VISIBLE);
			rgReminderOnTime.setVisibility(View.VISIBLE);
		} else {
			rgReminder30.setVisibility(View.GONE);
			rgReminder15.setVisibility(View.GONE);
			rgReminderOnTime.setVisibility(View.VISIBLE);
		}

		rgReminder.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				SimpleDateFormat formattera = new SimpleDateFormat(
						"HH:mm,MMM/dd/yyyy");
				Date oldDate;
				if (rgReminder15.isChecked()) {
					if (checkRimender.getIntValue("check15"
							+ currentMath.getMatchId()) == currentMath
							.getMatchId()) {

						Toast.makeText(InfoMatchActivity.this,
								R.string.toast_message_rimender15,
								Toast.LENGTH_LONG).show();

					} else {
						try {
							oldDate = formattera.parse(times);
							long time15 = oldDate.getTime() - (15 * 60 * 1000);
							serverUptimeSeconds = ((time15 - System
									.currentTimeMillis()) / 1000);
							if (serverUptimeSeconds < 0) {
								Toast.makeText(InfoMatchActivity.this,
										R.string.toast_title_elapsed_time,
										Toast.LENGTH_LONG).show();
								if (checkMedia == true) {
									media.stop();
									media.release();
									media = null;
									vibrator.cancel();
									System.runFinalizersOnExit(true);
									checkMedia = false;
								}

							} else {
								checkRimender.putIntValue("check15"
										+ currentMath.getMatchId(),
										currentMath.getMatchId());
								txtReminder15.setVisibility(View.VISIBLE);
								dialog.dismiss();
								rgReminder15.setSelected(true);
								rgReminder30.setSelected(false);
								rgReminderOnTime.setSelected(false);
								long abc = oldDate.getTime() - (15 * 60 * 1000);
								String timeReminder15Min = getResources()
										.getString(
												R.string.dialog_title_before15min);
								setAlarm(abc, timeReminder15Min,
										currentMath.getMatchId() + "",
										"check15");
								if (checkMedia == true) {
									media.stop();
									media.release();
									media = null;
									vibrator.cancel();
									System.runFinalizersOnExit(true);
									checkMedia = false;
								}
							}

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				} else if (rgReminder30.isChecked()) {
					if (checkRimender.getIntValue("check30"
							+ currentMath.getMatchId()) == currentMath
							.getMatchId()) {
						Toast.makeText(InfoMatchActivity.this,
								R.string.toast_message_rimender30,
								Toast.LENGTH_LONG).show();

					} else {
						try {
							oldDate = formattera.parse(times);
							long time30 = oldDate.getTime() - (30 * 60 * 1000);
							serverUptimeSeconds = ((time30 - System
									.currentTimeMillis()) / 1000);
							if (serverUptimeSeconds < 0) {
								Toast.makeText(InfoMatchActivity.this,
										R.string.toast_title_elapsed_time,
										Toast.LENGTH_LONG).show();
								if (checkMedia == true) {
									media.stop();
									media.release();
									media = null;
									vibrator.cancel();
									System.runFinalizersOnExit(true);
									checkMedia = false;
								}
							} else {

								dialog.dismiss();
								checkRimender.putIntValue("check30"
										+ currentMath.getMatchId(),
										currentMath.getMatchId());
								txtReminder30.setVisibility(View.VISIBLE);
								rgReminder15.setSelected(false);
								rgReminder30.setSelected(true);
								rgReminderOnTime.setSelected(false);
								long abc = oldDate.getTime() - (30 * 60 * 1000);
								String time30Reminder = getResources()
										.getString(
												R.string.dialog_title_before30min);
								setAlarm(abc, time30Reminder,
										currentMath.getMatchId() + "",
										"check30");
								if (checkMedia == true) {
									media.stop();
									media.release();
									media = null;
									vibrator.cancel();
									System.runFinalizersOnExit(true);
									checkMedia = false;
								}
							}

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}

				} else if (rgReminderOnTime.isChecked()) {
					if (checkRimender.getIntValue("checkOntime" + idMatch) == idMatch) {
						Toast.makeText(InfoMatchActivity.this,
								R.string.toast_message_rimender_ontime,
								Toast.LENGTH_LONG).show();
					} else {
						try {
							oldDate = formattera.parse(times);

							serverUptimeSeconds = ((oldDate.getTime() - System
									.currentTimeMillis()) / 1000);
							if (serverUptimeSeconds < 0) {
								String a = getResources().getString(
										R.string.toast_title_elapsed_time);
								Toast.makeText(InfoMatchActivity.this, a,
										Toast.LENGTH_LONG).show();
								if (checkMedia == true) {
									media.stop();
									media.release();
									media = null;
									vibrator.cancel();
									System.runFinalizersOnExit(true);
									checkMedia = false;
								}

							} else {
								dialog.dismiss();
								checkRimender.putIntValue("checkOntime"
										+ idMatch, idMatch);
								txtReminderOntime.setVisibility(View.VISIBLE);
								rgReminder15.setSelected(false);
								rgReminder30.setSelected(false);
								rgReminderOnTime.setSelected(true);
								long abc = oldDate.getTime();
								String timeOnReminder = getResources()
										.getString(R.string.dialog_title_onTime);
								setAlarm(abc, timeOnReminder,
										currentMath.getMatchId() + "",
										"checkOntime");
								if (checkMedia == true) {
									media.stop();
									media.release();
									media = null;
									vibrator.cancel();
									System.runFinalizersOnExit(true);
									checkMedia = false;

								}
							}

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}

				}
				bindReminderStatus();

			}
		});
		dialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				if (checkMedia == true) {
					media.stop();
					media.release();
					media = null;
					vibrator.cancel();
					System.runFinalizersOnExit(true);
					checkMedia = false;
				}
			}
		});
		dialog.show();
	}

	private void setAlarm(long abc, String title, String mathId, String type) {
		PendingIntent pendingIntent;
		Random r = new Random();
		Intent intent = new Intent(InfoMatchActivity.this, AlarmReceiver.class);
		Bundle b = new Bundle();
		b.putString("titleReminder", title);
		b.putString("matchId", mathId);
		b.putString("type", type);
		intent.putExtras(b);
		pendingIntent = PendingIntent.getBroadcast(InfoMatchActivity.this,
				r.nextInt(), intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, abc, pendingIntent);
	}

}
