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
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.O)
public class MineJobService extends JobService {
    MiningService miningService;

    @Override
    public boolean onStartJob(JobParameters params) {
        InfoPassing infoPassing = InfoPassing.readState(this);
        if (infoPassing == null) {
            return true;
        }

        miningService = new MiningService();
        miningService.context = this;
        miningService.infoPassing = infoPassing;
        miningService.runningInsideJob = true;
        miningService.prepareMine();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        miningService.stopMining(true);
        return true;
    }
}