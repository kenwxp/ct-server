package com.cloudtimes.partner.config;


import org.yaml.snakeyaml.Yaml;


import java.io.*;
import java.util.Map;

public class PartnerConfig {
    public static Map<String, Map<String, String>> configs;

    public static Map<String, String> getHikConfig() {
        if (configs == null) {
            readFromFile();
        }
        return configs.get("hik");
    }

    public static Map<String, String> getWeiXinConfig() {
        if (configs == null) {
            readFromFile();
        }
        return configs.get("wx");
    }

    private static void readFromFile() {
//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("partner_conf.yaml");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(PartnerConfig.class.getClassLoader().getResource("partner_conf.yaml").getPath());
            Yaml yaml = new Yaml();
            configs = yaml.loadAs(inputStream, Map.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
