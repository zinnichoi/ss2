package model.bill;

import domainapp.basics.model.meta.DAssoc;
import domainapp.basics.model.meta.DAttr;
import domainapp.basics.model.meta.DClass;
import model.people.Customer;
import model.product.Product;
import model.report.FinanceReport;


@DClass(schema = "internetCafe")
public class ProductOrder extends Bill {
    @DAttr(name = "customer", type = DAttr.Type.Domain, length = 5, optional = false)
    @DAssoc(ascName = "customer-has-productOrders", role = "productOrder",
            ascType = DAssoc.AssocType.One2Many, endType = DAssoc.AssocEndType.Many,
            associate = @DAssoc.Associate(type = Customer.class, cardMin = 1, cardMax = 1), dependsOn = true)
    private Customer customer;


    @DAttr(name = "product", type = DAttr.Type.Domain, length = 5, optional = false)
    @DAssoc(ascName = "product-has-productOrders", role = "productOrder",
            ascType = DAssoc.AssocType.One2Many, endType = DAssoc.AssocEndType.Many,
            associate = @DAssoc.Associate(type = Product.class, cardMin = 1, cardMax = 1), dependsOn = true)
    private Product product;

    @DAttr(name = "quantity", type = DAttr.Type.Integer, length = 10, mutable = true, optional = false)
    private Integer quantity;

    public ProductOrder(String billId, String date, FinanceReport financeReport, Customer customer, Product product, Integer quantity) {
        super(billId, date, financeReport);
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        financeReport.setTotalIncome(quantity*product.getProductPrice());
        financeReport.setTotalCost(quantity*product.getProductCost());
        //
        financeReport.setInterestRate(quantity*product.getProductPrice());
        financeReport.setInterestRate(quantity*(-product.getProductCost()));
    }

    public ProductOrder(String date, FinanceReport financeReport, Customer customer, Product product,Integer quantity) {
        this(null,date,financeReport,customer,product,quantity);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
