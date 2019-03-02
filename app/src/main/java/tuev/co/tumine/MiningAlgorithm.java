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

public enum  MiningAlgorithm {

    CRYPTONIGHT                    ("cryptonight"),
    CRYPTONIGHT_0                  ("cryptonight/0"),
    CRYPTONIGHT_1                  ("cryptonight/1"),
    CRYPTONIGHT_XTL                ("cryptonight/xtl"),
    CRYPTONIGHT_MSR                ("cryptonight/msr"),
    CRYPTONIGHT_XAO                ("cryptonight/xao"),
    CRYPTONIGHT_RTO                ("cryptonight/rto"),
    CRYPTONIGHT_2                  ("cryptonight/2"),
    CRYPTONIGHT_HALF               ("cryptonight/half"),
    CRYPTONIGHT_XTLV9              ("cryptonight/xtlv9"),
    CRYPTONIGHT_WOW                ("cryptonight/wow"),
    CRYPTONIGHT_R                  ("cryptonight/r"),

    CRYPTONIGHT_LITE               ("cryptonight-lite"),
    CRYPTONIGHT_LIGHT              ("cryptonight-light"),
    CRYPTONIGHT_LITE_0             ("cryptonight-lite/0"),
    CRYPTONIGHT_LITE_1             ("cryptonight-lite/1"),

    CRYPTONIGHT_HEAVY              ("cryptonight-heavy"),
    CRYPTONIGHT_HEAVY_0            ("cryptonight-heavy/0"),
    CRYPTONIGHT_HEAVY_XHV          ("cryptonight-heavy/xhv"),
    CRYPTONIGHT_HEAVY_TUBE         ("cryptonight-heavy/tube"),

    CRYPTONIGHT_PICO               ("cryptonight-pico"),
    CRYPTONIGHT_PICO_TRTL          ("cryptonight-pico/trtl"),
    CRYPTONIGHT_TURTLE             ("cryptonight-turtle"),
    CRYPTONIGHT_ULTRALITE          ("cryptonight-ultralite");

    private String algo;

    MiningAlgorithm(String algo) {
        this.algo = algo;
    }


    public static MiningAlgorithm fromString(String in) {
        for (MiningAlgorithm dow : MiningAlgorithm.values()) {
            // Use equalsIgnoreCase to make the getValue method a little more robust
            if (dow.toString().equalsIgnoreCase(in)) {
                return dow;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return algo;
    }
}
