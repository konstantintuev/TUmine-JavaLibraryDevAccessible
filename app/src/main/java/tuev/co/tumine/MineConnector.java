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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

@SuppressWarnings("unused")
public class MineConnector {
    OnMessageReceived messageReciever;
    Context context;

    /**
     * Automatically:
     *  * connects to the miner if the service is running.
     *  * stops itself and removes all hooks
     *
     *  @param isBasicLogging parse data from the miner ONLY about:
     *   - lastMiningJobResult
     *   - hashrate and hashratePerThread
     *   - lastError
     *   - initInfo
     */
    public MineConnector(OnMessageReceived messageReciever,
            Context context,
            boolean isBasicLogging, boolean useMinus11InsteadOfNull) {
        this.messageReciever = messageReciever;
        this.context = context;
        attach();
        sockListener = new SockClient(messageReciever, context, isBasicLogging, useMinus11InsteadOfNull);
    }

    public MineConnector(OnMessageReceived messageReciever, InfoPassing infoPassing) {
        this.messageReciever = messageReciever;
        this.context = infoPassing.context;
        attach();
        sockListener = new SockClient(messageReciever, context, infoPassing.minerOutput.isBasicLogging, infoPassing.minerOutput.useMinus11InsteadOfNull);
    }
    private boolean attached = false;

    private SockClient sockListener;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getExtras() == null) {
                return;
            }
            if(intent.getExtras().getBoolean("started")) {
                if (attached) {
                    if (!sockListener.isRunning()) {
                        attached = false;
                    }
                }
                if (!attached) {
                    attached = true;
                    sockListener.runTask();
                    messageReciever.connected();
                }
            } else if (intent.getExtras().getBoolean("stopped")) {
                attached = false;
                sockListener.stopClient();
                messageReciever.stopped();
            }
        }
    };
    public void pause() {
        sockListener.sendMessage("pause");
    }
    public void resume() {
        sockListener.sendMessage("resume");
    }
    public void requestHashratePerThread() {
        sockListener.sendMessage("advhash");
    }

    /**
     * **IMPORTANT**: Call when no longer needed.
     */
    public void detach() {
        try {
            context.unregisterReceiver(receiver);
            sockListener.stopClient();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        attached = false;
    }

    /**
     * Use ONLY when already called detach
     *
     * Don't worry I have made a check.
     */
    public void reattach() {
        if (!attached) {
            attach();
        }
    }

    private void attach() {
        IntentFilter filter = new IntentFilter("tuev.co.tumine.MineServiceUpdate");
        context.registerReceiver(receiver, filter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent isRunning = new Intent("tuev.co.tumine.MineServiceStatus");
                isRunning.putExtra("action", "report");
                context.sendBroadcast(isRunning);
            }
        }, 2500);
    }
}