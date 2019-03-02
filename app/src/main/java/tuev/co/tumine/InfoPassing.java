/* Tuev, Co
 * Copyright 2018-2019 Tuev, Co       <https://tuev-co.eu>, <touevco@gmail.com>
 *
 * This file contains Original Code as defined in and that are subject to
 * the License provided in the 'License.pdf' in the file tree or
 * available in 'https://tuev-co.eu'. You may not use this file except in
 * compliance with the License. The rights granted to you under the License
 * may not be used to distribute, or enable the distribution of,
 * unlawful or unlicensed copies of the 'Tumine Monero Software'
 * or any binaries or libraries built using the source code provided.
 *
 * The Original Code and all software distributed under the License are
 * distributed on an 'AS IS' basis, WITHOUT WARRANTY OF ANY KIND.
 *
 *
 * Please see the License for the specific governing rights and
 * limitations under the License.
 *
 */

package tuev.co.tumine;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

/**
 * **IMPORTANT**: Make sure the user has internet
 * to initialize some files if you use the
 * *nonative* version in your app.
 */
@SuppressWarnings("unused")
public class InfoPassing implements Parcelable, Serializable {


    transient Context context;

    public InfoPassing(Context context) {
        this.context = context;
    }



    public static class QuestionableUsefulness implements Parcelable, Serializable {
        /**
         * Cpu affinity set which cores will be used
         *
         * *Default*: "0x-1" - Auto
         */
        String cpuAffinity = "0x-1";

        /**
         * Between 0 and 5
         *
         * *Default*: -1 - Auto
         */
        int cpuPriority = -1;

        public QuestionableUsefulness() {}

        QuestionableUsefulness(Parcel parcel) {
            cpuAffinity = parcel.readString();
            cpuPriority = parcel.readInt();
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(cpuAffinity);
            parcel.writeInt(cpuPriority);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<QuestionableUsefulness> CREATOR
                = new Creator<QuestionableUsefulness>() {

            // This simply calls our new constructor (typically private) and
            // passes along the unmarshalled `Parcel`, and then returns the new object!
            @Override
            public QuestionableUsefulness createFromParcel(Parcel in) {
                return new QuestionableUsefulness(in);
            }

            // We just need to copy this and change the type to match our class.
            @Override
            public QuestionableUsefulness[] newArray(int size) {
                return new QuestionableUsefulness[size];
            }
        };

        //Getters/Setters - not needed by the library itself, all documentation is provided above the fields

        public String getCpuAffinity() {
            return cpuAffinity;
        }

        public QuestionableUsefulness setCpuAffinity(String cpuAffinity) {
            this.cpuAffinity = cpuAffinity;
            return this;
        }

        public int getCpuPriority() {
            return cpuPriority;
        }

        public QuestionableUsefulness setCpuPriority(int cpuPriority) {
            this.cpuPriority = cpuPriority;
            return this;
        }
    }

    //what output do you want to receive
    public static class MinerOutput implements Parcelable, Serializable {

        //get updates about what is happening with the service using the ReceiveInfo connector
        /** These are the keys which will be used in the ReceiveInfo connector:
            - info, without error can be: lowMemory, stopped, started
            - info, with error: contains the error message
            - Logcat messages about some checks before function execution
        */
        boolean isBasicLogging = true;

        //review start params as received by the miner
        boolean debugParams = false;

        //report ONLY simple hashrate, accepted and errors
        boolean reportOnlyBasics = false;

        //if true the miner won't create a file used for communication between it and the kotlin library
        //might improve performance
        boolean silent = false;

        /**
         * if returned number {@link Double} or {@link Integer}
         * is invalid, return -11 instead of null
         */
        boolean useMinus11InsteadOfNull = false;

        public MinerOutput() {}

        MinerOutput(Parcel parcel) {
            isBasicLogging = parcel.readInt() != 0;
            debugParams = parcel.readInt() != 0;
            reportOnlyBasics = parcel.readInt() != 0;
            silent = parcel.readInt() != 0;
            useMinus11InsteadOfNull = parcel.readInt() != 0;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeInt( (isBasicLogging) ? 1 : 0);
            parcel.writeInt( (debugParams) ? 1 : 0);
            parcel.writeInt( (reportOnlyBasics) ? 1 : 0);
            parcel.writeInt( (silent) ? 1 : 0);
            parcel.writeInt( (useMinus11InsteadOfNull) ? 1 : 0);
        }

        public int describeContents() {
            return 0;
        }

        public static final Creator<MinerOutput> CREATOR
                = new Creator<MinerOutput>() {

            // This simply calls our new constructor (typically private) and
            // passes along the unmarshalled `Parcel`, and then returns the new object!
            @Override
            public MinerOutput createFromParcel(Parcel in) {
                return new MinerOutput(in);
            }

            // We just need to copy this and change the type to match our class.
            @Override
            public MinerOutput[] newArray(int size) {
                return new MinerOutput[size];
            }
        };

        //Getters/Setters - not needed by the library itself, all documentation is provided above the fields


        public boolean isBasicLogging() {
            return isBasicLogging;
        }

        public MinerOutput setBasicLogging(boolean basicLogging) {
            isBasicLogging = basicLogging;
            return this;
        }

        public boolean isDebugParams() {
            return debugParams;
        }

        public MinerOutput setDebugParams(boolean debugParams) {
            this.debugParams = debugParams;
            return this;
        }

        public boolean isReportOnlyBasics() {
            return reportOnlyBasics;
        }

        public MinerOutput setReportOnlyBasics(boolean reportOnlyBasics) {
            this.reportOnlyBasics = reportOnlyBasics;
            return this;
        }

        public boolean isSilent() {
            return silent;
        }

        public MinerOutput setSilent(boolean silent) {
            this.silent = silent;
            return this;
        }

        public MinerOutput setUseMinus11InsteadOfNull(boolean useMinus11InsteadOfNull) {
            this.useMinus11InsteadOfNull = useMinus11InsteadOfNull;
            return this;
        }

        public boolean isUseMinus11InsteadOfNull() {
            return useMinus11InsteadOfNull;
        }
    }

    //the essential fields for the miner
    public static class MinerConfig implements Parcelable, Serializable {

        /**
         * If enabled update the mining part over the internet.
         *
         * *INFO*: Checks for update on every start.
         */
        boolean updateOverInternet = false;

        /**
         * ANDROID_VERSION >= OREO
         *
         * Newer android versions don't let background apps
         * use high performance cores.
         * This makes the phone laggy when mining is started
         * from JobScheduler.
         *
         * It's recommended to use **1/4** of all cores here.
         * @see InfoPassing#availableCores
         */
        int coresWhenInAJob = 0;

        /**
         * *NOTE*: check my website for benchmarks
         *
         * **Important**: use the [availableCores] to know the hardware that you're dealing with
         */
        int coresToUse = 0;

        /**
         * Download larger binary with SSL support (openssl included) if updateOverInternet is activated
         *
         * Because they are bigger I recommend to use ONLY the .jar file and let the miner download the binary when needed
         *
         * **Important**: If any of the pools uses ssl this will be enabled automatically
         */
        boolean useSSL = true;

        /**
         * Which mining algorithm to use.
         *
         * This affects which coins you can mine.
         *
         * *Default*: [MiningAlgorithm.CRYPTONIGHT]
         */
        MiningAlgorithm algorithm = MiningAlgorithm.CRYPTONIGHT;

        /**
         * Mining pools to use.
         *
         * If one of them fails the miner will
         * switch to the next one.
         */
        ArrayList<Pool> pools = new ArrayList<Pool>();

        MinerConfig(Parcel parcel){
            updateOverInternet = parcel.readInt() != 0;
            coresWhenInAJob = parcel.readInt();
            coresToUse = parcel.readInt();
            useSSL = parcel.readInt() != 0;
            algorithm = MiningAlgorithm.fromString(parcel.readString());
            pools = (ArrayList<Pool>) parcel.readArrayList(Pool.class.getClassLoader());
        }

        public MinerConfig(){}

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeInt( (updateOverInternet) ? 1 : 0);
            parcel.writeInt(coresWhenInAJob);
            parcel.writeInt(coresToUse);
            parcel.writeInt( (useSSL) ? 1 : 0);
            parcel.writeString(algorithm.toString());
            parcel.writeList(pools);
        }

        public int describeContents() {
            return 0;
        }

        public static final Creator<MinerConfig> CREATOR
                = new Creator<MinerConfig>() {

            // This simply calls our new constructor (typically private) and
            // passes along the unmarshalled `Parcel`, and then returns the new object!
            @Override
            public MinerConfig createFromParcel(Parcel in) {
                return new MinerConfig(in);
            }

            // We just need to copy this and change the type to match our class.
            @Override
            public MinerConfig[] newArray(int size) {
                return new MinerConfig[size];
            }
        };

        /*
        * Getters/Setters - not needed by the library itself (if you choose to remove them, make the fields above public).
        * Added because it's the java way of accessing fields.
        * All documentation is provided above the fields themselves.
        */


        public boolean isUpdateOverInternet() {
            return updateOverInternet;
        }

        public MinerConfig setUpdateOverInternet(boolean updateOverInternet) {
            this.updateOverInternet = updateOverInternet;
            return this;
        }

        public int getCoresWhenInAJob() {
            return coresWhenInAJob;
        }

        public MinerConfig setCoresWhenInAJob(int coresWhenInAJob) {
            this.coresWhenInAJob = coresWhenInAJob;
            return this;
        }

        public int getCoresToUse() {
            return coresToUse;
        }

        public MinerConfig setCoresToUse(int coresToUse) {
            this.coresToUse = coresToUse;
            return this;
        }

        public boolean isUseSSL() {
            return useSSL;
        }

        public MinerConfig setUseSSL(boolean useSSL) {
            this.useSSL = useSSL;
            return this;
        }

        public MiningAlgorithm getAlgorithm() {
            return algorithm;
        }

        public MinerConfig setAlgorithm(MiningAlgorithm algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public ArrayList<Pool> getPools() {
            return pools;
        }

        public MinerConfig setPools(ArrayList<Pool> pools) {
            this.pools = pools;
            return this;
        }
    }

    //all Android specific stuff needed for the mining to work
    public static class MiningInAndroid implements Parcelable, Serializable {
        /**
         * The id which will be used in startForeground(int, Notification)
         */
        int foregroundNotificationId = 6345;

        /**
         * Keep CPU from sleeping
         *
         * **Important**: add '<uses-permission android:name="android.permission.WAKE_LOCK" />' to your manifest
         */
        boolean keepCPUawake = false;

        /**
         * Provide a class in your Project which extends {@link NotificationGetter}
         * **Important**: if the service will be a foreground one
         */
        
        Class<? extends NotificationGetter> notificationGetterClass = null;

        /**
         * Although not recommended, you can provide a Notification
         *  for the mining foreground service here
         * **Important**: if the service will be a foreground one
         */

        /**
         * use {@link InfoPassing.MiningInAndroid#notificationGetterClass} instead
         */
        @Deprecated
        Notification notification = null;

        /**
         * Check cpu usage for 20 secs on start of the miner:
         * 75% or more - don't start mining, the OS is hard enough on the device
         * 50% to 75% - mine with only 1 thread
         */
        boolean smartStart = false;

        
        public MiningInAndroid(){}

        MiningInAndroid(Parcel parcel) {
            foregroundNotificationId = parcel.readInt();
            keepCPUawake = parcel.readInt() != 0;
            String className = parcel.readString();
            if (className != null) {
                try {
                    notificationGetterClass = Class.forName(className).asSubclass(NotificationGetter.class);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            smartStart = parcel.readInt() != 0;
            notification = parcel.readParcelable(Notification.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeInt(foregroundNotificationId);
            parcel.writeInt( (keepCPUawake) ? 1 : 0);
            parcel.writeString(notificationGetterClass == null ? null : notificationGetterClass.getName());
            parcel.writeInt( (smartStart) ? 1 : 0);
            parcel.writeParcelable(notification, 0);
        }

        public int describeContents() {
            return 0;
        }

        public static final Creator<MiningInAndroid> CREATOR
                = new Creator<MiningInAndroid>() {

            // This simply calls our new constructor (typically private) and
            // passes along the unmarshalled `Parcel`, and then returns the new object!
            @Override
            public MiningInAndroid createFromParcel(Parcel in) {
                return new MiningInAndroid(in);
            }

            // We just need to copy this and change the type to match our class.
            @Override
            public MiningInAndroid[] newArray(int size) {
                return new MiningInAndroid[size];
            }
        };

        /*
         * Getters/Setters - not needed by the library itself (if you choose to remove them, make the fields above public).
         * Added because it's the java way of accessing fields.
         * All documentation is provided above the fields themselves.
         */

        public int getForegroundNotificationId() {
            return foregroundNotificationId;
        }

        public MiningInAndroid setForegroundNotificationId(int foregroundNotificationId) {
            this.foregroundNotificationId = foregroundNotificationId;
            return this;
        }

        public boolean isKeepCPUawake() {
            return keepCPUawake;
        }

        public MiningInAndroid setKeepCPUawake(boolean keepCPUawake) {
            this.keepCPUawake = keepCPUawake;
            return this;
        }

        public Class<? extends NotificationGetter> getNotificationGetterClass() {
            return notificationGetterClass;
        }

        public MiningInAndroid setNotificationGetterClass(Class<? extends NotificationGetter> notificationGetterClass) {
            this.notificationGetterClass = notificationGetterClass;
            return this;
        }

        public boolean isSmartStart() {
            return smartStart;
        }

        public MiningInAndroid setSmartStart(boolean smartStart) {
            this.smartStart = smartStart;
            return this;
        }

        @Deprecated
        public Notification getNotification() {
            return notification;
        }

        @Deprecated
        public MiningInAndroid setNotification(Notification notification) {
            this.notification = notification;
            return this;
        }
    }

    MiningInAndroid miningInAndroid = new MiningInAndroid();
    MinerConfig minerConfig = new MinerConfig();
    MinerOutput minerOutput = new MinerOutput();
    QuestionableUsefulness questionableUsefulness = new QuestionableUsefulness();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(miningInAndroid, 0);
        dest.writeParcelable(minerConfig, 0);
        dest.writeParcelable(minerOutput, 0);
        dest.writeParcelable(questionableUsefulness, 0);

    }

    private InfoPassing(Parcel orig) {
        ClassLoader classLoader = InfoPassing.class.getClassLoader();

        miningInAndroid = orig.readParcelable(classLoader);
        minerConfig = orig.readParcelable(classLoader);
        minerOutput = orig.readParcelable(classLoader);
        questionableUsefulness = orig.readParcelable(classLoader);
        
        if (miningInAndroid == null) {
            miningInAndroid = new MiningInAndroid();
        }
        if (minerConfig == null) {
            minerConfig = new MinerConfig();
        }
        if (minerOutput == null) {
            minerOutput = new MinerOutput();
        }
        if (questionableUsefulness == null) {
            questionableUsefulness = new QuestionableUsefulness();
        }
        
    }

    /**save this InfoPassing's state (fields) to storage
     * @see InfoPassing#readState(Context) for retrieval of the saved InfoPassing's state
     */
    public void saveState() {
        if (context == null) {
            return;
        }
        File file = new File(getDefaultTuminePrivateDir(context), "TUmine.info");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos;
        ObjectOutputStream out;
        try {
            if (!file.createNewFile()) {
                return;
            }
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //Each time the service starts, it checks for this file and if it is not present, it recreates it from the newest source available
    public void updateLibrary() {
        if (context == null) {
            return;
        }
        new File(getDefaultTuminePrivateDir(context).getAbsolutePath()+"/tumine").delete();
    }

    public void startMiningService(boolean dontSaveState) {
        if (context == null) {
            return;
        }
        if (!dontSaveState) {
            saveState();
        }
        Intent startMine = new Intent(context, MiningService.class);
        startMine.putExtra("data", (Parcelable) this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (miningInAndroid.notificationGetterClass != null) {
                context.startForegroundService(startMine);
            } else {
                scheduleJob(context);
            }
        } else {
            context.startService(startMine);
        }
    }

    public void startMiningService() {
        startMiningService(false);
    }

    private transient MiningService miningService = null;

    /**
     * This starts in another thread, it doesn't block the one you start it in.
     */
    public void startMiningNoService(boolean dontSaveState) {
        if (context == null) {
            return;
        }
        if (!dontSaveState) {
            saveState();
        }
        miningService = new MiningService();
        miningService.context = context;
        miningService.infoPassing = this;
        miningService.runningWithoutService = true;
        miningService.prepareMine();
    }

    public void startMiningNoService() {
        startMiningNoService(false);
    }

    public void stopMiningNoService() {
        miningService.stopMining(true);
        miningService = null;
    }

    public static void stopMiningService(Context context) {
        Intent startMine = new Intent(context, MiningService.class);
        startMine.putExtra("action", "stop");
        context.startService(startMine);
    }

    public void stopMiningService() {
        InfoPassing.stopMiningService(context);
    }


    public void changeMiningServiceSpeed(int threads) {
        Intent startMine = new Intent(context, MiningService.class);
        startMine.putExtra("action", "threads");
        startMine.putExtra("threads", threads);
        context.startService(startMine);
    }

    public static int availableCores = Runtime.getRuntime().availableProcessors();

    public static int getAvailableCores() {
        return availableCores;
    }

    public static int getVersion() {
        return version;
    }

    public static final Creator<InfoPassing> CREATOR
            = new Creator<InfoPassing>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public InfoPassing createFromParcel(Parcel in) {
            return new InfoPassing(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public InfoPassing[] newArray(int size) {
            return new InfoPassing[size];
        }
    };

    /*
     * Getters - not needed by the library itself (if you choose to remove them, make the fields above public).
     * Added because it's the java way of accessing fields.
     */

    public MiningInAndroid getMiningInAndroid() {
        return miningInAndroid;
    }

    public MinerConfig getMinerConfig() {
        return minerConfig;
    }

    public MinerOutput getMinerOutput() {
        return minerOutput;
    }

    public QuestionableUsefulness getQuestionableUsefulness() {
        return questionableUsefulness;
    }

    //local miner version
    public static int version = 10;

    //all static methods are below

    /**
     * May be null.
     * @param context passed as context for the restored 'InfoPassing'
     */
    public static InfoPassing readState(Context context) {
        FileInputStream fis;
        ObjectInputStream in;
        InfoPassing outInfo = null;
        File file = new File(getDefaultTuminePrivateDir(context), "TUmine.info");
        if (!file.exists()) {
            return null;
        }
        try {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            outInfo = (InfoPassing) in.readObject();
            in.close();
            outInfo.context = context;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return outInfo;
    }

    /**
     * start the mine service without passing a 'Info Passing' instance
     * @see InfoPassing#readState(Context) is used to start the miner
     */
    public static void startMiningServiceRestoreState(Context context) {
        InfoPassing state = readState(context);
        if (state != null) {
            Intent startMine = new Intent(context, MiningService.class);
            startMine.putExtra("action", "restore");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (state.miningInAndroid.notificationGetterClass != null) {
                    context.startForegroundService(startMine);
                } else {
                    scheduleJob(context);
                }
            } else {
                context.startService(startMine);
            }
        }
    }

    public static Notification getNotificationFromClass(Class<? extends NotificationGetter> notificationGetterClass, Context context) {
        try {
            return notificationGetterClass.newInstance().returnNotification(context);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**Our best case scenario for newer devices.
     * The new android versions forbid background service but
     * using JobScheduler we get around 7 mins (tested on Pie)
     * every 15 to 20 minutes of mining.
     *
     * The native miner first mines for you for 4 minutes
     * and then for me - your risk nothing:
     * The initialisation, connection, update and
     * all other task eat up from the mining time for me.
     *
     * This is NOT very reliable but it's the best we have for the
     * newer androids with their power management.
     * It's possible that Android will execute it only once and
     * stop until the app is reopen.
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static void scheduleJob(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
        if (jobScheduler.getPendingJob(2521) != null) {
            return;
        }

        ComponentName componentName = new ComponentName(context, MineJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(2521, componentName)
                .setRequiresCharging(false)
                .setPeriodic(15 * 60 * 1000)
                .build();

        int resultCode = jobScheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d("TUmine", "Job scheduled!");
        } else {
            Log.d("TUmine", "Job not scheduled");
        }
    }

    /**
     * @return the location where all of the miner's files are saved
     */
    public static File getDefaultTuminePrivateDir(Context context) {
        File dir = new File(context.getFilesDir().getAbsolutePath(), "tumineFiles");
        if (dir.exists()){
            return dir;
        }
        dir.mkdir();
        return dir;
    }

    /**
     * To keep a single instance of
     * itself the miner responds to
     * port '4672', if this port is
     * open then the miner is running.
     */
     public static void checkIfMinerIsRunning(final OnRunningResult onRunningResult) {
         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     Socket socket = new Socket();
                     socket.connect(new InetSocketAddress("127.0.0.1", 4672), 1000);
                     socket.close();
                     new Handler(Looper.getMainLooper()).post(new Runnable() {
                         @Override
                         public void run() {
                             onRunningResult.result(true);
                         }
                     });
                 } catch (Exception ex) {
                     new Handler(Looper.getMainLooper()).post(new Runnable() {
                         @Override
                         public void run() {
                             onRunningResult.result(false);
                         }
                     });
                 }
             }
         }).start();
    }



}
