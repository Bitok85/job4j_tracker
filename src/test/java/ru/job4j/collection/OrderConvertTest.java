package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderConvertTest {

    @Test
    public void whenSingleOrder() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("123", "test1"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("123"), is(new Order("123", "test1")));
    }

    @Test
    public void whenDoubleOrederWithDublicate() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("123", "test1"));
        orders.add(new Order("123", "test2"));
        orders.add(new Order("1234", "test3"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.size(), is(2));
    }
}
