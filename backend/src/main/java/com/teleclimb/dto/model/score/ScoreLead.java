package com.teleclimb.dto.model.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreLead {
    private Integer value;
    private Boolean plus;
    private LocalTime time;

    public int compare(ScoreLead o2) {
        if (Objects.equals(this.getValue(), o2.getValue())) {
            if (this.getPlus() == o2.getPlus()) return 0;
            else if (this.getPlus()) return -1;
            else return 1;
        } else return o2.getValue() - this.getValue();
    }

    public int compareWithTime(ScoreLead o2) {
        if (compare(o2) != 0) return compare(o2);
        return o2.getTime().compareTo(this.getTime());
    }

    @Override
    public String toString() {
        return value.toString() + (plus ? "+" : "");
    }

    public static int compareNullSafe(ScoreLead s1, ScoreLead s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return 1;
        if (s2 == null) return -1;
        return s1.compare(s2);
    }

    public static int compareNullSafeWithTime(ScoreLead s1, ScoreLead s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return 1;
        if (s2 == null) return -1;
        return s1.compareWithTime(s2);
    }
}
