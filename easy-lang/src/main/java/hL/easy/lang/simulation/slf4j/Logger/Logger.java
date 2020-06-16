package hL.easy.lang.simulation.slf4j.Logger;

import org.slf4j.Marker;

/**
 * @author @HL
 * @since 0.1.001
 */
public class Logger {

    private final org.slf4j.Logger logger;

    public Logger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    public Logger(Class<?> clazz) {
        this((org.slf4j.Logger) null);
        //TODO
    }

    public boolean isTraceEnabled() {
        if (logger != null) {
            return logger.isTraceEnabled();
        } else {
            //TODO
            return true;
        }
    }

    public void trace(String msg) {
        if (logger != null) {
            logger.trace(msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void trace(String format, Object arg) {
        if (logger != null) {
            logger.trace(format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void trace(String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.trace(format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void trace(String format, Object... arguments) {
        if (logger != null) {
            logger.trace(format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void trace(String msg, Throwable t) {
        if (logger != null) {
            logger.trace(msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }

    public boolean isTraceEnabled(Marker marker) {
        if (logger != null) {
            return logger.isTraceEnabled(marker);
        } else {
            //TODO
            return true;
        }
    }

    public void trace(Marker marker, String msg) {
        if (logger != null) {
            logger.trace(marker, msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void trace(Marker marker, String format, Object arg) {
        if (logger != null) {
            logger.trace(marker, format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.trace(marker, format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void trace(Marker marker, String format, Object... arguments) {
        if (logger != null) {
            logger.trace(marker, format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void trace(Marker marker, String msg, Throwable t) {
        if (logger != null) {
            logger.trace(marker, msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }


    public boolean isDebugEnabled() {
        if (logger != null) {
            return logger.isDebugEnabled();
        } else {
            //TODO
            return true;
        }
    }

    public void debug(String msg) {
        if (logger != null) {
            logger.debug(msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void debug(String format, Object arg) {
        if (logger != null) {
            logger.debug(format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void debug(String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.debug(format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void debug(String format, Object... arguments) {
        if (logger != null) {
            logger.debug(format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void debug(String msg, Throwable t) {
        if (logger != null) {
            logger.debug(msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }

    public boolean isDebugEnabled(Marker marker) {
        if (logger != null) {
            return logger.isDebugEnabled(marker);
        } else {
            //TODO
            return true;
        }
    }

    public void debug(Marker marker, String msg) {
        if (logger != null) {
            logger.debug(marker, msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void debug(Marker marker, String format, Object arg) {
        if (logger != null) {
            logger.debug(marker, format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.debug(marker, format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void debug(Marker marker, String format, Object... arguments) {
        if (logger != null) {
            logger.debug(marker, format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void debug(Marker marker, String msg, Throwable t) {
        if (logger != null) {
            logger.debug(marker, msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }

    public boolean isInfoEnabled() {
        if (logger != null) {
            return logger.isInfoEnabled();
        } else {
            //TODO
            return true;
        }
    }

    public void info(String msg) {
        if (logger != null) {
            logger.info(msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void info(String format, Object arg) {
        if (logger != null) {
            logger.info(format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void info(String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.info(format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void info(String format, Object... arguments) {
        if (logger != null) {
            logger.info(format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void info(String msg, Throwable t) {
        if (logger != null) {
            logger.info(msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }

    public boolean isInfoEnabled(Marker marker) {
        if (logger != null) {
            return logger.isInfoEnabled(marker);
        } else {
            //TODO
            return true;
        }
    }

    public void info(Marker marker, String msg) {
        if (logger != null) {
            logger.info(marker, msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void info(Marker marker, String format, Object arg) {
        if (logger != null) {
            logger.info(marker, format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.info(marker, format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void info(Marker marker, String format, Object... arguments) {
        if (logger != null) {
            logger.info(marker, format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void info(Marker marker, String msg, Throwable t) {
        if (logger != null) {
            logger.info(marker, msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }

    public boolean isWarnEnabled() {
        if (logger != null) {
            return logger.isWarnEnabled();
        } else {
            //TODO
            return true;
        }
    }

    public void warn(String msg) {
        if (logger != null) {
            logger.warn(msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void warn(String format, Object arg) {
        if (logger != null) {
            logger.warn(format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void warn(String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.warn(format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void warn(String format, Object... arguments) {
        if (logger != null) {
            logger.warn(format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void warn(String msg, Throwable t) {
        if (logger != null) {
            logger.warn(msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }

    public boolean isWarnEnabled(Marker marker) {
        if (logger != null) {
            return logger.isWarnEnabled(marker);
        } else {
            //TODO
            return true;
        }
    }

    public void warn(Marker marker, String msg) {
        if (logger != null) {
            logger.warn(marker, msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void warn(Marker marker, String format, Object arg) {
        if (logger != null) {
            logger.warn(marker, format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.warn(marker, format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void warn(Marker marker, String format, Object... arguments) {
        if (logger != null) {
            logger.warn(marker, format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void warn(Marker marker, String msg, Throwable t) {
        if (logger != null) {
            logger.warn(marker, msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }

    public boolean isErrorEnabled() {
        if (logger != null) {
            return logger.isErrorEnabled();
        } else {
            //TODO
            return true;
        }
    }

    public void error(String msg) {
        if (logger != null) {
            logger.error(msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void error(String format, Object arg) {
        if (logger != null) {
            logger.error(format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void error(String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.error(format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void error(String format, Object... arguments) {
        if (logger != null) {
            logger.error(format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void error(String msg, Throwable t) {
        if (logger != null) {
            logger.error(msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }

    public boolean isErrorEnabled(Marker marker) {
        if (logger != null) {
            return logger.isErrorEnabled(marker);
        } else {
            //TODO
            return true;
        }
    }

    public void error(Marker marker, String msg) {
        if (logger != null) {
            logger.error(marker, msg);
        } else {
            //TODO
            System.out.println(msg);
        }
    }

    public void error(Marker marker, String format, Object arg) {
        if (logger != null) {
            logger.error(marker, format, arg);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        if (logger != null) {
            logger.error(marker, format, arg1, arg2);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void error(Marker marker, String format, Object... arguments) {
        if (logger != null) {
            logger.error(marker, format, arguments);
        } else {
            //TODO
            System.out.println(format);
        }
    }

    public void error(Marker marker, String msg, Throwable t) {
        if (logger != null) {
            logger.error(marker, msg, t);
        } else {
            //TODO
            System.out.println(msg);
            t.printStackTrace();
        }
    }

}
