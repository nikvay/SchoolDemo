package com.nikvay.schooldemo.domain.module;

public class HolidayListModule {

    private String pdf_name;
    private String id;
    private String updated_date;
    private String title;
    private String status;

    public HolidayListModule(String pdf_name, String id, String updated_date, String title, String status) {
        this.pdf_name = pdf_name;
        this.id = id;
        this.updated_date = updated_date;
        this.title = title;
        this.status = status;
    }

    public String getPdf_name() {
        return pdf_name;
    }

    public void setPdf_name(String pdf_name) {
        this.pdf_name = pdf_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
