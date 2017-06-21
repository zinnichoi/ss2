package model.bill;

import domainapp.basics.model.meta.DAssoc;
import domainapp.basics.model.meta.DAttr;
import domainapp.basics.model.meta.DClass;
import model.people.Customer;
import model.report.FinanceReport;

/**
 * Created by nguye on 16/04/2017.
 */
@DClass(schema = "internetCafe")
public class InternetTimeOrder extends Bill {
    @DAttr(name = "customer", type = DAttr.Type.Domain, length = 5, optional = false)
    @DAssoc(ascName = "customer-has-internetTimeOrders", role = "InternetTimeOrder",
            ascType = DAssoc.AssocType.One2Many, endType = DAssoc.AssocEndType.Many,
            associate = @DAssoc.Associate(type = Customer.class, cardMin = 1, cardMax = 1), dependsOn = true)
    private Customer customer;

    @DAttr(name = "money", type = DAttr.Type.Integer, mutable = true, optional = false, min = 0)
    private Integer money;

    public InternetTimeOrder(String billId, String date, FinanceReport financeReport, Customer customer, Integer money) {
        super(billId, date, financeReport);
        this.customer = customer;
        this.money = money;
        customer.setTotalInternetTime(convertMoneyToTime(money));
        financeReport.setTotalIncome(money);
        financeReport.setInterestRate(money);
    }

    public InternetTimeOrder(String date, FinanceReport financeReport, Customer customer, Integer money) {
        this(null,date,financeReport,customer,money);
    }

    // 1 hour = 3000 vnd
    //
    private Integer convertMoneyToTime(Integer money) {
        return money / 50;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        customer.setTotalInternetTime(convertMoneyToTime(money));
    }

}
