package by.bsuir.discussionservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public abstract class RestController {

    protected List<Order> getSortOrders(String[] sortParameters) {
        List<Order> sortOrders = new ArrayList<>();
        if (isMultipleSortsOrders(sortParameters)) {
            for (String sortParameter : sortParameters) {
                String[] sort = sortParameter.split(",");
                sortOrders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }
        }
        else {
            sortOrders.add(new Order(getSortDirection(sortParameters[1]), sortParameters[0]));
        }

        return sortOrders;
    }
    
    protected boolean isMultipleSortsOrders(String[] sortParameters) {
        return sortParameters[0].contains(",");
    }

    protected Sort.Direction getSortDirection(String directionString) {
        return directionString.contains("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

}
