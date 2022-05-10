package reporting_timeClock;
import company.Employee;
import timePackage.Date;
import timePackage.Time;

public class Reporting {
    private Date reportingDate;
    private Time reportingTime;
    private Employee employee;

    public Date getReportingDate() {
        return reportingDate;
    }

    public Reporting(Date reportingDate, Time reportingTime, Employee employee){
        this.reportingDate = reportingDate;
        this.reportingTime = reportingTime;
        this.employee = employee;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
    }

    public Time getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(Time reportingTime) {
        this.reportingTime = reportingTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setEmployeeTimeDateReporting(Date reportingDate, Time reportingTime, Employee employee){
        setEmployee(employee);
        setReportingDate(reportingDate);
        setReportingTime(reportingTime);
    }
}
