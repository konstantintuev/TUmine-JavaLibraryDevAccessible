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

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


@SuppressWarnings("unused")
public class Pool implements Parcelable, Serializable {

    /**
     * **IMPORTANT**: requires larger binaries with SSL support (openssl included)
     *
     * Because they are bigger I recommend to use the 'nonative' version and let the miner download the binary when needed
     *
     * If the value is wrong the miner will try to reconnect without/with ssl
     */
    boolean useSSL;
    String url;//-the pool where you want to mine monero (XMR) or other cryptonight coins - look at this: http://moneropools.com/
    //specify appropriate username based on the mining pool you are using
    String user;
    //specify appropriate password based on the mining pool you are using
    String password;// - this is important for earnings tacking
    String rigID = "null";
    //send keepAlive message to the server every 'keepAlive' seconds
    //1 = default, 0 = off, custom = seconds
    int keepAlive = 1;
    boolean nicehash = false;
    /**
     * **IMPORTANT**: requires larger binaries with SSL support (openssl included)
     *
     * Because they are bigger I recommend to use the 'nonative' version and let the miner download the binary when needed
     *
     * If the value is wrong the miner will try to reconnect without/with ssl
     */
    String ip = null;
    String urlDebug = null;

    public String getUrlDebug() {
        return urlDebug;
    }

    public Pool setUrlDebug(String urlDebug) {
        this.urlDebug = urlDebug;
        return this;
    }

    public Pool setUrl(String url) {
        this.url = url;
        return this;
    }

    public Pool setUser(String user) {
        this.user = user;
        return this;
    }

    public Pool setPassword(String password) {
        this.password = password;
        return this;
    }

    public Pool setRigID(String rigID) {
        this.rigID = rigID;
        return this;
    }

    public Pool setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
        return this;
    }


    public Pool setNicehash(boolean nicehash) {
        this.nicehash = nicehash;
        return this;
    }
    public Pool setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Pool setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getRigID() {
        return rigID;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public boolean isNicehash() {
        return nicehash;
    }

    public String getIp() {
        return ip;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    public Pool(String url,//-the pool where you want to mine monero (XMR) or other cryptonight coins - look at this: http://moneropools.com/
                //specify appropriate username based on the mining pool you are using
                String user,
                //specify appropriate password based on the mining pool you are using
                String password,// - this is important for earnings tacking
                String rigID,
                //send keepAlive message to the server every 'keepAlive' seconds
                //1 = default, 0 = off, custom = seconds
                int keepAlive,
                boolean nicehash,
                String ip,
                boolean useSSL) {
        this.url = url;
        this.user = user;
        this.rigID = rigID;
        this.keepAlive = keepAlive;
        this.nicehash = nicehash;
        this.ip = ip;
        this.password = password;
        this.useSSL = useSSL;
    }

    public Pool(String url,//-the pool where you want to mine monero (XMR) or other cryptonight coins - look at this: http://moneropools.com/
                //specify appropriate username based on the mining pool you are using
                String user,
                //specify appropriate password based on the mining pool you are using
                String password// - this is important for earnings tacking
    ) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Pool(Parcel parcel) {
        this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readInt() != 0,
                parcel.readString(),
                parcel.readInt() != 0
        );
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(url);
        parcel.writeString(user);
        parcel.writeString(password);
        parcel.writeString(rigID);
        parcel.writeInt(keepAlive);
        parcel.writeInt( (nicehash) ? 1 : 0);
        parcel.writeString(ip);
        parcel.writeInt( (useSSL) ? 1 : 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pool> CREATOR
            = new Creator<Pool>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Pool createFromParcel(Parcel in) {
            return new Pool(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Pool[] newArray(int size) {
            return new Pool[size];
        }
    };
}