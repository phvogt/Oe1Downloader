// (c) 2013 by Philipp Vogt
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

/**
 * Traces method calls.
 */
public aspect AutoTrace pertypewithin(*) {

  /** the TraceLogger to use */
  private Logger traceLogger;

  /** contains the method entry times. */
  private final Map<String , List<Long>> _methodEntryTimes = Collections.synchronizedMap(new HashMap<String , List<Long>>());

  /**
   * Insert the TraceLogger in the static part of the class.
   */
  after() : staticinitialization(*) && !within(AutoTrace)  {
    traceLogger = Logger.getLogger(getWithinTypeName());
  }

  /**
   * Define a trace pointcut for all methods.
   */
  pointcut traceMethods() :
        (execution(* *.*(..))  || execution(*.new(..))) && !within(AutoTrace) && !execution(* *.toString(..));

  /**
   * Logs method entry.
   */
  before() : traceMethods() {
    // should only be null in enums
    if (traceLogger == null) {
      traceLogger = Logger.getLogger(getWithinTypeName());
    }

    if (traceLogger.isDebugEnabled()) {

      final Signature sig = thisJoinPointStaticPart.getSignature();
      addTime(sig);

      final StringBuilder logInfo = new StringBuilder();
      addMethodName(logInfo, sig);
      logInfo.append("(");
      addArguments(logInfo, thisJoinPoint);
      logInfo.append("): entering");

      traceLogger.debug(logInfo.toString());
    }
  }

  /**
   * Logs the normal exit of a method.
   * @param o the result
   */
  after() returning (final Object o) : traceMethods() {

    if (traceLogger != null && traceLogger.isDebugEnabled()) {

      final Signature sig = thisJoinPointStaticPart.getSignature();

      final StringBuilder logInfo = new StringBuilder();
      addMethodName(logInfo, sig);
      logInfo.append("(..)  exiting (");
      addTime(logInfo, sig);
      logInfo.append("), returning " + o);

      traceLogger.debug(logInfo.toString());
    }
  }

  /**
   * Logs the exit of a method by throwing an error.
   * @param e the error that is thrown.
   */
  after() throwing (final Throwable e) : traceMethods() {

    if (traceLogger != null && traceLogger.isDebugEnabled()) {

      final Signature sig = thisJoinPointStaticPart.getSignature();

      final StringBuilder logInfo = new StringBuilder();
      addMethodName(logInfo, sig);
      logInfo.append("(..)  exiting (");
      addTime(logInfo, sig);
      logInfo.append("} with error: " + e);

      traceLogger.debug(logInfo.toString(), e);
    }
  }

  /**
   * Makes a key.
   * @param currentThread thread
   * @param sig the signature
   * @return a key
   */
  private String makeKey(final Thread currentThread, final Signature sig) {

    final String result = currentThread.getId() + "/" + sig.toLongString();
    return result;
  }

  /**
   * Adds the time.
   * @param sig the method signature
   */
  private void addTime(final Signature sig) {

    final long time = System.currentTimeMillis();

    final String key = makeKey(Thread.currentThread(), sig);
    List<Long> times = _methodEntryTimes.get(key);
    if (times == null) {
      times = new ArrayList<Long>();
      _methodEntryTimes.put(key, times);
    }
    times.add(time);

  }

  /**
   * Gets the last time.
   * @param sig the method signature
   * @return the last time
   */
  private Long getLastTime(final Signature sig) {

    Long result = null;

    final String key = makeKey(Thread.currentThread(), sig);
    List<Long> times = _methodEntryTimes.get(key);

    if ((times != null) && (times.size() > 0)) {
      result = times.remove(times.size() - 1);

      // remove key if no longer necessary
      if (times.size() == 0) {
        _methodEntryTimes.remove(key);
      }
    }

    return result;
  }

  /**
   * Adds the method name to the StringBuilder.
   * @param sb the StringBuilder
   * @param sig the signature
   */
  private void addMethodName(final StringBuilder sb, final Signature sig) {
    sb.append(sig.getName());
  }

  /**
   * Adds method arguments to StringBuilder.
   * @param sb the StringBuilder to add the arguments to
   * @param joinPoint the join point with the arguments
   */
  private void addArguments(final StringBuilder sb, final JoinPoint joinPoint) {

    final Object[] args = joinPoint.getArgs();
    boolean first = true;
    for (Object arg : args) {
      if (!first) {
        sb.append(",");
      }
      else {
        first = false;
      }

      if ((arg != null) && Object[].class.isAssignableFrom(arg.getClass())) {
        sb.append("{").append((arg == null ? "null" : Arrays.toString((Object[]) arg))).append("}");
      }
      else {
        sb.append("[").append(arg).append("]");
      }
    }
  }

  /**
   * Adds the time the method run to the StringBuilder.
   * @param sb the StringBuilder
   * @param sig signature of the method
   */
  private void addTime(final StringBuilder sb, final Signature sig) {

    final Long oldTime = getLastTime(sig);
    if (oldTime != null) {
      final long time = System.currentTimeMillis();
      sb.append(time - oldTime);
      sb.append(" ms");
    }
    else {
      sb.append("unknown runtime!");
    }

  }

}
