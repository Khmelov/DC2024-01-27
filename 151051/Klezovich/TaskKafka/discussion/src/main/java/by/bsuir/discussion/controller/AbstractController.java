package by.bsuir.discussion.controller;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController {

    protected boolean isMultipleSortOrders(String[] sortParameters) {
        return sortParameters[0].contains(",");
    }

    protected List<Sort.Order> getSortOrderList(String[] paramsList) {
        List<Sort.Order> sortOrderList = new ArrayList<>();
        if (isMultipleSortOrders(paramsList)) {
            for (String param : paramsList) {
                String[] sortList = param.split(",");
                sortOrderList.add(new Sort.Order(getSortDirection(sortList[1]), sortList[0]));
            }
        }
        else {
            sortOrderList.add(new Sort.Order(getSortDirection(paramsList[1]), paramsList[0]));
        }

        return sortOrderList;
    }

    protected Sort.Direction getSortDirection(String sortDirection) {
        Sort.Direction defaultDirection = Sort.Direction.ASC;
        return sortDirection.contains("asc") ? Sort.Direction.ASC :
                sortDirection.contains("desc") ? Sort.Direction.DESC : defaultDirection;
    }
}
