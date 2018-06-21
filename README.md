The World Cup Application was coded in Eclipse IDE with the Android SDK.
![Screenshot](https://user-images.githubusercontent.com/8135354/41693837-7952dece-7531-11e8-9bc3-41f3d8fe6cc5.png)
<b>Installing the Eclipse Plugin</b><br />
Android offers a custom plugin for the Eclipse IDE, called Android Development Tools (ADT). This plugin provides a powerful, integrated environment in which to develop Android apps. It extends the capabilities of Eclipse to let you quickly set up new Android projects, build an app UI, debug your app, and export signed (or unsigned) app packages (APKs) for distribution.

If you need to install Eclipse, you can download it from http://eclipse.org/

<b>Download the ADT Plugin</b><br />
Start Eclipse, then select Help > Install New Software.
Click Add, in the top-right corner.
In the Add Repository dialog that appears, enter "ADT Plugin" for the Name and the following URL for the Location:
https://dl-ssl.google.com/android/eclipse/

Click OK.

If you have trouble acquiring the plugin, try using "http" in the Location URL, instead of "https" (https is preferred for security reasons).

In the Available Software dialog, select the checkbox next to Developer Tools and click Next.

In the next window, you'll see a list of the tools to be downloaded. Click Next.

Read and accept the license agreements, then click Finish.

If you get a security warning saying that the authenticity or validity of the software can't be established, click OK.

When the installation completes, restart Eclipse.

<b>Configure the ADT Plugin</b><br />
Once Eclipse restarts, you must specify the location of your Android SDK directory:

In the "Welcome to Android Development" window that appears, select Use existing SDKs.
Browse and select the location of the Android SDK directory you recently downloaded and unpacked.

Click Next.

Your Eclipse IDE is now set up to develop Android apps, but you need to add the latest SDK platform tools and an Android platform to your environment. To get these packages for your SDK, continue to Adding Platforms and Packages.

<b>Features of the World Cup Application</b><br />
* Countdown to the kickoff time
* Show all matches
* Set alarm for each match
* Users are able to see each teams current position, scores, win, lose, draw per group
* Live scores with a detailed timeline for each match
* Top scorers of the tournament
* Admob integrated

After installation has been done, Import the Complete Project into your Workspace.
