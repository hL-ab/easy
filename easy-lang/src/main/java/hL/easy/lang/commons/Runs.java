package hL.easy.lang.commons;

import hL.easy.lang.model.Kelue;
import hL.easy.lang.model.Pack;
import hL.easy.lang.simulation.commons.lang.ArrayUtils;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class Runs {

    private static final Logger logger = LoggerFactory.getLogger(Runs.class);

    public static Kelue<String, String> shell(Writer success, Writer error,
        Pack<Process> processPack,
        boolean needWait, long timeOut, boolean ifKillWhenTimeOut, Charset charset,
        String... shells) {
        if (timeOut <= 0) {
            timeOut = 1;
        }
        if (charset == null) {
            charset = Charset.forName("UTF-8");
        }
        if (!ArrayUtils.isEmpty(shells)) {
            Process process = null;
            try {
                String shell = String.join(";", shells);
                logger.info("Start command execution:{}", shell);
                process = Runtime.getRuntime().exec(shell);
                if (processPack != null) {
                    processPack.put(process);
                }
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                if (needWait) {
                    String line;
                    String str = "";
                    boolean flag = true;
                    LineNumberReader br = new LineNumberReader(
                        new InputStreamReader(process.getInputStream(), charset));
                    while ((line = br.readLine()) != null) {
                        sb1.append(line).append(";");
                        if (success != null) {
                            success.write(line);
                            success.write("\r\n");
                            success.flush();
                        }
                        if (!"".equals(line)) {
                            str = line;
                        }
                    }
                    String str1 = str.substring(str.indexOf(':') + 1);
                    String str2 = str1.replaceAll("\\s*", "");
                    if ("0".equals(str2)) {
                        flag = false;
                    }
                    br = new LineNumberReader(new InputStreamReader(
                        process.getErrorStream(), "utf-8"));
                    while (flag && (line = br.readLine()) != null) {
                        sb2.append(line).append(";");
                        if (error != null) {
                            error.write(line);
                            error.write("\r\n");
                            error.flush();
                        }
                    }
                    if (process.waitFor(timeOut, TimeUnit.SECONDS)) {
                    } else if (ifKillWhenTimeOut) {
                        if (process.isAlive()) {
                            process.destroyForcibly();
                            sb2.append("Time out!");
                        }
                    } else {
                        sb1.append("Time out!");
                    }
                }
                return new Kelue<>(sb1.toString(), sb2.toString());
            } catch (IOException | InterruptedException e) {
                logger.error(e.getMessage(), e);
                if (process != null && process.isAlive()) {
                    process.destroyForcibly();
                }
            }
        }
        return new Kelue<>("", "");
    }

    public static Kelue<String, String> shell(boolean needWait, long timeOut,
        boolean ifKillWhenTimeOut, Charset charset, String... shells) {
        return shell(null, null, null, needWait, timeOut, ifKillWhenTimeOut, charset, shells);
    }
}
