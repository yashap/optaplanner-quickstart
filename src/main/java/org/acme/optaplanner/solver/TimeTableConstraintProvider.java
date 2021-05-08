package org.acme.optaplanner.solver;

import org.acme.optaplanner.domain.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class TimeTableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
            // Hard constraints
            roomConflict(constraintFactory),
            teacherConflict(constraintFactory),
            studentGroupConflict(constraintFactory)
            // Soft constraints are only implemented in optaplanner-quickstart
        };
    }

    // A room can accommodate at most one lesson at the same time
    private Constraint roomConflict(ConstraintFactory constraintFactory) {
        // Select a lesson ...
        return constraintFactory.from(Lesson.class)
            // ... and pair it with another lesson ...
            .join(Lesson.class,
                // ... in the same timeslot ...
                Joiners.equal(Lesson::getTimeslot),
                // ... in the same room ...
                Joiners.equal(Lesson::getRoom)
            )
            // ... then penalize each pair with a hard weight
            .penalize("Room conflict", HardSoftScore.ONE_HARD);
    }

    // A teacher can teach at most one lesson at the same time
    private Constraint teacherConflict(ConstraintFactory constraintFactory) {
        // Select a pair of lessons with the same timeslot and teacher
        return constraintFactory
            .fromUniquePair(Lesson.class,
                Joiners.equal(Lesson::getTimeslot),
                Joiners.equal(Lesson::getTeacher)
            )
            // ... then penalize each pair with a hard weight
            .penalize("Teacher conflict", HardSoftScore.ONE_HARD);
    }

    // A student can attend at most one lesson at the same time
    private Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
            .fromUniquePair(Lesson.class,
                Joiners.equal(Lesson::getTimeslot),
                Joiners.equal(Lesson::getStudentGroup)
            )
            // ... then penalize each pair with a hard weight
            .penalize("Student group conflict", HardSoftScore.ONE_HARD);
    }
}
