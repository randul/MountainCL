package com.example.mountaincl.ui.faq;

public class Questions{
    private String qtitle;
    private String qcontent;

    public Questions(String FTitle, String FContent){

    this.qtitle = FTitle;

    this.qcontent = FContent;
    }

    public String getQtitle() {
        return qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }

    public String getQcontent() {
        return qcontent;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent;
    }


}
