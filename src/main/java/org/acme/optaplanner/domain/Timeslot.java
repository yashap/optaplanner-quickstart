package org.acme.optaplanner.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * The Timeslot class represents a time interval when lessons are taught, for example, Monday 10:30 - 11:30 or Tuesday
 * 13:30 - 14:30. For simplicity’s sake, all time slots have the same duration and there are no time slots during lunch
 * or other breaks.
 *
 * A time slot has no date, because a high school schedule just repeats every week.
 *
 * Because no Timeslot instances change during solving, a Timeslot is called a problem fact. Problem facts require no
 * OptaPlanner specific annotations.
 */
public class Timeslot {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public Timeslot() {}

    public Timeslot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Timeslot{" +
                "dayOfWeek=" + dayOfWeek +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
