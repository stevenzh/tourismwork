package com.opentravelsoft.common;

public class TravelConstants {

  /**
   * 文件类型
   */
  public enum ExportType {
    PDF(0), HTML(1), IMG(2), DOC(3), TEXT(4), EXCEL(5), XML(6);

    private final int code;

    ExportType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public static ExportType findByCode(int code) {
      for (ExportType exportType : ExportType.values()) {
        if (exportType.getCode() == code)
          return exportType;
      }

      return null;
    }
  }

  public enum ScheduleType {
    ONCE(0), DAILY(1), WEEKLY(2), MONTHLY(3), WEEKDAYS(4), HOURLY(5), CRON(6);

    private final int code;

    ScheduleType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public static ScheduleType findByCode(int code) {
      for (ScheduleType scheduleType : ScheduleType.values()) {
        if (scheduleType.getCode() == code)
          return scheduleType;
      }

      return null;
    }
  }

  public enum ScheduleAmPm {
    AM(0), PM(1);

    private final int code;

    ScheduleAmPm(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public static ScheduleAmPm findByCode(int code) {
      for (ScheduleAmPm scheduleAmPm : ScheduleAmPm.values()) {
        if (scheduleAmPm.getCode() == code)
          return scheduleAmPm;
      }

      return null;
    }
  }

  public enum Status {
    SUCCESS, FAILURE, INVALID
  };
}
