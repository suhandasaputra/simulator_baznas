/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asac.singleton;

import com.asac.entity.ResponseCodeEntity;
import java.util.HashMap;

/**
 *
 * @author herrysuganda
 */
public class ResponseCodeSingleton {

    private static ResponseCodeSingleton log = null;
    private HashMap responseCode;

    public static ResponseCodeSingleton getInstance() {
        if (log == null) {
            log = new ResponseCodeSingleton();
        }

        return log;
    }

    public ResponseCodeSingleton() {
        responseCode = new HashMap();
        responseCode.put("00", new ResponseCodeEntity("00", "Successful"));
        responseCode.put("01", new ResponseCodeEntity("01", "Refer to issuer"));
        responseCode.put("02", new ResponseCodeEntity("02", ""));
        responseCode.put("03", new ResponseCodeEntity("03", "Merchant  is not recognized"));
        responseCode.put("04", new ResponseCodeEntity("04", "Capture card / pick up card"));
        responseCode.put("05", new ResponseCodeEntity("05", "Do not honor"));
        responseCode.put("06", new ResponseCodeEntity("01", "System Error"));
        responseCode.put("07", new ResponseCodeEntity("01", ""));
        responseCode.put("08", new ResponseCodeEntity("01", ""));
        responseCode.put("09", new ResponseCodeEntity("01", ""));
        responseCode.put("10", new ResponseCodeEntity("01", ""));
        responseCode.put("11", new ResponseCodeEntity("01", ""));
        responseCode.put("12", new ResponseCodeEntity("01", "Unknown journal code in liability transaction!"));
        responseCode.put("13", new ResponseCodeEntity("13", "Rupiah tagihan tidak sesuai dengan hasil inquiry"));
        responseCode.put("14", new ResponseCodeEntity("01", "Invalid card or card not found"));
        responseCode.put("15", new ResponseCodeEntity("01", "No such issuer"));
        responseCode.put("16", new ResponseCodeEntity("01", ""));
        responseCode.put("17", new ResponseCodeEntity("01", ""));
        responseCode.put("18", new ResponseCodeEntity("01", ""));
        responseCode.put("19", new ResponseCodeEntity("01", ""));
        responseCode.put("20", new ResponseCodeEntity("01", "Invalid response"));
        responseCode.put("21", new ResponseCodeEntity("01", ""));
        responseCode.put("22", new ResponseCodeEntity("01", ""));
        responseCode.put("23", new ResponseCodeEntity("01", ""));
        responseCode.put("24", new ResponseCodeEntity("01", ""));
        responseCode.put("25", new ResponseCodeEntity("01", ""));
        responseCode.put("26", new ResponseCodeEntity("01", ""));
        responseCode.put("27", new ResponseCodeEntity("01", ""));
        responseCode.put("28", new ResponseCodeEntity("01", ""));
        responseCode.put("29", new ResponseCodeEntity("01", ""));
        responseCode.put("30", new ResponseCodeEntity("30", "Format salah"));
        responseCode.put("31", new ResponseCodeEntity("01", "Destination bank  is not registered"));
        responseCode.put("32", new ResponseCodeEntity("01", ""));
        responseCode.put("33", new ResponseCodeEntity("01", "Expired card"));
        responseCode.put("34", new ResponseCodeEntity("01", ""));
        responseCode.put("36", new ResponseCodeEntity("01", "Restricted card"));
        responseCode.put("35", new ResponseCodeEntity("01", ""));
        responseCode.put("38", new ResponseCodeEntity("01", "Allowable PIN tries exceeded"));
        responseCode.put("39", new ResponseCodeEntity("01", "No credit card"));
        responseCode.put("40", new ResponseCodeEntity("01", "Request function not supported"));
        responseCode.put("41", new ResponseCodeEntity("01", "Lost card"));
        responseCode.put("42", new ResponseCodeEntity("01", ""));
        responseCode.put("43", new ResponseCodeEntity("01", "Stolen card"));
        responseCode.put("44", new ResponseCodeEntity("01", ""));
        responseCode.put("45", new ResponseCodeEntity("01", ""));
        responseCode.put("46", new ResponseCodeEntity("01", ""));
        responseCode.put("47", new ResponseCodeEntity("01", ""));
        responseCode.put("48", new ResponseCodeEntity("01", ""));
        responseCode.put("49", new ResponseCodeEntity("01", ""));
        responseCode.put("50", new ResponseCodeEntity("01", ""));
        responseCode.put("51", new ResponseCodeEntity("01", "Insufficient fund"));
        responseCode.put("52", new ResponseCodeEntity("01", "No chequing account"));
        responseCode.put("53", new ResponseCodeEntity("01", "Invalid from saving account"));
        responseCode.put("54", new ResponseCodeEntity("01", "Expired card"));
        responseCode.put("55", new ResponseCodeEntity("01", "Invalid PIN"));
        responseCode.put("56", new ResponseCodeEntity("01", ""));
        responseCode.put("57", new ResponseCodeEntity("01", "Transaction not permitted to cardholder"));
        responseCode.put("58", new ResponseCodeEntity("01", "Transaction not permitted to terminal"));
        responseCode.put("59", new ResponseCodeEntity("01", ""));
        responseCode.put("60", new ResponseCodeEntity("01", ""));
        responseCode.put("61", new ResponseCodeEntity("01", "Exceed withdrawl / transfer amount limit"));
        responseCode.put("62", new ResponseCodeEntity("01", "Sender Card is already registered"));
        responseCode.put("63", new ResponseCodeEntity("01", "Security violation"));
        responseCode.put("64", new ResponseCodeEntity("01", ""));
        responseCode.put("65", new ResponseCodeEntity("01", "Exceed withdrawl / transfer frequency limit"));
        responseCode.put("66", new ResponseCodeEntity("01", ""));
        responseCode.put("67", new ResponseCodeEntity("01", "Hard capture"));
        responseCode.put("68", new ResponseCodeEntity("01", "Response received to late"));
        responseCode.put("69", new ResponseCodeEntity("01", "Reserved for ISO use"));
        responseCode.put("70", new ResponseCodeEntity("01", "Reserved for ISO use"));
        responseCode.put("71", new ResponseCodeEntity("01", "Reserved for ISO use"));
        responseCode.put("72", new ResponseCodeEntity("01", "Reserved for ISO use"));
        responseCode.put("73", new ResponseCodeEntity("01", "Reserved for ISO use"));
        responseCode.put("74", new ResponseCodeEntity("01", "Reserved for ISO use"));
        responseCode.put("75", new ResponseCodeEntity("01", "Invalid PIN last retry"));
        responseCode.put("76", new ResponseCodeEntity("01", "Invalid to account"));
        responseCode.put("77", new ResponseCodeEntity("01", "Invalid account"));
        responseCode.put("78", new ResponseCodeEntity("01", "Closed account"));
        responseCode.put("79", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("80", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("81", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("82", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("83", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("84", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("85", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("86", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("87", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("88", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("89", new ResponseCodeEntity("01", "Reserved for private use"));
        responseCode.put("90", new ResponseCodeEntity("01", ""));
        responseCode.put("91", new ResponseCodeEntity("01", "issuer or switch is inoperative"));
        responseCode.put("92", new ResponseCodeEntity("01", "Unable to route transaction"));
        responseCode.put("93", new ResponseCodeEntity("01", ""));
        responseCode.put("94", new ResponseCodeEntity("01", "Duplicate transmision / request message"));
        responseCode.put("95", new ResponseCodeEntity("01", ""));
        responseCode.put("96", new ResponseCodeEntity("01", "System malfunction / system error"));
        responseCode.put("97", new ResponseCodeEntity("01", "Reserved for national use"));
        responseCode.put("98", new ResponseCodeEntity("01", "Reserved for national use"));
        responseCode.put("99", new ResponseCodeEntity("01", "Reserved for national use"));
        responseCode.put("0000", new ResponseCodeEntity("00", "Successful"));
        responseCode.put("0069", new ResponseCodeEntity("69", "Waiting"));
        responseCode.put("9999", new ResponseCodeEntity("68", "Timeout"));
        responseCode.put("300", new ResponseCodeEntity("01", "Error"));
        responseCode.put("404", new ResponseCodeEntity("53", "Invalid from saving account"));
        responseCode.put("210", new ResponseCodeEntity("94", "Duplicate request message"));
        responseCode.put("0", new ResponseCodeEntity("00", "Successful"));
        responseCode.put("1", new ResponseCodeEntity("01", "failed"));
        responseCode.put("2", new ResponseCodeEntity("02", "failed"));
        
        //------------------ MKM -----------------------
        responseCode.put("0001", new ResponseCodeEntity("01", ""));
        responseCode.put("0002", new ResponseCodeEntity("02", ""));
        responseCode.put("0003", new ResponseCodeEntity("03", ""));
        responseCode.put("0004", new ResponseCodeEntity("04", ""));
        responseCode.put("0005", new ResponseCodeEntity("05", "Other"));
        responseCode.put("0006", new ResponseCodeEntity("06", "Other"));
        responseCode.put("0011", new ResponseCodeEntity("11", "Need to sign-on"));
        responseCode.put("0012", new ResponseCodeEntity("12", "Settlement had been done"));
        responseCode.put("0013", new ResponseCodeEntity("13", "Invalid transaction amount"));
        responseCode.put("0014", new ResponseCodeEntity("14", "PAYMENT NBR NOT FOUND/ Kode Iuran tidak valid"));
        responseCode.put("0015", new ResponseCodeEntity("15", ""));
        responseCode.put("0021", new ResponseCodeEntity("21", ""));
        responseCode.put("0022", new ResponseCodeEntity("22", ""));
        responseCode.put("0023", new ResponseCodeEntity("23", ""));
        responseCode.put("0025", new ResponseCodeEntity("25", ""));
        responseCode.put("0026", new ResponseCodeEntity("26", ""));
        responseCode.put("0030", new ResponseCodeEntity("30", "Invalid message"));
        responseCode.put("0031", new ResponseCodeEntity("31", "Unregistered bank code"));
        responseCode.put("0032", new ResponseCodeEntity("32", "Unregistered switching"));
        responseCode.put("0033", new ResponseCodeEntity("33", "Unregistered product"));
        responseCode.put("0040", new ResponseCodeEntity("40", ""));
        responseCode.put("0063", new ResponseCodeEntity("63", "No payment"));
        responseCode.put("0068", new ResponseCodeEntity("68", "Timeout"));
        responseCode.put("0076", new ResponseCodeEntity("76", ""));
        responseCode.put("0077", new ResponseCodeEntity("77", ""));
        responseCode.put("0078", new ResponseCodeEntity("78", ""));
        responseCode.put("0079", new ResponseCodeEntity("79", ""));
        responseCode.put("0080", new ResponseCodeEntity("80", ""));
        responseCode.put("0081", new ResponseCodeEntity("81", ""));
        responseCode.put("0083", new ResponseCodeEntity("83", "Kode Iuran untuk program JKK|JHT|JKM tidak dapat diproses karena masih terdapat program yang sama belum dibayar."));
        responseCode.put("0088", new ResponseCodeEntity("88", "Bills already paid"));
        responseCode.put("0089", new ResponseCodeEntity("89", "Current bill is not available"));
        responseCode.put("0090", new ResponseCodeEntity("90", "Cut-off is in progress"));
        responseCode.put("0091", new ResponseCodeEntity("91", ""));
        responseCode.put("0092", new ResponseCodeEntity("92", "Switcher receipt reference number is not available"));
        responseCode.put("0093", new ResponseCodeEntity("93", "Invalid switcher reference number"));
        responseCode.put("0094", new ResponseCodeEntity("94", "Reversal had been done"));
        responseCode.put("0095", new ResponseCodeEntity("95", ""));
        responseCode.put("0096", new ResponseCodeEntity("96", ""));
        responseCode.put("0097", new ResponseCodeEntity("97", "Switching ID / Bank Code is not identical with inquiry"));
        responseCode.put("0098", new ResponseCodeEntity("98", "PLN ref number is not valid"));
        responseCode.put("0104", new ResponseCodeEntity("04", "Gagal generate SessionId"));
        responseCode.put("0105", new ResponseCodeEntity("05", "Database MKM bermasalah"));
        responseCode.put("0112", new ResponseCodeEntity("12", "Parameter payment tidak sesuai dengan hasil inquiry"));
        responseCode.put("0113", new ResponseCodeEntity("13", "Rupiah tagihan tidak sesuai dengan hasil inquiry"));
        responseCode.put("0115", new ResponseCodeEntity("15", "Parameter tidak lengkap"));
        responseCode.put("0116", new ResponseCodeEntity("16", "Gagal saat inquiry, tidak dapat melakukan payment"));
        responseCode.put("0163", new ResponseCodeEntity("63", "Transaksi sebelumnya gagal, silahkan lakukan pembayaran kembali"));
        responseCode.put("0167", new ResponseCodeEntity("67", "Parameter input tidak lengkap"));
        responseCode.put("0168", new ResponseCodeEntity("68", "Connection timeout MKM - Biller"));
        responseCode.put("0169", new ResponseCodeEntity("69", "Kode action tidak dikenal"));
        responseCode.put("0170", new ResponseCodeEntity("70", "Kode produk tidak dikenal"));
        responseCode.put("0171", new ResponseCodeEntity("71", "Client ID tidak terdaftar"));
        responseCode.put("0172", new ResponseCodeEntity("72", "Quota tidak mencukupi"));
        responseCode.put("0173", new ResponseCodeEntity("73", "Akses untuk produk ini diblok"));
        responseCode.put("0174", new ResponseCodeEntity("74", "Akses ke H2H diblok"));
        responseCode.put("0180", new ResponseCodeEntity("80", "Server sedang cut-off, tidak dapat payment saat ini"));
        responseCode.put("0181", new ResponseCodeEntity("81", "Tidak dapat melakukan advice untuk nomor pelanggan ini"));
        responseCode.put("0184", new ResponseCodeEntity("84", "Proses pelunasan sedang berlangsung"));
        responseCode.put("0185", new ResponseCodeEntity("85", "Ada tagihan tetapi pernah tercatat lunas di MKM"));
        responseCode.put("0186", new ResponseCodeEntity("86", "Pembayaran belum dilakukan"));
        responseCode.put("0187", new ResponseCodeEntity("87", "Pembayaran sudah dilakukan sebelumnya"));
        responseCode.put("0188", new ResponseCodeEntity("88", "Tagihan sudah tercatat lunas di MKM"));
        responseCode.put("0189", new ResponseCodeEntity("89", "Detail tagihan untuk periode [YYYYmm] tidak ditemukan"));
        responseCode.put("0190", new ResponseCodeEntity("90", "Data pelanggan tidak ditemukan"));
        responseCode.put("0191", new ResponseCodeEntity("91", "Tanggal transaksi berbeda dengan tanggal inquiry"));
        responseCode.put("0192", new ResponseCodeEntity("92", "SessionId tidak ditemukan"));
        responseCode.put("0193", new ResponseCodeEntity("93", "Transaksi bermasalah, proses pembatalan sedang berlangsung"));
        responseCode.put("0194", new ResponseCodeEntity("94", "Transaksi bermasalah dan telah dibatalkan secara otomatis"));
        responseCode.put("0195", new ResponseCodeEntity("95", "Transaksi bermasalah, status pembatalan tidak diketahui"));
        responseCode.put("0198", new ResponseCodeEntity("98", "Response biller error"));
        responseCode.put("0199", new ResponseCodeEntity("99", "Request transaksi kembar"));
    }

    /**
     * @return the responseCode
     */
    public HashMap getResponseCode() {
        return responseCode;
    }

    public String getResponseCode(String errcode) {

        if (responseCode.get(errcode) == null) {
            return "99";
        } else {
            if (errcode.length() != 2) {
                return ((ResponseCodeEntity) responseCode.get(errcode)).getCode();
            } else {
                return errcode;
            }
        }

    }
}
