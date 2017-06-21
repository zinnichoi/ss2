package model.bill;

import domainapp.basics.exceptions.ConstraintViolationException;
import domainapp.basics.model.meta.DAssoc;
import domainapp.basics.model.meta.DAttr;
import domainapp.basics.model.meta.DClass;
import domainapp.basics.model.meta.DOpt;
import domainapp.basics.util.Tuple;
import model.equipment.Equipment;
import model.product.Product;
import model.report.FinanceReport;


@DClass(schema = "internetCafe")
public class EquipmentBill extends Bill {

    @DAttr(name = "equipment", type = DAttr.Type.Domain, length = 5, optional = false)
    @DAssoc(ascName = "equipment-has-equipmentBills", role = "equipmentBill",
            ascType = DAssoc.AssocType.One2Many, endType = DAssoc.AssocEndType.Many,
            associate = @DAssoc.Associate(type = Equipment.class, cardMin = 1, cardMax = 1), dependsOn = true)
    private Equipment equipment;

    public EquipmentBill(String billId, String date, FinanceReport financeReport, Equipment equipment) {
        super( billId,date, financeReport);
        this.equipment = equipment;
        financeReport.setTotalCost(equipment.getCost());
        financeReport.setInterestRate((- equipment.getCost()));
    }

    public EquipmentBill(String date, FinanceReport financeReport, Equipment equipment) {
        this(null,date,financeReport,equipment);
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
