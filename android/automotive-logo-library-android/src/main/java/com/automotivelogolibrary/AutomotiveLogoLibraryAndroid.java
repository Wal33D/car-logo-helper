package com.automotivelogolibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Android helper for mapping car manufacturers to their logo assets and decoding VINs.
 *
 * Logos are bundled under the module's assets directory at: assets/logos/
 * You can reference them via the Android asset URI scheme: file:///android_asset/logos/<filename>
 */
public final class AutomotiveLogoLibraryAndroid {

    private static final Map<String, String> LOGO_MAP = new HashMap<>();
    private static final Map<String, String> VIN_WMI_MAP = new HashMap<>(); // World Manufacturer Identifier

    static {
        // Initialize logo filename mappings
        LOGO_MAP.put("MERCEDES-BENZ", "logo_mercedes_benz.png");
        LOGO_MAP.put("MERCEDES BENZ", "logo_mercedes_benz.png");
        LOGO_MAP.put("MERCEDES", "logo_mercedes_benz.png");
        LOGO_MAP.put("BMW", "logo_bmw.png");
        LOGO_MAP.put("AUDI", "logo_audi.png");
        LOGO_MAP.put("TOYOTA", "logo_toyota.png");
        LOGO_MAP.put("HONDA", "logo_honda.png");
        LOGO_MAP.put("FORD", "logo_ford.png");
        LOGO_MAP.put("CHEVROLET", "logo_chevrolet.png");
        LOGO_MAP.put("CHEVY", "logo_chevrolet.png");
        LOGO_MAP.put("VOLKSWAGEN", "logo_volkswagen.png");
        LOGO_MAP.put("VW", "logo_volkswagen.png");
        LOGO_MAP.put("NISSAN", "logo_nissan.png");
        LOGO_MAP.put("HYUNDAI", "logo_hyundai.png");
        LOGO_MAP.put("MAZDA", "logo_mazda.png");
        LOGO_MAP.put("SUBARU", "logo_subaru.png");
        LOGO_MAP.put("PORSCHE", "logo_porsche.png");
        LOGO_MAP.put("TESLA", "logo_tesla.png");
        LOGO_MAP.put("LEXUS", "logo_lexus.png");
        LOGO_MAP.put("ACURA", "logo_acura.png");
        LOGO_MAP.put("INFINITI", "logo_infiniti.png");
        LOGO_MAP.put("JAGUAR", "logo_jaguar.png");
        LOGO_MAP.put("LAND ROVER", "logo_land_rover.png");
        LOGO_MAP.put("LAND-ROVER", "logo_land_rover.png");
        LOGO_MAP.put("VOLVO", "logo_volvo.png");
        LOGO_MAP.put("MITSUBISHI", "logo_mitsubishi.png");
        LOGO_MAP.put("KIA", "logo_kia.png");
        LOGO_MAP.put("GENESIS", "logo_genesis.png");
        LOGO_MAP.put("GMC", "logo_gmc.png");
        LOGO_MAP.put("JEEP", "logo_jeep.png");
        LOGO_MAP.put("RAM", "logo_ram.png");
        LOGO_MAP.put("DODGE", "logo_dodge.png");
        LOGO_MAP.put("CHRYSLER", "logo_chrysler.png");
        LOGO_MAP.put("BUICK", "logo_buick.png");
        LOGO_MAP.put("CADILLAC", "logo_cadillac.png");
        LOGO_MAP.put("LINCOLN", "logo_lincoln.png");
        LOGO_MAP.put("MINI", "logo_mini.png");
        LOGO_MAP.put("FIAT", "logo_fiat.png");
        LOGO_MAP.put("ALFA ROMEO", "logo_alfa_romeo.png");
        LOGO_MAP.put("MASERATI", "logo_maserati.png");
        LOGO_MAP.put("FERRARI", "logo_ferrari.png");
        LOGO_MAP.put("LAMBORGHINI", "logo_lamborghini.png");
        LOGO_MAP.put("BENTLEY", "logo_bentley.png");
        LOGO_MAP.put("ROLLS ROYCE", "logo_rolls_royce.png");
        LOGO_MAP.put("ROLLS-ROYCE", "logo_rolls_royce.png");
        LOGO_MAP.put("ASTON MARTIN", "logo_aston_martin.png");
        LOGO_MAP.put("PEUGEOT", "logo_peugeot.png");
        LOGO_MAP.put("RENAULT", "logo_renault.png");
        LOGO_MAP.put("CITROEN", "logo_citroen.png");
        LOGO_MAP.put("SEAT", "logo_seat.png");
        LOGO_MAP.put("SKODA", "logo_skoda.png");
        LOGO_MAP.put("OPEL", "logo_opel.png");

        // Initialize VIN WMI (World Manufacturer Identifier) mappings
        // First 3 characters of VIN identify the manufacturer
        VIN_WMI_MAP.put("1FA", "FORD");
        VIN_WMI_MAP.put("1FB", "FORD");
        VIN_WMI_MAP.put("1FC", "FORD");
        VIN_WMI_MAP.put("1FD", "FORD");
        VIN_WMI_MAP.put("1FM", "FORD");
        VIN_WMI_MAP.put("1FT", "FORD");
        VIN_WMI_MAP.put("1G1", "CHEVROLET");
        VIN_WMI_MAP.put("1G2", "PONTIAC");
        VIN_WMI_MAP.put("1G3", "OLDSMOBILE");
        VIN_WMI_MAP.put("1G4", "BUICK");
        VIN_WMI_MAP.put("1G6", "CADILLAC");
        VIN_WMI_MAP.put("1GC", "CHEVROLET");
        VIN_WMI_MAP.put("1GM", "PONTIAC");
        VIN_WMI_MAP.put("1HG", "HONDA");
        VIN_WMI_MAP.put("1L1", "LINCOLN");
        VIN_WMI_MAP.put("1ME", "MERCURY");
        VIN_WMI_MAP.put("1VW", "VOLKSWAGEN");
        VIN_WMI_MAP.put("1YV", "MAZDA");
        VIN_WMI_MAP.put("2FA", "FORD");
        VIN_WMI_MAP.put("2FB", "FORD");
        VIN_WMI_MAP.put("2FC", "FORD");
        VIN_WMI_MAP.put("2FM", "FORD");
        VIN_WMI_MAP.put("2FT", "FORD");
        VIN_WMI_MAP.put("2G1", "CHEVROLET");
        VIN_WMI_MAP.put("2G2", "PONTIAC");
        VIN_WMI_MAP.put("2HG", "HONDA");
        VIN_WMI_MAP.put("2HK", "HONDA");
        VIN_WMI_MAP.put("2HM", "HONDA");
        VIN_WMI_MAP.put("3FA", "FORD");
        VIN_WMI_MAP.put("3FB", "FORD");
        VIN_WMI_MAP.put("3FC", "FORD");
        VIN_WMI_MAP.put("3FE", "FORD");
        VIN_WMI_MAP.put("3G1", "CHEVROLET");
        VIN_WMI_MAP.put("3VW", "VOLKSWAGEN");
        VIN_WMI_MAP.put("4F2", "MAZDA");
        VIN_WMI_MAP.put("4M2", "MERCURY");
        VIN_WMI_MAP.put("4S3", "SUBARU");
        VIN_WMI_MAP.put("4S4", "SUBARU");
        VIN_WMI_MAP.put("4T1", "TOYOTA");
        VIN_WMI_MAP.put("4T3", "TOYOTA");
        VIN_WMI_MAP.put("4US", "BMW");
        VIN_WMI_MAP.put("5FN", "HONDA");
        VIN_WMI_MAP.put("5L1", "LINCOLN");
        VIN_WMI_MAP.put("5LM", "LINCOLN");
        VIN_WMI_MAP.put("5N1", "NISSAN");
        VIN_WMI_MAP.put("5NP", "HYUNDAI");
        VIN_WMI_MAP.put("5TB", "TOYOTA");
        VIN_WMI_MAP.put("5TD", "TOYOTA");
        VIN_WMI_MAP.put("5TF", "TOYOTA");
        VIN_WMI_MAP.put("5UX", "BMW");
        VIN_WMI_MAP.put("5YJ", "TESLA");
        VIN_WMI_MAP.put("JA3", "MITSUBISHI");
        VIN_WMI_MAP.put("JA4", "MITSUBISHI");
        VIN_WMI_MAP.put("JA7", "MITSUBISHI");
        VIN_WMI_MAP.put("JF1", "SUBARU");
        VIN_WMI_MAP.put("JF2", "SUBARU");
        VIN_WMI_MAP.put("JHM", "HONDA");
        VIN_WMI_MAP.put("JM1", "MAZDA");
        VIN_WMI_MAP.put("JM3", "MAZDA");
        VIN_WMI_MAP.put("JN1", "NISSAN");
        VIN_WMI_MAP.put("JN8", "NISSAN");
        VIN_WMI_MAP.put("JT2", "TOYOTA");
        VIN_WMI_MAP.put("JT3", "TOYOTA");
        VIN_WMI_MAP.put("JTD", "TOYOTA");
        VIN_WMI_MAP.put("JTE", "TOYOTA");
        VIN_WMI_MAP.put("JTH", "LEXUS");
        VIN_WMI_MAP.put("JTJ", "LEXUS");
        VIN_WMI_MAP.put("JTK", "LEXUS");
        VIN_WMI_MAP.put("JTL", "LEXUS");
        VIN_WMI_MAP.put("JTM", "TOYOTA");
        VIN_WMI_MAP.put("JTN", "TOYOTA");
        VIN_WMI_MAP.put("KL1", "CHEVROLET");
        VIN_WMI_MAP.put("KL2", "DAEWOO");
        VIN_WMI_MAP.put("KL4", "BUICK");
        VIN_WMI_MAP.put("KM8", "HYUNDAI");
        VIN_WMI_MAP.put("KMF", "HYUNDAI");
        VIN_WMI_MAP.put("KMH", "HYUNDAI");
        VIN_WMI_MAP.put("KMJ", "GENESIS");
        VIN_WMI_MAP.put("KMX", "GENESIS");
        VIN_WMI_MAP.put("KNA", "KIA");
        VIN_WMI_MAP.put("KND", "KIA");
        VIN_WMI_MAP.put("SAJ", "JAGUAR");
        VIN_WMI_MAP.put("SAL", "LAND ROVER");
        VIN_WMI_MAP.put("SAR", "ROVER");
        VIN_WMI_MAP.put("SCA", "ROLLS ROYCE");
        VIN_WMI_MAP.put("SCB", "BENTLEY");
        VIN_WMI_MAP.put("SCC", "LOTUS");
        VIN_WMI_MAP.put("SCE", "DELOREAN");
        VIN_WMI_MAP.put("SCF", "ASTON MARTIN");
        VIN_WMI_MAP.put("TRU", "AUDI");
        VIN_WMI_MAP.put("VF1", "RENAULT");
        VIN_WMI_MAP.put("VF2", "RENAULT");
        VIN_WMI_MAP.put("VF3", "PEUGEOT");
        VIN_WMI_MAP.put("VF6", "RENAULT");
        VIN_WMI_MAP.put("VF7", "CITROEN");
        VIN_WMI_MAP.put("VF8", "MATRA");
        VIN_WMI_MAP.put("VS6", "FORD");
        VIN_WMI_MAP.put("VSS", "SEAT");
        VIN_WMI_MAP.put("VV1", "RENAULT");
        VIN_WMI_MAP.put("WAU", "AUDI");
        VIN_WMI_MAP.put("WAP", "ALPINA");
        VIN_WMI_MAP.put("WBA", "BMW");
        VIN_WMI_MAP.put("WBB", "BMW");
        VIN_WMI_MAP.put("WBS", "BMW");
        VIN_WMI_MAP.put("WBW", "BMW");
        VIN_WMI_MAP.put("WBX", "BMW");
        VIN_WMI_MAP.put("WBY", "BMW");
        VIN_WMI_MAP.put("WDA", "DAIMLER");
        VIN_WMI_MAP.put("WDB", "MERCEDES-BENZ");
        VIN_WMI_MAP.put("WDC", "MERCEDES-BENZ");
        VIN_WMI_MAP.put("WDD", "MERCEDES-BENZ");
        VIN_WMI_MAP.put("WDF", "MERCEDES-BENZ");
        VIN_WMI_MAP.put("WMW", "MINI");
        VIN_WMI_MAP.put("W0L", "OPEL");
        VIN_WMI_MAP.put("W0V", "OPEL");
        VIN_WMI_MAP.put("WP0", "PORSCHE");
        VIN_WMI_MAP.put("WP1", "PORSCHE");
        VIN_WMI_MAP.put("WUA", "AUDI");
        VIN_WMI_MAP.put("WVG", "VOLKSWAGEN");
        VIN_WMI_MAP.put("WVW", "VOLKSWAGEN");
        VIN_WMI_MAP.put("WV1", "VOLKSWAGEN");
        VIN_WMI_MAP.put("WV2", "VOLKSWAGEN");
        VIN_WMI_MAP.put("WV3", "VOLKSWAGEN");
        VIN_WMI_MAP.put("XTA", "LADA");
        VIN_WMI_MAP.put("YK1", "SAAB");
        VIN_WMI_MAP.put("YS2", "SCANIA");
        VIN_WMI_MAP.put("YS3", "SAAB");
        VIN_WMI_MAP.put("YTN", "SAAB");
        VIN_WMI_MAP.put("YV1", "VOLVO");
        VIN_WMI_MAP.put("YV2", "VOLVO");
        VIN_WMI_MAP.put("YV3", "VOLVO");
        VIN_WMI_MAP.put("YV4", "VOLVO");
        VIN_WMI_MAP.put("YV5", "VOLVO");
        VIN_WMI_MAP.put("ZA9", "LAMBORGHINI");
        VIN_WMI_MAP.put("ZAM", "MASERATI");
        VIN_WMI_MAP.put("ZAP", "PIAGGIO");
        VIN_WMI_MAP.put("ZAR", "ALFA ROMEO");
        VIN_WMI_MAP.put("ZCF", "IVECO");
        VIN_WMI_MAP.put("ZFA", "FIAT");
        VIN_WMI_MAP.put("ZFC", "FIAT");
        VIN_WMI_MAP.put("ZFF", "FERRARI");
        VIN_WMI_MAP.put("ZGA", "IVM");
        VIN_WMI_MAP.put("ZGU", "MASERATI");
        VIN_WMI_MAP.put("ZHW", "LAMBORGHINI");
        VIN_WMI_MAP.put("ZLA", "LANCIA");
        VIN_WMI_MAP.put("ZOM", "OM");

        // Two-letter codes (less specific)
        VIN_WMI_MAP.put("1C", "CHRYSLER");
        VIN_WMI_MAP.put("1D", "DODGE");
        VIN_WMI_MAP.put("1F", "FORD");
        VIN_WMI_MAP.put("1G", "GENERAL MOTORS");
        VIN_WMI_MAP.put("1H", "HONDA");
        VIN_WMI_MAP.put("1J", "JEEP");
        VIN_WMI_MAP.put("1L", "LINCOLN");
        VIN_WMI_MAP.put("1M", "MERCURY");
        VIN_WMI_MAP.put("1N", "NISSAN");
        VIN_WMI_MAP.put("1V", "VOLKSWAGEN");
        VIN_WMI_MAP.put("1Y", "MAZDA");
        VIN_WMI_MAP.put("2C", "CHRYSLER");
        VIN_WMI_MAP.put("2D", "DODGE");
        VIN_WMI_MAP.put("2F", "FORD");
        VIN_WMI_MAP.put("2G", "GENERAL MOTORS");
        VIN_WMI_MAP.put("2H", "HONDA");
        VIN_WMI_MAP.put("2M", "MERCURY");
        VIN_WMI_MAP.put("2T", "TOYOTA");
        VIN_WMI_MAP.put("3C", "CHRYSLER");
        VIN_WMI_MAP.put("3D", "DODGE");
        VIN_WMI_MAP.put("3F", "FORD");
        VIN_WMI_MAP.put("3G", "GENERAL MOTORS");
        VIN_WMI_MAP.put("3H", "HONDA");
        VIN_WMI_MAP.put("3N", "NISSAN");
        VIN_WMI_MAP.put("3V", "VOLKSWAGEN");
        VIN_WMI_MAP.put("4F", "MAZDA");
        VIN_WMI_MAP.put("4J", "MERCEDES-BENZ");
        VIN_WMI_MAP.put("4M", "MERCURY");
        VIN_WMI_MAP.put("4S", "SUBARU");
        VIN_WMI_MAP.put("4T", "TOYOTA");
        VIN_WMI_MAP.put("4U", "BMW");
        VIN_WMI_MAP.put("5F", "HONDA");
        VIN_WMI_MAP.put("5L", "LINCOLN");
        VIN_WMI_MAP.put("5N", "NISSAN");
        VIN_WMI_MAP.put("5T", "TOYOTA");
        VIN_WMI_MAP.put("5U", "BMW");
        VIN_WMI_MAP.put("5Y", "TESLA");
    }

    private AutomotiveLogoLibraryAndroid() {
        // no instances
    }

    public static String getManufacturerFromVIN(String vin) {
        if (vin == null || vin.length() < 3) {
            return null;
        }

        String upperVin = vin.toUpperCase().trim();

        String wmi = upperVin.substring(0, Math.min(3, upperVin.length()));
        String manufacturer = VIN_WMI_MAP.get(wmi);

        if (manufacturer == null && upperVin.length() >= 2) {
            String prefix = upperVin.substring(0, 2);
            manufacturer = VIN_WMI_MAP.get(prefix);
        }

        return manufacturer;
    }

    public static String getLogoFilename(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        String manufacturer = input;
        if (input.length() >= 3 && input.length() <= 17) {
            String vinManufacturer = getManufacturerFromVIN(input);
            if (vinManufacturer != null) {
                manufacturer = vinManufacturer;
            }
        }

        String upperMake = manufacturer.toUpperCase().trim();
        String logoFile = LOGO_MAP.get(upperMake);

        if (logoFile == null) {
            String cleanedMake = upperMake.replaceAll("[-\\s]", "");
            for (Map.Entry<String, String> entry : LOGO_MAP.entrySet()) {
                String cleanedKey = entry.getKey().replaceAll("[-\\s]", "");
                if (cleanedMake.contains(cleanedKey) || cleanedKey.contains(cleanedMake) ||
                        upperMake.contains(entry.getKey()) || entry.getKey().contains(upperMake)) {
                    logoFile = entry.getValue();
                    break;
                }
            }
        }

        return logoFile;
    }

    public static boolean hasLogo(Context context, String input) {
        String filename = getLogoFilename(input);
        if (filename == null) return false;
        try (InputStream ignored = context.getAssets().open("logos/" + filename)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static InputStream openLogoStream(Context context, String input) {
        String filename = getLogoFilename(input);
        if (filename == null) return null;
        try {
            return context.getAssets().open("logos/" + filename);
        } catch (IOException e) {
            return null;
        }
    }

    public static Drawable getLogoDrawable(Context context, String input) {
        InputStream is = openLogoStream(context, input);
        if (is == null) return null;
        try {
            return Drawable.createFromStream(is, null);
        } finally {
            try { is.close(); } catch (IOException ignored) {}
        }
    }

    public static String getLogoAssetPath(String input) {
        String filename = getLogoFilename(input);
        return filename == null ? null : ("logos/" + filename);
    }

    public static Uri getLogoAssetUri(String input) {
        String filename = getLogoFilename(input);
        return filename == null ? null : Uri.parse("file:///android_asset/logos/" + filename);
    }

    public static String[] getSupportedManufacturers() {
        return LOGO_MAP.keySet().toArray(new String[0]);
    }
}

