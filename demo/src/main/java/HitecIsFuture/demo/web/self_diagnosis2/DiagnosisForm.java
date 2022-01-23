package HitecIsFuture.demo.web.self_diagnosis2;

public class DiagnosisForm {
    private int day;
    private int month;
    private boolean check;

    public DiagnosisForm() {

    }
    public DiagnosisForm(int day, int month, boolean check) {
        this.day = day;
        this.month = month;
        this.check = check;
    }
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public boolean isCheck() {
        return check;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
