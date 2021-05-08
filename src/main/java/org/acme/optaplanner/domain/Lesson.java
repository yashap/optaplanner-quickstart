package org.acme.optaplanner.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

/**
 * During a lesson, represented by the Lesson class, a teacher teaches a subject to a group of students, for example,
 * Math by A.Turing for 9th grade or Chemistry by M.Curie for 10th grade. If a subject is taught multiple times per week
 * by the same teacher to the same student group, there are multiple Lesson instances that are only distinguishable by
 * id. For example, the 9th grade has six math lessons a week.
 *
 * During solving, OptaPlanner changes the timeslot and room fields of the Lesson class, to assign each lesson to a time
 * slot and a room. Because OptaPlanner changes these fields, Lesson is a planning entity.
 *
 * A lesson’s timeslot and room fields are unassigned (null) in the input data and assigned (not null) in the output
 * data. OptaPlanner changes these fields during solving. Such fields are called planning variables. In order for
 * OptaPlanner to recognize them, both the timeslot and room fields require an @PlanningVariable annotation. Their
 * containing class, Lesson, requires an @PlanningEntity annotation.
 */
@PlanningEntity
public class Lesson {
    public static final String TIMESLOT_RANGE = "timeslotRange";
    public static final String ROOM_RANGE = "roomRange";

    @PlanningId
    private Long id;

    private String subject;
    private String teacher;
    private String studentGroup;

    @PlanningVariable(valueRangeProviderRefs = TIMESLOT_RANGE)
    private Timeslot timeslot;

    @PlanningVariable(valueRangeProviderRefs = ROOM_RANGE)
    private Room room;

    public Lesson() {}

    public Lesson(Long id, String subject, String teacher, String studentGroup) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                ", studentGroup='" + studentGroup + '\'' +
                ", timeslot=" + timeslot +
                ", room=" + room +
                '}';
    }
}
