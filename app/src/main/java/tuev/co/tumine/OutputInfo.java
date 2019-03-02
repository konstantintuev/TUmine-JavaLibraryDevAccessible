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

import java.util.ArrayList;

import tuev.co.tumine.OutputHelperClasses.ChangedValue;
import tuev.co.tumine.OutputHelperClasses.Debuginfo;
import tuev.co.tumine.OutputHelperClasses.Hashrate;
import tuev.co.tumine.OutputHelperClasses.InitInfo;
import tuev.co.tumine.OutputHelperClasses.MiningJob;
import tuev.co.tumine.OutputHelperClasses.MiningJobResult;
import tuev.co.tumine.OutputHelperClasses.SslInfo;
import tuev.co.tumine.OutputHelperClasses.StartMiningInfo;

@SuppressWarnings("unused")
public class OutputInfo {
    
    ChangedValue lastChangedValue;
    
    InitInfo initInfo;
    
    String message;
    
    SslInfo sslVersion;
    
    Debuginfo debugInfo;
    
    StartMiningInfo startMiningInfo;
    
    Pool usingPool;
    
    MiningJobResult lastMiningJobResult;
    
    MiningJob lastMiningJob;
    
    Hashrate hashrate;
    
    String lastError;
    
    ArrayList<OutputHelperClasses.HashratePerThread> hashratePerThread;

    
    public ChangedValue getLastChangedValue() {
        return this.lastChangedValue;
    }

    public void setLastChangedValue( ChangedValue lastChangedValue) {
        this.lastChangedValue = lastChangedValue;
    }

    
    public InitInfo getInitInfo() {
        return this.initInfo;
    }

    public void setInitInfo( InitInfo initInfo) {
        this.initInfo = initInfo;
    }

    
    public String getMessage() {
        return this.message;
    }

    public void setMessage( String message) {
        this.message = message;
    }

    
    public SslInfo getSslVersion() {
        return this.sslVersion;
    }

    public void setSslVersion( SslInfo sslVersion) {
        this.sslVersion = sslVersion;
    }

    
    public Debuginfo getDebugInfo() {
        return this.debugInfo;
    }

    public void setDebugInfo( Debuginfo debugInfo) {
        this.debugInfo = debugInfo;
    }

    
    public StartMiningInfo getStartMiningInfo() {
        return this.startMiningInfo;
    }

    public void setStartMiningInfo( StartMiningInfo startMiningInfo) {
        this.startMiningInfo = startMiningInfo;
    }

    
    public Pool getUsingPool() {
        return this.usingPool;
    }

    public void setUsingPool( Pool usingPool) {
        this.usingPool = usingPool;
    }

    
    public MiningJobResult getLastMiningJobResult() {
        return this.lastMiningJobResult;
    }

    public void setLastMiningJobResult( MiningJobResult lastMiningJobResult) {
        this.lastMiningJobResult = lastMiningJobResult;
    }

    
    public MiningJob getLastMiningJob() {
        return this.lastMiningJob;
    }

    public void setLastMiningJob( MiningJob lastMiningJob) {
        this.lastMiningJob = lastMiningJob;
    }

    
    public Hashrate getHashrate() {
        return this.hashrate;
    }

    public void setHashrate( Hashrate hashrate) {
        this.hashrate = hashrate;
    }

    
    public String getLastError() {
        return this.lastError;
    }

    public void setLastError( String lastError) {
        this.lastError = lastError;
    }

    
    public ArrayList<OutputHelperClasses.HashratePerThread> getHashratePerThread() {
        return this.hashratePerThread;
    }

    public void setHashratePerThread( ArrayList<OutputHelperClasses.HashratePerThread> hashratePerThread) {
        this.hashratePerThread = hashratePerThread;
    }
}
