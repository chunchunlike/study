package org.xi.common.utils.text;

import java.io.ByteArrayOutputStream;

/**
 * Created by Xi on 5/17/2017.
 */
public class HexUtil {

    public static byte[] toHex(byte[] datas) {
        Character[] hexChars = new Character[] { '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();

        for (int i = 0; i < datas.length; i++) {
            byte data = datas[i];
            byte[] hexByte = new byte[2];
            hexByte[0] = (byte) ((int) hexChars[(data & 0xF0) >> 4]);
            hexByte[1] = (byte) ((int) hexChars[data & 0x0F]);
            memoryStream.write(hexByte, 0, 2);
        }

        return memoryStream.toByteArray();
    }

    public static String toHex(String input, String encoding) {
        try {
            byte[] data = input.getBytes(encoding);
            byte[] hexData = toHex(data);
            return new String(hexData, encoding);
        } catch (Exception e) {

        }
        return "";
    }

    public static String toHex(String input) {
        return toHex(input, "utf8");
    }

    public static byte[] fromHex(byte[] hexData) {
        ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();

        for (int i = 0; i < hexData.length; i += 2) {
            byte[] hexPairInDecimal = new byte[2];
            for (int h = 0; h < 2; h++) {
                Character temp = Character.toChars((int) hexData[i + h])[0];
                if (temp == '0') {
                    hexPairInDecimal[h] = 0;
                } else if (temp == '1') {
                    hexPairInDecimal[h] = 1;
                } else if (temp == '2') {
                    hexPairInDecimal[h] = 2;
                } else if (temp == '3') {
                    hexPairInDecimal[h] = 3;
                } else if (temp == '4') {
                    hexPairInDecimal[h] = 4;
                } else if (temp == '5') {
                    hexPairInDecimal[h] = 5;
                } else if (temp == '6') {
                    hexPairInDecimal[h] = 6;
                } else if (temp == '7') {
                    hexPairInDecimal[h] = 7;
                } else if (temp == '8') {
                    hexPairInDecimal[h] = 8;
                } else if (temp == '9') {
                    hexPairInDecimal[h] = 9;
                } else if (temp == 'A' || temp == 'a') {
                    hexPairInDecimal[h] = 10;
                } else if (temp == 'B' || temp == 'b') {
                    hexPairInDecimal[h] = 11;
                } else if (temp == 'C' || temp == 'c') {
                    hexPairInDecimal[h] = 12;
                } else if (temp == 'D' || temp == 'd') {
                    hexPairInDecimal[h] = 13;
                } else if (temp == 'E' || temp == 'e') {
                    hexPairInDecimal[h] = 14;
                } else if (temp == 'F' || temp == 'f') {
                    hexPairInDecimal[h] = 15;
                }
            }
            memoryStream.write((byte) ((hexPairInDecimal[0] << 4) | hexPairInDecimal[1]));

        }
        return memoryStream.toByteArray();
    }

    public static String fromHex(String input, String encoding) {
        try {
            byte[] hexData = input.getBytes(encoding);
            byte[] data = fromHex(hexData);
            return new String(data, encoding);
        } catch (Exception e) {

        }
        return "";
    }

    public static String fromHex(String input) {
        return fromHex(input, "utf8");
    }
}
