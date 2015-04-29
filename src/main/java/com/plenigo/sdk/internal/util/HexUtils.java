package com.plenigo.sdk.internal.util;


/**
 * <p>
 * Provides different helper methods for working with hex encoding and decoding.
 * </p>
 * <p>
 * <b>IMPORTANT:</b> This class is part of the internal API, please do not use it, because it can
 * be removed in future versions of the SDK or access to such elements could
 * be changed from 'public' to 'default' or less.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public final class HexUtils {
    /**
     * This is the fill needed when
     * shifting during hex encoding.
     */
    private static final int FOUR_DIGIT_FILL = 4;
    /**
     * This TWO char binary is used to decode
     * every byte.
     */
    private static final int BINARY_TWO_CHAR = 0xFF;
    /**
     * This is the ending binary number range used during hex encoding.
     */
    private static final int ENDING_HX = 0x0F;
    /**
     * This is the starting binary number range used during hex encoding.
     */
    private static final int STARTING_HX = 0xF0;
    /**
     * The array used for hexing all the characters.
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Default constructor.
     */
    private HexUtils() {
    }

    /**
     * This method decodes hexadecimal data
     * that is being contained in a char array.
     *
     * @param hexedString String of hexed characters
     *
     * @return decoded data as a byte array
     */
    public static byte[] decodeHex(String hexedString) {
        final char[] data = hexedString.toCharArray();
        final int len = data.length;

        if ((len & 0x01) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }

        final byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << FOUR_DIGIT_FILL;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & BINARY_TWO_CHAR);
        }

        return out;
    }


    /**
     * This method encodes a byte array into a
     * hexed string.
     *
     * @param data The data to encode
     *
     * @return A Hexadecimal encoded string.
     */
    public static String encodeHexString(final byte[] data) {
        final int l = data.length;
        StringBuilder out = new StringBuilder(l << 1);
        // two characters form the hex value.
        for (byte aData : data) {
            out.append(DIGITS_LOWER[(STARTING_HX & aData)
                    >>> FOUR_DIGIT_FILL]);
            out.append(DIGITS_LOWER[ENDING_HX & aData]);
        }
        return out.toString();
    }


    /**
     * Converts a character into a hexadecimal number.
     *
     * @param ch    The character to convert
     * @param index The index of the character in the array.
     *
     * @return a Digit representing the hexadecimal character.
     */
    private static int toDigit(final char ch, final int index) {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new IllegalArgumentException("Illegal hexadecimal character "
                    + ch + " at index " + index);
        }
        return digit;
    }
}
