package com.nikvay.schooldemo.ui.module;

public class FeesModel
{
    String id,academic_year,total_fees,paid_fees,next_due_date,pending_fees;

    public FeesModel(String id, String academic_year, String total_fees, String paid_fees, String next_due_date, String pending_fees)
    {
        this.id = id;
        this.academic_year = academic_year;
        this.total_fees = total_fees;
        this.paid_fees = paid_fees;
        this.next_due_date = next_due_date;
        this.pending_fees = pending_fees;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public String getTotal_fees() {
        return total_fees;
    }

    public void setTotal_fees(String total_fees) {
        this.total_fees = total_fees;
    }

    public String getPaid_fees() {
        return paid_fees;
    }

    public void setPaid_fees(String paid_fees) {
        this.paid_fees = paid_fees;
    }

    public String getNext_due_date() {
        return next_due_date;
    }

    public void setNext_due_date(String next_due_date) {
        this.next_due_date = next_due_date;
    }

    public String getPending_fees() {
        return pending_fees;
    }

    public void setPending_fees(String pending_fees) {
        this.pending_fees = pending_fees;
    }
}
