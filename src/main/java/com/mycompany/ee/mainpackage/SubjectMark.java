package com.mycompany.ee.mainpackage;

import com.mycompany.ee.exceptions.InvalidMarkException;

public class SubjectMark {
    private Enum subject;
    private int mark;

    public SubjectMark(Enum subject, int mark) {
        setSubject(subject);
        setMark(mark);
    }

    public Enum getSubject() {
        return subject;
    }

    public void setSubject(Enum subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) throws InvalidMarkException {
        if (mark < 0 || mark > 10) {
            throw new InvalidMarkException("Incorrect mark value: " + mark);
        }
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "\nSubject{" +
                "subject=" + subject +
                ", mark=" + mark +
                '}';
    }
}