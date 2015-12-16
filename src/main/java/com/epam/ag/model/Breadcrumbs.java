package com.epam.ag.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class Breadcrumbs {

    public List<BreadcrumbsItem> items = new ArrayList<>();

    public List<BreadcrumbsItem> getItems(String className) {

        //
        // TODO Ужас!!!! Надо убрать эти case
        //
        switch (className) {
            case "ListManufacturerAction":
                items.add(new BreadcrumbsItem("/", "Index", false));
                items.add(new BreadcrumbsItem("manufacturer-create", "Add manufacturer", false));
                items.add(new BreadcrumbsItem(null, "Manufacturer`s list", true));
                break;

            case "VehicleCreateAction":
                items.add(new BreadcrumbsItem("/", "Index", false));
                items.add(new BreadcrumbsItem("vehicle-list", "List of vehicle`s", false));
                items.add(new BreadcrumbsItem(null, "Vehicle`s form", true));
                break;

            case "ShowVehicleListAction":
                items.add(new BreadcrumbsItem("/", "Index", false));
                items.add(new BreadcrumbsItem("vehicle-create", "Add vehicle", false));
                items.add(new BreadcrumbsItem(null, "Vehicle`s list", true));
                break;

            case "VehicleUpdateAction":
                items.add(new BreadcrumbsItem("/", "Index", false));
                items.add(new BreadcrumbsItem("vehicle-create", "Add vehicle", false));
                items.add(new BreadcrumbsItem("vehicle-list", "List of vehicle`s", false));
                items.add(new BreadcrumbsItem(null, "Vehicle`s update", true));
                break;

        }

        //

        return items;
    }
}
