package org.acme.optaplanner.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

/**
 * A TimeTable wraps all Timeslot, Room, and Lesson instances of a single dataset. Furthermore, because it contains all
 * lessons, each with a specific planning variable state, it is a planning solution and it has a score:
 *     > If lessons are still unassigned, then it is an uninitialized solution, for example, a solution with the score
 *       -4init/0hard/0soft
 *     > If it breaks hard constraints, then it is an infeasible solution, for example, a solution with the score
 *       -2hard/-3soft
 *     > If it adheres to all hard constraints, then it is a feasible solution, for example, a solution with the score
 *       0hard/-7soft
 *
 * The @PlanningSolution annotation tells OptaPlanner that this class contains all of the input and output data.
 *
 * The TimeTable holds the input of the problem:
 *     > A timeslotList field with all time slots
 *         > This is a list of problem facts, because they do not change during solving
 *     > A roomList field with all rooms
 *         > This is a list of problem facts, because they do not change during solving
 *     > A lessonList field with all lessons
 *         > This is a list of planning entities, because they change during solving
 *         > Of each Lesson:
 *             > The values of the timeslot and room fields are typically still null, so unassigned. They are planning
 *               variables (do change during solving)
 *             > The other fields, such as subject, teacher and studentGroup, are filled in. These fields are problem
 *               properties (don't change during solving)
 *
 * The TimeTable also hold the output of the solution:
 *     > A lessonList field for which each Lesson instance has non-null timeslot and room fields after solving
 *     > A score field that represents the quality of the output solution, for example, 0hard/-5soft
 */
@PlanningSolution
public class TimeTable {

    // ==========> I LEFT OFF AT https://quarkus.io/guides/optaplanner#the-value-range-providers

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = Lesson.TIMESLOT_RANGE)
    private List<Timeslot> timeslotList;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = Lesson.ROOM_RANGE)
    private List<Room> roomList;

    @PlanningEntityCollectionProperty
    private List<Lesson> lessonList;

    @PlanningScore
    private HardSoftScore score;

    public TimeTable() {}

    public TimeTable(List<Timeslot> timeslotList, List<Room> roomList, List<Lesson> lessonList) {
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.lessonList = lessonList;
    }

    public List<Timeslot> getTimeslotList() {
        return timeslotList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public HardSoftScore getScore() {
        return score;
    }
}
