package model.equipment;

import domainapp.basics.model.meta.DClass;

@DClass(schema = "internetCafe")
public class Hardware extends Equipment {
    public Hardware(String equipmentId, String equipmentName, String provider, Integer cost) {
        super(equipmentId, equipmentName, provider, cost);
    }

    public Hardware(String equipmentName, String provider, Integer cost) {
        super(equipmentName, provider, cost);
    }
}
