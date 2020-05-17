package com.example.coronavirus.model;

public class FAQModel {

    private String title_FAQ;
    private String sub_Title_FAQ;

    private boolean expanded;

    public FAQModel() {

    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public FAQModel(String title_FAQ, String sub_Title_FAQ) {
        this.title_FAQ = title_FAQ;
        this.sub_Title_FAQ = sub_Title_FAQ;
    }

    public String getTitle_FAQ() {
        return title_FAQ;
    }

    public void setTitle_FAQ(String title_FAQ) {
        this.title_FAQ = title_FAQ;
    }

    public String getSub_Title_FAQ() {
        return sub_Title_FAQ;
    }

    public void setSub_Title_FAQ(String sub_Title_FAQ) {
        this.sub_Title_FAQ = sub_Title_FAQ;
    }
}
