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


@SuppressWarnings("unused")
public class OutputHelperClasses {
    public enum ChangedValue {
        initInfo,

        message,

        debugInfo,

        sslVersion,

        startMiningInfo,
        usingPool,
        lastMiningJobResult,
        lastMiningJob,
        hashrate,
        lastError,


        hashratePerThread
    }

    public enum CpuArchitecture {
        x86(0),
        x64(1);
        private Integer value;

        CpuArchitecture(Integer value) {
            this.value = value;
        }


        public static CpuArchitecture fromInt(Integer in) {
            for (CpuArchitecture dow : CpuArchitecture.values()) {
                // Use equalsIgnoreCase to make the getValue method a little more robust
                if (dow.value == in) {
                    return dow;
                }
            }
            return null;
        }

        public Integer toInt() {
            return value;
        }
    }

    public static class VersionInfo {
        
        private String minerVersion;
        
        private String libuvVersion;
        
        private String compilerVersion;

        
        public final String getMinerVersion() {
            return this.minerVersion;
        }

        public final void setMinerVersion(String minerVersion) {
            this.minerVersion = minerVersion;
        }

        
        public final String getLibuvVersion() {
            return this.libuvVersion;
        }

        public final void setLibuvVersion(String libuvVersion) {
            this.libuvVersion = libuvVersion;
        }

        
        public final String getCompilerVersion() {
            return this.compilerVersion;
        }

        public final void setCompilerVersion(String compilerVersion) {
            this.compilerVersion = compilerVersion;
        }

        public VersionInfo(String minerVersion, String libuvVersion, String compilerVersion) {
            this.minerVersion = minerVersion;
            this.libuvVersion = libuvVersion;
            this.compilerVersion = compilerVersion;
        }
    }
    public static class BasicStartInfo {
        private Integer threads;
        
        private MiningAlgorithm algorithm;
        private Integer miningVariant;

        public final Integer getThreads() {
            return this.threads;
        }

        public final void setThreads(Integer threads) {
            this.threads = threads;
        }

        
        public final MiningAlgorithm getAlgorithm() {
            return this.algorithm;
        }

        public final void setAlgorithm( MiningAlgorithm algorithm) {
            this.algorithm = algorithm;
        }

        public final Integer getMiningVariant() {
            return this.miningVariant;
        }

        public final void setMiningVariant(Integer miningVariant) {
            this.miningVariant = miningVariant;
        }

        public BasicStartInfo(Integer threads, MiningAlgorithm algorithm, Integer miningVariant) {
            this.threads = threads;
            this.algorithm = algorithm;
            this.miningVariant = miningVariant;
        }
    }
    
    public static class CpuInfo {
        
        private String name;
        
        private CpuArchitecture cpuArchitecture;
        private boolean hardwareAES;
        private final Double l2Cache;
        private final Double l3Cache;

        
        public final String getName() {
            return this.name;
        }

        public final void setName( String name) {
            this.name = name;
        }

        
        public final CpuArchitecture getCpuArchitecture() {
            return this.cpuArchitecture;
        }

        public final void setCpuArchitecture( CpuArchitecture cpuArchitecture) {
            this.cpuArchitecture = cpuArchitecture;
        }

        public final boolean getHardwareAES() {
            return this.hardwareAES;
        }

        public final void setHardwareAES(boolean hardwareAES) {
            this.hardwareAES = hardwareAES;
        }

        public final Double getL2Cache() {
            return this.l2Cache;
        }

        public final Double getL3Cache() {
            return this.l3Cache;
        }

        public CpuInfo( String name,  CpuArchitecture cpuArchitecture, boolean hardwareAES, Double l2Cache, Double l3Cache) {
            this.name = name;
            this.cpuArchitecture = cpuArchitecture;
            this.hardwareAES = hardwareAES;
            this.l2Cache = l2Cache;
            this.l3Cache = l3Cache;
        }
    }
    
    public static class MiningJobResult {
        
        private String error;
        private Integer accepted;
        private Integer rejected;
        private Integer difficulty;
        private Integer elapsedInMs;

        
        public final String getError() {
            return this.error;
        }

        public final void setError( String error) {
            this.error = error;
        }

        public final Integer getAccepted() {
            return this.accepted;
        }

        public final void setAccepted(Integer accepted) {
            this.accepted = accepted;
        }

        public final Integer getRejected() {
            return this.rejected;
        }

        public final void setRejected(Integer rejected) {
            this.rejected = rejected;
        }

        public final Integer getDifficulty() {
            return this.difficulty;
        }

        public final void setDifficulty(Integer difficulty) {
            this.difficulty = difficulty;
        }

        public final Integer getElapsedInMs() {
            return this.elapsedInMs;
        }

        public final void setElapsedInMs(Integer elapsedInMs) {
            this.elapsedInMs = elapsedInMs;
        }

        public MiningJobResult( String error, Integer accepted, Integer rejected, Integer difficulty, Integer elapsedInMs) {
            this.error = error;
            this.accepted = accepted;
            this.rejected = rejected;
            this.difficulty = difficulty;
            this.elapsedInMs = elapsedInMs;
        }
    }

    public static class MiningJob {
        
        private String url;
        private Integer difficulty;
        
        private MiningAlgorithm algorithm;

        
        public final String getUrl() {
            return this.url;
        }

        public final void setUrl( String url) {
            this.url = url;
        }

        public final Integer getDifficulty() {
            return this.difficulty;
        }

        public final void setDifficulty(Integer difficulty) {
            this.difficulty = difficulty;
        }

        
        public final MiningAlgorithm getAlgorithm() {
            return this.algorithm;
        }

        public final void setAlgorithm( MiningAlgorithm algorithm) {
            this.algorithm = algorithm;
        }

        public MiningJob(String url, Integer difficulty, MiningAlgorithm algorithm) {
            this.url = url;
            this.difficulty = difficulty;
            this.algorithm = algorithm;
        }
    }
    
    public static class Hashrate {
        
        private Double shortInterval;
        
        private Double mediumInterval;
        
        private Double largeInterval;
        
        private Double highest;

        
        public final Double getShortInterval() {
            return this.shortInterval;
        }

        public final void setShortInterval( Double shortInterval) {
            this.shortInterval = shortInterval;
        }

        
        public final Double getMediumInterval() {
            return this.mediumInterval;
        }

        public final void setMediumInterval( Double mediumInterval) {
            this.mediumInterval = mediumInterval;
        }

        
        public final Double getLargeInterval() {
            return this.largeInterval;
        }

        public final void setLargeInterval( Double largeInterval) {
            this.largeInterval = largeInterval;
        }

        
        public final Double getHighest() {
            return this.highest;
        }

        public final void setHighest( Double highest) {
            this.highest = highest;
        }

        public Hashrate( Double shortInterval,  Double mediumInterval,  Double largeInterval,  Double highest) {
            this.shortInterval = shortInterval;
            this.mediumInterval = mediumInterval;
            this.largeInterval = largeInterval;
            this.highest = highest;
        }
    }

    public static class HashratePerThread {
        private Integer index;
        
        private final String affinity;
        
        private Double shortInterval;
        
        private Double mediumInterval;
        
        private Double largeInterval;

        public final Integer getIndex() {
            return this.index;
        }

        public final void setIndex(Integer index) {
            this.index = index;
        }

        
        public final String getAffinity() {
            return this.affinity;
        }

        
        public final Double getShortInterval() {
            return this.shortInterval;
        }

        public final void setShortInterval( Double shortInterval) {
            this.shortInterval = shortInterval;
        }

        
        public final Double getMediumInterval() {
            return this.mediumInterval;
        }

        public final void setMediumInterval( Double mediumInterval) {
            this.mediumInterval = mediumInterval;
        }

        
        public final Double getLargeInterval() {
            return this.largeInterval;
        }

        public final void setLargeInterval( Double largeInterval) {
            this.largeInterval = largeInterval;
        }

        public HashratePerThread(Integer index,  String affinity,  Double shortInterval,  Double mediumInterval,  Double largeInterval) {
            this.index = index;
            this.affinity = affinity;
            this.shortInterval = shortInterval;
            this.mediumInterval = mediumInterval;
            this.largeInterval = largeInterval;
        }
    }

    public static class StartMiningInfo {
        
        private Integer threads;
        
        private Integer ways;
        
        private Double memoryInMB;

        
        public final Integer getThreads() {
            return this.threads;
        }

        public final void setThreads( Integer threads) {
            this.threads = threads;
        }

        
        public final Integer getWays() {
            return this.ways;
        }

        public final void setWays( Integer ways) {
            this.ways = ways;
        }

        
        public final Double getMemoryInMB() {
            return this.memoryInMB;
        }

        public final void setMemoryInMB( Double memoryInMB) {
            this.memoryInMB = memoryInMB;
        }

        public StartMiningInfo( Integer threads,  Integer ways,  Double memoryInMB) {
            this.threads = threads;
            this.ways = ways;
            this.memoryInMB = memoryInMB;
        }
    }

    public static class InitInfo {
        
        private String pkg;
        private boolean paid;
        
        private Integer percentageToMe;

        
        public final String getPkg() {
            return this.pkg;
        }

        public final void setPkg( String pkg) {
            this.pkg = pkg;
        }

        public final boolean getPaid() {
            return this.paid;
        }

        public final void setPaid(boolean paid) {
            this.paid = paid;
        }

        
        public final Integer getPercentageToMe() {
            return this.percentageToMe;
        }

        public final void setPercentageToMe( Integer percentageToMe) {
            this.percentageToMe = percentageToMe;
        }

        public InitInfo( String pkg, boolean paid,  Integer percentageToMe) {
            this.pkg = pkg;
            this.paid = paid;
            this.percentageToMe = percentageToMe;
        }
    }

    public static class Debuginfo {
        
        private BasicStartInfo basicStartInfo;
        
        private VersionInfo versionInfo;
        
        private CpuInfo cpuInfo;
        
        private String[] pools;

        
        public final BasicStartInfo getBasicStartInfo() {
            return this.basicStartInfo;
        }

        public final void setBasicStartInfo( BasicStartInfo basicStartInfo) {
            this.basicStartInfo = basicStartInfo;
        }

        
        public final VersionInfo getVersionInfo() {
            return this.versionInfo;
        }

        public final void setVersionInfo( VersionInfo versionInfo) {
            this.versionInfo = versionInfo;
        }

        
        public final CpuInfo getCpuInfo() {
            return this.cpuInfo;
        }

        public final void setCpuInfo( CpuInfo cpuInfo) {
            this.cpuInfo = cpuInfo;
        }

        
        public final String[] getPools() {
            return this.pools;
        }

        public final void setPools( String[] pools) {
            this.pools = pools;
        }

        public Debuginfo( BasicStartInfo basicStartInfo,  VersionInfo versionInfo,  CpuInfo cpuInfo,  String[] pools) {
            this.basicStartInfo = basicStartInfo;
            this.versionInfo = versionInfo;
            this.cpuInfo = cpuInfo;
            this.pools = pools;
        }
    }

    public static class SslInfo {
        
        private String tlsVersion;
        
        private String tlsFingerprInteger;

        
        public final String getTlsVersion() {
            return this.tlsVersion;
        }

        public final void setTlsVersion( String tlsVersion) {
            this.tlsVersion = tlsVersion;
        }

        
        public final String getTlsFingerprInteger() {
            return this.tlsFingerprInteger;
        }

        public final void setTlsFingerprInteger( String tlsFingerprInteger) {
            this.tlsFingerprInteger = tlsFingerprInteger;
        }

        public SslInfo( String tlsVersion,  String tlsFingerprInteger) {
            this.tlsVersion = tlsVersion;
            this.tlsFingerprInteger = tlsFingerprInteger;
        }
    }
}