package com.nikvay.schooldemo.domain.module;

public class ExamListModel
{
    String exam_id,name;

    public ExamListModel(String exam_id, String name)
    {
        this.exam_id = exam_id;
        this.name = name;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
