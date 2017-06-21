package model.people;

import domainapp.basics.exceptions.ConstraintViolationException;
import domainapp.basics.model.meta.DAssoc;
import domainapp.basics.model.meta.DAttr;
import domainapp.basics.model.meta.DClass;
import domainapp.basics.model.meta.DOpt;
import domainapp.basics.util.Tuple;
import model.report.FinanceReport;

@DClass(schema = "internetCafe")
public class Staff {
    @DAttr(name="staffId", id = true, auto = true, type= DAttr.Type.String, length = 10, mutable = false, optional = false)
    private String staffId;
    private static int idCounter = 0;

    @DAttr(name="name", type = DAttr.Type.String, length = 30, mutable = true, optional = false)
    private String name;

    @DAttr(name="staffDob", type = DAttr.Type.String, length = 10, mutable = true, optional = false)
    private String staffDob;

    @DAttr(name = "staffAddress", type = DAttr.Type.String, length = 20, mutable = true, optional = false)
    private String staffAddress;

    @DAttr(name="salary", type = DAttr.Type.Integer, length = 11, mutable = true, optional = false)
    private Integer salary;

    @DAttr(name="startDate", type = DAttr.Type.String,length = 10, mutable = true, optional = false)
    private String startDate;

    @DAttr(name = "financeReport", type = DAttr.Type.Domain, length = 5, optional = false)
    @DAssoc(ascName = "FinanceReport-has-salary", role = "staff",
            ascType = DAssoc.AssocType.One2Many, endType = DAssoc.AssocEndType.Many,
            associate = @DAssoc.Associate(type = Customer.class, cardMin = 1, cardMax = 1), dependsOn = true)
    private FinanceReport financeReport;

    public Staff(String staffId, String name, String staffDob, String staffAddress, Integer salary, String startDate, FinanceReport financeReport) {
        this.staffId = nextID(staffId);
        this.name = name;
        this.staffDob = staffDob;
        this.staffAddress = staffAddress;
        this.salary = salary;
        this.startDate = startDate;
        this.financeReport = financeReport;
        financeReport.setTotalCost(salary);
        financeReport.setInterestRate((-salary));
    }

    public Staff(String name, String staffDob, String staffAddress, Integer salary, String startDate, FinanceReport financeReport) {
        this(null,name,staffDob,staffAddress,salary,startDate,financeReport);
    }

    private String nextID(String currId){
        if (currId == null){
            idCounter++;
            return "S"+ idCounter;
        }else {
            int num = Integer.parseInt(currId.substring(1));
            if (num > idCounter){
                idCounter = num;
            }
            return currId;
        }
    }

    @DOpt(type=DOpt.Type.AutoAttributeValueSynchroniser)
    public static void updateAutoGeneratedValue(
            DAttr attrib,
            Tuple derivingValue,
            Object minVal,
            Object maxVal) throws ConstraintViolationException {

        if (minVal != null && maxVal != null) {
            //TODO: update this for the correct attribute if there are more than one auto attributes of this class

            String maxId = (String) maxVal;
            try {
                int maxIdNum = Integer.parseInt(maxId.substring(1));

                if (maxIdNum > idCounter) // extra check
                    idCounter = maxIdNum;

            } catch (RuntimeException e) {
                throw new ConstraintViolationException(
                        ConstraintViolationException.Code.INVALID_VALUE, e, new Object[] {maxId});
            }
        }
    }

    public String getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffDob() {
        return staffDob;
    }

    public void setStaffDob(String staffDob) {
        this.staffDob = staffDob;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public FinanceReport getFinanceReport() {
        return financeReport;
    }

    public void setFinanceReport(FinanceReport financeReport) {
        this.financeReport = financeReport;
    }
}
