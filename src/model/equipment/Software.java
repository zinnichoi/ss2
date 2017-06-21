package model.equipment;

import domainapp.basics.model.meta.DClass;


@DClass(schema = "internetCafe")
public class Software extends Equipment {
    public Software(String equipmentId, String equipmentName, String provider, Integer cost) {
        super(equipmentId, equipmentName, provider, cost);
    }

    public Software(String equipmentName, String provider, Integer cost) {
        super(equipmentName, provider, cost);
    }
}
